package Pages;

import enums.Reason;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class DashboardPage extends Page {

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



    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public static DashboardPage navigateTo(WebDriver driver) {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.open();
        return dashboardPage;
    }

    /**
     * Use enum to exclude mistakes in reason names
     * @param creditReason
     */
    public void selectCreditReason(Reason creditReason) {
        By by = By.xpath(String.format(".//*[@aria-label='%s']", creditReason.getReasonName()));
        driverWait.until(elementToBeClickable(creditReasonSelect)).click();
        driverWait.until(elementToBeClickable(by)).click();
    }

    /**
     * ugly hardCode, spent a lot of time to find how to unify. Unsuccessfully
     */
    public void selectCreditAmount() {
        driverWait.until(elementToBeClickable(creditAmountSelect)).click();
        WebElement element = driver.findElement(By.id("react-select-3--option-9"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public void selectCreditTerm(String amountOfMonths) {
        By by = By.xpath(String.format(".//*[@aria-label='%s Monate']", amountOfMonths));
        driverWait.until(elementToBeClickable(creditTermSelect)).click();
        WebElement element = driver.findElement(by);
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
}
