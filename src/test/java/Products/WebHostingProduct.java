package Products;

import EmailNotification.TestScreenshot;
import Objects.Addon;
import Objects.Domain;
import Objects.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class WebHostingProduct extends Product {

    private Domain productDomain;
    private ArrayList<Addon> productAddons;
    private String productPlanPageUrl; //maybe not need this

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
            error = error + "Error3: Wrong product name: on Order Page it was " + this.getProductName() +
                    ", but in Shopping Cart it's "
                    + product.getProductName() + "\n";
            TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongProductNameOrderPage");
            TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongProductNameShoppingCart");
        }

        if (!this.getProductDomain().getDomainName().equals(product.getProductDomain().getDomainName())) {
            error = error + "Error4: Wrong product domain: on Order Page it was " + this.getProductDomain().getDomainName() +
                    ", but in Shopping Cart it's " + product.getProductDomain().getDomainName() + "\n";
            TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongProductDomainOrderPage");
            TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongProductDomainShoppingCart");
        }
        if (!this.getProductPlan().getPlanName().equals(product.getProductPlan().getPlanName())) {
            error = error + "Error5: For " + this.getProductName() + " product, Wrong Plan Name on Order Page it was: "
                    + this.getProductPlan().getPlanName() + ", but in Shopping Cart it's: "
                    + product.getProductPlan().getPlanName() + "\n";
            TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongPlanNameOrderPage");
            TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongPlanNameShoppingCart");
        }
        if (!this.getProductPlan().getTerm().equals(product.getProductPlan().getTerm())) {
            error = error + "Error6: For " + this.getProductName() + " product, on Order Page was selected term of plan "
                    + this.getProductPlan().getTerm() + ", but in Shopping Cart it's: "
                    + product.getProductPlan().getTerm() + "\n";
            TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongPlanTermOrderPage");
            TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongPlanTermShoppingCart");
        }
        if (!(this.getProductAddons().size() == product.getProductAddons().size())) {
            error = error + "Error7: For " + this.getProductName() + " wrong count of product Addons, expect productAddons: " + this.getProductAddons()
                    + "\n" + "but found: " + product.getProductAddons() + "\n";
            TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongCountAddonsOrderPage");
            TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongCountAddonsShoppingCart");
        }

        for (int i = 0; i < product.getProductAddons().size(); i++) {
            if (!this.getProductAddons().contains(product.getProductAddons().get(i))) {
                error = error + "Error8: For " + this.getProductName() +
                        " product, Wrong Addon Name: on Order Page was selected addon "
                        + this.getProductAddons().get(i).getAddonName() +
                        ", but in shopping Cart it's " + product.getProductAddons().get(i).getAddonName() + "\n";
                TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongAddonNameOrderPage");
                TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongAddonNameShoppingCart");
            }
        }

        //ned to change logic to valid
        for (int i = 0; i < this.getProductAddons().size(); i++) {
            if (!this.getProductAddons().get(i).getAddonTerm().equals(product.getProductAddons().get(i).getAddonTerm()))
                error = error + "Error9: For " + this.getProductName() + " product, on Order Page was selected term " +
                        this.getProductAddons().get(i).getAddonTerm() +
                        ", but in Shopping Cart Term for addon " + this.getProductAddons().get(i).getAddonName() + " found term " +
                        product.getProductAddons().get(i).getAddonTerm() + "\n";
            TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongAddonTermOrderPage");
            TestScreenshot.saveScreenShot(product.getClass().getName(), "WrongAddonTermShoppingCart");
        }
        return error;
    }

}