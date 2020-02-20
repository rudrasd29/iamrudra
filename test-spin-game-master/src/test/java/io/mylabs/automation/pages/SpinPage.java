package io.mylabs.automation.pages;

import io.mylabs.automation.factory.DriverFactory;
import io.mylabs.automation.util.FileReaderManager;
import io.mylabs.automation.util.PageLoadHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.ArrayList;
import java.util.List;

public class SpinPage extends LoadableComponent<SpinPage> {

    private WebDriver driver;
    private String APP_URL = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();

    @FindBy(id = "status")
    private WebElement status;

    @FindBy(xpath = "//div[@id='result']/div")
    private List<WebElement> symbols;

    @FindBy(id = "start")
    private WebElement start;

    public SpinPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public SpinPage startSpin() {
        start.click();
        PageLoadHelper.isLoaded().hardWait();
        return this;
    }

    public List<String> getSymbolInfo() {
        List<String> symbolInfo = new ArrayList();
        symbols.forEach(reel -> symbolInfo.add(reel.getAttribute("style")));
        return symbolInfo;
    }

    public String getStatusMessage() {
        return status.getText();
    }

    @Override
    protected void load() {
        driver.get(APP_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        driver.get(APP_URL);
        PageLoadHelper.isLoaded().isElementVisible(start)
                .isElementClickable(start);
    }
}
