package Objects;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class Product {
    private static final Logger LOGGER = Logger.getLogger(Product.class);

    private String productName;
    private Price productPrice;
    private Addon addon;
    private Term term;
    private Plan plan;

    private ArrayList<Plan> plans;
    private ArrayList<Term> terms;
    private ArrayList<Addon> addons;

    private Domain productDomain;

    public Domain getProductDomain() {
        return productDomain;
    }

    public void setProductDomain(Domain productDomain) {
        this.productDomain = productDomain;
    }

    private String productPlanPageUrl;

    public Product(String productName) {
        this.productName = productName;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Product(String productName, String Url) {
        this.productName = productName;
        this.productPlanPageUrl = Url;
    }


    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }

    public Addon getAddon() {
        return addon;
    }



    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setPlanTerm(Term term) {
        this.plan.setTerm(term);
    }

    public void plansToString() {
        for (int i = 0; i < plans.size(); i++) {
            System.out.println(plans.get(i).planToString());
        }
    }

    public ArrayList<Plan> getPlans() {
        return plans;
    }


    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Price getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Price productPrice) {
        this.productPrice = productPrice;
    }

    public ArrayList<Addon> getAddons() {
        Collections.sort(addons, addonComparator);
        return addons;
    }

    static Comparator<Addon> addonComparator = new Comparator<Addon>() {

        public int compare(Addon o1, Addon o2) {
            return o1.getAddonName().compareTo(o2.getAddonName());
        }
    };

    public void setAddons(ArrayList<Addon> addons) {
        this.addons = addons;
    }

    public String getProductPlanPageUrl() {
        return productPlanPageUrl;
    }

    public void setProductPlanPageUrl(String productPlanPageUrl) {
        this.productPlanPageUrl = productPlanPageUrl;
    }

    @Override
    public String toString() {
        return productName + plans;
    }

    //method check product from plan page and order page
    public String getErrorOrderPage(Object o) {
        String error = "";
        Product product = (Product) o;
        if (!this.getProductName().equals(product.getProductName())) {
            error = error + "Error1: Wrong product name: on Plan Page it was " + this.getProductName() + ", but on Order Page it's: " + product.getProductName() + "\n";
        }
        if (!this.getPlan().getPlanName().equals(product.getPlan().getPlanName())) {
            error = error + "Error2: For " + this.getProductName() + " product, Wrong Plan Name: on Plan Page it was " + this.getPlan().getPlanName() + " but in Order Page it's: " + product.getPlan().getPlanName() + "\n";
        }
        return error;
    }

    //method check product from order page and shopping cart
    public String getErrorShoppingCartPage(Object o) {
        String error = "";
        Product product = (Product) o;
        if (!this.getProductName().equals(product.getProductName())) {
            error = error + "Error3: Wrong product name: on Order Page it was " + this.getProductName() + ", but in Shopping Cart it's " + product.getProductName() + "\n";
        }
        if (!this.getProductDomain().equals(product.getProductDomain())) {
            error = error + "Error4: Wrong product domain: on Order Page it was " + this.getProductDomain() + ", but in Shopping Cart it's " + product.getProductDomain() + "\n";
        }
        if (!this.getPlan().getPlanName().equals(product.getPlan().getPlanName())) {
            error = error + "Error5: For " + this.getProductName() + " product, Wrong Plan Name on Order Page it was: " + this.getPlan().getPlanName() + ", but in Shopping Cart it's: " + product.getPlan().getPlanName() + "\n";
        }
        if (!this.getPlan().getTerm().equals(product.getPlan().getTerm())) {
            error = error + "Error6: For " + this.getProductName() + " product, on Order Page was selected term of plan " + this.getPlan().getTerm() + ", but in Shopping Cart it's: "
                    + product.getPlan().getTerm() + "\n";
        }
        if (!(this.getAddons().size() == product.getAddons().size())) {
            error = error + "Error7: For " + this.getProductName() + " wrong count of addons, expect addons: " + this.getAddons() + "\n" + "but found: " + product.getAddons() + "\n";
        }

        for (int i = 0; i < product.getAddons().size(); i++) {
            if (!this.getAddons().contains(product.getAddons().get(i))) {
                                error = error + "Error8: For " + this.getProductName() + " product, Wrong Addon Name: on Order Page was selected addon " + this.getAddons().get(i).getAddonName() +
                        ", but in shopping Cart it's " + product.getAddons().get(i).getAddonName() + "\n";
            }
        }

        //ned to change logic to valid
//        for (int i = 0; i < this.getAddons().size(); i++) {
//            if (!this.getAddons().get(i).getAddonName().equals(product.getAddons().get(i).getAddonName()))
//                error = error + "Error8: For " + this.getProductName() + " product, Wrong Addon Name: on Order Page was selected addon " + this.getAddons().get(i).getAddonName() +
//                        ", but in shopping Cart it's " + product.getAddons().get(i).getAddonName() + "\n";
//        }
        for (int i = 0; i < this.getAddons().size(); i++) {
            if (!this.getAddons().get(i).getAddonTerm().equals(product.getAddons().get(i).getAddonTerm()))
                error = error + "Error9: For " + this.getProductName() + " product, on Order Page was selected term " + this.getAddons().get(i).getAddonTerm() +
                        ", but in Shopping Cart Term for addon " + this.getAddons().get(i).getAddonName() + " found term " + product.getAddons().get(i).getAddonTerm() + "\n";
        }
        return error;
    }
    // method check product specification
    public String isProduct(Product product) {
        String error = "";
        if (!this.getProductName().equals(product.getProductName())) {
            error = error + "Specification Error1: Wrong product name: on Order Page it was " + this.getProductName() + ", but in Shopping Cart it's: " + product.getProductName() + "\n";
        }

        if (!this.getPlans().contains(product.getPlan())) {
            error = error + "Specification Error2: Wrong plan name: on Order Page it was " + this.getPlans() + ", but in Shopping Cart it's: " + product.getPlan() + " specific error" + "\n";
        }

        for (int i = 0; i < product.getAddons().size(); i++) {
            if (!this.getAddons().contains(product.getAddons().get(i))) {
                error = error + "Specification Error3: Wrong addon name: on Order Page it was " + this.getAddons() + ", but in Shopping Cart it's: " + product.getAddons().get(i) + "\n";
            }
        }

        return error;
    }

}