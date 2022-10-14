package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import modals.RegisterData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class RegisterPage extends BasePage {

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

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill in the fields for registration.")
    public void createRegistration(RegisterData registerData) {
        log.info("Fill in the fields for registration.");
        driver.findElement(genderFemale).click();
        driver.findElement(firstNameInput).sendKeys(registerData.getFirstName());
        driver.findElement(lastNameInput).sendKeys(registerData.getLastName());
        driver.findElement(emailInput).sendKeys(registerData.getEmail());
        driver.findElement(passwordInput).sendKeys(registerData.getPassword());
        driver.findElement(confirmPasswordInput).sendKeys(registerData.getConfirmPassword());

    }

    @Step("Click on register.")
    public void clickOnRegisterButton() {
        log.info("Click on registerButton.");
        driver.findElement(registerButton).click();

    }

    @Step("Check if validate message is exist")
    public boolean validateMessageInFirstNameIsExist() {
        log.info("Check if validate message is exist.");
        return driver.findElement(validateMessageFirstName).isDisplayed();

    }

    @Step("Check if validate message is exist")
    public boolean validateMessageInLastNameIsExist() {
        log.info("Check if validate message is exist.");
        return driver.findElement(validateMessageLastName).isDisplayed();

    }

    @Step("Check if validate message is exist")
    public boolean validateMessageInEmailIsExist() {
        log.info("Check if validate message is exist.");
        return driver.findElement(validateMessageEmail).isDisplayed();

    }


}
