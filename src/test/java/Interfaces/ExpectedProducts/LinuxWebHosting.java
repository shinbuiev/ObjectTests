package Interfaces.ExpectedProducts;

import Objects.*;

import java.util.ArrayList;

/**
 * Created by Sergiy.K on 25-Oct-16.
 */
public class LinuxWebHosting {
/*
 *
 *
 *
 */


    public String getProductName() {
        return "Web Hosting";
    }

    public ArrayList<Term> getProductTerms() {
        ArrayList<Term> terms = new ArrayList<Term>() {{
            add(new Term("12"));
            add(new Term("24"));
            add(new Term("36"));
            add(new Term("120"));
        }};
        return terms;
    }

    public ArrayList<Plan> getProductPlans() {
        ArrayList<Plan> plans = new ArrayList<Plan>() {{
            add(new Plan("Economy"));
            add(new Plan("Premium"));
            add(new Plan("Unlimited"));
        }};
        return plans;
    }

    public ArrayList<Addon> getProductAddons() {
        final ArrayList<Addon> addons = new ArrayList<Addon>() {{
            add(new Addon("Traffic Booster"));
            add(new Addon("Web Analytics"));
            add(new Addon("Premium Email Protection"));
            add(new Addon("Secure Web Hosting"));
            add(new Addon("Mailing List Manager"));
        }};
        return addons;
    }

    public String isProduct(Product product) {
        // method check webHostingProduct specification
        WebHostingProduct webHostingProduct = (WebHostingProduct) product;

        String error = "";
        if (!this.getProductName().equals(webHostingProduct.getProductName())) {
            error = error + "Specification Error1: Wrong product name: in specification it was " +
                    this.getProductName() + ", but in Shopping Cart it's: " + webHostingProduct.getProductName() + "\n";
        }

        if (!this.getProductPlans().contains(webHostingProduct.getProductPlan())) {
            error = error + "Specification Error2: Wrong plan name: on Order Page it was " + this.getProductPlans() +
                    ", but in Shopping Cart it's: " + webHostingProduct.getProductPlan() + " specific error" + "\n";
        }

        for (int i = 0; i < webHostingProduct.getProductAddons().size(); i++) {
            if (!this.getProductAddons().contains(webHostingProduct.getProductAddons().get(i))) {
                error = error + "Specification Error3: Wrong addon name: in specification it was " + this.getProductAddons() +
                        "\n" + ", but in Shopping Cart it's: " + webHostingProduct.getProductAddons().get(i) + "\n";
            }
        }

        for (int i = 0; i < getProductTerms().size(); i++) {
            if (!this.getProductTerms().contains(product.getProductTerm()))
                error = error + "Specification Error4: Wrong product Term: in specification it was " + this.getProductTerms() +
                        " , but in Shopping Cart it's: " + product.getProductTerm() + "\n";
        }

        return error;
    }


}
