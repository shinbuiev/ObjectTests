package Pages.SSLCertificates;

import Objects.*;
import Pages.BasePage;
import Pages.OrderPage;
import Products.SSLproduct;
import com.sun.corba.se.spi.activation.BadServerDefinition;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

/**
 * Created by geser on 27.10.16.
 */
public class SSLOrderPage extends BasePage
//        extends OrderPage
{
    public SSLOrderPage(EventFiringWebDriver driver) {
        super(driver);
    }

    protected EventFiringWebDriver driver;

    private boolean optionStatus;
    private String optionTerm;
    private String optionPrice;
    private int optionCount;

    private SSLproduct actualProduct;
    private SSLproduct finalProduct;
    private String domainName;

    @FindBy(xpath = "//*[@id='all']/div[4]/div[1]/div[1]/div/div[1]/h1")
    private static WebElement PRODUCT_NAME;

    @FindBy(xpath = "//*[@id='crazy_order_ssl_form']/div[1]/div[3]/div[2]/span")
    private static WebElement PLAN_NAME_TEXT;

    @FindBy(xpath = "//*[contains(text(),'Continue Order')]")
    private WebElement CONTINUE_ORDER_BUTTON;

    @FindBy(xpath = "//*[@id='search_domain_input']")
    private WebElement DOMAIN_SEARCH_FIELD;

    @FindBy(xpath = "//*[@id='total']")
    private WebElement TOTAL_PRICE;

    @FindBy(xpath = "//*[@class='table-details']/span[1]/span[1]")
    private List<WebElement> NAME_PLANS_OPTIONS_LIST;

    @FindBy(xpath = "/html/body")
    private WebElement CLICK;

    public int getOptionCount() {
        optionCount = (NAME_PLANS_OPTIONS_LIST).size();
        return optionCount;
    }

    public String getTotalPrice() {
        return TOTAL_PRICE.getText();
    }

    public String getOptionTerm() {
        return optionTerm;
    }

    public void selectOption(String term) {
        for (int i = 0; i < getOptionCount(); i++) {
            if (NAME_PLANS_OPTIONS_LIST.get(i).getText().equals(term)) {
                NAME_PLANS_OPTIONS_LIST.get(i).click();
                setOptionTerm(term);
            }
        }
    }

    public void setOptionTerm(String nameOption) {
        this.optionTerm = nameOption;
    }

    public void inputDomainName(String domainName) {
        this.domainName = domainName;
        DOMAIN_SEARCH_FIELD.sendKeys(domainName);
    }

    public void clearDomainInputField() {
        DOMAIN_SEARCH_FIELD.clear();
    }

    public void clickOnPage() {
        CLICK.click();
    }



    public void clickContinueOrderButton() {
        finalProduct = new SSLproduct(getProductName());
        finalProduct.setProductDomain(new Domain(domainName));
        finalProduct.setPlan(new Plan(getPlanName(), new Term(getOptionTerm())));
        finalProduct.setProductPrice(new Price(getTotalPrice()));
        CONTINUE_ORDER_BUTTON.click();
    }

    public SSLproduct getProduct() {
        actualProduct = new SSLproduct(getProductName());
        actualProduct.setPlan(new Plan(getPlanName(), new Term(getOptionTerm())));
        actualProduct.setProductPrice(new Price(getTotalPrice()));
        return actualProduct;
    }

    public void productToString(SSLproduct actual) {
        System.out.println("Final product in SSL Order Page: " + finalProduct.getProductName() + " Domain: " +
                finalProduct.getProductDomain() + ", selected plan:  " + finalProduct.getPlan()
        + " term: " + finalProduct.getPlan().getTerm() + " price: " + finalProduct.getProductPrice());
    }

    public SSLproduct getFinalProduct() {
        return finalProduct;
    }

    public String getPlanName() {
        return PLAN_NAME_TEXT.getText();
    }

    public String getProductName() {
        return PRODUCT_NAME.getText();
    }

    public SSLproduct getActualProduct() {
        actualProduct = new SSLproduct(getProductName());
        actualProduct.setPlan(new Plan(getPlanName()));
        return actualProduct;
    }
}
