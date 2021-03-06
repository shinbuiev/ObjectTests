package Tests;

import EmailNotification.Email;
import EmailNotification.ErrorMessage;
import Interfaces.ExpectedProducts.BaseExpectedProduct;
import Interfaces.ExpectedProducts.LinuxWebHosting;
import Interfaces.ExpectedProducts.WindowsWebHosting;
import Objects.Product;
import Pages.BasePage;
import Pages.WebHosting.HostingOrderPage;
import Pages.WebHosting.HostingPlanPage;
import Pages.WebHosting.HostingShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class HostingBuyTest {
    public static EventFiringWebDriver driver;

    private Product productBefore;
    private Product productAfter;
    private java.lang.String errors = "";
    private HostingOrderPage orderPage;
    private HostingPlanPage hostingPlanPage;
    private HostingShoppingCartPage hostingShoppingCartPage;
    private LinuxWebHosting linuxWebHosting = new LinuxWebHosting();
    private WindowsWebHosting windowsWebHosting = new WindowsWebHosting();
    private ArrayList<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();

    @BeforeSuite
    public void initEnv() {
        //testByValik
        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver\\chromedriver.exe"); //Chrome driver
        String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64; Dreamscape/1.0;) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36";
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--disable-extensions");
        co.addArguments("--user-agent=" + userAgent);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, co);
        WebDriver webDriver = new ChromeDriver(cap);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }


    //data provider for get expect product and try to buy product
    @DataProvider
    public Object[][] getExpectedProduct(){
        return new Object[][]{
                {windowsWebHosting, windowsWebHosting.getProductPlans().get(0).getOrderPageUrl()},
                {windowsWebHosting, windowsWebHosting.getProductPlans().get(1).getOrderPageUrl()},
                {windowsWebHosting, windowsWebHosting.getProductPlans().get(2).getOrderPageUrl()},
                {linuxWebHosting, linuxWebHosting.getProductPlans().get(0).getOrderPageUrl()},
                {linuxWebHosting, linuxWebHosting.getProductPlans().get(1).getOrderPageUrl()},
                {linuxWebHosting, linuxWebHosting.getProductPlans().get(2).getOrderPageUrl()},
        };
    }

    @Test(dataProvider = "getExpectedProduct")
    public void byHostingTest(BaseExpectedProduct product, String plan) {

        gotoPage(product.getProductMainPage());
        hostingPlanPage.selectPlan(plan);
        //here compare product from plan page and order page, add screenshots and errors to errorMessageList if exist come differences
        rememberProductBefore(hostingPlanPage);
        rememberProductAfter(orderPage);
        comparePlanPageAndOrderPageProducts();

        orderPage.selectOption("24");
        orderPage.addAddon("Traffic Booster");
        orderPage.addAddon("Premium Email Protection");
        orderPage.addAddon("Secure Web Hosting");
        orderPage.pageDown();
        orderPage.clearDomainInputField();
        orderPage.inputDomainName("DomainForTesting.com");
        orderPage.clickContinueOrderButton();
        hostingShoppingCartPage.clickCart();
        //here compare product from order page and shopping cart page, add screenshots and errors to errorMessageList if exist come differences
        rememberProductBefore(orderPage);
        rememberProductAfter(hostingShoppingCartPage);
        compareProductsOrderPageAndShoppingCart();
        //here compare final product in shopping cart with expected product (The expected product is a product created based on specifications)
        //if exist some differences add error message to array
        checkProductSpecification(hostingShoppingCartPage);
        hostingShoppingCartPage.clearShoppingCart();

        isProductOk();

    }

    public void isProductOk() {
        //must remake the logic: add possibility check the errorMessageList
        Assert.assertTrue(errors.equals(""), "\n" + errors);
        if (!errors.equals("")){

        }
    }

    public void gotoPage(java.lang.String url) {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
        hostingPlanPage = new HostingPlanPage(driver);
        orderPage = new HostingOrderPage(driver);
        hostingShoppingCartPage = new HostingShoppingCartPage(driver);
    }

    public void checkProductSpecification(BasePage page) {
        if (!errors.equals("")) {
            errors = errors + "\n";
        }
        errors = errors + linuxWebHosting.isProduct(page.getProduct());
    }

    public void rememberProductBefore(BasePage page) {
        productBefore = page.getProduct();
    }

    public void rememberProductAfter(BasePage page) {
        productAfter = page.getProduct();
    }

    public void comparePlanPageAndOrderPageProducts(){

        productBefore.comparePlanPageOrderPageProductsAndGetErrors(productAfter);
        if (productBefore.getErrorMessages().size() > 0){
            errorMessageList.addAll(productBefore.getErrorMessages());
        }
    }

    public void compareProductsOrderPageAndShoppingCart() {
        productBefore.getErrorShoppingCartPage(productAfter);
        if (productBefore.getErrorMessages().size() > 0){
            errorMessageList.addAll(productBefore.getErrorMessages());
        }
    }

    @AfterTest
    public void sendEmailNotificationWithErrors(){
        if (errorMessageList.size() > 0)
            {
                Email email = new Email();
                try {
                    email.execute("Result for Web Hosting buy test ", errorMessageList);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("can't send email  \n" + e.getMessage());
                }
            }
        }

    @AfterTest
    public void evnSgut() {
        if (driver != null)
            driver.quit();
    }
}
