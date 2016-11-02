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
    public ArrayList<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();

    public Product(String productName) {
        this.productName = productName;
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

    public ArrayList<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }

    public void comparePlanPageOrderPageProductsAndGetErrors(Object o) {
        String error;
        Product product = (Product) o;
        if (!this.getProductName().equals(product.getProductName())) {
            error = "Error1: Wrong product name: on Plan Page it was " + this.getProductName() +
                    ", but on Order Page it's: " + product.getProductName() + "\n";
            errorMessages.add(new ErrorMessage(error));
        }
    }

    public abstract void getErrorShoppingCartPage(Object o);

    public void saveScreen(String folderForScreen, String nameFile) {
        try {
            File f1 = screenFile;
            FileUtils.copyFile(screenFile, screenFile =
                    new File("/home/geser/Automation/SreenShot/TestObjects/Errors/"
                            + folderForScreen + "/" +
                            nameFile + ".png"));
        } catch (IOException e) {
            System.out.println("cant create a screen shot \n" + e.getMessage());
        }
    }

    public void takeScreenshot() {
        screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }


    @Override
    public String toString() {
        return "Product: " + productName + " price:" + productPrice;
    }

}
