package com.mobile.framework.drivermanager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static AppiumDriver driver;

    public static AppiumDriver getDriver(String platform) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equalsIgnoreCase("android")) {
            capabilities.setCapability("appium:platformName", "Android");
            capabilities.setCapability("appium:platformVersion", "14.0");
            capabilities.setCapability("appium:udid", "emulator-5554");
            capabilities.setCapability("appium:appPackage", "com.swaglabsmobileapp");
            capabilities.setCapability("appium:appActivity", "com.swaglabsmobileapp.MainActivity");
            File appFile = new File("src/test/resources/app/Android_SauceLabs.apk");
            if (!appFile.exists()) {
                throw new RuntimeException("APK file not found at: " + appFile.getAbsolutePath());
            }
            capabilities.setCapability("appium:app", appFile.getAbsolutePath());
            capabilities.setCapability("appium:automationName", "UiAutomator2");
            capabilities.setCapability("appium:fullReset", "true");

            try {
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

        } else if (platform.equalsIgnoreCase("ios")) {
            capabilities.setCapability("appium:platformName", "iOS");
            capabilities.setCapability("appium:platformVersion", "17.2");//TestApp.app
            capabilities.setCapability("appium:deviceName", "iPhone 15");
            File appFile = new File("src/test/resources/app/iOS_SauceLabs.app");
            if (!appFile.exists()) {
                throw new RuntimeException("APK file not found at: " + appFile.getAbsolutePath());
            }
            capabilities.setCapability("appium:app", appFile.getAbsolutePath());
            capabilities.setCapability("appium:automationName", "xcuitest");
            capabilities.setCapability("appium:fullReset", "true");

            try {
                driver = new IOSDriver(new URL("http://127.0.0.1:4723"), capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        return driver;
    }

    public static AppiumDriver getCurrentDriver() {
        return driver;
    }
}
