package Tests;

import EmailNotification.Email;
import EmailNotification.ErrorMessage;
import Interfaces.ExpectedProducts.BaseExpectedProduct;
import Interfaces.ExpectedProducts.EmailHosting;
import Interfaces.ExpectedProducts.LinuxWebHosting;
import Interfaces.ExpectedProducts.WindowsWebHosting;
import Objects.Product;
import Pages.BasePage;
import Pages.EmailHosting.EmailHostingOrderPage;
import Pages.EmailHosting.EmailHostingPlanPage;
import Pages.EmailHosting.EmailHostingShoppingCart;
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
 * Created by geser on 31.10.16.
 */
public class EmailHostingBuyTest extends BasicTest{
//    public static EventFiringWebDriver driver;

    private EmailHosting emailHosting = new EmailHosting();

    private Product productBefore;
    private Product productAfter;
    private java.lang.String errors = "";
    private EmailHostingOrderPage orderPage;
    private EmailHostingPlanPage hostingPlanPage;
    private EmailHostingShoppingCart hostingShoppingCartPage;
    private ArrayList<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();


    //data provider for get expect product and try to buy product
    @DataProvider
    public Object[][] getExpectedProduct(){
        return new Object[][]{
                {emailHosting, emailHosting.getProductPlans().get(0).getOrderPageUrl()},
                {emailHosting, emailHosting.getProductPlans().get(1).getOrderPageUrl()},
                {emailHosting, emailHosting.getProductPlans().get(2).getOrderPageUrl()},
        };
    }

    @Test(dataProvider = "getExpectedProduct")
    public void buyEmailHostingTest(BaseExpectedProduct product, String plan) {

        gotoPage(product.getProductMainPage());
        hostingPlanPage.selectPlan(plan);
        //here compare product from plan page and order page, add screenshots and errors to errorMessageList if exist come differences
        rememberProductBefore(hostingPlanPage);
        rememberProductAfter(orderPage);
        comparePlanPageAndOrderPageProducts();

        orderPage.selectOption("24");
        orderPage.addAddon("Premium Email Protection");
        orderPage.pageDown();
        orderPage.clearDomainInputField();
        orderPage.inputDomainName("EmailHosingTest.com");
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
        hostingPlanPage = new EmailHostingPlanPage(driver);
        orderPage = new EmailHostingOrderPage(driver);
        hostingShoppingCartPage = new EmailHostingShoppingCart(driver);
    }

    public void checkProductSpecification(BasePage page) {
        if (!errors.equals("")) {
            errors = errors + "\n";
        }
        errors = errors + emailHosting.isProduct(page.getProduct());
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

