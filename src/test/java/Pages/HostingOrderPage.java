package Pages;

import Objects.*;
import Products.WebHostingProduct;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergiy.K on 17-Oct-16.
 */
public class HostingOrderPage extends BasePage {

    @FindBy(xpath = "//*[@class='bold item-name']")
    private List<WebElement> NAME_ADDONS_LIST;

    @FindBy(xpath = "//*[@class='table-details']/span[1]/span[1]")
    private List<WebElement> NAME_PLANS_OPTIONS_LIST;

    //connect to block
    @FindBy(xpath = "//*[@id='domain_price']")
    private WebElement DOMAIN_PRICE;

    @FindBy(xpath = "//*[@class='requiredField']")
    private WebElement DOMAIN_NAME_VALIDATION_ERROR;

    @FindBy(xpath = "//*[@for='domain_name_own']")
    private WebElement I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON;

    @FindBy(xpath = "//*[@for='domain_name_register']")
    private WebElement REGISTER_A_NEW_DOMAIN_RADIO_BUTTON;

    @FindBy(xpath = "//*[@id='search_domain_input']")
    private WebElement DOMAIN_SEARCH_FIELD;

    @FindBy(xpath = "/html/body")
    private WebElement CLICK;

    @FindBy(xpath = "//*[@id='domain_available_tick']")
    private WebElement DOMAIN_AVAILABLE_TICK;

    @FindBy(xpath = "//*[contains(text(),'Continue Order')]")
    private WebElement CONTINUE_ORDER_BUTTON;

    @FindBy(xpath = "//*[@id='total']")
    private WebElement TOTAL_PRICE;

    @FindBy(xpath = "//*[@class = 'main-title']")
    private WebElement PRODUCT_NAME_TITLE_TEXT;

    @FindBy(xpath = "//*[@class='plan-title-square row _middle _center']")
    private WebElement PLAN_NAME_TEXT;

    //for options
    private boolean optionStatus;
    private String optionTerm;
    private String optionPrice;
    private int optionCount;

    private String domainName;
    //for addons
    private ArrayList<Addon> addons = new ArrayList<Addon>();
    private boolean addonStatus;
    private String addonName;
    private String addonPrice;
    private int addonCount;
    protected EventFiringWebDriver driver;
    private WebHostingProduct actual;
    private WebHostingProduct finalProduct;

