package Interfaces.ExpectedProducts;

import Objects.Addon;
import Objects.Plan;
import Objects.Product;
import Objects.Term;

import java.util.ArrayList;

/**
 * Created by Sergiy.K on 28-Oct-16.
 */
public class ExpectedProduct extends Product {
    ArrayList<Term> productTerms;
    ArrayList<Plan> productPlans;
    ArrayList<Addon> productAddons;

    public ArrayList<Term> getProductTerms() {
        return productTerms;
    }

    public void setProductTerms(ArrayList<Term> productTerms) {
        this.productTerms = productTerms;
    }

    public ArrayList<Plan> getProductPlans() {
        return productPlans;
    }

    public void setProductPlans(ArrayList<Plan> productPlans) {
        this.productPlans = productPlans;
    }

    public ArrayList<Addon> getProductAddons() {
        return productAddons;
    }

    public void setProductAddons(ArrayList<Addon> productAddons) {
        this.productAddons = productAddons;
    }

    public ExpectedProduct(String productName) {
        super(productName);
    }

    public String getErrorShoppingCartPage(Object o) {
        return "this method is not for this use";
    }
}
