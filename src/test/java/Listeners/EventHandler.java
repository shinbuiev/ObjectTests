package Listeners;


import Pages.BasePage;
import Tests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static Pages.BasePage.takeScreen;

/**
 * Created by Dmitriy.F on 03.11.2016.
 */
public class EventHandler  implements WebDriverEventListener {
    private EventFiringWebDriver eventDriver;

    public static final Logger LOG = LogManager.getLogger(EventHandler.class);
    private static String errorMessage;
    private long interval;
    private final int count;
    private final String color;


//----------Constructor -------------------------------------------------------------------------------
    public EventHandler(EventFiringWebDriver eventDriver,String color, int count, long interval, TimeUnit unit) {
        this.eventDriver=eventDriver;
        this.color = color;
        this.count = count;
        this.interval = TimeUnit.MILLISECONDS.convert(Math.max(0, interval), unit);
    }
//----------Constructor --------------------------------------------------------------------------------

//----------Light UP methods ---------------------------------------------------------------------------
    private void flash(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("scrollTo(0, " + (element.getLocation()
        .getY()-driver.manage().window().getSize().getHeight()/2) + ")"); //Super scroll
        String bgcolor = element.getCssValue("backgroundColor");
        for (int i = 0; i < count; i++) {
            changeColor(color, element, js);
            changeColor(bgcolor, element, js);
        }
    }

    private void changeColor(String color, WebElement element, JavascriptExecutor js) {
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//----------Light UP methods ---------------------------------------------------------------------------

//----------Listeners ----------------------------------------------------------------------------------
    public void beforeNavigateTo(String s, WebDriver webDriver) {
        LOG.info("Start running  "+ BaseTest.testName+ " and go to link- "+s);
    }

    public void afterNavigateTo(String s, WebDriver webDriver) {

    }

    public void beforeNavigateBack(WebDriver webDriver) {

    }

    public void afterNavigateBack(WebDriver webDriver) {

    }

    public void beforeNavigateForward(WebDriver webDriver) {

    }

    public void afterNavigateForward(WebDriver webDriver) {

    }

    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }

    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
    }

    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        flash(webElement, webDriver);
    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
    }

    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {
        flash(webElement, webDriver);
    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {
        flash(webElement, webDriver);
    }

    public void beforeScript(String s, WebDriver webDriver) {
    }

    public void afterScript(String s, WebDriver webDriver) {
    }

    public void onException(Throwable throwable, WebDriver webDriver) {
        String []errorMessages=throwable.getMessage().split("\n");
        errorMessage=errorMessages[0].replace("\":\"","=").replace("\"","").replace(":","-").replace("<","").replace(">","").replace("/","").trim();
        LOG.error(errorMessages[0].trim());
        takeScreen(eventDriver,errorMessage);
    }
//----------Listeners ----------------------------------------------------------------------------------


}
