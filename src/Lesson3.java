import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Lesson3 {
    private AppiumDriver<?> driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "androidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/anastasia.kachalova/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        Thread.sleep(5000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void isElementHasText() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                5
        );
        assertElementHasText(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_src_text')]"),
                "Search Wikipedia",
                "Text is not equals!"
        );

    }

    public void assertElementHasText(By by, String expected, String assertErrorMessage) {
        WebElement element = waitForElementPresent(by, 5);
        Assert.assertEquals(
                assertErrorMessage,
                expected,
                element.getAttribute("text")
        );
    }

    private WebElement waitForElementPresent(By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage("element is not found \n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, timeoutInSeconds);
        element.click();
        return element;
    }
}
