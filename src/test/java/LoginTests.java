import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

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
    }

    @Test
    public void testWrongFormatEmail() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD, "invalid-email");
    }

    @Test
    public void testEmptyUsername() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", PASSWORD, EMAIL);
    }

    @Test
    public void testEmptyPassword() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, "", EMAIL);
    }

    @Test
    public void testEmptyEmail() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(USERNAME, PASSWORD, "");
    }

    @Test



    @AfterEach
    void tearDown() {
        driver.quit();
    }
}