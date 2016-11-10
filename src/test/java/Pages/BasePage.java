package Pages;

import Tests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dmitriy.F on 02.11.2016.
 */
public abstract class BasePage {
    private EventFiringWebDriver eventDriver;



    //----Top menu -------------------------------------



    //--------------------------------------------------------------

    public BasePage(EventFiringWebDriver eventDriver) {
        PageFactory.initElements(eventDriver,this);
        this.eventDriver = eventDriver;
        eventDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }


    public void waitForElement(WebElement element) {
        new WebDriverWait(eventDriver,4).until(ExpectedConditions.visibilityOf(element));
    }


    public void randomClick(List<WebElement> list){
        try {
            list.get((int) (Math.random() * list.size())).click();
        }catch (Exception e){
        }
    }


    //---------Screenshot method----------------------------------------------------------------------------
    public  static void takeScreen(EventFiringWebDriver recDriver,String eoorMess) {
        try {
            String date = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date());
            File screenS = ((TakesScreenshot) (recDriver)).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenS, new File("C:\\Automation\\chromedriver\\Screen\\" + BaseTest.testName + "\\" + eoorMess +" "+ date + ".jpg"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//---------Screenshot method-------------------------------------------------------------------------------



}
