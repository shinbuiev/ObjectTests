package Tests;

import Pages.WebHosting.HostingOrderPage;
import org.testng.annotations.Test;

/**
 * Created by Sergiy.K on 02-Nov-16.
 */
public class TestOrder extends BasicTest {
    @Test
    public void test1() {
        driver.get("https://www.crazydomains.com.au/web-hosting/order-economy-linux-hosting/");
        HostingOrderPage orderPage = new HostingOrderPage(driver);

        orderPage.setPlan("36 months");

        orderPage.setAddon("Traffic Booster");
        orderPage.setAddon("Secure Web Hosting");
        orderPage.setAddon("Web Analytics");
        orderPage.setAddon("Traffic Booster");




        orderPage.getPlans();
        orderPage.getAddons();
        orderPage.getDomainStatus();
    }

}
