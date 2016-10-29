package Tests;

import EmailNotification.Email;
import EmailNotification.TestScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sergiy.K on 28-Oct-16.
 */
public class TestEmail {
    public static EventFiringWebDriver driver;
    @BeforeSuite
    public void initEnv() {

        if (System.getProperty("os.name").equals("Linux"))
        {
            System.setProperty("webdriver.chrome.driver", "/home/geser/IdeaProjects/chromedriver"); //Chrome driver linux
        }
        if (System.getProperty("os.name").equals("Windows"))
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
        //WebDriver webDriver = new ChromeDriver();
        driver = new EventFiringWebDriver(webDriver);
        //use this Highlight if need, when you debug your test
//        driver.register(new ListenerThatHiglilightsElements("#FFFF00", 1, 200, TimeUnit.MILLISECONDS));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

//    public void saveScreenShot(String folderForScreen, String nameFile) {
//            FileUtils.copyFile(screenFile, screenFile =
//                    new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/"
//                            + folderForScreen + "/" +
//                            nameFile + ".jpg"));
    @Test
    public void sendEmail() throws Exception {
        System.out.println(System.getProperty("os.name"));
        driver.get("http://google.com.ua");
        TestScreenshot screen = new TestScreenshot(driver);
        screen.takeScreenshot();
        screen.saveScreenShot("TestFolder", "TestImage1");
        Email email = new Email();
        email.execute("fdsfdsfsfa", "/home/geser/Automation/Sreenshot/TestObjects/Errors/TestFolder", "TestImage1.png");

    }

    @AfterTest
    public void evnSgut() {
        if (driver != null)
            driver.quit();
    }
}
