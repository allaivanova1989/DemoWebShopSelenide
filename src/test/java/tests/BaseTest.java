package tests;

import config.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import pages.ShoppingCartPage;

import java.util.concurrent.TimeUnit;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    public WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;
    ShoppingCartPage shoppingCartPage;

    @BeforeMethod(description = "Setup and start browser")
    public void setup(ITestContext context) {
        log.info("Setup settings");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1600, 900));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        context.setAttribute("driver", driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        homePage.open();

    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        log.info("Close browser");
        driver.quit();


    }
}

