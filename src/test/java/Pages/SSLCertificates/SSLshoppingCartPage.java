package Pages.SSLCertificates;

import Objects.*;
import Pages.BasePage;
import Products.SSLproduct;
import Products.WebHostingProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;

/**
 * Created by Sergiy.K on 25-Oct-16.
 */
public class SSLshoppingCartPage extends BasePage {

    private static final By firstElement = By.xpath("//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div/div[1]/div/strong");
    private static final By TOTAL_PRICE = By.xpath("//*[@id='domain_table_total']");

    private static final By PRODUCT_TERMS_LIST = By.xpath("//*[@class='menuSelect']/div[1]/a/span[@class='captionTextReal']");
    private static final By PRODUCT_LIST = By.xpath("//*[@class='item-name']/strong");

    @FindBy(xpath = "//*[@id='all']/div[4]/div[2]/div[1]/div/div[1]/div/div[1]/span/a")
    private WebElement cartClick;

    @FindBy(xpath = "//*[@id=\"shopping_cart_table\"]/div[1]/div[2]/div[1]/div[2]/div[1]/div/div[1]/div[1]/a")
    private WebElement productTerm;

    @FindBy(xpath = "//*[@id='shopping_cart_table']/div[1]/div[2]/div/div[1]/div/div/div")
    private WebElement productDomain;

    @FindBy(xpath = "//*[@id='shopping_cart_table']/div[1]/div[2]/div/div[1]/div/strong")
    private WebElement productTitle;


    private SSLproduct actual;
    protected EventFiringWebDriver driver;

    public SSLshoppingCartPage(EventFiringWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    int count = 0;

    public int getProductCount() {
        return driver.findElements(PRODUCT_LIST).size();
    }

    public String getPrice() {
        return driver.findElement(TOTAL_PRICE).getText();
    }

    public void clickCart() {
        cartClick.click();
        actual = new SSLproduct(getProductName());
        actual.setProductDomain(new Domain(getProductDomain()));
        actual.setProductPrice(new Price(getPrice()));
        actual.setProductTerm(new Term(productTerm.getText()));
        actual.setProductPlan(new Plan(getProductPlan(), new Term(productTerm.getText())));
    }

    public String getProductName() {
        return productTitle.getText().split(" ")[1] + productTitle.getText().split(" ")[2];
    }

    public String getProductDomain() {
        return productDomain.getText();
    }

    public String getProductPlan() {
        return productTitle.getText().split(" ")[0];// last 7 symbols
    }

    public SSLproduct getProduct() {
        actual.takeScreenshot();
        return actual;
    }

    public void productToString() {
        System.out.println("Actual product in Shopping Cart: " + actual.getProductName() + " Domain name: " +
                actual.getProductDomain() + ", selected plan:  " + actual.getProductPlan() + " for  " + actual.getProductPlan().getTerm() + " ");
        System.out.println("Total price is: " + actual.getProductPrice().getPrice());
    }

}
