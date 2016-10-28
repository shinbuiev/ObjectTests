package Products;

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
            this.saveScreenShot(product.getClass().getName(),"error3OrderPage");
            product.saveScreenShot(product.getClass().getName(),"error3ShoppingCart");
        }

        if (!this.getProductDomain().getDomainName().equals(product.getProductDomain().getDomainName())) {
            error = error + "Error4: Wrong product domain: on Order Page it was " + this.getProductDomain().getDomainName() +
                    ", but in Shopping Cart it's " + product.getProductDomain().getDomainName() + "\n";
            this.saveScreenShot(product.getClass().getName(),"error4OrderPage");
            product.saveScreenShot(product.getClass().getName(),"error4ShoppingCart");
        }
        if (!this.getProductPlan().getPlanName().equals(product.getProductPlan().getPlanName())) {
            error = error + "Error5: For " + this.getProductName() + " product, Wrong Plan Name on Order Page it was: "
                    + this.getProductPlan().getPlanName() + ", but in Shopping Cart it's: "
                    + product.getProductPlan().getPlanName() + "\n";
            this.saveScreenShot(product.getClass().getName(),"error5OrderPage");
            product.saveScreenShot(product.getClass().getName(),"error5ShoppingCart");
        }
        if (!this.getProductPlan().getTerm().equals(product.getProductPlan().getTerm())) {
            error = error + "Error6: For " + this.getProductName() + " product, on Order Page was selected term of plan "
                    + this.getProductPlan().getTerm() + ", but in Shopping Cart it's: "
                    + product.getProductPlan().getTerm() + "\n";
            this.saveScreenShot(product.getClass().getName(),"error6OrderPage");
            product.saveScreenShot(product.getClass().getName(),"error6ShoppingCart");
        }
        if (!(this.getProductAddons().size() == product.getProductAddons().size())) {
            error = error + "Error7: For " + this.getProductName() + " wrong count of productAddons, expect productAddons: " + this.getProductAddons()
                    + "\n" + "but found: " + product.getProductAddons() + "\n";
            this.saveScreenShot(product.getClass().getName(),"error7OrderPage");
            product.saveScreenShot(product.getClass().getName(),"error7ShoppingCart");
        }

        for (int i = 0; i < product.getProductAddons().size(); i++) {
            if (!this.getProductAddons().contains(product.getProductAddons().get(i))) {
                error = error + "Error8: For " + this.getProductName() +
                        " product, Wrong Addon Name: on Order Page was selected addon "
                        + this.getProductAddons().get(i).getAddonName() +
                        ", but in shopping Cart it's " + product.getProductAddons().get(i).getAddonName() + "\n";
                this.saveScreenShot(product.getClass().getName(),"error8OrderPage");
                product.saveScreenShot(product.getClass().getName(),"error8ShoppingCart");
            }
        }

        //ned to change logic to valid
        for (int i = 0; i < this.getProductAddons().size(); i++) {
            if (!this.getProductAddons().get(i).getAddonTerm().equals(product.getProductAddons().get(i).getAddonTerm()))
                error = error + "Error9: For " + this.getProductName() + " product, on Order Page was selected term " +
                        this.getProductAddons().get(i).getAddonTerm() +
                        ", but in Shopping Cart Term for addon " + this.getProductAddons().get(i).getAddonName() + " found term " +
                        product.getProductAddons().get(i).getAddonTerm() + "\n";
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