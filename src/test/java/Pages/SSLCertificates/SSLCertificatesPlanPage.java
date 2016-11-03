package Pages.SSLCertificates;

import Interfaces.ExpectedProducts.EventFiringWebDriverWrapper;
import Objects.Plan;
import Pages.BasePage;
import Products.SSLProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by geser on 27.10.16.
 */
public class SSLCertificatesPlanPage extends BasePage{

    @FindBy(xpath = "//*[@id='moving_object_container']/div[1]/div/header/div[1]")
    private WebElement STANDARD_PLAN_TEXT;

    @FindBy(xpath = "//*[@id='moving_object_container']/div[2]/div/header/div[1]")
    private WebElement PREMIUM_PLAN_TEXT;

    @FindBy(xpath = "//*[@id='moving_object_container']/div[3]/div/header/div[1]")
    private WebElement WILDCARD_PLAN_TEXT;


    @FindBy(xpath = "//*[@class='title-h1']")
    private static WebElement PRODUCT_NAME;

    public SSLCertificatesPlanPage(EventFiringWebDriverWrapper driver) {
        super(driver);
    }

    private SSLProduct actual;

    public SSLProduct getProduct() {
        return actual;
    }

    public static String getProductName(){
        return PRODUCT_NAME.getText();
    }

    public void selectPlan(String url){
        actual = new SSLProduct(getProductName());

        // it will be change later
        if (url.contains("standard")){
            actual.setProductPlan(new Plan(STANDARD_PLAN_TEXT.getText()));
        }
        if (url.contains("premium")){
            actual.setProductPlan(new Plan(PREMIUM_PLAN_TEXT.getText()));
        }
        if (url.contains("wildcard")){
            actual.setProductPlan(new Plan(WILDCARD_PLAN_TEXT.getText()));
        }
        driver.findElement(By.xpath("//a[@href='" + url + "']")).click();
    }

}
