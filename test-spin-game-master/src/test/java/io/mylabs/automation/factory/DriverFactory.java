package io.mylabs.automation.factory;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public final class DriverFactory {

    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    // Save the drivers in a pool, so that it can be used to quit the drivers and browsers at the end.
    private static List<WebDriver> poolOfDrivers = new ArrayList<>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                poolOfDrivers.forEach(WebDriver::quit);
            }
        });
    }

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        return drivers.get();
    }

    public static void addDriver(WebDriver driver) {
        poolOfDrivers.add(driver);
        drivers.set(driver);
    }

    public static void removeDriver() {
        poolOfDrivers.remove(drivers.get());
        drivers.remove();
    }
}
