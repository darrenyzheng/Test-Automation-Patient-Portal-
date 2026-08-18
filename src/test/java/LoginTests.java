import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTests {

    private WebDriver driver;
    private static final String BASE_URL = System.getenv("BASE_URL");
    private static final String USERNAME = System.getenv("USERNAME");
    private static final String PASSWORD = System.getenv("PASSWORD");
    private static final String EMAIL =  System.getenv("EMAIL");

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testValidLogin() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD, EMAIL);
    }

    @Test
    public void testEmptyLogin() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "", "");
        WebElement activeElement = (WebElement) ((JavascriptExecutor) driver) .executeScript("return document.activeElement");
        assertEquals(driver.findElement(LoginPage.USERNAME), activeElement);
        assert !driver.findElement(LoginPage.ERROR_MESSAGE).isDisplayed();
    }

    @Test
    public void testWrongFormatEmail() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD, "invalid-email");
        WebElement activeElement = (WebElement) ((JavascriptExecutor) driver) .executeScript("return document.activeElement");
        assertEquals(driver.findElement(LoginPage.EMAIL), activeElement);
        assert !driver.findElement(LoginPage.ERROR_MESSAGE).isDisplayed();
    }

    @Test
    public void testEmptyUsername() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", PASSWORD, EMAIL);
        WebElement activeElement = (WebElement) ((JavascriptExecutor) driver) .executeScript("return document.activeElement");
        assertEquals(driver.findElement(LoginPage.USERNAME), activeElement);
        assert !driver.findElement(LoginPage.ERROR_MESSAGE).isDisplayed();
    }

    @Test
    public void testInvalidUsername() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Darren3", PASSWORD, EMAIL);
    }

    @Test
    public void testEmptyPassword() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, "", EMAIL);
    }

    @Test
    public void testInvalidPassword() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, "invalid-password", EMAIL);
    }

    // to do
    @Test
    public void testPasswordVisibilityToggle() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(USERNAME, "test123", EMAIL);
        driver.findElement(LoginPage.PASSWORD_VISIBILITY_BUTTON).click();
    }

    @Test
    public void testEmptyEmail() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD, "");
        assert loginPage.getText(LoginPage.ERROR_MESSAGE).contains("Something went wrong. Please try again.");
    }

    @Test
    public void testNoDomainEmail() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        String emailWithoutDomain = "test@";
        loginPage.login(USERNAME, PASSWORD, emailWithoutDomain);
        String validationMessage = driver.findElement(LoginPage.EMAIL).getAttribute("validationMessage");
        assert ("Please enter a part following '@'. '" + emailWithoutDomain + "' is incomplete.")
                .equals(validationMessage);
    }

    @Test
    public void testNoLocalPartEmail() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        String emailWithoutLocalPart = "@test.com";
        loginPage.login(USERNAME, PASSWORD, emailWithoutLocalPart);
        String validationMessage = driver.findElement(LoginPage.EMAIL).getAttribute("validationMessage");
        assert ("Please enter a part followed by '@'. '" + emailWithoutLocalPart + "' is incomplete.")
                .equals(validationMessage);

    }

    @Test
    public void testNoDomainSuffixEmail() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        String emailWithoutDomainSuffix = "test@test";
        loginPage.login(USERNAME, PASSWORD, emailWithoutDomainSuffix);
        assert loginPage.getText(LoginPage.ERROR_MESSAGE).contains("Something went wrong. Please try again.");
    }

    @Test
    public void testFieldsAfterRefresh() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(USERNAME, PASSWORD, EMAIL);
        driver.navigate().refresh();

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}