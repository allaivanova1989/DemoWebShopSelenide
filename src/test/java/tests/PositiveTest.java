package tests;

import modals.FactoryRegisterData;
import modals.RegisterData;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.PropertyReader.getProperty;

public class PositiveTest extends BaseTest {
    @Test
    public void registration() {
        homePage.clickOnRegister();
        RegisterData registerdata = FactoryRegisterData.getRegisterData();
        registerPage.createRegistration(registerdata);
        registerPage.clickOnRegisterButton();
        registerPage.successfulRegistrationMessageIsExist();

    }

    @Test
    public void login() {
        homePage.clickOnLogIn();
        loginPage.logIn(getProperty("emailForLogin"), getProperty("password"));
        loginPage.checkIfLoginSuccessful();
    }

    @Test
    public void addToCart() {
        homePage.clickOnLogIn();
        loginPage.logIn(getProperty("emailForLogin"), getProperty("password"));
        homePage.chooseNotebook();
        homePage.successAddingMessageIsExist();
        homePage.goToShopCart();

        assertTrue(shoppingCartPage.selectedProductIsExist(getProperty("modelOfNotebook")));
    }

    @Test
    public void checkFinalSumInShoppCart() {
        homePage.clickOnLogIn();
        loginPage.logIn(getProperty("emailForLogin"), getProperty("password"));
        homePage.chooseNotebook();
        homePage.successAddingMessageIsExist();
        homePage.goToShopCart();

        assertEquals(shoppingCartPage.getPriceOfOneProduct(), HomePage.price);
        assertEquals(shoppingCartPage.getFinalSumOfProduct(), HomePage.price * shoppingCartPage.getQuantity());


    }

    @Test
    public void changeQuantityInShoppCart() {
        homePage.clickOnLogIn();
        loginPage.logIn(getProperty("emailForLogin"), getProperty("password"));
        homePage.chooseNotebook();
        homePage.successAddingMessageIsExist();
        homePage.goToShopCart();
        shoppingCartPage.changeQuantity();
        assertEquals(shoppingCartPage.getFinalSumOfProduct(), HomePage.price * shoppingCartPage.getQuantity());


    }

    @Test
    public void clearShoppCart() {
        homePage.clickOnLogIn();
        loginPage.logIn(getProperty("emailForLogin"), getProperty("password"));
        homePage.chooseNotebook();
        homePage.successAddingMessageIsExist();
        homePage.goToShopCart();
        shoppingCartPage.removeProduct();
        shoppingCartPage.checkEmptyCartMessage();


    }

    @Test
    public void search() {
        homePage.searchTheProduct(getProperty("modelOfBook"));
        assertEquals(homePage.getTextOfFirstFoundProduct(), getProperty("modelOfBook"));

    }
}
