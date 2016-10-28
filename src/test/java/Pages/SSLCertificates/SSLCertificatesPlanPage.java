//package Pages.SSLCertificates;
//
//import Objects.Plan;
//import Objects.WebHostingProduct;
//import Pages.BasePage;
//import Products.SSLproduct;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.events.EventFiringWebDriver;
//
///**
// * Created by geser on 27.10.16.
// */
//public class SSLCertificatesPlanPage extends BasePage{
//
//    @FindBy(xpath = "//*[@id='moving_object_container']/div[1]/div/main/div/div[3]/a")
//    private WebElement STANDARD_PLAN_BUTTON;
//
//    @FindBy(xpath = "//*[@id='moving_object_container']/div[1]/div/header/div[1]")
//    private WebElement STANDARD_PLAN_TEXT;
//
//    @FindBy(xpath = "//*[@id='moving_object']/a")
//    private WebElement PREMIUM_PLAN_BUTTON;
//
//    @FindBy(xpath = "//*[@id='moving_object_container']/div[2]/div/header/div[1]")
//    private WebElement PREMIUM_PLAN_TEXT;
//
//
//    @FindBy(xpath = "//*[@id='moving_object_container']/div[3]/div/main/div/div[2]/a/span")
//    private WebElement WILDCARD_PLAN_BUTTON;
//
//    @FindBy(xpath = "//*[@id='moving_object_container']/div[3]/div/header/div[1]")
//    private WebElement WILDCARD_PLAN_TEXT;
//
//
//    @FindBy(xpath = "//*[@class='title-h1']")
//    private static WebElement PRODUCT_NAME;
//
//    public SSLCertificatesPlanPage(EventFiringWebDriver driver) {
//        super(driver);
//    }
//
////    https://www.crazydomains.com.au/ssl-certificates/order-standard-ssl/
//
//    private SSLproduct actual;
//
//    public SSLproduct getWebHostingProduct() {
//        return actual;
//    }
//
//    public static String getProductName(){
//        return PRODUCT_NAME.getText();
//    }
//
//    public void selectStandardPlan(){
//        actual = new SSLproduct(getProductName());
//        actual.setPlan(new Plan(STANDARD_PLAN_TEXT.getText()));
//        STANDARD_PLAN_BUTTON.click();
//    }
//    public void selectPremiumPlan(){
//        actual = new SSLproduct(getProductName());
//        actual.setPlan(new Plan(PREMIUM_PLAN_TEXT.getText()));
//        PREMIUM_PLAN_BUTTON.click();
//    }
//    public void selectWildCardPln(){
//        actual = new SSLproduct(getProductName());
//        actual.setPlan(new Plan(WILDCARD_PLAN_TEXT.getText()));
//        WILDCARD_PLAN_BUTTON.click();
//    }
//
//    public void productToString(WebHostingProduct product){
//        System.out.println("Actual product in Plan  Page: " + product.getProductName() + ", selected plan:  " + product.getPlan());
//    }
//
//
//}