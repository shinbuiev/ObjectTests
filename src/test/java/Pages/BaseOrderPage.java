package Pages;

import EmailNotification.ErrorMessage;
import Objects.*;
import Utils.EventFiringWebDriverWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geser on 07.11.16.
 */
public abstract class BaseOrderPage extends BasePage {

    // BASE PRODUCT INFO
    @FindBy(xpath = "//*[@class = 'main-title']")
    private WebElement PRODUCT_NAME_TITLE_TEXT;

    @FindBy(xpath = "//h3 [starts-with(@class,'plan-title-square')]")
    private WebElement PLAN_NAME_TEXT;

    // PLAN OPTION BLOCK
    @FindBy(xpath = "//span[starts-with(@class,\"item-name\")]/span")
    private List<WebElement> PLAN_TERM_RADIO_BUTTONS;

    @FindBy(xpath = "//div[starts-with(@class,\"g-custom-radio\")]/input")
    private List<WebElement> PLAN_RADIO_BUTTON_STATUSES;

    // ADDON BLOCK
    @FindBy(xpath = "//div[starts-with(@class,\"g-custom-checkbox\")]/input")
    private List<WebElement> ADDON_CHECKBOX_STATUSES;

    @FindBy(xpath = "//span[@class = 'bold item-name']")
    private List<WebElement> ADDON_NAMES_CHECKBOXES;

    // CONNECT TO BLOCK
    @FindBy(xpath = "//*[@id='domain_name_own']")
    private WebElement I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON_STATUS;

    @FindBy(xpath = "//*[@id='domain_name_register']")
    private WebElement REGISTER_A_NEW_DOMAIN_RADIO_BUTTON_STATUS;

    @FindBy(xpath = "//div[@class='g-custom-radio']/label")
    private WebElement I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON;

    @FindBy(xpath = "//*[@class = 'linkTip tooltip_register_domain']")
    private WebElement REGISTER_A_NEW_DOMAIN_RADIO_BUTTON;

    @FindBy(xpath = "//*[@id='domain_price']")
    private WebElement REGISTER_NEW_DOMAIN_PRICE;

    @FindBy(xpath = "//*[@id='search_domain_input']")
    private WebElement DOMAIN_SEARCH_FIELD;

    @FindBy(xpath = "//span[@id = 'domain_price']")
    private WebElement DOMAIN_PRICE;

    @FindBy(xpath = "//*[@class='requiredField']")
    private WebElement DOMAIN_NAME_VALIDATION_ERROR;

    @FindBy(xpath = "//div[@class=\"row col-xl-auto col-m-24\"]")
    private WebElement CLICK;

    @FindBy(xpath = "//*[contains(text(),'Continue Order')]")
    private WebElement CONTINUE_ORDER_BUTTON;

    // NOT USEFUL FOR NOW
    @FindBy(xpath = "//*[@id='domain_available_tick']")
    private WebElement DOMAIN_AVAILABLE_TICK;

    @FindBy(xpath = "//*[@id='domain_available_cross']")
    private WebElement DOMAIN_NOT_AVAILABLE_TICK;
    // TOTAL PRICE
    @FindBy(xpath = "//*[@id='total']")
    private WebElement TOTAL_PRICE;

    private Price priceBeforeAction;
    private ArrayList<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>(); // for errors on this page
    protected EventFiringWebDriverWrapper driver;

    public BaseOrderPage(EventFiringWebDriverWrapper driver) {
        super(driver);
        this.driver = driver;
    }

