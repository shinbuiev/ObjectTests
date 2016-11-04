package Pages.WebHosting;

import Interfaces.ExpectedProducts.EventFiringWebDriverWrapper;
import Objects.*;
import Pages.BasePage;
import Products.WebHostingProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;

/**
 * Created by Sergiy.K on 25-Oct-16.
 */
public class HostingShoppingCartPage extends BasePage {
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


    @FindBy(xpath = "//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div[5]/div[1]/span")
    private WebElement fourthAddonName;

    @FindBy(xpath = "//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div[5]/div[2]/div/div/div[1]/div[1]/a/span")
    private WebElement fourthAddonTerm;

    @FindBy(xpath = "//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div[6]/div[1]/span")
    private WebElement fifthAddonName;

    @FindBy(xpath = "//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div[6]/div[2]/div/div/div[1]/div[1]/a/span")
    private WebElement fifthAddonTerm;

    @FindBy(xpath = "//a[@href='confirm_delete']")
    private WebElement clearShoppingCartButton;

    @FindBy(xpath = "//*[@id='cart_reset']")
    private WebElement confirmClearCart;

    @FindBy(xpath = "//strong[@class = 'cuteText']")
    private WebElement PRODUCTS_NAME_LIST;

    @FindBy(xpath = "//strong[@class = 'cuteText']")
    private WebElement PRODUCTS_TERM_LIST;

    //   //div[contains(@class, 'table-upgrade')]/div/span[starts-with(@class,'upgrade-title')]  it's equals
    @FindBy(xpath = "//span[starts-with(@class,'upgrade-title')]")
    private WebElement ADDONS_NAME_LIST;

    @FindBy(xpath = "(//div[contains(@class, 'table-upgrade')])/div[2]/*/select/option/")
    private WebElement ADDONS_TERM_LIST;


    private WebHostingProduct actual;
    protected EventFiringWebDriverWrapper driver;

    public HostingShoppingCartPage(EventFiringWebDriverWrapper driver) {
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
        actual = new WebHostingProduct(getProductName());
        actual.setProductDomain(new Domain(getProductDomain()));
        actual.setProductPrice(new Price(getPrice()));
        actual.setProductTerm(new Term(productTerm.getText()));
        actual.setProductPlan(new Plan(getProductPlan(), new Term(productTerm.getText())));
        ArrayList<Addon> addons = new ArrayList<Addon>();
        addons.add(new Addon(firstAddonName.getText(), new Term(firstAddonTerm.getText())));
        addons.add(new Addon(secondAddonName.getText(), new Term(secondAddonTerm.getText())));
        addons.add(new Addon(thirdAddonName.getText(), new Term(thirdAddonTerm.getText())));
        addons.add(new Addon(fourthAddonName.getText(), new Term(fourthAddonTerm.getText())));
        addons.add(new Addon(fifthAddonName.getText(), new Term(fifthAddonTerm.getText())));
        actual.setProductAddons(addons);
    }

    public String getProductName() {
        return PRODUCTS_NAME_LIST.getText().split(" - ")[0];
    }

    public String getProductDomain() {
        return productDomain.getText();
    }

    public String getProductPlan() {
        return PRODUCTS_NAME_LIST.getText().split(" - ")[1];// last 7 symbols
    }

    Product testShop;
    public WebHostingProduct getProduct() {
        testShop = new WebHostingProduct(getProductName());


        return actual;
    }

    public void clearShoppingCart(){
//        clearShoppingCartButton.clickOnPage();
//        confirmClearCart.clickOnPage();
    }
}
