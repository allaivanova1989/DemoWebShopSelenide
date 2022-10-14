package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {
    public static By emailField = By.id("Email");
    public static By passwordField = By.id("Password");
    public static By loginButton = By.xpath("//*[@class='button-1 login-button']");
    public static By loginSuccessful = By.xpath("//*[@href='/customer/info'][contains(text(), 'bear345@gmail.com')]");
    public static By loginUnSuccessMessage = By.xpath("//span[contains(text(),'Login was unsuccessful.')]");

    public LoginPage(WebDriver driver) {
        super(driver);

    }

    @Step("Log in.")
    public void logIn(String email, String password) {
        log.info("Log in.");
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();

    }
}
