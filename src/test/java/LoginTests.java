import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}