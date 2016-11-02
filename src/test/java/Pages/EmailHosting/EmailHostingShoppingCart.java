package Pages.EmailHosting;

import Objects.*;
import Pages.BasePage;
import Products.EmailHostingProduct;
import Products.WebHostingProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;

/**
 * Created by geser on 31.10.16.
 */
public class EmailHostingShoppingCart extends BasePage{
    private static final By cartClick = By.xpath("//*[@id=\"all\"]/div[4]/div[2]/div[1]/div/div[1]/div/div[1]/span/a");
    private static final By firstElement = By.xpath("//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div/div[1]/div/strong");
    private static final By TOTAL_PRICE = By.xpath("//*[@id='domain_table_total']");

    private static final By PRODUCT_TERMS_LIST = By.xpath("//*[@class='menuSelect']/div[1]/a/span[@class='captionTextReal']");
    private static final By PRODUCT_LIST = By.xpath("//*[@class='item-name']/strong");

    @FindBy(xpath = "//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div[1]/div[2]/div[1]/div/div[1]/div[1]/a")
    private WebElement productTerm;

    @FindBy(xpath = "//*[@class='item-name']/div[@class='item-desc']/div")
    private WebElement productDomain;


    @FindBy(xpath = "//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div[2]/div[1]/span")
    private WebElement firstAddonName;

    @FindBy(xpath = "//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div[2]/div[2]/div[1]/div/div[1]/div[1]/a")
    private WebElement firstAddonTerm;

    @FindBy(xpath = "//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div[3]/div[1]/span")
    private WebElement secondAddonName;
    @FindBy(xpath = "//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div[1]/div[2]/div[1]/div/div[1]/div[1]/a")
    private WebElement secondAddonTerm;

    @FindBy(xpath = "//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div[4]/div[1]/span")
    private WebElement thirdAddonName;

    @FindBy(xpath = "//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div[4]/div[2]/div/div/div[1]/div[1]/a/span")
    private WebElement thirdAddonTerm;

    @FindBy(xpath = "//a[@href='confirm_delete']")
    private WebElement clearShoppingCartButton;

    @FindBy(xpath = "//*[@id='cart_reset']")
    private WebElement confirmClearCart;

    private EmailHostingProduct actual;
    protected EventFiringWebDriver driver;

    public EmailHostingShoppingCart(EventFiringWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    int count = 0;

    public void addAddon(String productName) {
        for (int i = 0; i < getProductCount(); i++) {
            if (productName.equals(driver.findElements(PRODUCT_LIST).get(i).getText())) {
                {
                    driver.findElements(PRODUCT_LIST).get(i).getText();
                    count = i;
                }
            }
        }
    }


    public int getProductCount() {
        return driver.findElements(PRODUCT_LIST).size();
    }

    public String getPrice() {
        return driver.findElement(TOTAL_PRICE).getText();
    }

    public void clickCart() {
        driver.findElement(cartClick).click();
        actual = new EmailHostingProduct(getProductName());
        actual.setProductDomain(new Domain(getProductDomain()));
        actual.setProductPrice(new Price(getPrice()));
        actual.setProductTerm(new Term(productTerm.getText()));
        actual.setProductPlan(new Plan(getProductPlan(), new Term(productTerm.getText())));
        ArrayList<Addon> addons = new ArrayList<Addon>();
        addons.add(new Addon(firstAddonName.getText(), new Term(firstAddonTerm.getText())));
//        addons.add(new Addon(secondAddonName.getText(), new Term(secondAddonTerm.getText())));
//        addons.add(new Addon(thirdAddonName.getText(), new Term(thirdAddonTerm.getText())));
        actual.setProductAddons(addons);
    }

    public String getProductName() {
        return driver.findElement(firstElement).getText();
    }

    public String getProductDomain() {
        return productDomain.getText();
    }

    public String getProductPlan() {
        return driver.findElement(firstElement).getText();// last 7 symbols
    }

    public EmailHostingProduct getProduct() {
        return actual;
    }

    public void clearShoppingCart(){
//        clearShoppingCartButton.clickOnPage();
//        confirmClearCart.clickOnPage();
    }
}
