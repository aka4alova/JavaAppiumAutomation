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

    @Test //Ex9*: Рефакторинг темплейта
    public void testFindThreeArticlesByTitleAndDescription() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        String [] titles = { "Java", "JavaScript", "Java (programming language)" };
        String [] descriptions = { "Island of Indonesia", "Programming language", "Object-oriented programming language" };
        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();
        System.out.println("Найдено статей: " + amountOfSearchResults);
        assertTrue("too few results was found", amountOfSearchResults > 2);
        for (int i = 0; i < titles.length; i++) {
            searchPageObject.waitForElementByTitleAndDescription(titles[i], descriptions[i]);
        }
        //searchPageObject.waitForElementByTitleAndDescription(titles[1], descriptions[2]); //тут тест должен упасть
    }

}
