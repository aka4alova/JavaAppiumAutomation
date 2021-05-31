package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {
    @Test
    public void testPassThroughWelcome(){
        if (Platform.getInstance().isAndroid()) {
            return;
        }

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForNewWaysToExplore();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForEditPreferredLanguagesLink();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForLearnMoreAboutDataCollected();
        welcomePageObject.clickGetStarted();

    }

}
