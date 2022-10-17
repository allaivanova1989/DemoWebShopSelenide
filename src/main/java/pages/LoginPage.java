package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class LoginPage {
    public static By emailField = By.id("Email");
    public static By passwordField = By.id("Password");
    public static By loginButton = By.xpath("//*[@class='button-1 login-button']");
    public static By loginSuccessful = By.xpath("//*[@href='/customer/info'][contains(text(), 'bear345@gmail.com')]");
    public static By loginUnsuccessMessage = By.xpath("//span[contains(text(),'Login was unsuccessful.')]");


    @Step("Log in.")
    public void logIn(String email, String password) {
        log.info("Log in.");
        $(emailField).sendKeys(email);
        $(passwordField).sendKeys(password);
        $(loginButton).click();

    }

    @Step("Check if login was successful")
    public void checkIfLoginSuccessful() {
        log.info("Check if login was successful.");
        $(loginSuccessful).shouldBe(Condition.visible);

    }

    @Step("Check if login was unsuccessful")
    public void checkIfLoginUnSuccessful() {
        log.info("Check if login was unsuccessful.");
        $(loginUnsuccessMessage).shouldBe(Condition.visible);

    }
}
