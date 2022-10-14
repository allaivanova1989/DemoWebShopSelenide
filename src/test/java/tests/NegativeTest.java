package tests;

import modals.FactoryRegisterData;
import modals.RegisterData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertTrue;
import static utils.PropertyReader.getProperty;

public class NegativeTest extends BaseTest {

    @DataProvider(name = "incorrectLoginData")
    public Object[][] incorrectLoginData() {
        return new Object[][]{
                {"", getProperty("password")},
                {getProperty("emailForLogin"), ""},
                {getProperty("emailForLogin"), "123456789"},
                {"planeta@gmail.com", getProperty("password")},
        };
    }

    @Test(dataProvider = "incorrectLoginData")
    public void loginWithIncorrectData(String email, String password) {
        homePage.clickOnLogIn();
        loginPage.logIn(email, password);

        assertTrue(driver.findElement(LoginPage.loginUnSuccessMessage).isDisplayed());
    }


    @DataProvider(name = "emptyFieldRegisterData")
    public Object[][] emptyFieldRegisterData() {
        return new Object[][]{
                {"", "Ivanova", "bear@gmail.com", "2986783DF", "2986783DF"},
                {"Ala", "", "bear@gmail.com", "2986783DF", "2986783DF"},
                {"Ala", "Ivanova", "", "2986783DF", "2986783DF"},
                {"Ala", "Ivanova", "bear@gmail.com", "", "2986783DF"},
                {"Ala", "Ivanova", "bear@gmail.com", "2986783DF", ""},

        };
    }

    @Test(dataProvider = "emptyFieldRegisterData")
    public void checkingButtonRegisterIsNotClickableWithoutOneField(String firstName, String lastName, String email, String password, String confirmPassword) {
        homePage.clickOnRegister();

        RegisterData registerData = RegisterData.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .confirmPassword(confirmPassword)
                .build();

        registerPage.createRegistration(registerData);
        registerPage.clickOnRegisterButton();

        assertTrue(driver.findElements(RegisterPage.successfulRegistrationMessage).isEmpty());
    }


    @Test
    public void registerWithDigitsInFirstName() {
        homePage.clickOnRegister();
        RegisterData registerdata = FactoryRegisterData.getRegisterDataWithDigitsInFirstName();
        registerPage.createRegistration(registerdata);

        assertTrue(registerPage.validateMessageInFirstNameIsExist());
    }

    @Test
    public void registerWithDigitsInLastName() {
        homePage.clickOnRegister();
        RegisterData registerdata = FactoryRegisterData.getRegisterDataWithDigitsInLastName();
        registerPage.createRegistration(registerdata);

        assertTrue(registerPage.validateMessageInLastNameIsExist());
    }

    @Test
    public void registerWithSpecSymbolsInFirstName() {
        homePage.clickOnRegister();
        RegisterData registerdata = FactoryRegisterData.getRegisterDataWithSpecSymbolsInFirstName();
        registerPage.createRegistration(registerdata);

        assertTrue(registerPage.validateMessageInFirstNameIsExist());
    }

    @Test
    public void registerWithSpecSymbolsInLastName() {
        homePage.clickOnRegister();
        RegisterData registerdata = FactoryRegisterData.getRegisterDataWithSpecSymbolsInLastName();
        registerPage.createRegistration(registerdata);

        assertTrue(registerPage.validateMessageInLastNameIsExist());
    }

    @Test
    public void registerWithoutSymbolInEmail() {
        homePage.clickOnRegister();
        RegisterData registerdata = FactoryRegisterData.getRegisterDataWithoutSymbolInEmail();
        registerPage.createRegistration(registerdata);

        assertTrue(registerPage.validateMessageInEmailIsExist());
    }
}