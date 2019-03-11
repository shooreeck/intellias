package Pages;

import enums.Reason;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class LandingPage extends Page {

    // Request form block
    @FindBy(xpath = "(.//div[@class='Select-value'])[1]")
    private WebElement creditReasonSelect;

    @FindBy(xpath = ".//input[@name='amount']/../div[@class='Select-control']")
    private WebElement creditAmountSelect;

    @FindBy(xpath = ".//div[@class='menuOptionsWrapper']/../..")
    private WebElement creditTermSelect;

    @FindBy(xpath = ".//a[@type='submit']")
    private WebElement submitBtn;

    @FindBy(css = ".Popup__tooltip.LoginForm__tooltip")
    private WebElement loginBlock;

    @FindBy(css = ".login-button")
    private WebElement loginBtn;

    //Login block
    @FindBy(xpath = ".//*[@class='Popup__content']//input[@name='email']")
    private WebElement emailTi;

    @FindBy(xpath = ".//*[@class='Popup__content']//input[@name='password']")
    private WebElement passwordTi;

    @FindBy(xpath = ".//*[@class='Popup__content']//button")
    private WebElement signInBtn;

    @FindBy(css = ".Popup__content .FieldWrapper__error-message")
    private WebElement errorMessage;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public static LandingPage navigateTo(WebDriver driver) {
        var landingPage = new LandingPage(driver);
        landingPage.open();
        return landingPage;
    }

    /**
     * Use enum to exclude mistakes in reason names
     * @param creditReason
     */
    public void selectCreditReason(Reason creditReason) {
        var by = By.xpath(String.format(".//*[@aria-label='%s']", creditReason.getReasonName()));
        driverWait.until(elementToBeClickable(creditReasonSelect)).click();
        driverWait.until(elementToBeClickable(by)).click();
    }

    /**
     * ugly hardCode, spent a lot of time to find how to unify. Unsuccessfully
     */
    public void selectCreditAmount() {
        driverWait.until(elementToBeClickable(creditAmountSelect)).click();
        var element = driver.findElement(By.id("react-select-3--option-9"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public void selectCreditTerm(String amountOfMonths) {
        var by = By.xpath(String.format(".//*[@aria-label='%s Monate']", amountOfMonths));
        driverWait.until(elementToBeClickable(creditTermSelect)).click();
        var element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public CreditRequestPage clickSubmitBtn() {
        driverWait.until(elementToBeClickable(submitBtn)).click();
        return new CreditRequestPage(driver);
    }

    public CreditRequestPage inputDataToCalculatorClickConfirm(Reason creditReason, String length) {
        selectCreditReason(creditReason);
        selectCreditAmount();
        selectCreditTerm(length);
        return clickSubmitBtn();
    }

    public void clickLoginBtn() {
        driverWait.until(elementToBeClickable(loginBtn)).click();
    }

    public void enterEmail(String email) {
        driverWait.until(visibilityOf(emailTi)).clear();
        emailTi.sendKeys(email);
    }

    public void enterPassword(String password) {
        driverWait.until(visibilityOf(passwordTi)).clear();
        passwordTi.sendKeys(password);
    }

    public void clickSignInBtn() {
        driverWait.until(elementToBeClickable(signInBtn)).click();
    }

    public String getLoginBlockError() {
        return driverWait.until(visibilityOf(errorMessage)).getText();
    }
}
