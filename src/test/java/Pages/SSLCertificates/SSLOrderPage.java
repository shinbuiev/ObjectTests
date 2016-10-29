package Pages.SSLCertificates;

import EmailNotification.TestScreenshot;
import Objects.*;
import Pages.BasePage;
import Pages.HostingOrderPage;
import Products.SSLproduct;
import com.sun.corba.se.spi.activation.BadServerDefinition;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.util.List;

/**
 * Created by geser on 27.10.16.
 */
public class SSLOrderPage extends BasePage
//        extends HostingOrderPage
{
    public SSLOrderPage(EventFiringWebDriver driver) {
        super(driver);
    }
//    protected EventFiringWebDriver driver;

    private boolean optionStatus;
    private static String optionTerm;
    private String optionPrice;
    private static int optionCount;

    private SSLproduct actualProduct;
    private SSLproduct finalProduct;
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
        System.out.println("final: " + actualProduct.getProductName() + " " + actualProduct.getProductDomain() + " " + actualProduct.getProductTerm() + " " +
                actualProduct.getProductPrice() + " " + actualProduct.getProductPlan());
        CONTINUE_ORDER_BUTTON.click();
    }


    public SSLproduct getProduct() {
        if (actualProduct != null)
            return actualProduct;
        else
        {
            actualProduct = new SSLproduct(getProductName());
            actualProduct.setProductPlan(new Plan(getPlanName(), new Term(getOptionTerm())));
        }

        System.out.println("start: "+ actualProduct);
        return actualProduct;
    }

}
