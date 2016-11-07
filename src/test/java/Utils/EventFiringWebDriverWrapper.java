package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Sergiy.K on 03-Nov-16.
 */

import static Tests.BasicTest.driver;

public class EventFiringWebDriverWrapper extends EventFiringWebDriver {

    public EventFiringWebDriverWrapper(WebDriver driver) {
        super(driver);
    }

    @Override
    public List<WebElement> findElements(By by) {
        return super.findElements(by);
    }

    private WebElement findElementTest(By by) {
        return super.findElement(by);
    }

    @Override
    public WebElement findElement(By by) {
//        waitFullLoading();
        WebElement element;
        try {
//            System.out.println("first trying");
            element = findElementTest(by);
//            element = super.findElement(by);
        } catch (NoSuchElementException e) {

            System.out.println("No such element");
            System.out.println("ERROR" + e.getMessage());
//            new WebDriverWait(driver, 4).until(ExpectedConditions.invisibilityOfElementLocated(by));
//            element = super.findElement(by);
            element = findElementTest(by);
        } catch (StaleElementReferenceException r) {
            System.out.println("StaleElementReference Exception");
            element = findElement(by);
        } catch (WebDriverException r) {
            System.out.println("WebDriver Exception");
            element = findElement(by);
        }

        return element;
    }


    @Override
    public Object executeScript(String script, Object... args) {
        return super.executeScript(script, args);
    }

    public static void waitFullLoading() {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")));
            }
        });
    }


}
