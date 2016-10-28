package Tests;

import Interfaces.ExpectedProducts.SSLCertificatesProducts;
import Objects.Product;
import Pages.BasePage;
import Pages.SSLCertificates.SSLCertificatesPlanPage;
import Pages.SSLCertificates.SSLOrderPage;
import Pages.SSLCertificates.SSLshoppingCartPage;
import Products.SSLproduct;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by geser on 27.10.16.
 */
public class SSLCertificateBuyTest extends HostingBuyTest {

    private Product productBefore;
    private Product productAfter;
    private String errors = "";

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
//        SSLCertificatesPlanPage sslPage = new SSLCertificatesPlanPage(driver);
//        SSLOrderPage sslOrderPage = new SSLOrderPage(driver);
//        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        gotoPage("https://www.crazydomains.com.au/ssl-certificates/");
//        sslPage.pageDown();

        sslPage.selectPremiumPlan();
//        sslPage.finalProductToString(sslPage.getWebHostingProduct());
//        sslOrderPage.finalProductToString(sslOrderPage.getActualProduct());
        rememberProductBefore(sslPage);
        rememberProductAfter(sslOrderPage);
        compareProductsPlanOrderPage();
//        isProductOk();
        sslOrderPage.selectOption("24");
        sslOrderPage.pageEnd();
        sslOrderPage.inputDomainName("SSLForTesting.ru");
        sslOrderPage.clickContinueOrderButton();


        shoppingCartPage.clickCart();
        rememberProductBefore(sslOrderPage);
        shoppingCartPage.productToString();
        rememberProductAfter(shoppingCartPage);
        compareProductsOrderShoppingCartPage();
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
    }

    public void rememberProductBefore(BasePage page) {
        productBefore = page.getProduct();

    }

    public void rememberProductAfter(BasePage page) {
        productAfter = page.getProduct();
    }


    public void compareProductsPlanOrderPage() {
        errors = errors + productBefore.getErrorOrderPage(productAfter);
    }

    public void compareProductsOrderShoppingCartPage() {
        if (!errors.equals("")) {
            errors = errors + "\n";
        }
        errors = errors + productBefore.getErrorShoppingCartPage(productAfter);
    }

    @AfterTest
    @Override
    public void evnSgut() {
        super.evnSgut();
    }
}
