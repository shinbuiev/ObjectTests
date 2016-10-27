package Pages;

import Objects.Product;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class BasePage {

    protected EventFiringWebDriver driver;
    Product product;

    public BasePage(EventFiringWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public Product getProduct() {
        return product;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
