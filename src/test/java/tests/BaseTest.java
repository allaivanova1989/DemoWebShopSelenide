package tests;

import config.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    public WebDriver driver;


    @BeforeMethod(description = "Setup and start browser")
    public void setup(ITestContext context) {
        log.info("Setup settings");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        context.setAttribute("driver", driver);

    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        log.info("Close browser");
        driver.quit();


    }
}

