package io.mylabs.automation.util;

import io.mylabs.automation.factory.DriverFactory;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageLoadHelper {
    public static PageLoadHelper isLoaded() {
        PageLoadHelper loadHelper = new PageLoadHelper();
        return loadHelper;
    }

    public PageLoadHelper isElementClickable(WebElement element) {
        try {
            new WebDriverWait(DriverFactory.getDriver(), 2).until(ExpectedConditions.elementToBeClickable(element));
            return this;
        } catch (WebDriverException e) {
            throw new Error("Element is not clickable");
        }
    }

    public PageLoadHelper isElementVisible(WebElement element) {
        try {
            new WebDriverWait(DriverFactory.getDriver(), 2).until(ExpectedConditions.visibilityOf(element));
            return this;
        } catch (WebDriverException e) {
            throw new Error("Element is not visible");
        }
    }

    public void hardWait() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
