package io.mylabs.automation.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactoryManager {

    private WebDriver driver;
    private static final String DEFAULT_BROWSER = "CHROME";
    private static final String BROWSER = System.getProperty("browser", DEFAULT_BROWSER).toUpperCase();
    private static final Boolean HEADLESS = Boolean.valueOf(System.getProperty("headless", "false"));

    public DriverFactoryManager() {
        if (DriverFactory.getDriver() == null) {
            switch (BROWSER) {
                case "CHROME":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setHeadless(HEADLESS);
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "FIREFOX":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setHeadless(HEADLESS);
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
            }
            driver.manage().window().maximize();
            DriverFactory.addDriver(driver);
        }
    }
}
