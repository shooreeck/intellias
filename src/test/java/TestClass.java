import Pages.DashboardPage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static enums.Reason.LIVE;

public class TestClass extends SettingsClass {

    private DashboardPage dashboardPage;

    @BeforeClass
    public void setup() {
        dashboardPage = DashboardPage.navigateTo(driver);
    }

    @Test
    public void testOne() {
        dashboardPage.selectCreditReason(LIVE);
        dashboardPage.selectCreditAmount();
        dashboardPage.selectCreditTerm("24");

        Assert.assertTrue(dashboardPage.clickSubmitBtn().isPageLoaded(), "Page was not load");
    }


}
