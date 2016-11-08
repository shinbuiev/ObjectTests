package Utils;

/**
 * Created by Sergiy.K on 08-Nov-16.
 */
public class sdfsdfsdf {
    package Listeners;


import Pages.BasePage;
import Tests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
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

    /**
     * Created by Dmitriy.F on 03.11.2016.
     */
    public class EventHandler implements WebDriverEventListener {
        private String path="";
        private static final Logger LOG = LogManager.getLogger(EventHandler.class);
        private WebElement element;
        private WebDriver driver;
        private String testName;
        private static String errorMessage;
        private long interval;
        private final int count;
        private final String color;

        public EventHandler(String color, int count, long interval, TimeUnit unit) {
            this.color = color;
            this.count = count;
            this.interval = TimeUnit.MILLISECONDS.convert(Math.max(0, interval), unit);
        }


        private void flash(WebElement element, WebDriver driver) {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
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



        public void beforeNavigateTo(String s, WebDriver webDriver) {

            LOG.info("Start running  "+ BaseTest.testName+ " and go to link- "+s);
            driver=webDriver;
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
            path= String.valueOf(by);

        }

        public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
            flash(webElement, webDriver);
        }

        public void afterClickOn(WebElement webElement, WebDriver webDriver) {
            element=webElement;
        }

        public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {
            flash(webElement, webDriver);
            LOG.info("Start changing WebElement @FindBy(\""+path+"\")");
        }

        public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {
            LOG.info("WebElement @FindBy(\""+path+"\") changed value to \'"+webElement.getAttribute("value")+"\'");

        }

        public void beforeScript(String s, WebDriver webDriver) {
        }

        public void afterScript(String s, WebDriver webDriver) {
        }

        public void onException(Throwable throwable, WebDriver webDriver) {
            String []errorMessages=throwable.getMessage().split("\n");
            errorMessage=errorMessages[0].replace("\":\"","=").replace("\"","").replace(":","-").replace("<","").replace(">","").trim();
            LOG.error(errorMessages[0].trim());
            recordStep();
        }

        //---------Screenshot method---------------------------------------------------------------------
        public  void recordStep() {
            try {
                String date = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date());
                File screenS = ((TakesScreenshot) (driver)).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenS, new File("C:\\Automation\\chromedriver\\Screen\\" + BaseTest.testName + "\\" + errorMessage +" "+ date + ".jpg"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
//---------Screenshot method---------------------------------------------------------------------
    }

    public void selectPagesNumber (int planNumber, int pagesNumber) {
        eventDriver.executeScript("scroll(0, " + webSiteBuilderDropdown.get(planNumber-1).getLocation().getY() + ");");
        webSiteBuilderDropdown.get(planNumber-1).click();
        numberPages.get(pagesNumber-1).click();

    }

}