    public HostingOrderPage(EventFiringWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebHostingProduct getProduct() {
        actual = new WebHostingProduct(getProductName());
        actual.setProductPlan(new Plan(getPlanName(), new Term(getOptionTerm())));
        actual.setProductPrice(new Price(getTotalPrice()));
        actual.setProductAddons(addons);
        return actual;
    }

    public void scrollDownPage() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 1800);");
    }

    public String getTotalPrice() {
        return TOTAL_PRICE.getText();
    }

    public WebHostingProduct getActualProduct() {
        WebHostingProduct actualWebHostingProduct = new WebHostingProduct(getProductName());
        actualWebHostingProduct.setProductPlan(new Plan(getPlanName(), getCurrentURL()));
        ArrayList<Addon> addons = new ArrayList<Addon>();
        for (int i = 0; i < getAddonCount(); i++) {
            addons.add(new Addon(NAME_ADDONS_LIST.get(i).getText()));
            System.out.println(NAME_ADDONS_LIST.get(i).getText());

        }
        for (int i = 0; i < addons.size(); i++) {
            System.out.println(addons.get(i));
        }
        return actualWebHostingProduct;
    }

    //for options
    public void selectOption(String term) {
        for (int i = 0; i < getOptionCount(); i++) {
            if (NAME_PLANS_OPTIONS_LIST.get(i).getText().equals(term)) {
                NAME_PLANS_OPTIONS_LIST.get(i).click();
                setOptionTerm(term);
            }
        }
    }

    public boolean getStatusOption(String plan) {
        for (int i = 0; i < getOptionCount(); i++) {
            if (NAME_PLANS_OPTIONS_LIST.get(i).getText().equals(plan)) {
                optionStatus = NAME_PLANS_OPTIONS_LIST.get(i).isSelected();
            }
        }
        return optionStatus;
    }

    public void setOptionStatus(boolean optionStatus) {
        this.optionStatus = optionStatus;
    }

    public String getOptionTerm() {
        return optionTerm;
    }

    public void setOptionTerm(String nameOption) {
        this.optionTerm = nameOption;
    }

    public String getOptionPrice() {
        return optionPrice;
    }

    public void setOptionPrice(String optionPrice) {
        this.optionPrice = optionPrice;
    }

    public int getOptionCount() {
        optionCount = (NAME_PLANS_OPTIONS_LIST).size();
        return optionCount;
    }

    public void setOptionCount(int optionCount) {
        this.optionCount = optionCount;
    }

    //for addons
    public void addAddon(String addonName) {

        for (int i = 0; i < getAddonCount(); i++) {
            if (addonName.equals(NAME_ADDONS_LIST.get(i).getText())) {
                NAME_ADDONS_LIST.get(i).click();
                addons.add(new Addon(addonName, new Term(getOptionTerm())));
            }
        }
    }

    public void productToString(WebHostingProduct actual) {
        System.out.println("Actual webHostingProduct in Order Page: " + actual.getProductName() + " Domain name: " + actual.getProductDomain() +
                ", selected plan:  " + actual.getProductPlan() + " for  " + actual.getProductPlan().getTerm() + " month");
        System.out.println("Selected addons: " + actual.getProductAddons());
        System.out.println("Total price is: " + actual.getProductPrice().getPrice());
    }

    public void pageDown() {
        driver.findElement(By.xpath("/html/body")).sendKeys(Keys.END);
    }

    public boolean getAddonStatus(String addonName) {
        for (int i = 0; i < getAddonCount(); i++) {
            if (addonName.equals(NAME_ADDONS_LIST.get(i).getText())) {
                NAME_ADDONS_LIST.get(i).isSelected();
            }
        }
        return addonStatus;
    }

    public void setAddonStatus(boolean addonStatus) {
        this.addonStatus = addonStatus;
    }

    public String getAddonName() {
        return addonName;
    }

    public void setAddonName(String addonName) {
        this.addonName = addonName;
    }

    public String getAddonPrice() {
        return addonPrice;
    }

    public void setAddonPrice(String addonPrice) {
        this.addonPrice = addonPrice;
    }

    public int getAddonCount() {
        addonCount = NAME_ADDONS_LIST.size();
        return addonCount;
    }

    public void setAddonCount(int addonCount) {
        this.addonCount = addonCount;
    }

    public void clickRegisterNewDomain() {
        REGISTER_A_NEW_DOMAIN_RADIO_BUTTON.click();
    }

    public void clickIownThisDomain() {
        I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON.click();
    }

    public void inputDomainName(String domainName) {
        this.domainName = domainName;
        DOMAIN_SEARCH_FIELD.sendKeys(domainName);
    }

    public void clearDomainInputField() {
        DOMAIN_SEARCH_FIELD.clear();
    }

    public String getRegisterNewDomainPrice() {
        return DOMAIN_PRICE.getText();
    }

    public String getValidationErrorMessage() {
        return DOMAIN_NAME_VALIDATION_ERROR.getText();
    }

    public boolean getDomainAvailableTickStatus() {
        return DOMAIN_AVAILABLE_TICK.isEnabled();
    }

    public void clickContinueOrderButton() {
        finalProduct = new WebHostingProduct(getProductName());
        finalProduct.setProductDomain(new Domain(domainName));
        finalProduct.setProductPlan(new Plan(getPlanName(), new Term(getOptionTerm())));
        finalProduct.setProductPrice(new Price(getTotalPrice()));
        finalProduct.setProductAddons(addons);
        CONTINUE_ORDER_BUTTON.click();
    }

    public WebHostingProduct getFinalProduct() {
        return finalProduct;
    }

    public String getProductName() {
        return PRODUCT_NAME_TITLE_TEXT.getText();
    }

    public String getPlanName() {
        return PLAN_NAME_TEXT.getText();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }


}
