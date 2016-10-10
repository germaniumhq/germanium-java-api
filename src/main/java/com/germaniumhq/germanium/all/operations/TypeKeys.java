package com.germaniumhq.germanium.all.operations;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.all.operations.actions.DelayAction;
import com.germaniumhq.germanium.locators.Locator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.interactions.CompositeAction;
import org.openqa.selenium.interactions.KeyDownAction;
import org.openqa.selenium.interactions.KeyUpAction;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.internal.Locatable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.germaniumhq.germanium.all.GermaniumSelectors.Alert;
import static com.germaniumhq.germanium.impl.FilterNotDisplayed.filterOneForAction;


interface KeysAction {
}

class BasicKeysAction implements KeysAction {
    private StringBuilder keys = new StringBuilder("");

    public BasicKeysAction(List<String> keys) {
        keys.forEach(this.keys::append);
    }


    public BasicKeysAction(String keys) {
        this.keys.append(keys);
    }

    public String getKeysString() {
        return keys.toString();
    }

    public void addKeys(String keys) {
        this.keys.append(keys);
    }
}

class ComboKeyUp implements KeysAction {
    private String key;

    public ComboKeyUp(String key) {
        this.key = key;
    }

    public Keys getKeyValue() {
        return Keys.getKeyFromUnicode(key.charAt(0));
    }
}

class ComboKeyDown implements KeysAction {
    private String key;

    public ComboKeyDown(String key) {
        this.key = key;
    }

    public Keys getKeyValue() {
        return Keys.getKeyFromUnicode(key.charAt(0));
    }
}

public class TypeKeys {
    private static Pattern MULTIPLE_TIMES_KEY_PRESS_RE = Pattern.compile("^(.*?)\\*(\\d+)$");

    public static void typeKeys(String keys, Object selector, float delay) {
        GermaniumDriver germanium = GermaniumApi.getGermanium();
        List<KeysAction> keysArray = transformToKeys(keys); // FIXME:
        Object element = null;

        if (selector != null) {
            List<Object> potentialElements = germanium.S(selector).elementList(Locator.Visibility.ALL_ELEMENTS);
            if (potentialElements.size() == 1 && potentialElements.get(0) instanceof Alert) {
                element = potentialElements.get(0);
            } else {
                element = filterOneForAction((List) potentialElements);
            }
        } else if (Alert().exists()) {
            element = Alert().element();
        }

        if (element instanceof Alert) {
            ((Alert)element).sendKeys(keys);
            return;
        }

        CompositeAction actionChain = new CompositeAction();

        /*
         # In case we have delays, we will pass insert after each character a small delay.
         # This is factored in different functions, since otherwise there would be way
         # to much ifing to add the delays.
         */
        if (delay > 0) {
            addDelayedKeys(actionChain, keysArray, element, delay);
        } else {
            addImmediateKeys(actionChain, keysArray, element);
        }

        actionChain.perform();
    }

    private static void addDelayedKeys(CompositeAction actionChain, List<KeysAction> keysArray, Object element, float delay) {
        if (element != null && element instanceof Locatable) {
            addClickAction(actionChain, (Locatable) element);
        }

        for (int i = 0; i < keysArray.size(); i++) {
            if (i != 0) {
                addDelayAction(actionChain, delay);
            }

            addSingleKeysAction(actionChain, keysArray.get(i));
        }
    }

    private static void addImmediateKeys(CompositeAction actionChain, List<KeysAction> keysArray, Object element) {
        if (element != null && element instanceof Locatable) {
            addClickAction(actionChain, (Locatable) element);
        }

        for (KeysAction keyAction : keysArray) {
            addSingleKeysAction(actionChain, keyAction);
        }
    }

    private static void addClickAction(CompositeAction actionChain, Locatable element) {
        actionChain.addAction(new ClickAction(
                GermaniumApi.getGermanium().getMouse(),
                element));
    }

    private static void addDelayAction(CompositeAction actionChain, float delay) {
        actionChain.addAction(new DelayAction(delay));
    }

