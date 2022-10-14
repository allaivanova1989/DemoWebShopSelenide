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

        assertTrue(driver.findElement(RegisterPage.successfulRegistrationMessage).isDisplayed());
    }

    @Test
    public void login() {
        homePage.clickOnLogIn();
        loginPage.logIn(getProperty("emailForLogin"), getProperty("password"));

        assertTrue(driver.findElement(LoginPage.loginSuccessful).isDisplayed());
    }

    @Test
    public void addToCart() {
        homePage.clickOnLogIn();
        loginPage.logIn(getProperty("emailForLogin"), getProperty("password"));
        homePage.chooseNotebook();

        assertTrue(driver.findElement(HomePage.successAdding).isDisplayed());
        homePage.goToShopCart();

        assertTrue(shoppingCartPage.selectedProductIsExist(getProperty("modelOfNotebook")));
    }

    @Test
    public void checkFinalSumInShoppCart() {
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
        homePage.searchTheProduct(getProperty("modelOfBook"));

        assertEquals(driver.findElement(HomePage.firstFoundedProduct).getText(), getProperty("modelOfBook"));


    }
}
