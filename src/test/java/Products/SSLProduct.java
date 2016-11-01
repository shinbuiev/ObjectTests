package Products;

import EmailNotification.ErrorMessage;
import Objects.*;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by geser on 27.10.16.
 */
public class SSLProduct extends Product {
    private Domain productDomain;

    public SSLProduct(String productName) {
        super(productName);
    }

    public Domain getProductDomain() {
        return productDomain;
    }

    public void setProductDomain(Domain productDomain) {
        this.productDomain = productDomain;
    }

    //method check product from order page and shopping cart
    public void getErrorShoppingCartPage(Object o) {
        String error = "";
        SSLProduct product = (SSLProduct) o;
        //will be refactor for this

        if (!this.getProductName().equals(product.getProductName())) {
            error = "Error3: Wrong product name: on Order Page it was " + this.getProductName() +
                    ", but in Shopping Cart it's " + product.getProductName() + "\n";
            errorMessages.add(new ErrorMessage(error));
        }
        if (!this.getProductDomain().getDomainName().equals(product.getProductDomain().getDomainName())) {
            error = "Error4: Wrong product domain: on Order Page it was " + this.getProductDomain().getDomainName() +
                    ", but in Shopping Cart it's " + product.getProductDomain().getDomainName() + "\n";
            errorMessages.add(new ErrorMessage(error));
        }
        if (!this.getProductPlan().getPlanName().equals(product.getProductPlan().getPlanName())) {
            error = "Error5: For " + this.getProductName() + " product, Wrong Plan Name on Order Page it was: "
                    + this.getProductPlan().getPlanName() + ", but in Shopping Cart it's: " + product.getProductPlan().getPlanName() + "\n";
            errorMessages.add(new ErrorMessage(error));
        }
        if (!this.getProductPlan().getTerm().equals(product.getProductPlan().getTerm())) {
            error = error + "Error6: For " + this.getProductName() + " product, on Order Page was selected term of plan "
                    + this.getProductPlan().getTerm() + ", but in Shopping Cart it's: "
                    + product.getProductPlan().getTerm() + "\n";
            errorMessages.add(new ErrorMessage(error));
        }
    }

    @Override
    public String toString() {
        return super.toString() + " domain:" + getProductDomain();
    }

}
