package Tests;

import EmailNotification.Email;
import EmailNotification.ErrorMessage;
import Interfaces.ExpectedProducts.BaseExpectedProduct;
import Interfaces.ExpectedProducts.EmailHosting;
import Interfaces.ExpectedProducts.LinuxWebHosting;
import Interfaces.ExpectedProducts.WindowsWebHosting;
import Objects.Product;
import Objects.Term;
import Pages.BasePage;
import Pages.WebHosting.HostingOrderPage;
import Pages.WebHosting.HostingPlanPage;
import Pages.WebHosting.HostingShoppingCartPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class HostingBuyTest extends BasicTest {
    private File screenFile;

    private Product productBefore;
    private Product productAfter;
    private java.lang.String errors = "";
    private HostingOrderPage orderPage;
    private HostingPlanPage hostingPlanPage;
    private HostingShoppingCartPage hostingShoppingCartPage;
    private LinuxWebHosting linuxWebHosting = new LinuxWebHosting();
    private WindowsWebHosting windowsWebHosting = new WindowsWebHosting();
    private ArrayList<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();

    //data provider for get expect product and try to buy product
    @DataProvider
    public Object[][] getExpectedProduct() {
        return new Object[][]{
                {windowsWebHosting, windowsWebHosting.getProductPlans().get(0).getOrderPageUrl()},
//                {windowsWebHosting, windowsWebHosting.getProductPlans().get(1).getOrderPageUrl()},
//                {windowsWebHosting, windowsWebHosting.getProductPlans().get(2).getOrderPageUrl()},
//                {linuxWebHosting, linuxWebHosting.getProductPlans().get(0).getOrderPageUrl()},
//                {linuxWebHosting, linuxWebHosting.getProductPlans().get(1).getOrderPageUrl()},
//                {linuxWebHosting, linuxWebHosting.getProductPlans().get(2).getOrderPageUrl()},
        };
    }

    @Test(dataProvider = "getExpectedProduct")
    public void buyHostingTest(BaseExpectedProduct product, String plan) {
        gotoPage(product.getProductMainPage());
        rememberProductBefore(hostingPlanPage);
        hostingPlanPage.selectPlan(plan);
        //here compare product from plan page and order page, add screenshots and errors to errorMessageList if exist come differences
        rememberProductAfter(orderPage);
        comparePlanPageAndOrderPageProducts();
        orderPage.selectAllTerms(product.getProductTerms());
        orderPage.addAddons(product.getProductAddons());
        orderPage.pageDown();
        orderPage.clearDomainInputField();
        orderPage.inputDomainName("DomainForTesting.com");
        rememberProductBefore(orderPage);
        orderPage.clickContinueOrderButton();
        hostingShoppingCartPage.clickCart();
        //here compare product from order page and shopping cart page, add screenshots and errors to errorMessageList if exist come differences
        rememberProductAfter(hostingShoppingCartPage);
        compareProductsOrderPageAndShoppingCart();
        hostingShoppingCartPage.clearShoppingCart();
        isProductOk();

    }

    public void isProductOk() {
       Assert.assertTrue(errorMessageList.size() == 0, "\n" + errorMessageList);
    }

    public void gotoPage(java.lang.String url) {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
        hostingPlanPage = new HostingPlanPage(driver);
        orderPage = new HostingOrderPage(driver);
        hostingShoppingCartPage = new HostingShoppingCartPage(driver);
    }

    public void rememberProductBefore(BasePage page) {
        productBefore = page.getProduct();
        productBefore.takeScreenshot();
    }

    public void rememberProductAfter(BasePage page) {
        productAfter = page.getProduct();
        productAfter.takeScreenshot();
    }

    public void comparePlanPageAndOrderPageProducts() {

        productBefore.comparePlanPageOrderPageProductsAndGetErrors(productAfter);
        if (productBefore.getErrorMessages().size() > 0) {
            errorMessageList.addAll(productBefore.getErrorMessages());
            productBefore.saveScreen(productBefore.getClass().getName().replace(".",""),"PlanPage");
            productAfter.saveScreen(productAfter.getClass().getName().replace(".",""), "OrderPage");
        }
    }

    public void compareProductsOrderPageAndShoppingCart() {
        productBefore.getErrorShoppingCartPage(productAfter);
        if (productBefore.getErrorMessages().size() > 0) {
            errorMessageList.addAll(productBefore.getErrorMessages());
            productBefore.saveScreen(productBefore.getClass().getName().replace(".",""), "OrderPage1");
            productAfter.saveScreen(productAfter.getClass().getName().replace(".",""), "ShoppingCart1");
        }
    }

    @AfterTest
    public void sendEmailNotificationWithErrors() {

//        if (errorMessageList.size() > 0) {
//            Email email = new Email();
//            try {
//                email.execute("Result for Web Hosting buy test ", errorMessageList);
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("can't send email  \n" + e.getMessage());
//            }
//        }

    }

    @AfterTest
    public void evnSgut() {
        if (driver != null)
            driver.quit();
    }
}
