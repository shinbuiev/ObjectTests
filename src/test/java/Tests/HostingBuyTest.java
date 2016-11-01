package Tests;

import EmailNotification.Email;
import EmailNotification.ErrorMessage;
import Features.OrderPage;
import Interfaces.ExpectedProducts.BaseExpectedProduct;
import Interfaces.ExpectedProducts.LinuxWebHosting;
import Interfaces.ExpectedProducts.WindowsWebHosting;
import Objects.Product;
import Pages.BasePage;
import Pages.WebHosting.HostingOrderPage;
import Pages.WebHosting.HostingPlanPage;
import Pages.WebHosting.HostingShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class HostingBuyTest {
    public  WebDriver driver;

    @BeforeTest
    public void initEnv() {

        System.setProperty("webdriver.chrome.driver", "/home/frunoyman/Загрузки/chromedriver");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.crazydomains.com.au/web-hosting/");
    }

    @Test
    public void test(){
        HostingPlanPage hostingPlanPage=new HostingPlanPage(driver);
        hostingPlanPage.rememberProductBefore(hostingPlanPage);

    }

    @Test(dataProviderClass = DataPoviders.class,dataProvider = "userType")
    public void byHostingTest(String plan) {
        HostingPlanPage hostingPlanPage=new HostingPlanPage(driver);
        hostingPlanPage.rememberProductBefore(hostingPlanPage);
        HostingOrderPage orderPage=hostingPlanPage.selectPlan(plan);
        orderPage.rememberProductAfter(orderPage);
        orderPage.comparePlanPageAndOrderPageProducts();
        orderPage.selectOption("24");
        orderPage.addAddon("Traffic Booster");
        orderPage.addAddon("Premium Email Protection");
        orderPage.addAddon("Secure Web Hosting");
        orderPage.pageDown();
        orderPage.clearDomainInputField();
        orderPage.inputDomainName("DomainForTesting.com");
        HostingShoppingCartPage hostingShoppingCartPage=orderPage.clickContinueOrderButton();
        hostingShoppingCartPage.clickCart();
        hostingShoppingCartPage.rememberProductBefore(orderPage);
        hostingShoppingCartPage.rememberProductAfter(hostingShoppingCartPage);
        hostingPlanPage.compareProductsOrderPageAndShoppingCart();
       // checkProductSpecification(hostingShoppingCartPage);
        hostingShoppingCartPage.clearShoppingCart();
    }

    @AfterTest
    public void sendEmailNotificationWithErrors() {
        BasePage.errorSending();
    }

    @AfterTest
    public void evnSgut() {

    }
}
