package Objects;

import EmailNotification.Email;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static Tests.HostingBuyTest.driver;

/**
 * Created by Sergiy.K on 28-Oct-16.
 */
public abstract class Product {

    private File scrFile;
    private String productName;
    private Price productPrice;
    private Term productTerm;
    private Plan productPlan;

    public Email email = new Email();

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

    //method check product from plan page and order page
    public String getErrorOrderPage(Object o) {
        String error = "";
        Product product = (Product) o;
        if (!this.getProductName().equals(product.getProductName())) {
            error = error + "Error1: Wrong product name: on Plan Page it was " + this.getProductName() +
                    ", but on Order Page it's: "
                    + product.getProductName() + "\n";
            this.saveScreenShot(product.getClass().getName(), "error1PlanPage");
            product.saveScreenShot(product.getClass().getName(), "error1OrderPage");
        }
        if (!this.getProductPlan().getPlanName().equals(product.getProductPlan().getPlanName())) {
            error = error + "Error2: For " + this.getProductName() + " webHostingProduct, Wrong Plan Name: on Plan Page it was "
                    + this.getProductPlan().getPlanName() + " but in Order Page it's: " + product.getProductPlan().getPlanName()
                    + "\n";
            this.saveScreenShot(product.getClass().getName(), "error2PlanPage");
            product.saveScreenShot(product.getClass().getName(), "error2OrderPage");
        }
        return error;
    }

    public abstract String getErrorShoppingCartPage(Object o);

    @Override
    public String toString() {
        return "Product: " + productName + " price:" + productPrice;
    }

    public void takeScreenshot() {
        scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    public void deleteScreenshot() {
        scrFile.delete();
    }

    public void saveScreenShot(String nameForScreen, String number) {
        try {
            FileUtils.copyFile(scrFile, new File("C:\\Automation\\Screenshot\\TestObjects\\Errors\\" +
                    nameForScreen + "\\" + number + ".jpg"));
        } catch (IOException e) {
            System.out.println("cant create a screen shot " + e.getMessage());
        }

    }

}
