package Tests;

import ExpectedProducts.BaseExpectedProduct;
import Pages.BasePage;
import Objects.Product;
import Pages.WebHosting.Windows.WindowsHostingOrderPage;
import org.testng.Assert;

import java.util.ArrayList;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import EmailNotification.ErrorMessage;
import Pages.WebHosting.HostingPlanPage;
import org.testng.annotations.DataProvider;
import Pages.WebHosting.HostingShoppingCartPage;
import ExpectedProducts.LinuxWebHosting;
import ExpectedProducts.WindowsWebHosting;

/**
 * Created by Sergiy.K on 02-Nov-16.
 */
public class TestOrderPage extends BasicTest {
    private Product productBefore;
    private Product productAfter;

    private WindowsHostingOrderPage orderPage;
    private HostingPlanPage hostingPlanPage;
    private HostingShoppingCartPage hostingShoppingCartPage;
    LinuxWebHosting linuxWebHosting = new LinuxWebHosting();
    WindowsWebHosting windowsWebHosting = new WindowsWebHosting();
    private ArrayList<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();
    private ConnectToValidation validation;

    @DataProvider
    public Object[][] getExpectedProduct() {
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
    public void buyHostingTest(BaseExpectedProduct product, String plan) {
        gotoPage(product.getProductMainPage());
        rememberProductBefore(hostingPlanPage);
        hostingPlanPage.selectPlan(plan);
        rememberProductAfter(orderPage);
        comparePlanPageAndOrderPageProducts();

        // here check validation on ConnectTo block
        validation.emptyDomainFieldOwnDomainTest();
        validation.emptyDomainFieldRegisterNewDomainTest();

        validation.domainWithWrongTldOwnDomainNameTest();
        validation.domainWithWrongTldRegisterNewDomainTest();

        validation.incorrectDomainNameOwnDomainTest();
        validation.incorrectDomainNameRegisterNewDomainTest();
        checkValidationErrors();
        //end check validation

        // here select all plans one by one and compare total price after each action, if price is still the same add message to errorMessage list
        orderPage.selectAllPlanOptions(windowsWebHosting.getProductTerms());
        // here add all of existing addons and compare total price after each action,  if price is still the same add message to errorMessage list
        orderPage.selectAllAddons(windowsWebHosting.getProductAddons());
        checkErrors();

        orderPage.pageEnd();
        orderPage.clickRegisterNewDomain();
        orderPage.clearDomainInputField();
        orderPage.clickOnPage();
        orderPage.inputDomainName("DomainWebHostingForCrazyTests.com");
        orderPage.clickOnPage();

        isAnyErrors();// if find some errors test is failed
    }

    public void isAnyErrors() {
        Assert.assertTrue(errorMessageList.size() == 0, "\n" + errorMessageList);
    }

    public void gotoPage(java.lang.String url) {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
        hostingPlanPage = new HostingPlanPage(driver);
        orderPage = new WindowsHostingOrderPage(driver);
        validation = new ConnectToValidation(orderPage);
        hostingShoppingCartPage = new HostingShoppingCartPage(driver);

    }

    public void checkErrors() {
        if (orderPage.getErrorMessages().size() > 0) {
            errorMessageList.addAll(orderPage.getErrorMessages());
        } else System.out.println(" Not found errors found on page ");
    }

    public void checkValidationErrors() {
        if (validation.getErrorMessageList().size() > 0) {
            errorMessageList.addAll(validation.getErrorMessageList());
        } else System.out.println(" Validation errors not found ");
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
        } else System.out.println("Compare Plan Page And Order Page Products is successful");
    }

    @AfterTest
    public void evnSgut() {
        for (int i = 0; i < errorMessageList.size(); i++) {
            System.out.println(errorMessageList.get(i));
        }
        if (driver != null)
            driver.quit();
    }
}
