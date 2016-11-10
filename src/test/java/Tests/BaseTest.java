package Tests;

import Listeners.EventHandler;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dmitriy.F on 07.11.2016.
 */
public abstract class BaseTest {
    private WebDriver driver;
    private EventFiringWebDriver eventDriver;
    public static String testName;


    public void initial(){
        this.testName=this.getClass().getName();
        System.setProperty("webdriver.chrome.driver", "C:\\\\Automation\\\\chromedriver\\\\chromedriver.exe");
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOADING_STRATEGY,"eagle");
        driver=new ChromeDriver(capabilities);
        eventDriver=new EventFiringWebDriver(driver);
        eventDriver.register(new EventHandler(eventDriver,"#FFFF00", 1, 300, TimeUnit.MILLISECONDS));
        eventDriver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public EventFiringWebDriver getEventDriver() {
        return eventDriver;
    }
}
