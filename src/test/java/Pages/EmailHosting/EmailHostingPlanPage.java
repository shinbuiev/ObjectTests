package Pages.EmailHosting;

import Objects.Plan;
import Objects.Product;
import Pages.BasePage;
import Products.EmailHostingProduct;
import Products.SSLProduct;
import Products.WebHostingProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by geser on 31.10.16.
 */
public class EmailHostingPlanPage extends BasePage{

    @FindBy(xpath = "//*[@id='moving_object_container']/div[1]/div/header/div[1]")
    private WebElement ECONOMY_PLAN_TEXT;

    @FindBy(xpath = "//*[@id='moving_object_container']/div[2]/div/header/div[1]")
    private WebElement PREMIUM_PLAN_TEXT;

    @FindBy(xpath = "//*[@id='moving_object_container']/div[3]/div/header/div[1]")
    private WebElement UNLIMITED_PLAN_TEXT;

    @FindBy(xpath = "//*[@class='title-h1']")
    private WebElement TITLE_PRODUCT;

    @FindBy(xpath = "//*[@id='moving_object_container']/div[2]/div/div/ul[2]/li[13]/span[1]")
    private WebElement LAST_ELEMENT_OF_THE_TABLE;

    private static final By MORE_LESS_BUTTON = By.xpath("//*[@id='moving_object_container']/div[4]/div/a");
    private static final By ECONOMY_HOSTING_BUTTON_AT_THE_BOTTOM = By.xpath("//*[@id='moving_object_container']/div[1]/div/div/ul[2]/footer/div/span/a");
    private static final By PREMIUM_HOSTING_BUTTON_AT_THE_BOTTOM = By.xpath("//*[@id='moving_object_container']/div[2]/div/div/ul[2]/footer/div/span/a");
    private static final By UNLIMITED_HOSTING_BUTTON_AT_THE_BOTTOM = By.xpath("//*[@id='moving_object_container']/div[3]/div/div/ul[2]/footer/div/span/a");

    protected EventFiringWebDriver driver;

    public EmailHostingPlanPage(EventFiringWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    EmailHostingProduct actual;
    JavascriptExecutor jse = (JavascriptExecutor) driver;

    public Product getProduct() {
        return actual;
    }

    public void selectPlan(String url){
        actual = new EmailHostingProduct(getProductName());

        // it will be change later
        if (url.contains("personal")){
            actual.setProductPlan(new Plan(ECONOMY_PLAN_TEXT.getText()));
        }
        if (url.contains("group")){
            actual.setProductPlan(new Plan(PREMIUM_PLAN_TEXT.getText()));
        }
        if (url.contains("unlimited")){
            actual.setProductPlan(new Plan(UNLIMITED_PLAN_TEXT.getText()));
        }
        driver.findElement(By.xpath("//a[@href='" + url + "']")).click();
    }

    public String getProductName() {
        return TITLE_PRODUCT.getText();
    }


    public String getUrlEconomyHostingButtonAtTheBottom() {
        return driver.findElement(ECONOMY_HOSTING_BUTTON_AT_THE_BOTTOM).getAttribute("href");
    }

    public String getUrlPremiumHostingButtonAtTheBottom() {
        return driver.findElement(PREMIUM_HOSTING_BUTTON_AT_THE_BOTTOM).getAttribute("href");
    }

    public String getUrlUnlimitedHostingButtonAtTheBottom() {
        return driver.findElement(UNLIMITED_HOSTING_BUTTON_AT_THE_BOTTOM).getAttribute("href");
    }

    public void clickMoreLessButton() {
        driver.findElement(MORE_LESS_BUTTON).click();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }


    public String getLastTableElementText() {
        jse.executeScript("scroll(0, 1300);");
        return LAST_ELEMENT_OF_THE_TABLE.getText();
    }

}
