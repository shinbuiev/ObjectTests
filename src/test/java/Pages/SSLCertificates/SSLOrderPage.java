package Pages.SSLCertificates;

import Utils.EventFiringWebDriverWrapper;
import Objects.*;
import Pages.BasePage;
import Products.SSLProduct;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geser on 27.10.16.
 */
public class SSLOrderPage extends BasePage{

    public SSLOrderPage(EventFiringWebDriverWrapper driver) {
        super(driver);
    }

    private boolean optionStatus;
    private static String optionTerm;
    private String optionPrice;
    private static int optionCount;

    private SSLProduct actualProduct;
    private SSLProduct finalProduct;
    private String domainName;

    @FindBy(xpath = "//*[@id='all']/div[4]/div[1]/div[1]/div/div[1]/h1")
    private static WebElement PRODUCT_NAME;

    @FindBy(xpath = "//*[@id='crazy_order_ssl_form']/div[1]/div[3]/div[2]/span")
    private static WebElement PLAN_NAME_TEXT;

    @FindBy(xpath = "//*[contains(text(),'Continue Order')]")
    private static WebElement CONTINUE_ORDER_BUTTON;

    @FindBy(xpath = "//*[@id='search_domain_input']")
    private static WebElement DOMAIN_SEARCH_FIELD;

    @FindBy(xpath = "//*[@id='total']")
    private static WebElement TOTAL_PRICE;

    @FindBy(xpath = "//*[@class='table-details']/span[1]/span[1]")
    private static List<WebElement> NAME_PLANS_OPTIONS_LIST;

    @FindBy(xpath = "/html/body")
    private static WebElement CLICK;

    public String getProductName() {
        return PRODUCT_NAME.getText();
    }

    public String getPlanName() {
        return PLAN_NAME_TEXT.getText();
    }

    public int getOptionCount() {
        optionCount = (NAME_PLANS_OPTIONS_LIST).size();
        return optionCount;
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

    public void selectAllTerms(ArrayList<Term> terms){
        for (int i = 0; i < terms.size(); i++) {
            selectOption(terms.get(i).getTerm());
        }

    }

    public void setOptionTerm(String nameOption) {
        this.optionTerm = nameOption;
    }

    public String getTotalPrice() {
        return TOTAL_PRICE.getText();
    }

    public void inputDomainName(String domainName) {
        this.domainName = domainName;
        DOMAIN_SEARCH_FIELD.sendKeys(domainName);
    }

    public void clearDomainInputField() {
        this.domainName = "";
        DOMAIN_SEARCH_FIELD.clear();
    }

    public void clickOnPage() {
        CLICK.click();
    }

    public void clickContinueOrderButton() {
        actualProduct.setProductPrice(new Price(getTotalPrice()));
        actualProduct.setProductDomain(new Domain(domainName));
        actualProduct.setProductPlan(new Plan(getPlanName(), new Term(getOptionTerm())));
        CONTINUE_ORDER_BUTTON.click();
    }


    public SSLProduct getProduct() {
        if (actualProduct != null)
            return actualProduct;
        else
        {
            actualProduct = new SSLProduct(getProductName());
            actualProduct.setProductPlan(new Plan(getPlanName(), new Term(getOptionTerm())));
            return actualProduct;
        }
    }

}
