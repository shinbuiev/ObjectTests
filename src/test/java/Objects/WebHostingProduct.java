package Objects;

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

    //method check product from plan page and order page
    public String getErrorOrderPage(Object o) {
        return super.getErrorOrderPage(o);
    }

    //method check product from order page and shopping cart
    public String getErrorShoppingCartPage(Object o) {
        String error = "";
        WebHostingProduct webHostingProduct = (WebHostingProduct) o;
        if (!this.getProductName().equals(webHostingProduct.getProductName())) {
            error = error + "Error3: Wrong product name: on Order Page it was " + this.getProductName() +
                    ", but in Shopping Cart it's "
                    + webHostingProduct.getProductName() + "\n";
        }

        if (!this.getProductDomain().getDomainName().equals(webHostingProduct.getProductDomain().getDomainName())) {
            error = error + "Error4: Wrong product domain: on Order Page it was " + this.getProductDomain().getDomainName() +
                    ", but in Shopping Cart it's " + webHostingProduct.getProductDomain().getDomainName() + "\n";
        }
        if (!this.getProductPlan().getPlanName().equals(webHostingProduct.getProductPlan().getPlanName())) {
            error = error + "Error5: For " + this.getProductName() + " product, Wrong Plan Name on Order Page it was: "
                    + this.getProductPlan().getPlanName() + ", but in Shopping Cart it's: "
                    + webHostingProduct.getProductPlan().getPlanName() + "\n";
        }
        if (!this.getProductPlan().getTerm().equals(webHostingProduct.getProductPlan().getTerm())) {
            error = error + "Error6: For " + this.getProductName() + " product, on Order Page was selected term of plan "
                    + this.getProductPlan().getTerm() + ", but in Shopping Cart it's: "
                    + webHostingProduct.getProductPlan().getTerm() + "\n";
        }
        if (!(this.getProductAddons().size() == webHostingProduct.getProductAddons().size())) {
            error = error + "Error7: For " + this.getProductName() + " wrong count of productAddons, expect productAddons: " + this.getProductAddons()
                    + "\n" + "but found: " + webHostingProduct.getProductAddons() + "\n";
        }

        for (int i = 0; i < webHostingProduct.getProductAddons().size(); i++) {
            if (!this.getProductAddons().contains(webHostingProduct.getProductAddons().get(i))) {
                error = error + "Error8: For " + this.getProductName() +
                        " product, Wrong Addon Name: on Order Page was selected addon "
                        + this.getProductAddons().get(i).getAddonName() +
                        ", but in shopping Cart it's " + webHostingProduct.getProductAddons().get(i).getAddonName() + "\n";
            }
        }

        //ned to change logic to valid
        for (int i = 0; i < this.getProductAddons().size(); i++) {
            if (!this.getProductAddons().get(i).getAddonTerm().equals(webHostingProduct.getProductAddons().get(i).getAddonTerm()))
                error = error + "Error9: For " + this.getProductName() + " product, on Order Page was selected term " +
                        this.getProductAddons().get(i).getAddonTerm() +
                        ", but in Shopping Cart Term for addon " + this.getProductAddons().get(i).getAddonName() + " found term " +
                        webHostingProduct.getProductAddons().get(i).getAddonTerm() + "\n";
        }
        return error;
    }

    //this must be remove to expected product methods!!!
    // method check webHostingProduct specification
//    public String isProduct(WebHostingProduct webHostingProduct) {
//        String error = "";
//        if (!this.getProductName().equals(webHostingProduct.getProductName())) {
//            error = error + "Specification Error1: Wrong webHostingProduct name: on Order Page it was " +
//                    this.getProductName() + ", but in Shopping Cart it's: " + webHostingProduct.getProductName() + "\n";
//        }

//        if (!this.getProductPlans().contains(webHostingProduct.getProductPlan())) {
//            error = error + "Specification Error2: Wrong plan name: on Order Page it was " + this.getProductPlans() +
//                    ", but in Shopping Cart it's: " + webHostingProduct.getProductPlan() + " specific error" + "\n";
//        }
//
//        for (int i = 0; i < webHostingProduct.getProductAddons().size(); i++) {
//            if (!this.getProductAddons().contains(webHostingProduct.getProductAddons().get(i))) {
//                error = error + "Specification Error3: Wrong addon name: on Order Page it was " + this.getProductAddons() +
//                        ", but in Shopping Cart it's: " + webHostingProduct.getProductAddons().get(i) + "\n";
//            }
//        }
//
//        return error;
//    }

}