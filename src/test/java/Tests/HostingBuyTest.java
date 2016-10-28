package Tests;

import Interfaces.ExpectedProducts.LinuxWebHosting;
import Objects.Product;
import Pages.BasePage;
import Pages.HostingOrderPage;
import Pages.HostingPlanPage;
import Pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class HostingBuyTest {
    public static EventFiringWebDriver driver;

    private Product productBefore;
    private Product productAfter;
    private java.lang.String errors = "";
    HostingPlanPage hostingPlanPage;
    HostingOrderPage orderPage;
    ShoppingCartPage shoppingCartPage;
    private LinuxWebHosting linuxWebHosting = new LinuxWebHosting();

    @BeforeSuite
    public void initEnv() {

      System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver\\chromedriver.exe"); //Chrome driver windows
//        System.setProperty("webdriver.chrome.driver", "/home/geser/IdeaProjects/chromedriver"); //Chrome driver linux
        java.lang.String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64; Dreamscape/1.0;) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36";
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--disable-extensions");
        co.addArguments("--user-agent=" + userAgent);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, co);
        WebDriver webDriver = new ChromeDriver(cap);
        //WebDriver webDriver = new ChromeDriver();
        driver = new EventFiringWebDriver(webDriver);
        //use this Highlight if need, when you debug your test
//        driver.register(new ListenerThatHiglilightsElements("#FFFF00", 1, 200, TimeUnit.MILLISECONDS));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test
    public void testByEconomyLinuxHosting() {
        gotoPage("https://www.crazydomains.com.au/web-hosting/");
        hostingPlanPage.clickBuyNowEconomyHosting();
        rememberProductBefore(hostingPlanPage);
        rememberProductAfter(orderPage);
        compareProducts();

        orderPage.selectOption("24");
        orderPage.addAddon("Traffic Booster");
        orderPage.addAddon("Premium Email Protection");
        orderPage.addAddon("Secure Web Hosting");
        orderPage.pageDown();
        orderPage.clearDomainInputField();
        orderPage.inputDomainName("DomainForTesting.com");
        orderPage.clickContinueOrderButton();
        rememberProductBefore(orderPage);
//        orderPage.productToString(orderPage.getFinalProduct());
        shoppingCartPage.clickCart();
//        shoppingCartPage.productToString(shoppingCartPage.getProduct());
        rememberProductAfter(shoppingCartPage);
        compareProductsShoppingCart();

        checkProductSpecification(shoppingCartPage);
        isProductOk();
    }

    public void isProductOk() {
        Assert.assertTrue(errors.equals(""), "\n" + errors);
    }

    public void gotoPage(java.lang.String url) {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
        hostingPlanPage = new HostingPlanPage(driver);
        orderPage = new HostingOrderPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    public void checkProductSpecification(BasePage page) {
        if (!errors.equals("")) {
            errors = errors + "\n";
        }
        errors = errors + linuxWebHosting.isProduct(page.getProduct());
    }

    public void rememberProductBefore(BasePage page) {
        productBefore = page.getProduct();
    }

    public void rememberProductBefore(HostingOrderPage page) {
        productBefore = page.getFinalProduct();
    }

    public void rememberProductAfter(BasePage page) {
        productAfter = page.getProduct();
    }

    public void compareProducts() {
        if (!errors.equals("")) {
            errors = errors + "\n";
        }
        errors = errors + productBefore.getErrorOrderPage(productAfter);
    }

    public void compareProductsShoppingCart() {
        if (!errors.equals("")) {
            errors = errors + "\n";
        }
        errors = errors + productBefore.getErrorShoppingCartPage(productAfter);
    }

    @AfterTest
    public void evnSgut() {
        if (driver != null)
            driver.quit();
    }
}
