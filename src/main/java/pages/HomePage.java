package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@Log4j2
public class HomePage extends BasePage {

    public static String modelOfNotebook;
    public static float price;
    public static By registerButton = By.cssSelector(".ico-register");
    public static By logInButton = By.cssSelector(".ico-login");
    public static By shoppingCart = By.xpath("//span[contains(text(),'Shopping cart')]");
    public static By sectionNotebooks = By.xpath("//ul[@class='sublist']/li/*[contains(text(), 'Notebooks')]");
    public static By nameOfNotebook = By.xpath("//div[@class='product-item']/div[@class='details']/h2/a");
    public static By priceOfNotebook = By.xpath("//div[@class='product-item']/div[@class='details']/div[@class='add-info']/div[@class='prices']/span");
    public static By addToCartButton = By.xpath("//div[@class='product-item']/div[@class='details']/div[@class='add-info']/div[@class='buttons']/input");
    public static By sectionComputers = By.xpath("(//*[@href='/computers' ][contains(text(), 'Computers')])[1]");
    public static By successAdding = By.cssSelector("[class='bar-notification success']");
    public static By searchField = By.xpath("//input[@id='small-searchterms']");
    public static By searchButton = By.xpath("//input[@value='Search']");
    public static By firstFoundedProduct = By.xpath("//div[@class='product-item']/div[@class='details']/h2/a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open site")
    public void open() {
        log.info("Open site");
        driver.get("https://demowebshop.tricentis.com/");
    }

    @Step("Click on register.")
    public void clickOnRegister() {
        log.info("Click on register.");
        driver.findElement(registerButton).click();
    }

    @Step("Click on log in.")
    public void clickOnLogIn() {
        log.info("Click on log in.");
        driver.findElement(logInButton).click();
    }

    @Step("Choose notebook.")
    public void chooseNotebook() {
        log.info("Choose notebook.");
        driver.findElement(sectionComputers).click();
        driver.findElement(sectionNotebooks).click();
        modelOfNotebook = driver.findElement(nameOfNotebook).getText();
        price = Float.parseFloat(driver.findElement(priceOfNotebook).getText());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(addToCartButton);
        js.executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(addToCartButton).click();

    }

    @Step("Go to shopping cart.")
    public void goToShopCart() {
        log.info("Go to shopping cart.");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(shoppingCart);
        js.executeScript("arguments[0].scrollIntoView();", element);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(successAdding));
        driver.findElement(shoppingCart).click();

    }

    @Step("Search product.")
    public void searchTheProduct(String nameOfProduct) {
        log.info("Search the product.");
        driver.findElement(searchField).sendKeys(nameOfProduct);
        driver.findElement(searchButton).click();

    }

}
