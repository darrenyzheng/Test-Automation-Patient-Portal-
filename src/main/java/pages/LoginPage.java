package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public static final By USERNAME = By.id("uname");
    public static final By PASSWORD = By.id("pass");
    public static final By EMAIL = By.id("passaddon");
    public static final By LOGIN_BUTTON = By.cssSelector(".btn.btn-success");
    public static final By PASSWORD_VISIBILITY_BUTTON = By.id("password-icon");
    public static final By ERROR_MESSAGE = By.cssSelector(".h6.alert");
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String userName, String password, String email) {
        this.fillLoginForm(userName, password, email);
        this.click(LOGIN_BUTTON);
    }

    public void fillLoginForm(String username, String password, String email) {
        this.type(USERNAME, username);
        this.type(PASSWORD, password);
        this.type(EMAIL, email);
    }
}