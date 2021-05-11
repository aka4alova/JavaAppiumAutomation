package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class iOSTestCase extends TestCase {

    protected AppiumDriver<?> driver;

    private static String appiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE (1st generation)");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/anastasiia.kachalova/Automation/Projects/JavaAppiumAutomation/apks/Wikipedia ios.app");
        capabilities.setCapability("orientation", "PORTRAIT");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        driver = new IOSDriver(new URL(appiumURL), capabilities);
    }

    @Override
    public void tearDown() throws Exception {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

}
