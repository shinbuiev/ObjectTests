package Features;

import org.testng.Assert;

import static Tests.HostingBuyTest.driver;


/**
 * Created by Sergiy.K on 18-Oct-16.
 */
public class BasicHostingPlansTest {
    OrderPage orderPage = new OrderPage(driver);

    public void goToPage(String url) {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
    }

    public void checkOrderPageUrl(String expectedURL) {
        Assert.assertEquals(orderPage.getCurrentURL(), expectedURL);
    }

    public void checkProductNameTitleText(String expectedNameOfProduct) {
        Assert.assertEquals(orderPage.getProductName(), expectedNameOfProduct);
    }

    public void checkPlanNameText(String expectedNameOfPlan) {
        Assert.assertEquals(orderPage.getPlanName(), expectedNameOfPlan);
    }
}
