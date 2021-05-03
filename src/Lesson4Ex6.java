import org.junit.Test;
import org.openqa.selenium.By;


public class Lesson4Ex6 extends CommonMethods {

    @Test
    public void isElementPresent() {                                                    // Ex6: Тест: assert title

        waitForElementAndClick(                                                         // тык в поиск
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(                                                      // ввели поисковую строку
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_src_text')]"),
                "Cannot find search input",
                5,
                "Java"
        );
        waitForElementAndClick(                                             //тык на статью
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language'",
                35
        );
        System.out.println("Открыли статью");

        //Utils.sleep(1);

        assertElementIsPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "cannot found article title"
        );
        String titleOfArticle = waitForElementAndGetAttribute( //   запомнили тайтл статьи
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                20
        );
        System.out.println(titleOfArticle);


    }
}
