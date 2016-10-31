package Pages.WebHosting;

import Objects.Plan;
import Objects.Product;
import Pages.BasePage;
import Products.WebHostingProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class HostingPlanPage extends BasePage {
    private WebDriver driver;

    @FindBy(xpath = ".//*[@id='moving_object_container']/div[1]/div/main/div/div[3]/a")
    private WebElement economyButton;
    @FindBy(xpath = ".//*[@id='moving_object']/div/div[2]/a")
    private WebElement premiumButton;
    @FindBy(xpath = ".//*[@id='moving_object_container']/div[3]/div/main/div/div[2]/a")
    private WebElement unlimittedButton;

    @FindBy(xpath = "//*[@id='moving_object_container']/div[1]/div/header/div[1]")
    private WebElement ECONOMY_PLAN_TEXT;

    @FindBy(xpath = "//*[@id='moving_object_container']/div[2]/div/header/div[1]")
    private WebElement PREMIUM_PLAN_TEXT;

    @FindBy(xpath = "//*[@id='moving_object_container']/div[3]/div/header/div[1]")
    private WebElement UNLIMITED_PLAN_TEXT;



    @FindBy(xpath = "//*[@id='moving_object_container']/div[2]/div/div/ul[2]/li[13]/span[1]")
    private WebElement LAST_ELEMENT_OF_THE_TABLE;


    public HostingPlanPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        System.out.println("Hosting plan page create ");
    }

    WebHostingProduct actual;

    public WebHostingProduct getProduct() {
        return actual;
    }

    public HostingOrderPage selectPlan(String url){
        actual = new WebHostingProduct(getProductName());

        // it will be change later
        if (url.contains("economy")){
            actual.setProductPlan(new Plan(ECONOMY_PLAN_TEXT.getText()));
            economyButton.click();
        }
        if (url.contains("premium")){
            actual.setProductPlan(new Plan(PREMIUM_PLAN_TEXT.getText()));
            premiumButton.click();
        }
        if (url.contains("unlimited")){
            actual.setProductPlan(new Plan(UNLIMITED_PLAN_TEXT.getText()));
            unlimittedButton.click();
        }

        System.out.println("Select plan");
        return new HostingOrderPage(driver);
    }

    public String getProductName() {
        return driver.getTitle();
    }




}


