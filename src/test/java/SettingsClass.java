import io.github.bonigarcia.wdm.WebDriverManager;
import org.awaitility.Awaitility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class SettingsClass {

    protected WebDriver driver;
    private final static String BROWSER = "chrome";

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        driver = getDriver(BROWSER);
        Awaitility.setDefaultPollDelay(4, TimeUnit.SECONDS);
        Awaitility.setDefaultTimeout(16, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public synchronized void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    private WebDriver getDriver(String browser) {
        WebDriver driver;
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(createChromeConfig());
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                return driver;
            case "firefox":
            default:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(setFirefoxProfile());
                driver.manage().window().maximize();
        }
        return driver;
    }

    private static ChromeOptions createChromeConfig() {
        ChromeOptions options = new ChromeOptions();
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            options.addArguments("start-fullscreen");
        }
        options.addArguments("start-maximized");
        options.addArguments("-disable-cache");
        options.setCapability(ChromeOptions.CAPABILITY, options);
        return options;
    }

    private static FirefoxOptions setFirefoxProfile() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("browser.download.folderList", 2);
        firefoxOptions.setCapability("browser.helperApps.alwaysAsk.force", false);
        firefoxOptions.setCapability("browser.helperApps.neverAsk.saveToDisk",
                "video/3gpp,video/mp4,audio/x-wav,audio/mp4a-latm,image/png,text/comma-separated-values," +
                        "text/csv,application/csv,application/excel,application/vnd.ms-excel,application/vnd.msexcel," +
                        "text/anytext,text/html,application/xhtml+xml,application/xml,binary/octet-stream");
        return firefoxOptions;
    }
}
