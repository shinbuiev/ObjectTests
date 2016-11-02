package Pages.WebHosting;

import Objects.*;
import Pages.BasePage;
import Products.WebHostingProduct;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
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
    @CacheLookup
    @FindBy(xpath = "//*[@id='domain_price']")
    private WebElement DOMAIN_PRICE;

    @CacheLookup
    @FindBy(xpath = "//*[@class='requiredField']")
    private WebElement DOMAIN_NAME_VALIDATION_ERROR;

    @CacheLookup
    @FindBy(xpath = "//*[@id='domain_name_own']")
    private WebElement I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON_STATUS;

    @CacheLookup
    @FindBy(xpath = "//*[@id='domain_name_register']")
    private WebElement REGISTER_A_NEW_DOMAIN_RADIO_BUTTON_STATUS;

    @CacheLookup
    @FindBy(xpath = "//*[@for='domain_name_own']")
    private WebElement I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON;

    @CacheLookup
    @FindBy(xpath = "//*[@for='domain_name_register']")
    private WebElement REGISTER_A_NEW_DOMAIN_RADIO_BUTTON;

    @CacheLookup
    @FindBy(xpath = "//*[@id='search_domain_input']")
    private WebElement DOMAIN_SEARCH_FIELD;

    @CacheLookup
    @FindBy(xpath = "/html/body")
    private WebElement CLICK;

    @CacheLookup
    @FindBy(xpath = "//*[@id='domain_available_tick']")
    private WebElement DOMAIN_AVAILABLE_TICK;

    @CacheLookup
    @FindBy(xpath = "//*[@id='domain_available_cross']")
    private WebElement DOMAIN_NOT_AVAILABLE_TICK;

    @CacheLookup
    @FindBy(xpath = "//*[contains(text(),'Continue Order')]")
    private WebElement CONTINUE_ORDER_BUTTON;

    @CacheLookup
    @FindBy(xpath = "//*[@id='total']")
    private WebElement TOTAL_PRICE;

    @CacheLookup
    @FindBy(xpath = "//*[@class = 'main-title']")
    private WebElement PRODUCT_NAME_TITLE_TEXT;

    @CacheLookup
    @FindBy(xpath = "//*[@class='plan-title-square row _middle _center']")
    private WebElement PLAN_NAME_TEXT;

    @CacheLookup
    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[1]/div[1]/div/div/label")
    private WebElement trafficBoosterCheckBox;

    @CacheLookup
    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[1]/div[3]/div/span")
    private WebElement trafficBoosterName;

    @CacheLookup
    @FindBy(xpath = "//*[@id='search_booster_promo_price']")
    private WebElement trafficBoosterPrice;

    @CacheLookup
    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[2]/div[1]/div/div/label")
    private WebElement WebAnalyticsCheckBox;

    @CacheLookup
    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[2]/div[3]/div/span")
    private WebElement WebAnalyticsName;

    @CacheLookup
    @FindBy(xpath = "//*[@id='webstats_promo_price']")
    private WebElement WebAnalyticsPrice;

    @CacheLookup
    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[3]/div[1]/div/div/label")
    private WebElement PremiumEmailProtectionCheckBox;

    @CacheLookup
    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[3]/div[3]/div/span")
    private WebElement PremiumEmailProtectionName;

    @CacheLookup
    @FindBy(xpath = "//*[@id='spam_protection_promo_price']")
    private WebElement PremiumEmailProtectionPrice;

    @CacheLookup
    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[4]/div[1]/div/div/label")
    private WebElement SecureWebHostingCheckBox;

    @CacheLookup
    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[4]/div[3]/div/span")
    private WebElement SecureWebHostingName;

    @CacheLookup
    @FindBy(xpath = "//*[@id='ssl_security_promo_price']")
    private WebElement SecureWebHostingPrice;

    @CacheLookup
    @FindBy(xpath = ".//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[5]/div[1]/div/div/label")
    private WebElement mailingListManagerCheckBox;

    @CacheLookup
    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[5]/div[3]/div/span")
    private WebElement mailingListManagerName;

    @CacheLookup
    @FindBy(xpath = ".//*[@id='mailing_list_sell_price']")
    private WebElement mailingListManagerPrice;

    @CacheLookup
    @FindBy(xpath = "(//div[starts-with(@class,\"_hover\")]/div)[1]/div/div/div/input")
    private WebElement optionTerm12MonthRadiobutton;

    @CacheLookup
    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[5]/div[1]/div/div/label")
    private WebElement optionTerm12MonthName;

    @CacheLookup
    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[5]/div[1]/div/div/label")
    private WebElement optionTerm12MonthPrice;

    @CacheLookup
    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[1]/div[2]/div[2]/div[1]/div/div/label")
    private WebElement optionTerm24MonthRadiobutton;

    @CacheLookup
    @FindBy(xpath = "//*[@class='bold'][contains(text(),'24')]")
    private WebElement optionTerm24MonthName;

    @CacheLookup
    @FindBy(xpath = "//*[@id='148_promo_price']")
    private WebElement optionTerm24MonthPrice;

    @CacheLookup
    @FindBy(xpath = "//*[@id='mailing_list_sell_price']")
    private WebElement optionTerm36MonthRadiobutton;

    @CacheLookup
    @FindBy(xpath = "//*[@id='mailing_list_sell_price']")
    private WebElement optionTerm36MonthName;

    @CacheLookup
    @FindBy(xpath = "//*[@id='mailing_list_sell_price']")
    private WebElement optionTerm36MonthPrice;

    @CacheLookup
    @FindBy(xpath = "//*[@id='mailing_list_sell_price']")
    private WebElement optionTerm120MonthRadiobutton;

    @CacheLookup
    @FindBy(xpath = "//*[@id='mailing_list_sell_price']")
    private WebElement optionTerm120MonthName;

    @CacheLookup
    @FindBy(xpath = "//*[@id='mailing_list_sell_price']")
    private WebElement optionTerm120MonthPrice;

    @CacheLookup
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

    private Plan productPlan;
    private Domain productDomain;

    //for addons
    private ArrayList<Addon> addons = new ArrayList<Addon>();

    protected EventFiringWebDriver driver;
    private WebHostingProduct actualProduct;

    public HostingOrderPage(EventFiringWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void setPlan(String plan) {
        for (int i = 0; i < planRadioButtonsNames.size(); i++) {
            if (planRadioButtonsNames.get(i).getText().equals(plan)) {
                planRadioButtonsNames.get(i).click();
            }
        }
    }

    public void setAddon(String addon) {
        for (int i = 0; i < addonNames.size(); i++) {
            if (addonNames.get(i).getText().equals(addon)) {
                addonNames.get(i).click();
                if (!addonCheckBoxStatusStatus.get(i).isSelected()){   //add addon work not correctly on site, if very quickly add addon
                    addonNames.get(i).click();
                }
            }
        }
    }

    public Plan getProductPlan() {
        if (productPlan!= null)
            productPlan = null;
        for (int i = 0; i < 4; i++) {
            if (planRadioButtonsStatus.get(i).isSelected())
                productPlan = new Plan(planRadioButtonsNames.get(i).getText());
        }
        return productPlan;
    }

    public ArrayList<Addon> getProductAddons() {
        if (addons.size()>0)
        {
            addons = null;
            addons = new ArrayList<Addon>();
        }
        for (int i = 0; i < addonCheckBoxStatusStatus.size(); i++) {
            if (addonCheckBoxStatusStatus.get(i).isSelected())
            {
                addons.add(new Addon(addonNames.get(i).getText()));
            }
        }
        return addons;
    }

    public void getDomainStatus() {
        System.out.println("I own this domain checkbox status:   " + I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON_STATUS.isSelected());
        System.out.println("Register new domain checkbox status: " + REGISTER_A_NEW_DOMAIN_RADIO_BUTTON_STATUS.isSelected());
    }


    public void scrollDownPage() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 1800);");
    }

    public Price getTotalPrice() {
        return new Price(TOTAL_PRICE.getText());
    }

    public String getAddonName() {
        return productDomain.getDomainName();
    }

    public String getAddonPrice() {
        return productDomain.getDomainPrice().getPrice();
    }

    public void clickRegisterNewDomain() {
        REGISTER_A_NEW_DOMAIN_RADIO_BUTTON.click();
    }

    public void clickIownThisDomain() {
        I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON.click();
    }

    // here can be validation!!!!
    public void inputDomainName(String domainName) {
        productDomain = new Domain(domainName);
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
        CONTINUE_ORDER_BUTTON.click();
    }

    public void clickOnPage(){
        CLICK.click();
    }

    public WebHostingProduct getProduct() {
        actualProduct = new WebHostingProduct(getProductName());
        actualProduct.setProductPlan(getProductPlan());
        actualProduct.setProductAddons(getProductAddons());
        actualProduct.setProductPrice(getTotalPrice());
        actualProduct.setProductDomain(productDomain);
        System.out.println(actualProduct.toString());
        return actualProduct;
    }


    public String getProductName() {
        return PRODUCT_NAME_TITLE_TEXT.getText();
    }

    public String getPlanName() {
        return PLAN_NAME_TEXT.getText();
    }

}
