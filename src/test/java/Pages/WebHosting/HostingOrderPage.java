package Pages.WebHosting;

import EmailNotification.ErrorMessage;
import Utils.EventFiringWebDriverWrapper;
import Objects.*;
import Pages.BasePage;
import Products.WebHostingProduct;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


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
    @FindBy(xpath = "//span[@id = 'domain_price']")
    private WebElement DOMAIN_PRICE;

    @FindBy(xpath = "//*[@class='requiredField']")
    private WebElement DOMAIN_NAME_VALIDATION_ERROR;

    @FindBy(xpath = "//*[@id='domain_name_own']")
    private WebElement I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON_STATUS;

    @FindBy(xpath = "//*[@id='domain_name_register']")
    private WebElement REGISTER_A_NEW_DOMAIN_RADIO_BUTTON_STATUS;

    @FindBy(xpath = "//div[@class='g-custom-radio']/label")
    private WebElement I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON;

    @FindBy(xpath = "//*[@class = 'linkTip tooltip_register_domain']")
    private WebElement REGISTER_A_NEW_DOMAIN_RADIO_BUTTON;

    @FindBy(xpath = "//*[@id='search_domain_input']")
    private WebElement DOMAIN_SEARCH_FIELD;

    @FindBy(xpath = "//div[@class=\"row col-xl-auto col-m-24\"]")
    private WebElement CLICK;

    @FindBy(xpath = "//*[@id='domain_available']")
    private WebElement IS_DOMAIN_AVAILABLE_IMAGE;

    @FindBy(xpath = "//*[@id='domain_available_tick']")
    private WebElement DOMAIN_AVAILABLE_TICK;

    @FindBy(xpath = "//*[@id='domain_available_cross']")
    private WebElement DOMAIN_NOT_AVAILABLE_TICK;

    @FindBy(xpath = "//*[contains(text(),'Continue Order')]")
    private WebElement CONTINUE_ORDER_BUTTON;

    @FindBy(xpath = "//*[@id='total']")
    private WebElement TOTAL_PRICE;

    @FindBy(xpath = "//*[@class = 'main-title']")
    private WebElement PRODUCT_NAME_TITLE_TEXT;

    @FindBy(xpath = "//*[@class='plan-title-square row _middle _center']")
    private WebElement PLAN_NAME_TEXT;

    @FindBy(xpath = "//*[@id='domain_price']")
    private WebElement registerNewDomainPrice;

    @FindBy(xpath = "//div[starts-with(@class,\"g-custom-radio\")]/input")
    private List<WebElement> planRadioButtonsStatus;

    @FindBy(xpath = "//div[starts-with(@class,\"g-custom-checkbox\")]/input")
    private List<WebElement> addonCheckBoxStatusStatus;

    @FindBy(xpath = "//span[@class = 'bold item-name']")
    private List<WebElement> addonNames;

    @FindBy(xpath = "//span[starts-with(@class,\"item-name\")]")
    private List<WebElement> planRadioButtonsNames;

    private Price priceBeforeAction;
    private ArrayList<ErrorMessage> priceErrors = new ArrayList<ErrorMessage>();
    private ArrayList<ErrorMessage> addonErrors = new ArrayList<ErrorMessage>();
    private ArrayList<ErrorMessage> planErrors = new ArrayList<ErrorMessage>();


    //for addons
    private ArrayList<Addon> addons = new ArrayList<Addon>();

    protected EventFiringWebDriverWrapper driver;
    private WebHostingProduct actualProduct;

    public HostingOrderPage(EventFiringWebDriverWrapper driver) {
        super(driver);
        this.driver = driver;
    }

    public void selectPlan(String planName) {
        for (int i = 0; i < planRadioButtonsNames.size(); i++) {
            if (planRadioButtonsNames.get(i).getText().equals(planName)) {
                priceBeforeAction = getTotalPrice();
                planRadioButtonsNames.get(i).click();
                if (!priceBeforeAction.equals(getTotalPrice()))
                    priceErrors.add(new ErrorMessage("Price not changed after select: " + planName));
            } else
                planErrors.add(new ErrorMessage("Can't find this plan: " + planName + " on this page " + driver.getCurrentUrl()));
        }
    }

    public void selectAllPlans(ArrayList<Plan> plans) {
        for (int i = 0; i < plans.size(); i++) {
            selectPlan(plans.get(i).getPlanName());
        }
    }

    public void selectAddon(String addonName) {
        for (int i = 0; i < addonNames.size(); i++) {
            if (addonNames.get(i).getText().equals(addonName)) {
                addonNames.get(i).click();
                if (!addonCheckBoxStatusStatus.get(i).isSelected()) {   //add addon work not correctly on site, if very quickly add addon
                    priceBeforeAction = getTotalPrice();
                    addonNames.get(i).click();
                    if (!priceBeforeAction.equals(getTotalPrice())) {
                        priceErrors.add(new ErrorMessage("Price not changed after select: " + addonName));
                    }
                }
            } else
                addonErrors.add(new ErrorMessage("Can't find this addon: " + addonName + " on this page " + driver.getCurrentUrl()));
        }
    }

    public void selectAllAddons(ArrayList<Addon> addons) {
        for (int i = 0; i < addons.size(); i++) {
            selectAddon(addons.get(i).getAddonName());
        }
    }

    public ArrayList<ErrorMessage> getPriceErrors() {
        return priceErrors;
    }

    public Plan getSelectedProductPlan() {// here need to change logic for plan, maybe don't need variable
        Plan productPlan = new Plan(getPlanName());
        for (int i = 0; i < 4; i++) {
            if (planRadioButtonsStatus.get(i).isSelected())
                productPlan.setTerm(new Term(planRadioButtonsNames.get(i).getText()));
        }
        return productPlan;
    }

    public ArrayList<Addon> getSelectedProductAddons() {
        for (int i = 0; i < addonCheckBoxStatusStatus.size(); i++) {
            if (addonCheckBoxStatusStatus.get(i).isSelected()) {
                addons.add(new Addon(addonNames.get(i).getText(), getSelectedProductPlan().getTerm()));
            }
        }
        return addons;
    }

    public void scrollDownPage() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 1800);");
    }

    public Price getTotalPrice() {
        return new Price(TOTAL_PRICE.getText());
    }

    public void clickRegisterNewDomain() {
        if (!REGISTER_A_NEW_DOMAIN_RADIO_BUTTON_STATUS.isSelected())
            REGISTER_A_NEW_DOMAIN_RADIO_BUTTON.click();
    }

    public void clickIownThisDomain() {
        if (!I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON_STATUS.isSelected())
            I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON.click();
    }

    public void clearDomainInputField() {
        DOMAIN_SEARCH_FIELD.clear();
    }

    public String getRegisterNewDomainPrice() {
        waitForElement(DOMAIN_PRICE);
        return DOMAIN_PRICE.getText();
    }

    public void waitErrorMessage(){
        waitForElement(DOMAIN_NAME_VALIDATION_ERROR);
    }

    public void waitForElement(WebElement element) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
    }

    public String getValidationErrorMessage() {
        return DOMAIN_NAME_VALIDATION_ERROR.getText();
    }

    public boolean getDomainAvailableTickStatus() {
        return DOMAIN_AVAILABLE_TICK.isEnabled();
    }

    public void inputDomainName(String domainName) {
        DOMAIN_SEARCH_FIELD.sendKeys(domainName);
    }

    public String getDomainName() {
        return DOMAIN_SEARCH_FIELD.getAttribute("value");
    }

    public String getConnectToSelectedRadioButton(){
        if (I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON_STATUS.isSelected())
            return I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON.getText();
        else return REGISTER_A_NEW_DOMAIN_RADIO_BUTTON.getText();
    }

    public Domain getSelectedProductDomain() {
        if (REGISTER_A_NEW_DOMAIN_RADIO_BUTTON_STATUS.isSelected()) {
            return new Domain(getDomainName(), new Price(getRegisterNewDomainPrice()));
        } else
            return new Domain(getDomainName());
    }

    public void clickContinueOrderButton() {
        CONTINUE_ORDER_BUTTON.click();
    }

    public void clickOnPage() {
        CLICK.click();
    }

    public String getProductName() {
        return PRODUCT_NAME_TITLE_TEXT.getText();
    }

    public String getPlanName() {
        return PLAN_NAME_TEXT.getText();
    }

    public WebHostingProduct getProduct() {
        actualProduct = new WebHostingProduct(getProductName());
        actualProduct.setProductPlan(getSelectedProductPlan());
        actualProduct.setProductAddons(getSelectedProductAddons());
        actualProduct.setProductPrice(getTotalPrice());
        actualProduct.setProductDomain(getSelectedProductDomain());
        System.out.println(actualProduct.toString());
        return actualProduct;
    }

}