    private static void addSingleKeysAction(CompositeAction actionChain, KeysAction keyAction) {
        Keyboard keyboard = GermaniumApi.getGermanium().getKeyboard();
        Mouse mouse = GermaniumApi.getGermanium().getMouse();

        if (keyAction instanceof BasicKeysAction) {
            actionChain.addAction(new SendKeysAction(
                    keyboard,
                    mouse,
                    ((BasicKeysAction) keyAction).getKeysString()));
        } else if (keyAction instanceof ComboKeyDown) {
            actionChain.addAction(new KeyDownAction(
                    keyboard,
                    mouse,
                    ((ComboKeyDown) keyAction).getKeyValue()));
        } else if (keyAction instanceof ComboKeyUp) {
            actionChain.addAction(new KeyUpAction(
                    keyboard,
                    mouse,
                    ((ComboKeyUp) keyAction).getKeyValue()));
        }
    }

    /**
     * Transforms the given keys string into a list of keys actions
     * that can be fed into an ActionChain
     */
    private static List<KeysAction> transformToKeys(String keys) {
        Pattern comboRe = Pattern.compile("<(.*?)>");
        Matcher comboKeyScanner = comboRe.matcher(keys);

        int initialKeyIndex = 0;
        List<KeysAction> transformedKeys = new ArrayList<>();
        KeysAction lastAction = null;

        while (true) {
            if (!comboKeyScanner.find()) {
                break;
            }

            if (comboKeyScanner.start() - initialKeyIndex > 0) {
                if (lastAction instanceof BasicKeysAction) {
                    BasicKeysAction basicLastAction = (BasicKeysAction) lastAction;
                    basicLastAction.addKeys(keys.substring(initialKeyIndex, comboKeyScanner.start()));
                } else {
                    BasicKeysAction action = new BasicKeysAction(keys.substring(initialKeyIndex, comboKeyScanner.start()));
                    lastAction = action;
                    transformedKeys.add(action);
                }
            }


            String pressedKeys = comboKeyScanner.group(1);

            if (isUpDownToggle(pressedKeys)) {
                KeysAction action = createUpDownToggle(pressedKeys);
                transformedKeys.add(action);
                lastAction = action;
            } else if (isMultikeyCombo(pressedKeys)) {
                List<KeysAction> multiComboActions = createMulticombo(pressedKeys);
                transformedKeys.addAll(multiComboActions);
                lastAction = multiComboActions.get(multiComboActions.size() - 1);
            } else {
                if (lastAction instanceof BasicKeysAction) {
                    BasicKeysAction basicKeysAction = (BasicKeysAction) lastAction;
                    String key = createCustomKey(pressedKeys);
                    int keypressCount = getKeypressCount(pressedKeys);

                    for (int i = 0; i < keypressCount; i++) {
                        basicKeysAction.addKeys(key);
                    }
                } else {
                    List<String> multipleKeys = new ArrayList<>();
                    String key = createCustomKey(pressedKeys);

                    int keypressCount = getKeypressCount(pressedKeys);

                    for (int i = 0; i < keypressCount; i++) {
                        multipleKeys.add(key);
                    }

                    BasicKeysAction action = new BasicKeysAction(multipleKeys);
                    transformedKeys.add(action);
                }
            }

            initialKeyIndex = comboKeyScanner.end();
        }

        if (keys.length() - initialKeyIndex > 0) {
            BasicKeysAction action = new BasicKeysAction(keys.substring(initialKeyIndex));
            transformedKeys.add(action);
        }

        return transformedKeys;
    }

    /**
     * Create a toggling of a button.
     * @param pressedKeys
     * @return
     */
    private static KeysAction createUpDownToggle(String pressedKeys) {
        if (pressedKeys.startsWith("^")) { // release key
            String key = createCustomKey(pressedKeys.substring(1));

            if (getKeypressCount(pressedKeys.substring(1)) != 1) {
                throw new IllegalArgumentException("The key can be released only once: " + pressedKeys);
            }

            return new ComboKeyUp(key);
        } else if (pressedKeys.startsWith("!")) { // press key
            String key = createCustomKey(pressedKeys.substring(1));

            if (getKeypressCount(pressedKeys.substring(1)) != 1) {
                throw new IllegalArgumentException("The key can be released only once: " + pressedKeys);
            }

            return new ComboKeyDown(key);
        } else {
            throw new IllegalArgumentException(String.format(
                    "Unable to create key toggle for: '%s'",
                    pressedKeys
            ));
        }
    }

