import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Lesson3Ex4 extends CoreTestCase {

    @Test // Ex4*: Тест: проверка слов в поиске
    public void testCheckContainsText() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_src_text')]"),
                "Cannot find search input",
                5,
                "Java"
        );

        WebElement element = mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Cannot find result list",
                5
        );

        List<WebElement> elements = element.findElements(By.xpath("//*[contains(@resource-id, 'org.wikipedia:id/page_list_item_title')]"));
        for (WebElement webElement : elements) {
            String text = webElement.getText();
            System.out.println("result is '" + text + "'");
            assertNotNull("result text is null", text);
            assertTrue("wrong results", text.toLowerCase().contains("java"));
        }
    }

}
