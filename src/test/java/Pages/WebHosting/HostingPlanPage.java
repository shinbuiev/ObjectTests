package Pages.WebHosting;

import Objects.Plan;
import Pages.BasePage;
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

    WebHostingProduct actual = new WebHostingProduct(getProductName()+ " wrong");
    //to this page need to add methods for testing tool tips

    public WebHostingProduct getProduct() {
        return actual;
    }

    public void selectPlan(String url){
        actual = new WebHostingProduct(getProductName()+ " wrong");
        driver.findElement(By.xpath("//a[@href='" + url + "']")).click();
    }

    public String getProductName() {
        return TITLE_PRODUCT.getText();
    }

}


