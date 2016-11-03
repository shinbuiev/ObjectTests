package Pages;

import Interfaces.ExpectedProducts.EventFiringWebDriverWrapper;
import Objects.Product;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public abstract class BasePage {

    @FindBy(xpath = "/html/body")
    private WebElement CLICK;

    protected EventFiringWebDriverWrapper driver;

    public BasePage(EventFiringWebDriverWrapper driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void pageEnd() {
        CLICK.sendKeys(Keys.END);
    }

    public void pageDown() {
        CLICK.sendKeys(Keys.PAGE_DOWN);
    }

//    public void waitElementAndClick(WebElement element)
//    {
//        new WebDriverWait(driver,4).until(ExpectedConditions.visibilityOf(element));
//        element.click();
//    }

    public abstract Product getProduct();

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
