package Pages.WebHosting;

import Objects.*;
import Pages.BasePage;
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

    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[1]/div[1]/div/div/label")
    private WebElement trafficBoosterCheckBox;

    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[1]/div[3]/div/span")
    private WebElement trafficBoosterName;

    @FindBy(xpath = "//*[@id='search_booster_promo_price']")
    private WebElement trafficBoosterPrice;

    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[2]/div[1]/div/div/label")
    private WebElement WebAnalyticsCheckBox;

    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[2]/div[3]/div/span")
    private WebElement WebAnalyticsName;

    @FindBy(xpath = "//*[@id='webstats_promo_price']")
    private WebElement WebAnalyticsPrice;

    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[3]/div[1]/div/div/label")
    private WebElement PremiumEmailProtectionCheckBox;

    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[3]/div[3]/div/span")
    private WebElement PremiumEmailProtectionName;

    @FindBy(xpath = "//*[@id='spam_protection_promo_price']")
    private WebElement PremiumEmailProtectionPrice;

    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[4]/div[1]/div/div/label")
    private WebElement SecureWebHostingCheckBox;

    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[4]/div[3]/div/span")
    private WebElement SecureWebHostingName;

    @FindBy(xpath = "//*[@id='ssl_security_promo_price']")
    private WebElement SecureWebHostingPrice;

    @FindBy(xpath = ".//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[5]/div[1]/div/div/label")
    private WebElement mailingListManagerCheckBox;

    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[5]/div[3]/div/span")
    private WebElement mailingListManagerName;

    @FindBy(xpath = ".//*[@id='mailing_list_sell_price']")
    private WebElement mailingListManagerPrice;


    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[5]/div[1]/div/div/label")
    private WebElement optionTerm12MonthRadiobutton;

    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[5]/div[1]/div/div/label")
    private WebElement optionTerm12MonthName;

    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[3]/div[2]/div[5]/div[1]/div/div/label")
    private WebElement optionTerm12MonthPrice;

    @FindBy(xpath = "//*[@id='crazy_order_web_hosting_form']/div[1]/div[2]/div[2]/div[1]/div/div/label")
    private WebElement optionTerm24MonthRadiobutton;

    //*[@class='bold'][contains(text(),'24')]
    //*[@id='crazy_order_web_hosting_form']/div[1]/div[2]/div[2]/div[2]/div/span
    @FindBy(xpath = "//*[@class='bold'][contains(text(),'24')]")
    private WebElement optionTerm24MonthName;

    @FindBy(xpath = "//*[@id='148_promo_price']")
    private WebElement optionTerm24MonthPrice;

    @FindBy(xpath = "//*[@id='mailing_list_sell_price']")
    private WebElement optionTerm36MonthRadiobutton;

    @FindBy(xpath = "//*[@id='mailing_list_sell_price']")
    private WebElement optionTerm36MonthName;

    @FindBy(xpath = "//*[@id='mailing_list_sell_price']")
    private WebElement optionTerm36MonthPrice;

    @FindBy(xpath = "//*[@id='mailing_list_sell_price']")
    private WebElement optionTerm120MonthRadiobutton;

    @FindBy(xpath = "//*[@id='mailing_list_sell_price']")
    private WebElement optionTerm120MonthName;

    @FindBy(xpath = "//*[@id='mailing_list_sell_price']")
    private WebElement optionTerm120MonthPrice;

    @FindBy(xpath = "//*[@id='domain_price']")
    private WebElement registerNewDomainPrice;

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

    public void scrollDownPage() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 1800);");
    }

    public void addTrafficBooster(){
        addons.add(new Addon(trafficBoosterName.getText(),new Term(productPlan.getPlanName()), new Price(trafficBoosterPrice.getText())));
        trafficBoosterName.click();
    }

    public void addWebAnalytics(){
        addons.add(new Addon(trafficBoosterName.getText(),new Term(productPlan.getPlanName()), new Price(trafficBoosterPrice.getText())));
        trafficBoosterName.click();
    }

    public void addPremiumEmailProtection(){
        addons.add(new Addon(trafficBoosterName.getText(),new Term(productPlan.getPlanName()), new Price(trafficBoosterPrice.getText())));
        trafficBoosterName.click();
    }

    public void addSecureWebHosting(){
        addons.add(new Addon(trafficBoosterName.getText(),new Term(productPlan.getPlanName()), new Price(trafficBoosterPrice.getText())));
        trafficBoosterName.click();
    }

    public void addMailingListManager(){
        addons.add(new Addon(trafficBoosterName.getText(),new Term(productPlan.getPlanName()), new Price(trafficBoosterPrice.getText())));
        trafficBoosterName.click();
    }

    public void select12monthOptionTerm(){
        productPlan = new Plan(optionTerm12MonthName.getText(), new Price(optionTerm12MonthPrice.getText()));
        optionTerm12MonthRadiobutton.click();
    }

    public void select24monthOptionTerm(){
        productPlan = new Plan(optionTerm24MonthName.getText(), new Price(optionTerm24MonthPrice.getText()));
        optionTerm24MonthRadiobutton.click();
    }

    public void select36monthOptionTerm(){
        productPlan = new Plan(optionTerm36MonthName.getText(), new Price(optionTerm36MonthPrice.getText()));
        optionTerm36MonthRadiobutton.click();
    }

    public void select120monthOptionTerm(){
        productPlan = new Plan(optionTerm120MonthName.getText(), new Price(optionTerm120MonthPrice.getText()));
        optionTerm120MonthRadiobutton.click();
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
        productDomain.setDomainPrice(new Price(getRegisterNewDomainPrice()));
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
        if (!productDomain.getDomainName().equals(""))
//                || productDomain != null)
            productDomain = new Domain("");
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

    public WebHostingProduct getProduct(){
        actualProduct = new WebHostingProduct(getProductName());
        actualProduct.setProductPlan(productPlan);
        actualProduct.setProductAddons(addons);
        actualProduct.setProductPrice(getTotalPrice());
        actualProduct.setProductDomain(productDomain);
        System.out.println(actualProduct);
        return actualProduct;
    }


    public String getProductName() {
        return PRODUCT_NAME_TITLE_TEXT.getText();
    }

    public String getPlanName() {
        return PLAN_NAME_TEXT.getText();
    }

}
