package Tests;

import Objects.Price;
import Pages.BasePage;
import Objects.Product;
import org.testng.Assert;

import java.util.ArrayList;

import org.testng.annotations.Test;
import EmailNotification.ErrorMessage;
import Pages.WebHosting.HostingPlanPage;
import org.testng.annotations.AfterTest;
import Pages.WebHosting.HostingOrderPage;
import org.testng.annotations.DataProvider;
import Pages.WebHosting.HostingShoppingCartPage;
import Interfaces.ExpectedProducts.LinuxWebHosting;
import Interfaces.ExpectedProducts.WindowsWebHosting;
import Interfaces.ExpectedProducts.BaseExpectedProduct;

/**
 * Created by Sergiy.K on 02-Nov-16.
 */
public class TestOrderPage extends BasicTest {


    private String domain = "myfirstbuyincrazy.com.au";
    private String secondDomain = "mysecondbuyincrazy.com.au";
    private String incorrectDomain = "myfirstbuyincrazy";
    private String domainWithWrongTld = "myfirstbuyincrazy.none";


    private Price priceBefore;
    private Price priceAfter;
    private Product productBefore;
    private Product productAfter;

    private HostingOrderPage orderPage;
    private HostingPlanPage hostingPlanPage;
    private HostingShoppingCartPage hostingShoppingCartPage;
    LinuxWebHosting linuxWebHosting = new LinuxWebHosting();
    WindowsWebHosting windowsWebHosting = new WindowsWebHosting();
    private ArrayList<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();


    @DataProvider
    public Object[][] getExpectedProduct() {
        return new Object[][]{
//                {windowsWebHosting, windowsWebHosting.getProductPlans().get(0).getOrderPageUrl()},
//                {windowsWebHosting, windowsWebHosting.getProductPlans().get(1).getOrderPageUrl()},
//                {windowsWebHosting, windowsWebHosting.getProductPlans().get(2).getOrderPageUrl()},
                {linuxWebHosting, linuxWebHosting.getProductPlans().get(0).getOrderPageUrl()},
//                {linuxWebHosting, linuxWebHosting.getProductPlans().get(1).getOrderPageUrl()},
//                {linuxWebHosting, linuxWebHosting.getProductPlans().get(2).getOrderPageUrl()},
        };
    }

