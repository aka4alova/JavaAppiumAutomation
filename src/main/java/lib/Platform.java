package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {

    protected AppiumDriver<?> driver;

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform () {}

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public boolean isAndroid (){
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS (){
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMW (){
        return isPlatform(PLATFORM_MOBILE_WEB);
    }


    private DesiredCapabilities getAndroidDesiredCapabilities () {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "androidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/anastasiia.kachalova/Automation/Projects/" +
                "JavaAppiumAutomation/apks/org.wiki.apk");
        capabilities.setCapability("orientation", "PORTRAIT");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);

        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities () {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iphone 11");
        capabilities.setCapability("platformVersion", "13.5");
        capabilities.setCapability("app", "/Users/anastasiia.kachalova/Automation/Projects/" +
                "JavaAppiumAutomation/apks/Wikipedia ios.app");

        return capabilities;
    }

    private ChromeOptions getMWChromeOptions() {
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=360,640");

        return chromeOptions;
    }

    private boolean isPlatform (String myPlatform) {
        String platform = this.getPlatformVar();
        return myPlatform.equals(platform);
    }

    public String getPlatformVar (){
        return System.getenv("PLATFORM");
    }

    public RemoteWebDriver getDriverByPlatformEnv() throws Exception{
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            System.out.println( "это андроид");
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        }
        if (this.isIOS()) {
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        }
        if (this.isMW()) {
            return new ChromeDriver(this.getMWChromeOptions());
        }
        else {
            throw new Exception("Cannot detect type of the Driver. Platform value = " + this.getPlatformVar());
        }
    }
}
