package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TYTLE_AND_DESCR_TPL,
            SEARCH_RESULT_BY_TYTLE_AND_DESCR_TPL_ALTERNATIVE,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_LABEL;

    protected static String []
            descriptions = new String[3],
            titles = {"Java", "JavaScript", "Java (programming language)"};

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndDescription(String title, String description) {
        //return SEARCH_RESULT_BY_TYTLE_AND_DESCR_TPL_ALTERNATIVE.replace("{TITLE_SUBSTRING}", title).replace("{DESCRIPTION_SUBSTRING}",description);
        return SEARCH_RESULT_BY_TYTLE_AND_DESCR_TPL.replace("{TITLE_SUBSTRING}", title).replace("{DESCRIPTION_SUBSTRING}", description);
    }
    /*TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search element", 5);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button!", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present!", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button!", 5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, "Cannot find and type into search input", 5, searchLine);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(searchResultXpath, "cannot find search result with substring " + substring, 15);
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultXpath, "cannot find and click search result with substring " + substring, 15);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_LABEL,
                "Cannot find empty result element",
                15);
    }

    public void assertThereIsNoResultsOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "we supposed not to find any results");
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String searchResultXpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(searchResultXpath, "cannot find search result by title " + title + " and description " + description, 15);
    }

    public void checkThreeElementsByTitleAndDescription() {
        for (int i = 0; i < 3; i++) {
            System.out.println("i ???? ???????????? checkThreeElementsByTitleAndDescription = " + i);
            String searchResultXpath = getResultSearchElementByTitleAndDescription(titles[i], descriptions[i]);
            this.waitForElementPresent(searchResultXpath, "cannot find search result by title " + titles[i] + " and description " + descriptions[i], 15);
        }


    }

}
