package Pages.WebHosting.Windows;

import Objects.Product;
import Pages.BaseOrderPage;
import Products.WebHostingProduct;
import Utils.EventFiringWebDriverWrapper;
import org.openqa.selenium.interactions.Action;

/**
 * Created by Sergiy.K on 08-Nov-16.
 */
public class WindowsHostingOrderPage extends BaseOrderPage {

    public WindowsHostingOrderPage(EventFiringWebDriverWrapper driver) {
        super(driver);
        this.driver = driver;
    }

    public WebHostingProduct getProduct() {
        WebHostingProduct actualProduct = new WebHostingProduct(getProductName());
        actualProduct.setProductPlan(getSelectedProductPlan());
        actualProduct.setProductAddons(getSelectedProductAddons());
        actualProduct.setProductPrice(getTotalPrice());
        actualProduct.setProductDomain(getSelectedProductDomain());
        System.out.println(actualProduct.toString());
        return actualProduct;
    }
}
