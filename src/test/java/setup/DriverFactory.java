package setup;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import setup.exceptions.UnknownDriverTypeException;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver driver;

    private static final String CHROME_DRIVER_PATH = "src/test/java/resources/driver/chromedriver.exe";

    private static final int IMPLICIT_WAIT = 20;
    private static final int PAGE_LOAD_TIMEOUT = 20;
    public static final int WEBDRIVER_WAIT_TIME_OUT = 30;

    public static WebDriver getWebDriverInstance(WebDriverTypes type) throws Exception {
        DesiredCapabilities caps;
        switch (type) {
            case FIREFOX: {
                driver = new FirefoxDriver();
                break;
            }
            case CHROME: {
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                caps = setChromeCapabilitiesForWindows();
                caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                ChromeOptions opt = new ChromeOptions();
                opt.addArguments("disable-blink-features=BlockCredentialedSubresources");
                caps.setCapability(ChromeOptions.CAPABILITY, opt);
                driver = new ChromeDriver(caps);
                System.out.println("Browser: " + type.name());
                break;
            }
            case IE: {
                driver = new InternetExplorerDriver();
                break;
            }
            default:
                throw new UnknownDriverTypeException("Unknown web driver specified: " + type);
        }
        prepareTimeouts();
        return driver;
    }

    public static WebDriver getFirefoxInstance() {
        driver = new FirefoxDriver();
        prepareTimeouts();
        return driver;
    }

    public static WebDriver getChromeInstance() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        prepareTimeouts();
        return driver;
    }

    private static void prepareTimeouts() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private static DesiredCapabilities setChromeCapabilitiesForWindows() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities = setCommonCapabilities(capabilities);
        return capabilities;
    }

    private static DesiredCapabilities setCommonCapabilities(DesiredCapabilities capabilities) {
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return capabilities;
    }
}