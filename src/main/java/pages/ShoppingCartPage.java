package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ShoppingCartPage extends BasePage {

    public static By agreement = By.xpath("//input[@id='termsofservice']");
    public static By checkoutButton = By.xpath("//button[@id='checkout']");
    public static By quantity = By.className("qty-input");
    public static By nameOfProductLocator = By.xpath("//*[@class='product-name']");
    public static By priceOfProduct = By.xpath("//span[@class='product-unit-price']");
    public static By sumOfProduct = By.xpath("//span[@class='product-subtotal']");
    public static By checkboxRemove = By.name("removefromcart");
    public static By updateCartButton = By.cssSelector("[class='button-2 update-cart-button']");
    public static By emptyCartMessage = By.xpath("//div[contains(text(),'Your Shopping Cart is empty!')]");


    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check if the added product is in the cart.")
    public boolean selectedProductIsExist(String nameOfProduct) {
        log.info("Check if the added product is in the cart.");
        return driver.findElements(nameOfProductLocator)
                .stream().anyMatch(webElement -> webElement.getText().equalsIgnoreCase(nameOfProduct));
    }

    @Step("Get quantity of product.")
    public int getQuantity() {
        log.info("Get quantity of product.");
        int valueOfQuantity = Integer.parseInt(driver.findElement(quantity).getAttribute("value"));
        return valueOfQuantity;

    }

    @Step("Change quantity of product.")
    public void changeQuantity() {
        log.info("Change quantity of product.");
        driver.findElement(quantity).clear();
        driver.findElement(quantity).sendKeys("2\n");

    }

    @Step("Get price of one product.")
    public float getPriceOfOneProduct() {
        log.info("Get price of one product.");
        float priceOfOneProduct = Float.parseFloat(driver.findElement(priceOfProduct).getText());
        return priceOfOneProduct;

    }

    @Step("Get final sum of product.")
    public float getFinalSumOfProduct() {
        log.info("Get final sum of product.");
        float finalSum = Float.parseFloat(driver.findElement(sumOfProduct).getText());
        return finalSum;

    }

    @Step("Delete products from shoppingCart.")
    public void removeProduct() {
        log.info("Delete products from shoppingCart.");
        driver.findElements(checkboxRemove).forEach(x -> x.click());
        driver.findElement(updateCartButton).click();

    }
}
