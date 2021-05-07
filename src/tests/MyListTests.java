package tests;

import lib.CoreTestCase;
import lib.Utils;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        String nameOfFolder = "MyFirstList";
        articlePageObject.addFirstArticleToMyList(nameOfFolder);
        articlePageObject.closeArticle();
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();
        Thread.sleep(4000);
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(nameOfFolder);
        Thread.sleep(10000);
        myListsPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticlesToMyList() { //Ex.5: Тест: сохранение двух статей
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String firstArticleTitle = articlePageObject.getArticleTitle();
        String nameOfFolder = "MyFirstList";
        articlePageObject.addFirstArticleToMyList(nameOfFolder);
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        articlePageObject.waitForTitleElement();
        String secondArticleTitle = articlePageObject.getArticleTitle();
        articlePageObject.addNextArticleToMyList(nameOfFolder);
        articlePageObject.closeArticle();
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();
        Utils.sleep(4);

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(nameOfFolder);
        Utils.sleep(10);
        myListsPageObject.swipeByArticleToDelete(firstArticleTitle);
        myListsPageObject.waitForArticleToAppearByTitle(secondArticleTitle);
        myListsPageObject.openArticleByName(secondArticleTitle);
        String titleAfterOpening = articlePageObject.getArticleTitle();

        assertEquals(
                "Article title is not equal",
                secondArticleTitle,
                titleAfterOpening
        );
    }

}
