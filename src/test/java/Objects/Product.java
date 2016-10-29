package Objects;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import EmailNotification.ErrorMessage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static Tests.HostingBuyTest.driver;

/**
 * Created by Sergiy.K on 28-Oct-16.
 */
public abstract class Product {
    public File screenFile;
    private String productName;
    private Price productPrice;
    private Term productTerm;
    private Plan productPlan;

    public Product(String productName) {
        this.productName = productName;
        takeScreenshot();
    }

    public ArrayList<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();

    public ArrayList<ErrorMessage> getErrorMessages(){
        return errorMessages;
    }

    public String getProductName() {
        return productName;
    }

    public Price getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Price productPrice) {
        this.productPrice = productPrice;
    }

    public Term getProductTerm() {
        return productTerm;
    }

    public void setProductTerm(Term productTerm) {
        this.productTerm = productTerm;
    }

    public Plan getProductPlan() {
        return productPlan;
    }

    public void setProductPlan(Plan productPlan) {
        this.productPlan = productPlan;
    }

//    public void saveScreen(String folderName, String imageName){
//        TestScreenshot.saveScreenShot(folderName, imageName);
//    }

    public ArrayList<ErrorMessage> getErrorMessagesListOrderPage(Object o) {
        ArrayList<ErrorMessage> errorMessages1 = new ArrayList<ErrorMessage>();

        String error = "";
        String er1;
        String er2;
        ArrayList<File> screenNamesList = new ArrayList<File>();
        Product product = (Product) o;
        if (!this.getProductName().equals(product.getProductName() + " errrrrr")) {
            er1 ="Error1: Wrong product name: on Plan Page it was " + this.getProductName() +
                    ", but on Order Page it's: " + product.getProductName() + "\n";
            this.saveScreen("1", "WrongProductNamePlanPage");
            product.saveScreen("1", "WrongProductNameOrderPage");

            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/1/WrongProductNamePlanPage.png"));
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/1/WrongProductNameOrderPage.png"));

            errorMessages1.add(new ErrorMessage(er1, "1/", screenNamesList));
        }

        if (!this.getProductPlan().getPlanName().equals(product.getProductPlan().getPlanName() + " wrong plan name")) {
            er2 = "Error2: For " + this.getProductName() + " webHostingProduct, Wrong Plan Name: on Plan Page it was "
                    + this.getProductPlan().getPlanName() + " but in Order Page it's: " + product.getProductPlan().getPlanName();
            this.saveScreen("2", "WrongPlanNamePlanPage");
            product.saveScreen("2", "WrongPlanNameOrderPage");

            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/2/WrongPlanNamePlanPage.png"));
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/2/WrongPlanNameOrderPage.png"));

            errorMessages1.add(new ErrorMessage(er2, "2", screenNamesList));
        }
        return errorMessages1;
    }

    //method check product from plan page and order page
    public String getErrorOrderPage(Object o) {

        String error = "";
        String er1;
        Product product = (Product) o;
        if (!this.getProductName().equals(product.getProductName() + " errrrrr")) {
            error = error + "Error1: Wrong product name: on Plan Page it was " + this.getProductName() +
                    ", but on Order Page it's: " + product.getProductName()+ "errrrrrrrrrr" + "\n";
            er1 = error + "Error1: Wrong product name: on Plan Page it was " + this.getProductName() +
                    ", but on Order Page it's: " + product.getProductName() + "\n";
            this.saveScreen("1", "WrongProductNamePlanPage");
            product.saveScreen("1", "WrongProductNameOrderPage");

//            ArrayList<File> screenNamesList = new ArrayList<File>();
//            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/1/WrongProductNamePlanPage.png"));
//            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/1/WrongProductNameOrderPage.png"));
//            errorMessages.add(new ErrorMessage(er1, "1", screenNamesList));
        }
        if (!this.getProductPlan().getPlanName().equals(product.getProductPlan().getPlanName() + " wrong plan name")) {
            error = error + "Error2: For " + this.getProductName() + " webHostingProduct, Wrong Plan Name: on Plan Page it was "
                    + this.getProductPlan().getPlanName() + " but in Order Page it's: " + product.getProductPlan().getPlanName() + " wrong plan name";
            er1 = error + "Error2: For " + this.getProductName() + " webHostingProduct, Wrong Plan Name: on Plan Page it was "
                    + this.getProductPlan().getPlanName() + " but in Order Page it's: " + product.getProductPlan().getPlanName();
            this.saveScreen("2", "WrongPlanNamePlanPage");
            product.saveScreen("2", "WrongPlanNameOrderPage");

//            ArrayList<File> screenNamesList = new ArrayList<File>();
//            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/2/WrongPlanNamePlanPage.png"));
//            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/2/WrongPlanNameOrderPage.png"));

        }
        return error;
    }

    public abstract String getErrorShoppingCartPage(Object o);

    @Override
    public String toString() {
        return "Product: " + productName + " price:" + productPrice;
    }

    public void saveScreen(String folderForScreen, String nameFile) {
        try {
//        if (System.getProperty("os.name").equals("Linux"))   need add possibility for windows/unix system

            FileUtils.copyFile(screenFile, screenFile =
                    new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/"
                            + folderForScreen + "/" +
                            nameFile + ".png"));

        } catch (IOException e) {
            System.out.println("cant create a screen shot \n" + e.getMessage());
        }
    }

    public void takeScreenshot() {
        screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }
}
