package Tests;

import Utils.EventFiringWebDriverWrapper;
import Utils.ListenerThatHiglilightsElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

/**
 * Created by geser on 31.10.16.
 */
public class BasicTest {
    public static EventFiringWebDriverWrapper driver;

    @BeforeSuite
    public void initEnv() {

        if (System.getProperty("os.name").equals("Linux"))
        {
            System.setProperty("webdriver.chrome.driver", "/home/geser/IdeaProjects/chromedriver"); //Chrome driver linux
        }
        if (System.getProperty("os.name").equals("Windows 10"))
        {
            System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver\\chromedriver.exe"); //Chrome driver windows
        }

        java.lang.String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64; Dreamscape/1.0;) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36";
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--disable-extensions");
        co.addArguments("--user-agent=" + userAgent);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, co);
        WebDriver webDriver = new ChromeDriver(cap);
        driver = new EventFiringWebDriverWrapper(webDriver);
        driver.register(new ListenerThatHiglilightsElements("#FFFF00 ", 1, 50, TimeUnit.MILLISECONDS));
//        driver = new EventFiringWebDriver(webDriver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }
}
