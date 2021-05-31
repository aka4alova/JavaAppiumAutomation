package lib.ui.mobileweb;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type=search]";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains (@class, 'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_BY_TYTLE_AND_DESCR_TPL = "xpath://XCUIElementTypeStaticText[contains (@name, '{TITLE_SUBSTRING}')]" +
                "/..//XCUIElementTypeStaticText[contains (@name, '{DESCRIPTION_SUBSTRING}')]/..";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_LABEL = "css:p.without-results";
        descriptions[0] = "Indonesian island";
        descriptions[1] = "High-level programming language";
        descriptions[2] = "Object-oriented programming language";
    }

    public MWSearchPageObject (RemoteWebDriver driver) {
        super(driver);
    }
}