    @Test(dataProvider = "getExpectedProduct")
    public void test1(BaseExpectedProduct product, String url) {
        gotoPage(product.getProductMainPage());
        rememberProductBefore(hostingPlanPage);
        hostingPlanPage.selectPlan(url);
        rememberProductAfter(orderPage);
        comparePlanPageAndOrderPageProducts();

        rememberTotalPriceBeforeSelect();
        orderPage.setPlan("24 months");
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.setPlan("12 months");
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.setPlan("120 months");
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.setPlan("36 months");
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.setAddon("Premium Email Protection");
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.setAddon("Traffic Booster");
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.setAddon("Secure Web Hosting");
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.setAddon("Web Analytics");
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.setAddon("Premium Email Protection");
        rememberTotalPriceAfterSelect();
        comparePrices();

        rememberTotalPriceBeforeSelect();
        orderPage.setAddon("Mailing List Manager");
        rememberTotalPriceAfterSelect();
        comparePrices();


        //CONNECT TO BLOCK VALIDATION   here need to change logic!!!!!
        //empty field  own this domain
        orderPage.pageEnd();
        orderPage.clearDomainInputField();
        orderPage.clickOnPage();
        orderPage.clickIownThisDomain();
        orderPage.clickContinueOrderButton();
        checkErrorMessage();

        //empty field  register domain
        orderPage.pageEnd();
        orderPage.clickRegisterNewDomain();
        orderPage.inputDomainName("asasdasdadasds.com");
        orderPage.clickOnPage();  //here need to add wait green light))!!!!!
        orderPage.clearDomainInputField();
        orderPage.clickOnPage();
        orderPage.clickContinueOrderButton();
        checkErrorMessage();

        //domain with wrong tld own this domain
        orderPage.pageEnd();
        orderPage.clickIownThisDomain();
        orderPage.inputDomainName("asasdasdadasds.com");
        orderPage.clickOnPage();
        orderPage.clearDomainInputField();
        orderPage.clickOnPage();
        orderPage.inputDomainName(domainWithWrongTld);
        orderPage.clickOnPage();
        checkStatusDomainAvailableTickStatus();

        //domain with wrong tld register new domain
        orderPage.pageEnd();
        orderPage.clickRegisterNewDomain();
        orderPage.inputDomainName("asasdasdadasds.com");
        orderPage.clickOnPage();
        orderPage.clearDomainInputField();
        orderPage.clickOnPage();
        orderPage.inputDomainName(domainWithWrongTld);
        orderPage.clickOnPage();
        orderPage.clickContinueOrderButton();
        checkErrorMessage();

        //incorrect domain own this domain
        orderPage.pageEnd();
        orderPage.clickIownThisDomain();
        orderPage.inputDomainName("asasdasdadasds.com");
        orderPage.clickOnPage();
        orderPage.clearDomainInputField();
        orderPage.clickOnPage();
        orderPage.inputDomainName(incorrectDomain);
        orderPage.clickOnPage();
        orderPage.clickContinueOrderButton();
        checkErrorMessage();

        //incorrect domain register new domain
        orderPage.pageEnd();
        orderPage.clickRegisterNewDomain();
        orderPage.inputDomainName("asasdasdadasds.com");
        orderPage.clickOnPage();
        orderPage.clearDomainInputField();
        orderPage.inputDomainName(incorrectDomain);
        orderPage.clickContinueOrderButton();
        checkErrorMessage();

        orderPage.pageEnd();
        orderPage.clearDomainInputField();
        orderPage.clickOnPage();
        orderPage.clickIownThisDomain();
        orderPage.inputDomainName("DomainHosting.com");
        orderPage.clickOnPage();
        rememberProductBefore(orderPage);
        orderPage.clickContinueOrderButton();
        hostingShoppingCartPage.clickCart();

        rememberProductAfter(hostingShoppingCartPage);
        compareProductsOrderPageAndShoppingCart();
//        hostingShoppingCartPage.clearShoppingCart();
        isProductOk();
    }


    private void isProductOk() {
        Assert.assertTrue(errorMessageList.size() == 0, "\n" + errorMessageList);
    }

    public void compareProductsOrderPageAndShoppingCart() {
        productBefore.getErrorShoppingCartPage(productAfter);
        if (productBefore.getErrorMessages().size() > 0) {
            errorMessageList.addAll(productBefore.getErrorMessages());
            productBefore.saveScreen(productBefore.getClass().getName().replace(".", ""), "OrderPage1");
            productAfter.saveScreen(productAfter.getClass().getName().replace(".", ""), "ShoppingCart1");
        }
    }

    public void checkStatusDomainAvailableTickStatus() {
        Assert.assertTrue(orderPage.getDomainAvailableTickStatus());
    }

    public void checkErrorMessage() {
        if (!(orderPage.getValidationErrorMessage().equals("Domain name is invalid") ||
                orderPage.getValidationErrorMessage().equals("Domain name is required") ||
                orderPage.getValidationErrorMessage().equals("Requested domain name is invalid")))
            errorMessageList.add(new ErrorMessage("!!!!!!!Validation Error Block ConnectTo on page: " + orderPage.getCurrentUrl() + "\n For domain name:"));
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

    public void rememberTotalPriceBeforeSelect() {
        priceBefore = orderPage.getTotalPrice();
    }

    public void rememberTotalPriceAfterSelect() {
        priceAfter = orderPage.getTotalPrice();
    }

    public void comparePrices() {
        if (priceBefore.getPrice().equals(priceAfter.getPrice())) {
            errorMessageList.add(new ErrorMessage("error with prices after action"));
        }
    }

    public void comparePlanPageAndOrderPageProducts() {
        productBefore.comparePlanPageOrderPageProductsAndGetErrors(productAfter);
        if (productBefore.getErrorMessages().size() > 0) {
            errorMessageList.addAll(productBefore.getErrorMessages());
            productBefore.saveScreen(productBefore.getClass().getName().replace(".", ""), "PlanPage");
            productAfter.saveScreen(productAfter.getClass().getName().replace(".", ""), "OrderPage");
        }
    }


    @AfterTest
    public void evnSgut() {
        System.out.println(errorMessageList);
        if (driver != null)
            driver.quit();
    }
}
