package test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_object.HomePage;
import page_object.InboxPage;
import setup.Browser;
import setup.WebDriverTypes;

public class MailRuCriticalPathTest {

    private static final String BASE_URL = "https://mail.ru/";

    private WebDriver driver;
    private HomePage homePage;
    private InboxPage inboxPage;

    @BeforeClass(description = "Start browser and initialize pages")
    public void setUpBefore() {
        driver = Browser.getWrappedDriver(WebDriverTypes.CHROME);
        driver.get(BASE_URL);
        homePage = new HomePage(driver);
        inboxPage = new InboxPage(driver);
    }

    @Test(description = "Login to Mail.ru mailbox")
    public void loginMailBox() {
        new HomePage(driver).loginToMailBox();
        boolean loginIsComplete = inboxPage.isUserMailAfterLoginDisplayed();
        Assert.assertTrue(loginIsComplete, "Yor are NOT logged in");
        System.out.println("Login was completed successfully");
    }

    @AfterClass(description = "Close browser")
    public void tearDown() {
        driver.quit();
    }
}
