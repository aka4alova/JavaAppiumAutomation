package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains (@name, '{SUBSTRING}')]";
        SEARCH_RESULT_BY_TYTLE_AND_DESCR_TPL = "xpath://XCUIElementTypeStaticText[contains (@name, '{TITLE_SUBSTRING}')]" +
                "/..//XCUIElementTypeStaticText[contains (@name, '{DESCRIPTION_SUBSTRING}')]/..";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT_LABEL = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        descriptions[0] = "Indonesian island";
        descriptions[1] = "High-level programming language";
        descriptions[2] = "Object-oriented programming language";
    }

    public IOSSearchPageObject (RemoteWebDriver driver) {
        super(driver);
    }
}
