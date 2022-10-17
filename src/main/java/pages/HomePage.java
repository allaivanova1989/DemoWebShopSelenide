package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class HomePage {


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


    @Step("Click on register.")
    public void clickOnRegister() {
        log.info("Click on register.");
        $(registerButton).click();
    }

    @Step("Click on log in.")
    public void clickOnLogIn() {
        log.info("Click on log in.");
        $(logInButton).click();
    }

    @Step("Choose notebook.")
    public void chooseNotebook() {
        log.info("Choose notebook.");
        $(sectionComputers).click();
        $(sectionNotebooks).click();
        modelOfNotebook = $(nameOfNotebook).getText();
        price = Float.parseFloat($(priceOfNotebook).getText());
        $(addToCartButton).click();

    }

    @Step("Go to shopping cart.")
    public void goToShopCart() {
        log.info("Go to shopping cart.");
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 15);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(successAdding));
        $(shoppingCart).click();


    }

    @Step("Search product.")
    public void searchTheProduct(String nameOfProduct) {
        log.info("Search the product.");
        $(searchField).sendKeys(nameOfProduct);
        $(searchButton).click();

    }

    @Step("Check if successAdding message is exist")
    public void successAddingMessageIsExist() {
        log.info("Check if successAdding message is exist.");
        $(successAdding).shouldBe(Condition.visible);

    }

    @Step("Get text of first found product.")
    public String getTextOfFirstFoundProduct() {
        log.info("Get text of first found product.");
        return $(firstFoundedProduct).getText();

    }


}
