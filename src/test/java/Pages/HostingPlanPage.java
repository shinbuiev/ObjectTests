package Pages;

import Objects.Plan;
import Products.WebHostingProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class HostingPlanPage extends BasePage {

    @FindBy(xpath = "//*[@id='moving_object_container']/div[1]/div/main/div/div[3]/a")
    private WebElement ECONOMY_PLAN;

    @FindBy(xpath = "//*[@id=\"moving_object\"]/div/div[2]/a")
    private WebElement PREMIUM_PLAN;

    @FindBy(xpath = "//*[@id='moving_object_container']/div[3]/div/main/div/div[2]/a/span")
    private WebElement UNLIMITED_PLAN;

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

    public HostingPlanPage(EventFiringWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    WebHostingProduct actual;
    JavascriptExecutor jse = (JavascriptExecutor) driver;

    public WebHostingProduct getProduct() {
        return actual;
    }

    public void clickBuyNowEconomyHosting() {
        actual = new WebHostingProduct(getProductName());
        actual.setProductPlan(new Plan(ECONOMY_PLAN_TEXT.getText()));
        ECONOMY_PLAN.click();
    }

    public void clickBuyNowPremiumHosting() {
        actual = new WebHostingProduct(getProductName());
        actual.setProductPlan(new Plan(PREMIUM_PLAN_TEXT.getText()));
        PREMIUM_PLAN.click();
    }

    public void clickBuyNowUnlimitedHosting() {
        actual = new WebHostingProduct(getProductName());
        actual.setProductPlan(new Plan(UNLIMITED_PLAN_TEXT.getText()));
        UNLIMITED_PLAN.click();
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


