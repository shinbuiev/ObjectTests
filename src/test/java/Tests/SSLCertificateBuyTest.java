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
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by geser on 27.10.16.
 */
public class SSLCertificateBuyTest extends HostingBuyTest {

    private Product productBefore;
    private Product productAfter;
    private String errors = "";
    private ArrayList<String> errorList = new ArrayList<String>(); //maybe delete???????

    private ArrayList<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();

    SSLCertificatesPlanPage sslPage;
    SSLOrderPage sslOrderPage;
    SSLshoppingCartPage shoppingCartPage;
    SSLCertificatesProducts sslSpecification = new SSLCertificatesProducts();
    @BeforeSuite
    @Override
    public void initEnv() {
        super.initEnv();
    }

    @Test
    public void testByStandardSSlcertificate() {
        gotoPage("https://www.crazydomains.com.au/ssl-certificates/");
//        sslPage.pageDown();
        sslPage.selectPremiumPlan();
        rememberProductBefore(sslPage);
        rememberProductAfter(sslOrderPage);
        comparePlanPageAndOrderPageProducts();

        sslOrderPage.selectOption("24");
        sslOrderPage.pageEnd();
        sslOrderPage.inputDomainName("SSLForTesting.ru");
        sslOrderPage.clickContinueOrderButton();

        shoppingCartPage.clickCart();
        rememberProductBefore(sslOrderPage);
        shoppingCartPage.productToString();
        rememberProductAfter(shoppingCartPage);
        compareProductsOrderPageAndShoppingCart();
        checkProductSpecification(shoppingCartPage);
        isProductOk();
    }

    public void isProductOk() {
        Assert.assertTrue(errors.equals(""), "\n" + errors);
    }

    public void gotoPage(String url) {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
        sslPage = new SSLCertificatesPlanPage(driver);
        sslOrderPage = new SSLOrderPage(driver);
        shoppingCartPage = new SSLshoppingCartPage(driver);
    }

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
        if (errorMessageList.size() > 0)
        {
            Email email = new Email();
            try {
                email.execute("Result for SSL Certificate buy test ", errorMessageList);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("can't send email  \n" + e.getMessage());
            }
        }

    }

    @AfterTest
    public void printErrors()
    {
        if (errorList.size()>0)
        {
            for (int i = 0; i < errorList.size(); i++) {
                System.out.println(errorList.get(i));
            }
        }
    }

    @AfterTest
    @Override
    public void evnSgut() {
        super.evnSgut();
    }
}
