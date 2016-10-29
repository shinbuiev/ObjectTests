package EmailNotification;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static Tests.HostingBuyTest.driver;

/**
 * Created by geser on 29.10.16.
 */
public class TestScreenshot {
    public static File screenFile;
    public static ArrayList<String> screenShotNameList = new ArrayList<String>();  //maybe need to delete this
    public static ArrayList<String> screenShotFolderList = new ArrayList<String>();//maybe need to delete this

    public static void saveScreenShot(String folderForScreen, String nameFile) {
        try {
//        if (System.getProperty("os.name").equals("Linux"))   need add possibility for windows/unix system

            FileUtils.copyFile(screenFile, screenFile =
                    new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/"
                            + folderForScreen + "/" +
                              nameFile + ".png"));

            screenShotFolderList.add("/home/geser/Automation/Sreenshot/TestObjects/Errors/" + folderForScreen);
            screenShotNameList.add(nameFile);

        } catch (IOException e) {
            System.out.println("cant create a screen shot \n" + e.getMessage());
        }
    }

    public static void takeScreenshot() {
        screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

//    public void deleteScreenshot() {
//        screenFile.delete();
//    }
}