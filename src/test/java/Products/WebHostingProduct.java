package Products;

import EmailNotification.ErrorMessage;
import Objects.Addon;
import Objects.Domain;
import Objects.Product;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class WebHostingProduct extends Product {
    private WebDriver driver;
    private Domain productDomain;
    private ArrayList<Addon> productAddons;

    //constructors
    public WebHostingProduct(String productName) {
        super(productName);

    }




    public Domain getProductDomain() {
        return productDomain;
    }

    public void setProductDomain(Domain productDomain) {
        this.productDomain = productDomain;
    }

    public ArrayList<Addon> getProductAddons() {
        Collections.sort(productAddons, addonComparator);
        return productAddons;
    }

    public void setProductAddons(ArrayList<Addon> productAddons) {
        this.productAddons = productAddons;
    }

    private static Comparator<Addon> addonComparator = new Comparator<Addon>() {

        public int compare(Addon o1, Addon o2) {
            return o1.getAddonName().compareTo(o2.getAddonName());
        }
    };

    @Override
    public String toString() {
        return "Product:  " + super.toString() + " domain:" + getProductDomain() + " productAddons:" + getProductAddons();
    }

    //method check product from order page and shopping cart
    public String getErrorShoppingCartPage(Object o) {
        String error = "";
        WebHostingProduct product = (WebHostingProduct) o;
        if (!this.getProductName().equals(product.getProductName())) {
            error = "Error3: Wrong product name: on Order Page it was " + this.getProductName() +
                    ", but in Shopping Cart it's "
                    + product.getProductName() + "\n";
            this.saveScreen("3", "WrongProductNameOrderPage");
            product.saveScreen("3", "WrongProductNameShoppingCart");

            ArrayList<File> screenNamesList = new ArrayList<File>();  //must be in class not in method!!!
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/3/WrongProductNameOrderPage.png"));
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/3/WrongProductNameShoppingCart.png"));
            errorMessages.add(new ErrorMessage(error, "3", screenNamesList));
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
                    + this.getProductPlan().getPlanName() + ", but in Shopping Cart it's: "
                    + product.getProductPlan().getPlanName() + "\n";
            this.saveScreen("5", "WrongPlanNameOrderPage");
            product.saveScreen("5", "WrongPlanNameShoppingCart");

            ArrayList<File> screenNamesList = new ArrayList<File>();  //must be in class not in method!!!
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/5/WrongPlanNameOrderPage.png"));
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/5/WrongPlanNameShoppingCart.png"));
            errorMessages.add(new ErrorMessage(error,"5", screenNamesList));
        }
        if (!this.getProductPlan().getTerm().equals(product.getProductPlan().getTerm())) {
            error = "Error6: For " + this.getProductName() + " product, on Order Page was selected term of plan "
                    + this.getProductPlan().getTerm() + ", but in Shopping Cart it's: "
                    + product.getProductPlan().getTerm() + "\n";
            this.saveScreen("6", "WrongPlanTermOrderPage");
            product.saveScreen("6", "WrongPlanTermShoppingCart");

            ArrayList<File> screenNamesList = new ArrayList<File>();  //must be in class not in method!!!
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/6/WrongPlanTermOrderPage.png"));
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/6/WrongPlanTermShoppingCart.png"));
            errorMessages.add(new ErrorMessage(error, "6", screenNamesList));
        }
        if (!(this.getProductAddons().size() == product.getProductAddons().size())) {
            error = "Error7: For " + this.getProductName() + " wrong count of product Addons, expect productAddons: " + this.getProductAddons()
                    + "\n" + "but found: " + product.getProductAddons() + "\n";
            this.saveScreen("7", "WrongAddonCountOrderPage");
            product.saveScreen("7", "WrongAddonCountShoppingCart");

            ArrayList<File> screenNamesList = new ArrayList<File>();  //must be in class not in method!!!
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/7/WrongAddonCountOrderPage.png"));
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/7/WrongAddonCountShoppingCart.png"));
            errorMessages.add(new ErrorMessage(error, "7", screenNamesList));
        }

        for (int i = 0; i < product.getProductAddons().size(); i++) {
            if (!this.getProductAddons().contains(product.getProductAddons().get(i))) {
                error = "Error8: For " + this.getProductName() +
                        " product, Wrong Addon Name: on Order Page was selected addon "
                        + this.getProductAddons().get(i).getAddonName() +
                        ", but in shopping Cart it's " + product.getProductAddons().get(i).getAddonName() + "\n";

                this.saveScreen("8", "WrongAddonNameOrderPage");
                product.saveScreen("8", "WrongAddonNameShoppingCart");

                ArrayList<File> screenNamesList = new ArrayList<File>();  //must be in class not in method!!!
                screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/8/WrongAddonNameOrderPage.png"));
                screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/8/WrongAddonNameShoppingCart.png"));
                errorMessages.add(new ErrorMessage(error, "8", screenNamesList));
            }
        }

        //ned to change logic to valid
        for (int i = 0; i < this.getProductAddons().size(); i++) {
            if (!this.getProductAddons().get(i).getAddonTerm().equals(product.getProductAddons().get(i).getAddonTerm()))
                error = "Error9: For " + this.getProductName() + " product, on Order Page was selected term " +
                        this.getProductAddons().get(i).getAddonTerm() +
                        ", but in Shopping Cart Term for addon " + this.getProductAddons().get(i).getAddonName() + " found term " +
                        product.getProductAddons().get(i).getAddonTerm() + "\n";

            this.saveScreen("9", "WrongAddonTermOrderPage");
            product.saveScreen("9", "WrongAddonTermShoppingCart");

            ArrayList<File> screenNamesList = new ArrayList<File>();  //must be in class not in method!!!
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/9/WrongAddonTermOrderPage.png"));
            screenNamesList.add(new File("/home/geser/Automation/Sreenshot/TestObjects/Errors/9/WrongAddonTermShoppingCart.png"));
            errorMessages.add(new ErrorMessage(error, "9", screenNamesList));

        }
        return error;
    }

}