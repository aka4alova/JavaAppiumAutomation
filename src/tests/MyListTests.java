package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.Utils;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

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
            System.out.println("Андроид: добавили статью в папку");
            articlePageObject.closeArticle();
            System.out.println("Андроид: Закрыли статью");

        } else {
           articlePageObject.addArticlesToMySaved();
           articlePageObject.closeArticle();
           searchPageObject.clickCancelSearch();

        }

        navigationUI.clickMyLists();

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameOfFolder);
            System.out.println("Андроид: открыли нужную папку");
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
        searchPageObject.initSearchInput();
        System.out.println("Тапнули в поиск");
        searchPageObject.typeSearchLine("Java");
        System.out.println("Ввели поисковую строку");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        System.out.println("Открыли нужную статью");
        articlePageObject.waitForTitleElement();
        System.out.println("Дождались заголовка");
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
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();
        Utils.sleep(4);

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
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
