package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK,
            POPUP_BUTTON;

    public NavigationUI(AppiumDriver driver) {
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
        System.out.println("Нашли крестик");
        this.waitForElementAndClick(
                POPUP_BUTTON,
                "Cannot find  close popup button",
                10
        );
        System.out.println("Закрыли попап");
    }


}
