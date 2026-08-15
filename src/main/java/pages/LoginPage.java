package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    public static final By USERNAME = By.id("uname");
    public static final By PASSWORD = By.id("pass");
    public static final By EMAIL = By.id("passaddon");
    public static final By LANGUAGE = By.id("selLanguage");
    public static final By LOGIN_BUTTON = By.cssSelector(".btn.btn-success");
}