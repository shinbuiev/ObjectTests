package Tests;

import Objects.Product;
import Pages.BasePage;
import Pages.OrderPage;
import Pages.SSLCertificates.SSLCertificatesPlanPage;
import Pages.SSLCertificates.SSLOrderPage;
import Pages.SSLCertificates.SSLshoppingCartPage;
import Pages.ShoppingCartPage;
import Products.SSLproduct;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by geser on 27.10.16.
 */
public class SSLCertificateBuyTest extends HostingBuyTest {

    private SSLproduct productBefore;
    private SSLproduct productAfter;
    private String errors = "";

    SSLCertificatesPlanPage sslPage;
    SSLOrderPage sslOrderPage;
    SSLshoppingCartPage shoppingCartPage;

    @BeforeSuite
    @Override
    public void initEnv() {
        super.initEnv();
    }

    @Test
    public void testByStandardSSlcertificate(){
//        SSLCertificatesPlanPage sslPage = new SSLCertificatesPlanPage(driver);
//        SSLOrderPage sslOrderPage = new SSLOrderPage(driver);
//        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        gotoPage("https://www.crazydomains.com.au/ssl-certificates/");
        sslPage.pageDown();
        sslPage.selectPremiumPlan();
//        sslPage.productToString(sslPage.getProduct());
//        sslOrderPage.productToString(sslOrderPage.getActualProduct());
        rememberProductBefore(sslPage);
        rememberProductAfter(sslOrderPage);
        compareProductsPlanOrderPage();
//        isProductOk();
        sslOrderPage.selectOption("24");
        sslOrderPage.pageEnd();
        sslOrderPage.inputDomainName("SSLForTesting.ru");
        sslOrderPage.clickContinueOrderButton();


        shoppingCartPage.clickCart();
        sslOrderPage.productToString(sslOrderPage.getFinalProduct());
        rememberProductBefore(sslOrderPage);
        shoppingCartPage.productToString(shoppingCartPage.getProduct());
        rememberProductAfter(shoppingCartPage);
        compareProductsOrderShoppingCartPage11();
//        checkProductSpecification(shoppingCartPage);
//        isProductOk();
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

    //need to remake

    public void checkProductSpecification(BasePage page) {
        super.checkProductSpecification(page);
    }

    public void rememberProductBefore(SSLCertificatesPlanPage page) {
//        productBefore = page.getProduct();
        pr1 = page.getProduct();
    }

    public void rememberProductBefore(SSLOrderPage page) {
//        productAfter = page.getFinalProduct();
    }


    public void rememberProductAfter(BasePage page) {
//    productAfter = page.getProduct();
    }


    public void compareProductsPlanOrderPage() {
    errors = errors + productBefore.getErrorOrderPage(productAfter);
    }
    SSLproduct pr1;
    SSLproduct pr2;
    //need to remake
    public void compareProductsOrderShoppingCartPage11() {
        if (!errors.equals("")) {
            errors = errors + "\n";
        }

        errors = errors + pr1.getErrorShoppingCartPage(pr2);
    }

    @AfterTest
    @Override
    public void evnSgut() {
        super.evnSgut();
    }
}
