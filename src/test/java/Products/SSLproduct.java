package Products;

import EmailNotification.TestScreenshot;
import Objects.*;

/**
 * Created by geser on 27.10.16.
 */
public class SSLproduct extends Product {
    private Domain productDomain;

    public SSLproduct(String productName) {
        super(productName);
    }

    public Domain getProductDomain() {
        return productDomain;
    }

    public void setProductDomain(Domain productDomain) {
        this.productDomain = productDomain;
    }

    //method check product from order page and shopping cart
    public String getErrorShoppingCartPage(Object o) {
        String error = "";
        SSLproduct product = (SSLproduct) o;
        if (!this.getProductName().equals(product.getProductName())) {
            error = error + "Error3: Wrong product name: on Order Page it was " + this.getProductName() +
                    ", but in Shopping Cart it's " + product.getProductName() + "\n";
//            TestScreenshot.saveScreenShot(product.getClass().getName(), "error3OrderPage");
//            TestScreenshot.saveScreenShot(product.getClass().getName(), "error3ShoppingCart");
//            sendEmailWithError(error, product.getClass().getName(), "error3ShoppingCart" + ".jpg");
        }
        if (!this.getProductDomain().getDomainName().equals(product.getProductDomain().getDomainName())) {
            error = error + "Error4: Wrong product domain: on Order Page it was " + this.getProductDomain().getDomainName() +
                    ", but in Shopping Cart it's " + product.getProductDomain().getDomainName() + "\n";
//            TestScreenshot.saveScreenShot(product.getClass().getName(), "error4OrderPage");
//            TestScreenshot.saveScreenShot(product.getClass().getName(),"error4ShoppingCart");
        }
        if (!this.getProductPlan().getPlanName().equals(product.getProductPlan().getPlanName())) {
            error = error + "Error5: For " + this.getProductName() + " product, Wrong Plan Name on Order Page it was: "
                    + this.getProductPlan().getPlanName() + ", but in Shopping Cart it's: " + product.getProductPlan().getPlanName() + "\n";
//            TestScreenshot.saveScreenShot(product.getClass().getName(), "error5OrderPage");
//            TestScreenshot.saveScreenShot(product.getClass().getName(),"error5ShoppingCart");
        }
        if (!this.getProductPlan().getTerm().equals(product.getProductPlan().getTerm())) {
            error = error + "Error6: For " + this.getProductName() + " product, on Order Page was selected term of plan "
                    + this.getProductPlan().getTerm() + ", but in Shopping Cart it's: "
                    + product.getProductPlan().getTerm() + "\n";
//            TestScreenshot.saveScreenShot(product.getClass().getName(), "errorOrderPage6");
//            TestScreenshot.saveScreenShot(product.getClass().getName(),"error6ShoppingCart");
        }
        return error;
    }

    @Override
    public String toString() {
        return super.toString() + " domain:" + getProductDomain();
    }

}
