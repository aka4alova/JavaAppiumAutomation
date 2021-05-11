package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver<?> driver;

    private static String appiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = new AndroidDriver<>(new URL(appiumURL), capabilities);
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

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception{
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "androidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/anastasiia.kachalova/Automation/Projects/JavaAppiumAutomation/apks/org.wiki.apk");
            capabilities.setCapability("orientation", "PORTRAIT");
            capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
            capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        } else if (platform.equals(PLATFORM_IOS)) {

            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE (1st generation)");
            capabilities.setCapability("platformVersion", "11.3");
            capabilities.setCapability("app", "/Users/anastasiia.kachalova/Automation/Projects/JavaAppiumAutomation/apks/Wikipedia ios.app");
        }
        else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }
        return capabilities;
    }

}
