package Objects;

import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class Product {
    private static final Logger LOGGER = Logger.getLogger(Product.class);

    private String productName;
    private Price productPrice;
    public Addon addon;

    private Term term;
    private ArrayList<Plan> plans;
    private ArrayList<Term> terms;
    private ArrayList<Addon> addons;

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

    private Plan plan;

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
        return addons;
    }

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

    public String getErrorOrderPage(Object o) {

        String error = "";
        Product product = (Product) o;
        if (!this.getProductName().equals(product.getProductName())) {
            error = error + "Error1: Wrong product name:  " + this.getProductName() + " expected, but found: " + product.getProductName() + "\n";
            LOGGER.error(error);
        }
        if (!this.getPlan().getPlanName().equals(product.getPlan().getPlanName())) {
            error = error + "Error2: For " + this.getProductName() + " product, find plans: " + this.getPlan().getPlanName() + " and " + product.getPlan().getPlanName() + "\n";
            LOGGER.error(error);
        }

        return error;
    }

    public String getErrorShoppingCartPage(Object o) {
        String error = "";
        Product product = (Product) o;
        if (!this.getProductName().equals(product.getProductName())) {
            error = error + "Error3: Wrong product name:  " + this.getProductName() + " expected, but found: " + product.getProductName() + "\n";
            LOGGER.error(error);
        }

        if (!this.getPlan().getPlanName().equals(product.getPlan().getPlanName())) {
            error = error + "Error4: For " + this.getProductName() + " product, find plans: " + this.getPlan().getPlanName() + " and " + product.getPlan().getPlanName() + "\n";
            LOGGER.error(error);
        }
        if (!this.getPlan().getTerm().equals(product.getPlan().getTerm())) {
            error = error + "Error5: For " + this.getProductName() + " product, on order Page was selected term of plan: " + this.getPlan().getTerm() + " but found in shopping cart: "
                    + product.getPlan().getTerm() + "\n";
            LOGGER.error(error);
        }
        if (!this.getAddons().equals(product.getAddons())) {
            error = error + "Error6: For " + this.getProductName() + " expect addons: " + this.getAddons() + "\n" + "but found: " + product.getAddons() + "\n";
            LOGGER.error(error);
        }

        return error;
    }

    public String isProductNormal(Product product) {

        String error = "";
        if (!this.getProductName().equals(product.getProductName())) {
            error = error + "Error7: Wrong product name:  " + this.getProductName() + " expected, but found: " + product.getProductName() + "\n";
            LOGGER.error(error);
        }

        if (!this.getPlans().contains(product.getPlan())) {
            error = error + "Error8: For " + this.getProductName() + " product, expected plans: " + this.getPlans()
                    + " but found " + product.getPlan().getPlanName() + "\n";
            LOGGER.error(error);
        }//this.getPlans()getTerms.contains(product.getPlan.getTerm)

        for (int i = 0; i < this.getPlans().size(); i++) {
            //из продукта взять все из него выбрать термсы и посмотреть есть ли такой же в текущего продукта
            if (!this.getPlans().get(i).getTerms().contains(product.getPlan().getTerm())) {
                error = error + "Error9: For " + this.getProductName() + " possible terms is: " + this.getPlans().get(i).getTerms() + " but find: " + product.getPlan().getTerm() + "\n";
                LOGGER.error(error);
            }
        }

        for (int j = 0; j < product.getAddons().size(); j++) {
            if (!this.getAddons().contains(product.getAddons().get(j))) {
                error = error + "Error10: For " + this.getProductName() + " possible addons is: " + this.getAddons() + "\n" + "but found: " + product.getAddons().get(j) + " addon\n";
                LOGGER.error(error);
            }
        }

        return error;
    }

    public String isProduct(Product product) {
        String error = "";
        if (!this.getProductName().equals(product.getProductName())) {
            error = error + "Error7: Wrong product name:  " + this.getProductName() + " expected, but found: " + product.getProductName() + "\n";
        }

            if (!this.getPlans().contains(product.getPlan())) {
//            error = error + "test message for error 9 " + this.getPlans();
                error = error + "Error9: Wrong plan name: " + this.getPlans() + " expected, but found: " + product.getPlan() + "\n";
            }

        for (int i = 0; i < product.getAddons().size(); i++) {
            if (!this.getAddons().contains(product.getAddons().get(i))) {
                error = error + "Error10: Wrong addon name: " + this.getAddons() + " is expected, but found: " + product.getAddons().get(i) + "\n";
            }
        }

        return error;
    }

}
















































