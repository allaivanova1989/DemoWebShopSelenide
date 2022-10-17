package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Log4j2
public class ShoppingCartPage {

    public static By agreement = By.xpath("//input[@id='termsofservice']");
    public static By checkoutButton = By.xpath("//button[@id='checkout']");
    public static By quantity = By.className("qty-input");
    public static By nameOfProductLocator = By.xpath("//*[@class='product-name']");
    public static By priceOfProduct = By.xpath("//span[@class='product-unit-price']");
    public static By sumOfProduct = By.xpath("//span[@class='product-subtotal']");
    public static By checkboxRemove = By.name("removefromcart");
    public static By updateCartButton = By.cssSelector("[class='button-2 update-cart-button']");
    public static By emptyCartMessage = By.xpath("//div[contains(text(),'Your Shopping Cart is empty!')]");


    @Step("Check if the added product is in the cart.")
    public boolean selectedProductIsExist(String nameOfProduct) {
        log.info("Check if the added product is in the cart.");

        return $$(nameOfProductLocator).findBy(ownText(nameOfProduct)).isDisplayed();

    }

    @Step("Get quantity of product.")
    public int getQuantity() {
        log.info("Get quantity of product.");
        int valueOfQuantity = Integer.parseInt($(quantity).getAttribute("value"));
        return valueOfQuantity;

    }

    @Step("Change quantity of product.")
    public void changeQuantity() {
        log.info("Change quantity of product.");
        $(quantity).clear();
        $(quantity).sendKeys("2\n");

    }

    @Step("Get price of one product.")
    public float getPriceOfOneProduct() {
        log.info("Get price of one product.");
        float priceOfOneProduct = Float.parseFloat($(priceOfProduct).getText());
        return priceOfOneProduct;

    }

    @Step("Get final sum of product.")
    public float getFinalSumOfProduct() {
        log.info("Get final sum of product.");
        float finalSum = Float.parseFloat($(sumOfProduct).getText());
        return finalSum;

    }

    @Step("Delete products from shoppingCart.")
    public void removeProduct() {
        log.info("Delete products from shoppingCart.");
        $$(checkboxRemove).asFixedIterable().forEach(x -> x.click());
        $(updateCartButton).click();

    }

    @Step("Check if emptyCart message is exist.")
    public void checkEmptyCartMessage() {
        log.info("Check if emptyCart message is exist.");
        $(emptyCartMessage).shouldBe(Condition.visible);

    }
}
