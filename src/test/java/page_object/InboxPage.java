package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.DriverFactory;

public class InboxPage extends Page {

    private WebDriverWait webDriverWait = new WebDriverWait(driver, DriverFactory.WEBDRIVER_WAIT_TIME_OUT);

    private static final By USER_MAIL_STRING_AFTER_LOGIN = By.xpath("//i[@id='PH_user-email']");
    private static final By AGENT_LOCATOR = By.xpath("//span[@class='nwa-button-tab__nick']");

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserMailAfterLoginDisplayed() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (driver.findElement(AGENT_LOCATOR)));
        return driver.findElement(USER_MAIL_STRING_AFTER_LOGIN).isDisplayed();
    }
}
