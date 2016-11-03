package Interfaces.ExpectedProducts;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Sergiy.K on 03-Nov-16.
 */
public class EventFiringWebDriverWrapper extends EventFiringWebDriver {

    public EventFiringWebDriverWrapper(WebDriver driver) {
        super(driver);
    }

    @Override
    public List<WebElement> findElements(By by) {
        return super.findElements(by);
    }


    @Override
    public WebElement findElement(By by) {
        WebElement element;
        try {
            element = super.findElement(by);
        } catch (NoSuchElementException e) {
            System.out.println("ERROR" + e.getMessage()) ;
            new WebDriverWait(this, 4).until(ExpectedConditions.invisibilityOfElementLocated(by));
            element = super.findElement(by);
        }
        return element;
    }
}
