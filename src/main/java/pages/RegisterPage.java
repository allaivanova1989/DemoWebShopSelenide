package pages;

import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import modals.RegisterData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class RegisterPage extends BasePage {

    public static By genderFemale = By.id("gender-female");
    public static By registerButton = By.id("register-button");
    public static By successfulRegistrationMessage = By.xpath("//*[@class='result']");
    public static String validateMessage = "//span[@for='%s']";

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill in the fields for registration.")
    public void createRegistration(RegisterData registerData) {
        log.info("Fill in the fields for registration.");
        driver.findElement(genderFemale).click();
        new Input(driver, "First name").write(registerData.getFirstName());
        new Input(driver, "Last name").write(registerData.getLastName());
        new Input(driver, "Email").write(registerData.getEmail());
        new Input(driver, "Password").write(registerData.getPassword());
        new Input(driver, "Confirm password").write(registerData.getConfirmPassword());


    }

    @Step("Click on register.")
    public void clickOnRegisterButton() {
        log.info("Click on registerButton.");
        driver.findElement(registerButton).click();

    }
}
