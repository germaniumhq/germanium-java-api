package com.germaniumhq.germanium.impl;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.wa.FirefoxWithoutMarionetteAlertWorkaround;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriverException;

import java.util.function.Supplier;

import static com.germaniumhq.germanium.all.GermaniumApi.getGermanium;

/**
 * Code to allow alerts.
 */
public class AlertChecker {
    private final static Logger log = Logger.getLogger(AlertChecker.class);

    private GermaniumDriver germaniumDriver;

    public AlertChecker(GermaniumDriver germaniumDriver) {
        this.germaniumDriver = germaniumDriver;
    }

    public Alert getAlert() {
        return new FirefoxWithoutMarionetteAlertWorkaround(getGermanium(), () -> {
            try {
                Alert alert = germaniumDriver.switchTo().alert();
                alert.getText();

                germaniumDriver.setLastAlert(alert);

                return alert;
            } catch (NoAlertPresentException e) {
                return germaniumDriver.getLastAlert();
            }
        }).execute();
    }

    /**
     * Execute some code, expecting that an alert might popup
     * while executing the code.
     *
     * @param germaniumDriver
     * @param code
     * @param <T>
     * @return
     */
    public static <T> T allowAlert(GermaniumDriver germaniumDriver, Supplier<T> code) {
        try {
            return code.get();
        } catch (UnhandledAlertException unhandledAlertException) {
            // nothing on purpose
        } catch (WebDriverException e) {
            if (!e.getMessage().contains("unexpected alert open") &&
                !e.getMessage().contains("COM method IWebBrowser2::Navigate2()")) {
                throw e;
            }

            log.warn(String.format(
                    "An unexpected alert exception was caught by Germanium " +
                    "while loading the page: %s", e));
        }

        germaniumDriver.switchTo().alert();
        return null;
    }

    /**
     * Execute some code expecting some alert that might happen while executing the code.
     * @param germaniumDriver
     * @param code
     */
    public static void allowAlert(GermaniumDriver germaniumDriver, Runnable code) {
        try {
            code.run();
            return;
        } catch (UnhandledAlertException unhandledAlertException) {
            // nothing on purpose
        } catch (WebDriverException e) {
            if (!e.getMessage().contains("unexpected alert open") &&
                    !e.getMessage().contains("COM method IWebBrowser2::Navigate2()")) {
                throw e;
            }

            log.warn(String.format(
                    "An unexpected alert exception was caught by Germanium " +
                    "while loading the page: %s", e));
        }

        germaniumDriver.switchTo().alert();
    }
}
