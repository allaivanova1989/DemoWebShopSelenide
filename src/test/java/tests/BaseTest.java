package tests;

import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.logevents.SelenideLogger;
import lombok.extern.log4j.Log4j2;

import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import pages.ShoppingCartPage;
import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class BaseTest {

    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;
    ShoppingCartPage shoppingCartPage;

    @BeforeMethod(description = "Setup and start browser")
    public void setup() {
        log.info("Setup settings");
//        Configuration.headless = true;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demowebshop.tricentis.com";

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));

        Configuration.timeout = 10000;

        homePage = new HomePage();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        shoppingCartPage = new ShoppingCartPage();

        open("");


    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        log.info("Close browser");
        getWebDriver().quit();

    }
}

