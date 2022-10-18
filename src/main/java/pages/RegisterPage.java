package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import modals.RegisterData;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Log4j2
public class RegisterPage {

    public static By genderFemale = By.id("gender-female");
    public static By registerButton = By.id("register-button");
    public static By firstNameInput = By.id("FirstName");
    public static By lastNameInput = By.id("LastName");
    public static By emailInput = By.id("Email");
    public static By passwordInput = By.id("Password");
    public static By confirmPasswordInput = By.id("ConfirmPassword");
    public static By successfulRegistrationMessage = By.xpath("//*[@class='result']");
    public static By validateMessageFirstName = By.xpath("//span[@for='FirstName']");
    public static By validateMessageLastName = By.xpath("//span[@for='LastName']");
    public static By validateMessageEmail = By.xpath("//span[@for='Email']");

    @Step("Fill in the fields for registration.")
    public void createRegistration(RegisterData registerData) {
        log.info("Fill in the fields for registration.");
        $(genderFemale).click();
        $(firstNameInput).sendKeys(registerData.getFirstName());
        $(lastNameInput).sendKeys(registerData.getLastName());
        $(emailInput).sendKeys(registerData.getEmail());
        $(passwordInput).sendKeys(registerData.getPassword());
        $(confirmPasswordInput).sendKeys(registerData.getConfirmPassword());

    }

    @Step("Click on register.")
    public void clickOnRegisterButton() {
        log.info("Click on registerButton.");
        $(registerButton).click();

    }

    @Step("Check if validate message is exist")
    public void validateMessageInFirstNameIsExist() {
        log.info("Check if validate message is exist.");
        $(validateMessageFirstName).shouldBe(Condition.visible);
    }

    @Step("Check if validate message is exist")
    public void validateMessageInLastNameIsExist() {
        log.info("Check if validate message is exist.");
        $(validateMessageLastName).shouldBe(Condition.visible);

    }

    @Step("Check if validate message is exist")
    public void validateMessageInEmailIsExist() {
        log.info("Check if validate message is exist.");
        $(validateMessageEmail).shouldBe(Condition.visible);

    }


    @Step("Check if successfulRegistrationMessage is exist")
    public void successfulRegistrationMessageIsExist() {
        log.info("Check if successfulRegistrationMessage is exist.");
        $(successfulRegistrationMessage).shouldBe(Condition.visible);

    }

    @Step("Check if successfulRegistrationMessage isn't exist")
    public void successfulRegistrationMessageIsNotExist() {
        log.info("Check if successfulRegistrationMessage isn't exist.");
        $$(successfulRegistrationMessage).shouldBe(empty);

    }
}
