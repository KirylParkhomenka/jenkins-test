package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends Page {

    private static final By LOGIN_INPUT = By.xpath("//input[@id='mailbox:login']");
    private static final By SUBMIT_BUTTON = By.xpath("//input[@type = 'submit']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@id='mailbox:password']");
    private static final By ENTER_LINK = By.xpath("//a[@id='PH_authLink']");

    private static final String LOGIN = "tester.atm";
    private static final String PASSWORD = "q123456";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public InboxPage loginToMailBox() {
        driver.findElement(LOGIN_INPUT).sendKeys(LOGIN);
        driver.findElement(SUBMIT_BUTTON).click();
        driver.findElement(PASSWORD_INPUT).sendKeys(PASSWORD);
        driver.findElement(SUBMIT_BUTTON).click();
        return new InboxPage(driver);
    }

    public boolean isLogoutSuccessful() {
        return driver.findElement(ENTER_LINK).isDisplayed();
    }
}
