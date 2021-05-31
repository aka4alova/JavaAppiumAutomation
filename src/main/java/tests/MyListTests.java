package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.Utils;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class MyListTests extends CoreTestCase {

    private static final String nameOfFolder = "MyFirstList";

    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {

            articlePageObject.addFirstArticleToMyList(nameOfFolder);
            articlePageObject.closeArticle();

        } else {
           articlePageObject.addArticlesToMySaved();
           articlePageObject.closeArticle();
           searchPageObject.clickCancelSearch();

        }

        navigationUI.clickMyLists();

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameOfFolder);
        } else {
            navigationUI.clickClosePopup();
        }

        Thread.sleep(3000);

        myListsPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticlesToMyList() { //Ex.5: Тест: сохранение двух статей
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addFirstArticleToMyList(nameOfFolder);
            articlePageObject.closeArticle();
        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.closeArticle();
            searchPageObject.clickCancelSearch();
        }

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("ios");
        searchPageObject.clickByArticleWithSubstring("Mobile operating system by Apple");

        Utils.sleep(5);

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.waitForTitleElement();
            articlePageObject.addNextArticleToMyList(nameOfFolder);
            articlePageObject.closeArticle();
        } else {
            articlePageObject.waitForTitleElement("id:iOS");
            articlePageObject.addArticlesToMySaved();
            articlePageObject.closeArticle();
            searchPageObject.clickCancelSearch();
        }
        navigationUI.clickMyLists();
        Utils.sleep(4);
        String lastArticleID;

        if (Platform.getInstance().isIOS()) {
            navigationUI.clickClosePopup();
        } else {
            myListsPageObject.openFolderByName(nameOfFolder);
        }

        Utils.sleep(3);
        myListsPageObject.swipeByArticleToDelete(articleTitle);
        if (Platform.getInstance().isIOS()) {
            myListsPageObject.openArticleByName("iOS");
        } else {
            myListsPageObject.openArticleByName("IOS");
        }

        myListsPageObject.waitForArticleTitleAndDescription ("iOS", "Mobile operating system by Apple");

    }
}