    /**
     * Create a combo made of multiple keys (e.g. ctrl-shift-s)
     * @param pressedKeys
     * @return
     */
    private static List<KeysAction> createMulticombo(String pressedKeys) {
        List<KeysAction> result = new ArrayList<>();
        String comboKey = null;

        String[] tokens = pressedKeys.split("-");
        int keypressCount = 1;

        for (int i = tokens.length - 1; i >= 0; i--) {
            if (i < tokens.length - 1) {
                String customKey = createCustomKey(tokens[i]);
                comboKey = customKey + comboKey + customKey;
            } else  {
                comboKey = createKey(tokens[i]);
                keypressCount = getKeypressCount(tokens[i]);
            }
        }

        for (int i = 0; i < keypressCount; i++) {
            result.add(new BasicKeysAction(comboKey));
        }

        return result;
    }

    /**
     * Create a single key for webdriver that represents a regular
     * or a custom key, that is the last part of the macro.
     * @param comboString
     * @return
     */
    private static String createKey(String comboString) {
        // if it's a single character return it
        if (comboString.length() <= 1) {
            return comboString;
        }

        Matcher m = MULTIPLE_TIMES_KEY_PRESS_RE.matcher(comboString);

        if (m.matches()) {
            comboString = m.group(1);
        }

        String keyString = comboString.toUpperCase();
        String key = createAbbreviatedKey(keyString);

        if (key != null) {
            return key;
        }

        return "" + Keys.valueOf(keyString);
    }

    /**
     * Create a single key for webdriver that represents a custom
     * key (&lt;CR&gt;, &lt;SHIFT&gt;, &lt;UP&gt; etc)
     * @param comboString
     * @return
     */
    private static String createCustomKey(String comboString) {
        Matcher matcher = MULTIPLE_TIMES_KEY_PRESS_RE.matcher(comboString);

        if (matcher.matches()) {
            comboString = matcher.group(1);
        }

        String keyString = comboString.toUpperCase();

        String key = createAbbreviatedKey(keyString);
        if (key != null) {
            return key;
        }

        if ("C".equals(keyString)) {
            return "" + Keys.CONTROL;
        } else if ("S".equals(keyString)) {
            return "" + Keys.SHIFT;
        } else if ("M".equals(keyString)) {
            return "" + Keys.META;
        }

        return "" + Keys.valueOf(keyString);
    }

    /**
     * Add more abbreviations for keys, beside the enum values that are
     * in the Keys. For example `CR` for `ENTER`, etc.
     * @param keyString
     * @return
     */
    private static String createAbbreviatedKey(String keyString) {
        if ("CR".equals(keyString)) {
            return "" + Keys.ENTER;
        } else if ("CTRL".equals(keyString) || "CTL".equals(keyString)) {
            return "" + Keys.CONTROL;
        } else if ("DEL".equals(keyString)) {
            return "" + Keys.DELETE;
        } else if ("CMD".equals(keyString)) {
            return "" + Keys.COMMAND;
        } else if ("BS".equals(keyString)) {
            return "" + Keys.BACK_SPACE;
        } else if ("INS".equals(keyString)) {
            return "" + Keys.INSERT;
        } else if ("PGUP".equals(keyString)) {
            return "" + Keys.PAGE_UP;
        } else if ("PGDN".equals(keyString)) {
            return "" + Keys.PAGE_DOWN;
        }

        return null;
    }

    /**
     * Finds the number of times a key is pressed:
     * `c` - once
     * `c*3` - three times
     * @param comboString
     * @return
     */
    private static int getKeypressCount(String comboString) {
        Matcher m = MULTIPLE_TIMES_KEY_PRESS_RE.matcher(comboString);

        if (m.matches()) {
            int result = Integer.parseInt(m.group(2));

            if (result <= 0) {
                throw new IllegalArgumentException("The number of key presses should be more than 0.");
            }

            return result;
        }

        return 1;
    }

    /**
     * Checks if the given keypress signals a keypress, or a key release.
     * @param pressedKeys
     * @return
     */
    private static boolean isUpDownToggle(String pressedKeys) {
        return pressedKeys.startsWith("^") ||
                pressedKeys.startsWith("!");
    }

    private static boolean isMultikeyCombo(String pressedKeys) {
        return pressedKeys.contains("-");
    }

}