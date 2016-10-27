package Products;

import Objects.*;

/**
 * Created by geser on 27.10.16.
 */
public class SSLproduct
//        extends Product
{

    private String productName;
    private Price productPrice;
    private Addon addon;
    private Term term;
    private Domain productDomain;
    private Plan plan;

    public SSLproduct(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public Plan getPlan() {
        return plan;
    }


    public void setPlan(Plan plan) {
        this.plan = plan;
    }



    public Domain getProductDomain() {
        return productDomain;
    }

    public void setProductDomain(Domain productDomain) {
        this.productDomain = productDomain;
    }


    public Term getTerm() {
        return term;
    }


    public void setTerm(Term term) {
        this.term = term;
    }
    public Price getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Price productPrice) {
        this.productPrice = productPrice;
    }

    //method check product from plan page and order page
    public String getErrorOrderPage(Object o) {
        String error = "";
        SSLproduct product = (SSLproduct) o;
        if (!this.getProductName().equals(product.getProductName())) {
            error = error + "Error1: Wrong product name: on Plan Page it was " + this.getProductName() + ", but on Order Page it's: " + product.getProductName() + "\n";
        }
        if (!this.getPlan().getPlanName().equals(product.getPlan().getPlanName())) {
            error = error + "Error2: For " + this.getProductName() + " product, Wrong Plan Name: on Plan Page it was " + this.getPlan().getPlanName() + " but in Order Page it's: " + product.getPlan().getPlanName() + "\n";
        }
        return error;
    }
//        return super.getErrorOrderPage(o);


    //method check product from order page and shopping cart
    public String getErrorShoppingCartPage(Object o) {
        String error = "";
        SSLproduct product = (SSLproduct) o;
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
        return error;
    }

    // method check product specification

//    public String isProduct(Product product) {
//        return super.isProduct(product);
//    }
}
