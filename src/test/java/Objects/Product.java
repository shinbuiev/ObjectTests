package Objects;

/**
 * Created by Sergiy.K on 28-Oct-16.
 */
public abstract class Product {
    private String productName;
    private Price productPrice;
    private Term productTerm;
    private Plan productPlan;

    public Product(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public Price getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Price productPrice) {
        this.productPrice = productPrice;
    }

    public Term getProductTerm() {
        return productTerm;
    }

    public void setProductTerm(Term productTerm) {
        this.productTerm = productTerm;
    }

    public Plan getProductPlan() {
        return productPlan;
    }

    public void setProductPlan(Plan productPlan) {
        this.productPlan = productPlan;
    }

    //method check product from plan page and order page
    public String getErrorOrderPage(Object o) {
        String error = "";
        Product product = (WebHostingProduct) o;
        if (!this.getProductName().equals(product.getProductName())) {
            error = error + "Error1: Wrong webHostingProduct name: on Plan Page it was " + this.getProductName() +
                    ", but on Order Page it's: "
                    + product.getProductName() + "\n";
        }
        if (!this.getProductPlan().getPlanName().equals(product.getProductPlan().getPlanName())) {
            error = error + "Error2: For " + this.getProductName() + " webHostingProduct, Wrong Plan Name: on Plan Page it was "
                    + this.getProductPlan().getPlanName() + " but in Order Page it's: " + product.getProductPlan().getPlanName()
                    + "\n";
        }
        return error;
    }

    public abstract String getErrorShoppingCartPage(Object o);

    @Override
    public String toString() {
        return productName + " " + productPrice;
    }


}
