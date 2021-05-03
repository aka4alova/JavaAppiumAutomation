import org.junit.*;
import org.openqa.selenium.By;

public class Lesson4Ex5 extends CommonMethods {

    @Test
    public void saveTwoArticlesToMyList() { //Ex.5: Тест: сохранение двух статей
        waitForElementAndClick(                                             //тык в поиск
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(                                          //ввели поисковую строку
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_src_text')]"),
                "Cannot find search input",
                5,
                "Java"
        );
        waitForElementAndClick(                                             //тык на статью
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language'",
                15
        );
        waitForElementPresent(                                              //дождались загрузки статьи
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        String titleOfFirstArticle = waitForElementAndGetAttribute( //   запомнили тайтл первой статьи
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                10
        );

        waitForElementAndClick(                                             //тык на бургерное меню
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More' button",
                10
        );
        Utils.sleep(4);
        waitForElementAndClick(                                             //добавить в список чтения
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list' button",
                10
        );
        waitForElementAndClick(                                             //GOT IT!!!
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'GOT IT' button",
                10
        );
        waitForElementAndClear(                                             //Очистили имя
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );
        String nameOfFolder = "MyFirstList";                                //Ввели свое имя
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5,
                nameOfFolder
        );
        waitForElementAndClick(                                             //сохранили список и статью
                By.id("android:id/button1"),
                "Cannot find 'OK' button",
                5
        );
        waitForElementAndClick(                                             //ушли на главный экран
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'X' button",
                10
        );

        waitForElementAndClick(                                             //тык снова в поиск
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKeys(                                          //ввели поисковую строку
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_src_text')]"),
                "Cannot find search input",
                5,
                "Appium"
        );
        waitForElementAndClick(                                             //тык на статью
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find Appium",
                15
        );
        waitForElementPresent(                                              //дождались загрузки статьи
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        String titleOfSecondArticle = waitForElementAndGetAttribute( //   запомнили тайтл второй статьи
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                10
        );

        waitForElementAndClick(                                             //тык на бургерное меню
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More' button",
                10
        );
        Utils.sleep(4);
        waitForElementAndClick(                                             //добавить в список чтения
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list' button",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + nameOfFolder + "']"), // выбираю ранее созданный список
                "Cannot find bookmark folder",
                10
        );

        waitForElementAndClick(                                             //ушли на главный экран
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'X' button",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"), // зашли в закладки
                "Cannot find 'Bookmarks' button",
                10
        );

        Utils.sleep(4);
        waitForElementAndClick(
                By.xpath("//*[@text= '" + nameOfFolder + "']"),   // тык на созданный список
                "Cannot find created folder",
                10
        );

        swipeElementToLeft(
                By.xpath("//*[@text='" + titleOfFirstArticle + "']"),    // удалили статью про джаву
                "Cannot find the article 'Java (programming lang)'"
        );

        waitForElementNotPresent(                                       //убедились что первая статья удалилась
                By.xpath("//*[@text='" + titleOfFirstArticle + "']"),
                "Cannot delete saved article",
                5
        );

        waitForElementPresent(                                       //убедились что вторая статья осталась
                By.xpath("//*[@text='" + titleOfSecondArticle + "']"),
                "Cannot find saved article",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + titleOfSecondArticle + "']"),   // тык на оставшуюся статью
                "Cannot open saved article",
                10
        );

        String titleAfterOpening = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                10
        );

        System.out.println("titleAfterOpening =" + titleAfterOpening);

        Assert.assertEquals(
                "Article title is not equal",
                titleOfSecondArticle,
                titleAfterOpening
        );
    }
}
