package EmailNotification;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static Tests.HostingBuyTest.driver;

/**
 * Created by geser on 29.10.16.
 */
public class TestScreenshot {
    public static File screenFile;
    String fileName;
    String screenPath;
    public static String fileAbsolutePath;
    public static EventFiringWebDriver driver;

    public static ArrayList<String> screenShotNameList;
    public static ArrayList<String> screenShotFolderList;

    public TestScreenshot(EventFiringWebDriver driver){
    this.driver = driver;
    }

    public static void saveScreenShot(String folderForScreen, String nameFile) {
        try {
//        if (System.getProperty("os.name").equals("Linux"))   need add possibility for windows/unix system

            FileUtils.copyFile(screenFile, screenFile =
                    new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/"
                            + folderForScreen + "/" +
                              nameFile + ".png"));

            screenShotFolderList.add("/home/geser/Automation/Sreenshot/TestObjects/Errors/" + folderForScreen);
            screenShotNameList.add(nameFile);

            System.out.println(fileAbsolutePath.replace(nameFile + ".png",""));

        } catch (IOException e) {
            System.out.println("cant create a screen shot \n" + e.getMessage());
        }
    }
    public static void takeScreenshot() {
        screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    public void deleteScreenshot() {
        screenFile.delete();
    }
}