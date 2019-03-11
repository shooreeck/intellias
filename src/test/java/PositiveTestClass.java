import Pages.LandingPage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static enums.Reason.LIVE;

public class PositiveTestClass extends SettingsClass {

    private LandingPage landingPage;

    @BeforeClass
    public void setup() {
        landingPage = LandingPage.navigateTo(driver);
    }

    @Test
    public void testInputDataOnFirstStepRequest() {
        landingPage.selectCreditReason(LIVE);
        landingPage.selectCreditAmount();
        landingPage.selectCreditTerm("24");

        Assert.assertTrue(landingPage.clickSubmitBtn().isPageLoaded(), "Page was not load");
    }
}
