package Products;

import EmailNotification.ErrorMessage;
import EmailNotification.TestScreenshot;
import Objects.*;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by geser on 27.10.16.
 */
public class SSLproduct extends Product {
    private Domain productDomain;

    public SSLproduct(String productName) {
        super(productName);
    }

    public Domain getProductDomain() {
        return productDomain;
    }

    public void setProductDomain(Domain productDomain) {
        this.productDomain = productDomain;
    }

    //method check product from order page and shopping cart
    public String getErrorShoppingCartPage(Object o) {
        String error = "";
        SSLproduct product = (SSLproduct) o;


        if (!this.getProductName().equals(product.getProductName())) {
            error = "Error3: Wrong product name: on Order Page it was " + this.getProductName() +
                    ", but in Shopping Cart it's " + product.getProductName() + "\n";
            this.saveScreen("3", "WrongProductNameOrderPage");
            product.saveScreen("3", "WrongProductNameShoppingCart");

            ArrayList<File> screenNamesList = new ArrayList<File>();  //must be in class not in method!!!
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/3/WrongProductNameOrderPage.png"));
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/3/WrongProductNameShoppingCart.png"));
            errorMessages.add(new ErrorMessage(error,"3", screenNamesList));
        }
        if (!this.getProductDomain().getDomainName().equals(product.getProductDomain().getDomainName())) {
            error = "Error4: Wrong product domain: on Order Page it was " + this.getProductDomain().getDomainName() +
                    ", but in Shopping Cart it's " + product.getProductDomain().getDomainName() + "\n";
            this.saveScreen("4", "WrongProductDomainOrderPage");
            product.saveScreen("4", "WrongProductDomainShoppingCart");

            ArrayList<File> screenNamesList = new ArrayList<File>();  //must be in class not in method!!!
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/4/WrongProductDomainOrderPage.png"));
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/4/WrongProductDomainShoppingCart.png"));
            errorMessages.add(new ErrorMessage(error,"4", screenNamesList));
        }
        if (!this.getProductPlan().getPlanName().equals(product.getProductPlan().getPlanName())) {
            error = "Error5: For " + this.getProductName() + " product, Wrong Plan Name on Order Page it was: "
                    + this.getProductPlan().getPlanName() + ", but in Shopping Cart it's: " + product.getProductPlan().getPlanName() + "\n";
            this.saveScreen("5", "WrongProductDomainOrderPage");
            product.saveScreen("5", "WrongProductDomainShoppingCart");

            ArrayList<File> screenNamesList = new ArrayList<File>();  //must be in class not in method!!!
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/5/WrongPlanNameOrderPage.png"));
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/5/WrongPlanNameShoppingCart.png"));
            errorMessages.add(new ErrorMessage(error,"5", screenNamesList));
        }
        if (!this.getProductPlan().getTerm().equals(product.getProductPlan().getTerm())) {
            error = error + "Error6: For " + this.getProductName() + " product, on Order Page was selected term of plan "
                    + this.getProductPlan().getTerm() + ", but in Shopping Cart it's: "
                    + product.getProductPlan().getTerm() + "\n";
            this.saveScreen("6", "WrongPlanTermOrderPage");
            product.saveScreen("6", "WrongPlanTermShoppingCart");

            ArrayList<File> screenNamesList = new ArrayList<File>();  //must be in class not in method!!!
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/6/WrongPlanTermOrderPage.png"));
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/6/WrongPlanTermShoppingCart.png"));
            errorMessages.add(new ErrorMessage(error,"6", screenNamesList));
        }
        return error;
    }

    @Override
    public String toString() {
        return super.toString() + " domain:" + getProductDomain();
    }

}
