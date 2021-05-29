package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
                SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='{SUBSTRING}']";
                SEARCH_RESULT_BY_TYTLE_AND_DESCR_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']" +
                        "[@text='{TITLE_SUBSTRING}']/..//*[@resource-id='org.wikipedia:id/page_list_item_description']" +
                        "[@text='{DESCRIPTION_SUBSTRING}']/..";
                SEARCH_RESULT_BY_TYTLE_AND_DESCR_TPL_ALTERNATIVE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container' " +
                        "and *//*[@text = '{TITLE_SUBSTRING}' and @resource-id = 'org.wikipedia:id/page_list_item_title'] " +
                        "and *//*[@text = '{DESCRIPTION_SUBSTRING}' and @resource-id = 'org.wikipedia:id/page_list_item_description']]";
                SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']" +
                        "/*[@resource-id='org.wikipedia:id/page_list_item_container']";
                SEARCH_EMPTY_RESULT_LABEL = "xpath://*[@text='No results found']";
                descriptions[0] = "Island of Indonesia";
                descriptions[1] = "Programming language";
                descriptions[2] = "Object-oriented programming language";
    }

    public AndroidSearchPageObject (AppiumDriver driver) {
        super(driver);
    }
}
