package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
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
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String searchLine = "sdfsdfsdfsdf";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultsOfSearch();
    }

    @Test //Ex3: Тест: отмена поиска
    public void testCheckMultipleSearchResultAndCancel() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();
        assertTrue("too few results was found", amountOfSearchResults > 1);
        searchPageObject.clickCancelSearch();
        searchPageObject.assertThereIsNoResultsOfSearch();
    }

}
