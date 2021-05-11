package lib.ui;

import io.appium.java_client.AppiumDriver;

import lib.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            FOLDER_BY_NAME_TPL = "xpath://*[@text = '{FOLDER_NAME}']";

    private static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the article title",
                20
        );
    }

    public void addFirstArticleToMyList(String nameOfFolder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find 'More' button",
                10
        );

        Utils.sleep(4);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list' button",
                10
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'GOT IT' button",
                10
        );
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );
        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5,
                nameOfFolder
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find 'OK' button",
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find 'X' button",
                10
        );
    }

    public void addNextArticleToMyList(String nameOfFolder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find 'More' button",
                10
        );

        Utils.sleep(4);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list' button",
                10
        );
        String folderNameXpath = getFolderXpathByName(nameOfFolder);
        this.waitForElementAndClick(
                folderNameXpath, // выбираю ранее созданный список
                "Cannot find bookmark folder",
                10
        );
    }

}
