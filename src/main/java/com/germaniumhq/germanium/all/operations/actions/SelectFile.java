package com.germaniumhq.germanium.all.operations.actions;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.impl.FilterNotDisplayed;
import com.germaniumhq.germanium.locators.Locator;
import org.openqa.selenium.WebElement;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SelectFile {
    public static enum CheckPath {
        CHECK,
        IGNORE_MISSING
    }

    public static void selectFile(Object selector, String filePath, CheckPath checkPath) {
        java.nio.file.Path absolutePath = Paths.get(filePath).toAbsolutePath();

        if (checkPath == CheckPath.CHECK && Files.notExists(absolutePath)) {
            throw new IllegalStateException(String.format(
                    "File '%s' does not exist. You need to pass the path to the " +
                    "file that you want to select in the file input.",
                    absolutePath.toString()
            ));
        }

        Locator<WebElement> locator = GermaniumApi.getGermanium().<WebElement>S(selector);
        WebElement element = FilterNotDisplayed.filterOneForAction(
                locator.elementList(Locator.Visibility.ALL_ELEMENTS));

        element.sendKeys(absolutePath.toString());
    }
}
