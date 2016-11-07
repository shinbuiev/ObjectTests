package ExpectedProducts;

import Objects.Plan;
import Objects.Product;
import Objects.Term;
import Products.SSLProduct;

import java.util.ArrayList;

/**
 * Created by Sergiy.K on 28-Oct-16.
 */
public class SSLCertificatesProducts {
    public String getProductName() {
        return "SSL Certificates";
    }

    public String getProductMainPage(){
        return "https://www.crazydomains.com.au/ssl-certificates/";
    }

    public ArrayList<Term> getProductTerms() {
        ArrayList<Term> terms = new ArrayList<Term>() {{
            add(new Term("12 month"));
            add(new Term("24 month"));
            add(new Term("36 month"));
        }};
        return terms;
    }

    public ArrayList<Plan> getProductPlans() {
        ArrayList<Plan> plans = new ArrayList<Plan>() {{
            add(new Plan("Standard", "/ssl-certificates/order-standard-ssl/"));
            add(new Plan("Premium", "/ssl-certificates/order-premium-ssl/"));
            add(new Plan("Wildcard", "/ssl-certificates/order-wildcard-ssl/"));
        }};
        return plans;
    }

    public String isProduct(Product product) {
        // method check webHostingProduct specification
        SSLProduct SSLProduct = (SSLProduct) product;

        String error = "";
        if (!this.getProductName().equals(product.getProductName())) {
            error = error + "Specification Error1: Wrong product name: in specification it was " +
                    this.getProductName() + ", but in Shopping Cart it's: " + product.getProductName() + "\n";
        }

        if (!this.getProductPlans().contains(product.getProductPlan())) {
            error = error + "Specification Error2: Wrong plan name: on Order Page it was " + this.getProductPlans() +
                    ", but in Shopping Cart it's: " + product.getProductPlan() + " specific error" + "\n";
        }

        for (int i = 0; i < getProductTerms().size(); i++) {
            if (!this.getProductTerms().contains(product.getProductTerm()))
                error = error + "Specification Error4: Wrong product Term: in specification it was " + this.getProductTerms() +
                        " , but in Shopping Cart it's: " + product.getProductTerm() + "\n";
        }
        return error;
    }

    @Override
    public String toString() {
        return "SSL Certificate";
    }
}
