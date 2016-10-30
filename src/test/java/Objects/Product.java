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

    public ArrayList<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();

    public ArrayList<ErrorMessage> getErrorMessages(){
        return errorMessages;
    }

    public ArrayList<ErrorMessage> comparePlanPageOrderPageProductsAndGetErrors(Object o) {
        ArrayList<ErrorMessage> errorMessages1 = new ArrayList<ErrorMessage>();
        //here need to refactor method save screen shot and add ro errorMessages
        String error;

        Product product = (Product) o;
        if (!this.getProductName().equals(product.getProductName())) {
            error ="Error1: Wrong product name: on Plan Page it was " + this.getProductName() +
                    ", but on Order Page it's: " + product.getProductName() + "\n";
            this.saveScreen("1", "WrongProductNamePlanPage");
            product.saveScreen("1", "WrongProductNameOrderPage");

            ArrayList<File> screenNamesList = new ArrayList<File>();  //must be in class not in method?!?!?!
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/1/WrongProductNamePlanPage.png"));
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/1/WrongProductNameOrderPage.png"));
            errorMessages1.add(new ErrorMessage(error, "1/", screenNamesList));

            errorMessages.add(new ErrorMessage(error, "1/", screenNamesList));
        }

        if (!this.getProductPlan().getPlanName().equals(product.getProductPlan().getPlanName())) {

            error = "Error2: For " + this.getProductName() + " Product, Wrong Plan Name: on Plan Page it was "
                    + this.getProductPlan().getPlanName() + " but in Order Page it's: " + product.getProductPlan().getPlanName();
            this.saveScreen("2", "WrongPlanNamePlanPage");
            product.saveScreen("2", "WrongPlanNameOrderPage");

            ArrayList<File> screenNamesList = new ArrayList<File>();  //must be in class not in method?!?!?!
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/2/WrongPlanNamePlanPage.png"));
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/2/WrongPlanNameOrderPage.png"));

            errorMessages.add(new ErrorMessage(error, "1/", screenNamesList));
        }
        return errorMessages1;
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
