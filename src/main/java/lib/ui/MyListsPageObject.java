package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL;

    private static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        String folderNameXpath = getFolderXpathByName(nameOfFolder);
        System.out.println("folderNameXpath = " + folderNameXpath);
        this.waitForElementAndClick(
                folderNameXpath,
                "Cannot find folder by name " + nameOfFolder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementPresent(articleXpath, "Cannot find saved article by title " + articleTitle, 15);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementNotPresent(articleXpath, "Saved article still presents with title " + articleTitle, 15);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.swipeElementToLeft(
                articleXpath,
                "Cannot find saved article"
        );
        if (Platform.getInstance().isIOS()) {
            this.clickElementToDelete(articleXpath, "Cannot find saved article");

        }
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void openArticleByName(String articleTitle) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        System.out.println("article xpath = " + articleXpath);
        this.waitForElementAndClick(
                articleXpath,
                "Cannot find article by name " + articleXpath,
                5
        );
    }


    public void waitForArticleTitleAndDescription(String articleTitle, String articleDescription) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        System.out.println("article xpath = " + articleXpath);
        String descriptionXpath = getSavedArticleXpathByTitle(articleDescription);
        System.out.println("description xpath = " + descriptionXpath);
        this.waitForElementPresent(articleXpath, "Cannot find articleaaaaa title on page!", 15);
        this.waitForElementPresent(descriptionXpath, "Cannot find article description on page!", 15);

    }

}
