package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK,
            POPUP_BUTTON;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find  navigation button to My list",
                10
        );
    }

    public void clickClosePopup () {
        this.waitForElementPresent (
                POPUP_BUTTON,
                "Cannot find  close popup button",
                10
        );
        this.waitForElementAndClick(
                POPUP_BUTTON,
                "Cannot find  close popup button",
                10
        );
    }


}