    public ArrayList<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }

    public String getProductName() {
        return PRODUCT_NAME_TITLE_TEXT.getText();
    }

    public String getPlanName() {
        return PLAN_NAME_TEXT.getText();
    }

    // PLAN OPTION BLOCK
    public void selectPlanOption(String planTerm) {
        ArrayList<String> planTextList = new ArrayList<>();
        for (int i = 0; i < PLAN_TERM_RADIO_BUTTONS.size(); i++) {
            planTextList.add(PLAN_TERM_RADIO_BUTTONS.get(i).getText());
        }

        if (planTextList.contains(planTerm)) {
            for (int i = 0; i < PLAN_TERM_RADIO_BUTTONS.size(); i++) {
                if (PLAN_TERM_RADIO_BUTTONS.get(i).getText().equals(planTerm)) {
                    priceBeforeAction = getTotalPrice();
                    PLAN_TERM_RADIO_BUTTONS.get(i).click();
                    if (priceBeforeAction.equals(getTotalPrice())) {
                        errorMessages.add(new ErrorMessage("Price not changed after select term: " + planTerm));
                    }
                }
            }
        } else
            errorMessages.add(new ErrorMessage("Can't find this plan: " + planTerm + " on this page " + driver.getCurrentUrl()));
    }

    public void selectAllPlanOptions(ArrayList<Term> terms) {
        for (int i = 0; i < terms.size(); i++) {
            selectPlanOption(terms.get(i).getTerm());
        }
    }

    // ADDONS BLOCK
    public void selectAddon(String addonName) {
        ArrayList<String> addonNameList = new ArrayList<>();
        for (int i = 0; i < ADDON_NAMES_CHECKBOXES.size(); i++) {
            addonNameList.add(ADDON_NAMES_CHECKBOXES.get(i).getText());
        }

        if (addonNameList.contains(addonName)) {
            for (int i = 0; i < ADDON_NAMES_CHECKBOXES.size(); i++) {
                if (ADDON_NAMES_CHECKBOXES.get(i).getText().equals(addonName)) {
                    priceBeforeAction = getTotalPrice();
                    ADDON_NAMES_CHECKBOXES.get(i).click();
//                }
                    if (!ADDON_CHECKBOX_STATUSES.get(i).isSelected()) {   //add addon work not correctly on site, if very quickly add addon

                        ADDON_NAMES_CHECKBOXES.get(i).click();
                    }

                    if (priceBeforeAction.equals(getTotalPrice())) {
                        errorMessages.add(new ErrorMessage("Price not changed after select addon: " + addonName));
                    }
                }
            }
        } else {
            errorMessages.add(new ErrorMessage("Can't find this addon: " + addonName + " on this page " + driver.getCurrentUrl()));
        }
    }

    public void selectAllAddons(ArrayList<Addon> addons) {
        for (int i = 0; i < addons.size(); i++) {
            selectAddon(addons.get(i).getAddonName());
        }
    }

    // CONNECT TO BLOCK
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

    public String getREGISTER_NEW_DOMAIN_PRICE() {
        waitForElement(DOMAIN_PRICE);
        return DOMAIN_PRICE.getText();
    }

    public void waitErrorMessage() {
        waitForElement(DOMAIN_NAME_VALIDATION_ERROR);
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

    // this method return selected radio button name
    public String getDomainOwner() {
        if (I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON_STATUS.isSelected())
            return I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON.getText();
        else return REGISTER_A_NEW_DOMAIN_RADIO_BUTTON.getText();
    }

    public void clickContinueOrderButton() {
        CONTINUE_ORDER_BUTTON.click();
    }

    public void clickOnPage() {
        CLICK.click();
    }

    public Price getTotalPrice() {
        return new Price(TOTAL_PRICE.getText());
    }

    // PRODUCT INIT METHODS
    public Domain getSelectedProductDomain() {
        if (REGISTER_A_NEW_DOMAIN_RADIO_BUTTON_STATUS.isSelected()) {
            return new Domain(getDomainName(), new Price(getREGISTER_NEW_DOMAIN_PRICE()));
        } else
            return new Domain(getDomainName());
    }

    public Plan getSelectedProductPlan() {// here need to change logic for plan, maybe don't need variable term
        Plan productPlan = new Plan(getPlanName());
        for (int i = 0; i < 4; i++) {
            if (PLAN_RADIO_BUTTON_STATUSES.get(i).isSelected())
                productPlan.setTerm(new Term(PLAN_TERM_RADIO_BUTTONS.get(i).getText()));
        }
        return productPlan;
    }

    public ArrayList<Addon> getSelectedProductAddons() {
        ArrayList<Addon> addons = new ArrayList<>();
        for (int i = 0; i < ADDON_CHECKBOX_STATUSES.size(); i++) {
            if (ADDON_CHECKBOX_STATUSES.get(i).isSelected()) {
                addons.add(new Addon(ADDON_NAMES_CHECKBOXES.get(i).getText(), getSelectedProductPlan().getTerm()));
            }
        }
        return addons;
    }

    public abstract Product getProduct();
}
