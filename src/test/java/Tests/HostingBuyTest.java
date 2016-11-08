package Tests;

import EmailNotification.ErrorMessage;
import ExpectedProducts.BaseExpectedProduct;
import ExpectedProducts.LinuxWebHosting;
import ExpectedProducts.WindowsWebHosting;
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

    private Product productBefore;
    private Product productAfter;

    private HostingOrderPage orderPage;
    private HostingPlanPage hostingPlanPage;
    private HostingShoppingCartPage hostingShoppingCartPage;
    LinuxWebHosting linuxWebHosting = new LinuxWebHosting();
    WindowsWebHosting windowsWebHosting = new WindowsWebHosting();
    private ArrayList<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();
    private ConnectToValidation validation;


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

        validation.emptyDomainFieldOwnDomainTest();
        validation.emptyDomainFieldRegisterNewDomainTest();

        validation.domainWithWrongTldOwnDomainNameTest();
        validation.domainWithWrongTldRegisterNewDomainTest();

        validation.incorrectDomainNameOwnDomainTest();
        validation.incorrectDomainNameRegisterNewDomainTest();
        checkValidationErrors();

        // here select all plans one by one and compare total price after each action, if price is still the same add message to errorMessage list
        orderPage.selectAllPlans(product.getProductPlans());
        // here add all of existing addons and compare total price after each action,  if price is still the same add message to errorMessage list
        orderPage.selectAllAddons(product.getProductAddons());
        comparePrices();

        orderPage.pageEnd();
        orderPage.clickRegisterNewDomain();
        orderPage.clearDomainInputField();
        orderPage.clickOnPage();
        orderPage.inputDomainName("DomainWebHostingForCrazyTests.com");
        orderPage.clickOnPage();
        rememberProductBefore(orderPage);
        orderPage.clickContinueOrderButton();
        hostingShoppingCartPage.clickCart();

        rememberProductAfter(hostingShoppingCartPage);
        compareProductsOrderPageAndShoppingCart();
        hostingShoppingCartPage.clearShoppingCart();
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

    public void comparePrices() {
        if (orderPage.getPriceErrors().size() > 0) {
            errorMessageList.add(new ErrorMessage("error with prices after action"));
        } else System.out.println("price is ok ");
    }

    public void checkValidationErrors() {
        if (validation.getErrorMessageList().size() > 0) {
            errorMessageList.addAll(validation.getErrorMessageList());
        }
    }

    public void gotoPage(java.lang.String url) {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
//        hostingPlanPage = new HostingPlanPage(driver);
//        orderPage = new HostingOrderPage(driver);
//        hostingShoppingCartPage = new HostingShoppingCartPage(driver);
//        validation = new ConnectToValidation(orderPage);
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
            productBefore.saveScreen(productBefore.getClass().getName().replace(".", ""), "PlanPage");
            productAfter.saveScreen(productAfter.getClass().getName().replace(".", ""), "OrderPage");
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
        for (int i = 0; i < errorMessageList.size(); i++) {
//            System.out.println(errorMessageList.get(i));
//        }
            if (driver != null)
                driver.quit();
        }
    }
}
