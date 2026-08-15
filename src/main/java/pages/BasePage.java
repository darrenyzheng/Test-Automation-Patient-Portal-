package pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.Duration;

public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click(By locator) {
        WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.click();
    }

    public void type(By locator, String text) {
        WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(text);
    }

    public String getText(By locator) {
        WebElement element = this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }
}