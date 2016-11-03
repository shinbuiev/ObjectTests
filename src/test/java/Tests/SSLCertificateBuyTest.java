package Tests;

import EmailNotification.Email;
import EmailNotification.ErrorMessage;
import Interfaces.ExpectedProducts.SSLCertificatesProducts;
import Objects.Product;
import Pages.BasePage;
import Pages.SSLCertificates.SSLCertificatesPlanPage;
import Pages.SSLCertificates.SSLOrderPage;
import Pages.SSLCertificates.SSLshoppingCartPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by geser on 27.10.16.
 */
public class SSLCertificateBuyTest extends HostingBuyTest {

    private String errors = "";
    private Product productAfter;
    private Product productBefore;
    private SSLOrderPage sslOrderPage;
    private SSLCertificatesPlanPage sslPlanPage;
    private SSLshoppingCartPage shoppingCartPage;
    private ArrayList<String> errorList = new ArrayList<String>(); //will be delete
    private ArrayList<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();
    private SSLCertificatesProducts sslSpecification = new SSLCertificatesProducts();

    @BeforeSuite
    @Override
    public void initEnv() {
        super.initEnv();
    }

    @DataProvider
    public Object[][] getExpectedProduct(){
        return new Object[][]{
                {sslSpecification, sslSpecification.getProductPlans().get(0).getOrderPageUrl()},
                {sslSpecification, sslSpecification.getProductPlans().get(1).getOrderPageUrl()},
                {sslSpecification, sslSpecification.getProductPlans().get(2).getOrderPageUrl()},
        };
    }

    @Test(dataProvider = "getExpectedProduct")
    public void buySSLCertificateTest(SSLCertificatesProducts product, String plan) {

        gotoPage(product.getProductMainPage());
        sslPlanPage.pageDown();
        sslPlanPage.selectPlan(plan);
        rememberProductBefore(sslPlanPage);
        rememberProductAfter(sslOrderPage);
        comparePlanPageAndOrderPageProducts();

        sslOrderPage.selectAllTerms(product.getProductTerms());

//        sslOrderPage.selectOption(product.getProductTerms());
        sslOrderPage.pageEnd();
        sslOrderPage.inputDomainName("SSLForTesting.ru");
        sslOrderPage.clickContinueOrderButton();

        shoppingCartPage.clickCart();

        rememberProductBefore(sslOrderPage);
        rememberProductAfter(shoppingCartPage);
        compareProductsOrderPageAndShoppingCart();

        checkProductSpecification(shoppingCartPage);
        shoppingCartPage.clearShoppingCart();

        isProductOk();
    }

    public void isProductOk() {
        Assert.assertTrue(errors.equals(""), "\n" + errors);
    }

    public void gotoPage(String url) {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
        sslPlanPage = new SSLCertificatesPlanPage(driver);
        sslOrderPage = new SSLOrderPage(driver);
        shoppingCartPage = new SSLshoppingCartPage(driver);
    }

    //change logic!!!
    public void checkProductSpecification(BasePage page) {
        if (!errors.equals("")) {
            errors = errors + "\n";
        }
        errors = errors + sslSpecification.isProduct(page.getProduct());
        if (!(sslSpecification.isProduct(page.getProduct())).equals(""))
        errorList.add(sslSpecification.isProduct(page.getProduct()));
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
//        if (errorMessageList.size() > 0)
//        {
//            Email email = new Email();
//            try {
//                email.execute("Result for SSL Certificate buy test ", errorMessageList);
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("can't send email  \n" + e.getMessage());
//            }
//        }
    }

    @AfterTest
    @Override
    public void evnSgut() {
        super.evnSgut();
    }
}
