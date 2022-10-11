package tests;

import modals.FactoryRegisterData;
import modals.RegisterData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.PropertyReader.getProperty;

public class PositiveTest extends BaseTest {

    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    ShoppingCartPage shoppingCartPage;

    @BeforeMethod(description = "Init Pages")
    public void InitPages() {

        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);

    }

    @Test
    public void registration() {
        homePage.open();
        homePage.clickOnRegister();
        RegisterData registerdata = FactoryRegisterData.getRegisterData();
        registerPage.createRegistration(registerdata);
        registerPage.clickOnRegisterButton();

        assertTrue(driver.findElement(RegisterPage.successfulRegistrationMessage).isDisplayed());
    }

    @Test
    public void login() {
        homePage.open();
        homePage.clickOnLogIn();
        loginPage.logIn(getProperty("emailForLogin"), getProperty("password"));

        assertTrue(driver.findElement(LoginPage.loginSuccessful).isDisplayed());
    }

    @Test
    public void addToCart() {
        homePage.open();
        homePage.clickOnLogIn();
        loginPage.logIn(getProperty("emailForLogin"), getProperty("password"));
        homePage.chooseNotebook();

        assertTrue(driver.findElement(HomePage.successAdding).isDisplayed());
        homePage.goToShopCart();

        assertTrue(shoppingCartPage.selectedProductIsExist(getProperty("modelOfNotebook")));
    }

    @Test
    public void checkFinalSumInShoppCart() {
        homePage.open();
        homePage.clickOnLogIn();
        loginPage.logIn(getProperty("emailForLogin"), getProperty("password"));
        homePage.chooseNotebook();

        assertTrue(driver.findElement(HomePage.successAdding).isDisplayed());
        homePage.goToShopCart();

        assertEquals(shoppingCartPage.getPriceOfOneProduct(), HomePage.price);
        assertEquals(shoppingCartPage.getFinalSumOfProduct(), HomePage.price * shoppingCartPage.getQuantity());


    }

    @Test
    public void changeQuantityInShoppCart() {
        homePage.open();
        homePage.clickOnLogIn();
        loginPage.logIn(getProperty("emailForLogin"), getProperty("password"));
        homePage.chooseNotebook();

        assertTrue(driver.findElement(HomePage.successAdding).isDisplayed());

        homePage.goToShopCart();
        shoppingCartPage.changeQuantity();

        assertEquals(shoppingCartPage.getFinalSumOfProduct(), HomePage.price * shoppingCartPage.getQuantity());


    }

    @Test
    public void clearShoppCart() {
        homePage.open();
        homePage.clickOnLogIn();
        loginPage.logIn(getProperty("emailForLogin"), getProperty("password"));
        homePage.chooseNotebook();

        assertTrue(driver.findElement(HomePage.successAdding).isDisplayed());

        homePage.goToShopCart();
        shoppingCartPage.removeProduct();

        assertTrue(driver.findElement(ShoppingCartPage.emptyCartMessage).isDisplayed());


    }

    @Test
    public void search() {
        homePage.open();
        homePage.searchTheProduct(getProperty("modelOfBook"));

        assertEquals(driver.findElement(HomePage.firstFoundedProduct).getText(), getProperty("modelOfBook"));


    }
}
