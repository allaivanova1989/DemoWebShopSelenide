package tests;

import modals.FactoryRegisterData;
import modals.RegisterData;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertTrue;
import static utils.PropertyReader.getProperty;

public class NegativeTest extends BaseTest {

    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;

    @BeforeMethod(description = "Init Pages")
    public void InitPages() {

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }


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
        homePage.open();
        homePage.clickOnLogIn();
        loginPage.logIn(email, password);

        assertTrue(driver.findElement(LoginPage.loginUnSuccessMessage).isDisplayed());
    }

    @Test(dataProvider = "incorrectLoginData")
    public void loginWithData(String email, String password) {
        homePage.open();
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
        homePage.open();
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
        homePage.open();
        homePage.clickOnRegister();
        RegisterData registerdata = FactoryRegisterData.getRegisterDataWithDigitsInFirstName();
        registerPage.createRegistration(registerdata);

        assertTrue(driver.findElement(By.xpath(String.format(RegisterPage.validateMessage, "FirstName"))).isDisplayed());
    }

    @Test
    public void registerWithDigitsInLastName() {
        homePage.open();
        homePage.clickOnRegister();
        RegisterData registerdata = FactoryRegisterData.getRegisterDataWithDigitsInLastName();
        registerPage.createRegistration(registerdata);

        assertTrue(driver.findElement(By.xpath(String.format(RegisterPage.validateMessage, "LastName"))).isDisplayed());
    }

    @Test
    public void registerWithSpecSymbolsInFirstName() {
        homePage.open();
        homePage.clickOnRegister();
        RegisterData registerdata = FactoryRegisterData.getRegisterDataWithSpecSymbolsInFirstName();
        registerPage.createRegistration(registerdata);

        assertTrue(driver.findElement(By.xpath(String.format(RegisterPage.validateMessage, "FirstName"))).isDisplayed());
    }

    @Test
    public void registerWithSpecSymbolsInLastName() {
        homePage.open();
        homePage.clickOnRegister();
        RegisterData registerdata = FactoryRegisterData.getRegisterDataWithSpecSymbolsInLastName();
        registerPage.createRegistration(registerdata);

        assertTrue(driver.findElement(By.xpath(String.format(RegisterPage.validateMessage, "LastName"))).isDisplayed());
    }

    @Test
    public void registerWithoutSymbolInEmail() {
        homePage.open();
        homePage.clickOnRegister();
        RegisterData registerdata = FactoryRegisterData.getRegisterDataWithoutSymbolInEmail();
        registerPage.createRegistration(registerdata);

        assertTrue(driver.findElement(By.xpath(String.format(RegisterPage.validateMessage, "Email"))).isDisplayed());
    }
}