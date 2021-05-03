import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

public class Lesson4Ex7 extends CommonMethods {

    @Test
    public void testRotationAndFail()  {                //Ex7*: Поворот экрана, тест который падает после поворота.
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String searchLine = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_src_text')]"),
                "Cannot find search input",
                5,
                searchLine
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language'",
                15
        );
        String titleBeforeRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                10
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                10
        );

        Assert.assertEquals(
                "Article title is not equal",
                titleBeforeRotation,
                titleAfterRotation + "dfgf"
        );
    }

    @Test
    public void testRotation()  {                //Ex7*: Поворот экрана, тест запускается следующим после упавшего.
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String searchLine = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_src_text')]"),
                "Cannot find search input",
                5,
                searchLine
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language'",
                15
        );
        String titleBeforeRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                10
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                10
        );

        Assert.assertEquals(
                "Article title is not equal",
                titleBeforeRotation,
                titleAfterRotation + ""
        );
    }




}
