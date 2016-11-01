package Tests;

import EmailNotification.ErrorMessage;
import Interfaces.ExpectedProducts.BaseExpectedProduct;
import Interfaces.ExpectedProducts.LinuxWebHosting;
import Interfaces.ExpectedProducts.WindowsWebHosting;
import Objects.Price;
import Objects.Product;
import Pages.BasePage;
import Pages.WebHosting.HostingOrderPage;
import Pages.WebHosting.HostingPlanPage;
import Pages.WebHosting.HostingShoppingCartPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class HostingBuyTest extends BasicTest {
    private File screenFile;

    private Price priceBefore;
    private Price priceAfter;
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


        //PLANS
        rememberTotalPriceBeforeSelect();
        orderPage.select24monthOptionTerm();
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.select36monthOptionTerm();
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.select120monthOptionTerm();
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.select12monthOptionTerm();
        rememberTotalPriceAfterSelect();
        comparePrices();

        //ADDONS

        orderPage.pageDown();
        rememberTotalPriceBeforeSelect();
        orderPage.addTrafficBooster();
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.addWebAnalytics();
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.addPremiumEmailProtection();
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.addSecureWebHosting();
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.addMailingListManager();
        rememberTotalPriceAfterSelect();
        comparePrices();

//        orderPage.pageEnd();
//        orderPage.clearDomainInputField();
//        orderPage.inputDomainName("DomainForTesting.com");
//        rememberProductBefore(orderPage);
//        orderPage.clickContinueOrderButton();
//        hostingShoppingCartPage.clickCart();
//        //here compare product from order page and shopping cart page, add screenshots and errors to errorMessageList if exist come differences
//        rememberProductAfter(hostingShoppingCartPage);
//        compareProductsOrderPageAndShoppingCart();
//        hostingShoppingCartPage.clearShoppingCart();
//        isProductOk();

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

    public void rememberTotalPriceBeforeSelect(){
        priceBefore = orderPage.getTotalPrice();
    }

    public void rememberTotalPriceAfterSelect(){
        priceAfter = orderPage.getTotalPrice();
    }

    public void comparePrices(){
        if (priceBefore.getPrice().equals(priceAfter.getPrice()))
        {
            errorMessageList.add(new ErrorMessage("error with prices after action"));
        }
        else
        {
            System.out.println(" colo price ");
        }
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
