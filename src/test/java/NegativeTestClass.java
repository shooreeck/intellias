import Pages.LandingPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NegativeTestClass extends SettingsClass {

    private LandingPage landingPage;
    private static String LOGIN_BLOCK_ERROR = "Bitte überprüfen Sie Ihre Eingabe.";

    @BeforeClass
    public void setup() {
        landingPage = LandingPage.navigateTo(driver);
    }

    @Test
    public void testLoginNegative() {
        landingPage.clickLoginBtn();
        landingPage.enterEmail("notValidEmail");
        landingPage.enterPassword("anyPassword");
        landingPage.clickSignInBtn();

        Assert.assertEquals(landingPage.getLoginBlockError(), LOGIN_BLOCK_ERROR, "wrong error message");
    }
}
