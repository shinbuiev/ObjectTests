package Products;

import EmailNotification.ErrorMessage;
import Objects.Addon;
import Objects.Domain;
import Objects.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by geser on 31.10.16.
 */
public class EmailHostingProduct extends Product {

    private ArrayList<Addon> productAddons;
    private Domain productDomain;

    //constructors
    public EmailHostingProduct(String productName) {
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
    public void getErrorShoppingCartPage(Object o) {
        String error = "";
        EmailHostingProduct product = (EmailHostingProduct) o;
        if (!this.getProductName().equals(product.getProductName())) {
            error = "Error3: Wrong product name: on Order Page it was " + this.getProductName() +
                    ", but in Shopping Cart it's "
                    + product.getProductName() + "\n";
            errorMessages.add(new ErrorMessage(error));
        }

        if (!this.getProductDomain().getDomainName().equals(product.getProductDomain().getDomainName())) {
            error = "Error4: Wrong product domain: on Order Page it was " + this.getProductDomain().getDomainName() +
                    ", but in Shopping Cart it's " + product.getProductDomain().getDomainName() + "\n";

            errorMessages.add(new ErrorMessage(error));
        }
        if (!this.getProductPlan().getPlanName().equals(product.getProductPlan().getPlanName())) {
            error = "Error5: For " + this.getProductName() + " product, Wrong Plan Name on Order Page it was: "
                    + this.getProductPlan().getPlanName() + ", but in Shopping Cart it's: "
                    + product.getProductPlan().getPlanName() + "\n";
            errorMessages.add(new ErrorMessage(error));
        }
        if (!this.getProductPlan().getTerm().equals(product.getProductPlan().getTerm())) {
            error = "Error6: For " + this.getProductName() + " product, on Order Page was selected term of plan "
                    + this.getProductPlan().getTerm() + ", but in Shopping Cart it's: "
                    + product.getProductPlan().getTerm() + "\n";

            errorMessages.add(new ErrorMessage(error));
        }
        if (!(this.getProductAddons().size() == product.getProductAddons().size())) {
            error = "Error7: For " + this.getProductName() + " wrong count of product Addons, expect productAddons: " + this.getProductAddons()
                    + "\n" + "but found: " + product.getProductAddons() + "\n";
            errorMessages.add(new ErrorMessage(error));
        }

        for (int i = 0; i < product.getProductAddons().size(); i++) {
            if (!this.getProductAddons().contains(product.getProductAddons().get(i))) {
                error = "Error8: For " + this.getProductName() +
                        " product, Wrong Addon Name: on Order Page was selected addon "
                        + this.getProductAddons().get(i).getAddonName() +
                        ", but in shopping Cart it's " + product.getProductAddons().get(i).getAddonName() + "\n";
                errorMessages.add(new ErrorMessage(error));
            }
        }

        //ned to change logic to valid
        for (int i = 0; i < this.getProductAddons().size(); i++) {
            if (!this.getProductAddons().get(i).getAddonTerm().equals(product.getProductAddons().get(i).getAddonTerm()))
                error = "Error9: For " + this.getProductName() + " product, on Order Page was selected term " +
                        this.getProductAddons().get(i).getAddonTerm() +
                        ", but in Shopping Cart Term for addon " + this.getProductAddons().get(i).getAddonName() + " found term " +
                        product.getProductAddons().get(i).getAddonTerm() + "\n";
            errorMessages.add(new ErrorMessage(error));
        }
    }
}
