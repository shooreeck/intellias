package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class Page {

    protected WebDriverWait driverWait;
    protected WebDriver driver;
    protected String pageUrl = "https://www.smava.de/";

    protected Page(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, 20);
        driverWait.ignoring(StaleElementReferenceException.class);
        PageFactory.initElements(driver, this);
    }

    protected void open() {
        driver.get(this.pageUrl);
    }

    protected boolean isPageLoaded() {
        try {
            await().atMost(30, TimeUnit.SECONDS).until(() ->
                    ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }
}
