package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Linkin Park Diskography";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.getAmountOfFoundArticles();
        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "we found too few results!",
                amountOfSearchResults > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "sdfsdfsdfsdf";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultsOfSearch();
    }

    @Test //Ex3: Тест: отмена поиска
    public void testCheckMultipleSearchResultAndCancel() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();
        assertTrue("too few results was found", amountOfSearchResults > 1);
        searchPageObject.clickCancelSearch();
        searchPageObject.assertThereIsNoResultsOfSearch();
    }

    @Test //Ex9*: Рефакторинг темплейта
    public void testFindThreeArticlesByTitleAndDescription() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();
        System.out.println("Найдено статей: " + amountOfSearchResults);
        assertTrue("too few results was found", amountOfSearchResults > 2);
        searchPageObject.checkThreeElementsByTitleAndDescription();
    }
}
