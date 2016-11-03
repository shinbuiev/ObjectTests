package Features;

import Interfaces.ExpectedProducts.EventFiringWebDriverWrapper;
import Objects.Product;
import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.events.EventFiringWebDriver;


/**
 * Created by Sergiy.K on 17-Oct-16.
 */
public class OrderPage extends BasePage{

    private static final By TOTAL_PRICE = By.xpath("//*[@id='total']");
    private static final By NAME_ADDONS_LIST = By.xpath("//*[@class='bold item-name']");
    private static final By NAME_PLANS_OPTIONS_LIST = By.xpath("//*[@class='table-details']/span[1]/span[1]");

    //connect to block
    private static final By DOMAIN_PRICE = By.xpath("//*[@id='domain_price']");
    private static final By DOMAIN_NAME_VALIDATION_ERROR = By.xpath("//*[@class='requiredField']");
    private static final By I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON = By.xpath("//*[@for='domain_name_own']");
    private static final By REGISTER_A_NEW_DOMAIN_RADIO_BUTTON = By.xpath("//*[@for='domain_name_register']");
    private static final By DOMAIN_SEARCH_FIELD = By.xpath("//*[@id='search_domain_input']");
    private static final By CLICK = By.xpath("/html/body");
    private static final By DOMAIN_AVAILABLE_TICK = By.xpath("//*[@id='domain_available_tick']");
    private static final By CONTINUE_ORDER_BUTTON = By.xpath("//*[contains(text(),'Continue Order')]");
    private static final By PRODUCT_NAME_TITLE_TEXT = By.xpath("//*[@class = 'main-title']");
    private static final By PLAN_NAME_TEXT = By.xpath("//*[@class='plan-title-square row _middle _center']");

    //for options
    private boolean optionStatus;
    private String optionTerm;
    private String optionPrice;
    private int optionCount;

    //for addons
    private boolean addonStatus;
    private String addonName;
    private String addonPrice;
    private int addonCount;

    public OrderPage(EventFiringWebDriverWrapper driver) {
        super(driver);
    }

//    public OrderPage(EventFiringWebDriverWrapper driver) {
//        super(driver);
//    }

    public void scrollDownPage() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 1800);");
    }

    public String getTotalPrice() {
        return driver.findElement(TOTAL_PRICE).getText();
    }

    public void waitPrice() {
        clickOnPage();
//        Utils.waitElement(DOMAIN_PRICE);
    }

    //for options
    public void selectOption(String plan) {
        for (int i = 0; i < getOptionCount(); i++) {
            if (driver.findElements(NAME_PLANS_OPTIONS_LIST).get(i).getText().equals(plan)) {
                driver.findElements(NAME_PLANS_OPTIONS_LIST).get(i).click();
            }
        }
    }

    public boolean getStatusOption(String plan) {
        for (int i = 0; i < getOptionCount(); i++) {
            if (driver.findElements(NAME_PLANS_OPTIONS_LIST).get(i).getText().equals(plan)) {
                optionStatus = driver.findElements(NAME_PLANS_OPTIONS_LIST).get(i).isSelected();
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
        optionCount = driver.findElements(NAME_PLANS_OPTIONS_LIST).size();
        return optionCount;
    }

    public void setOptionCount(int optionCount) {
        this.optionCount = optionCount;
    }

    //for addons
    public void addAddon(String addonName) {
        for (int i = 0; i < getAddonCount(); i++) {
            if (addonName.equals(driver.findElements(NAME_ADDONS_LIST).get(i).getText())) {
                driver.findElements(NAME_ADDONS_LIST).get(i).click();
            }
        }
    }

    public void pageDown() {
        driver.findElement(By.xpath("/html/body")).sendKeys(Keys.END);
    }

    public Product getProduct() {
        return null;
    }

    public boolean getAddonStatus(String addonName) {
        for (int i = 0; i < getAddonCount(); i++) {
            if (addonName.equals(driver.findElements(NAME_ADDONS_LIST).get(i).getText())) {
                driver.findElements(NAME_ADDONS_LIST).get(i).isSelected();
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
        addonCount = driver.findElements(NAME_ADDONS_LIST).size();
        return addonCount;
    }

    public void setAddonCount(int addonCount) {
        this.addonCount = addonCount;
    }

    public void clickRegisterNewDomain() {
        driver.findElement(REGISTER_A_NEW_DOMAIN_RADIO_BUTTON).click();
    }

    public void clickIownThisDomain() {
        driver.findElement(I_OWN_THIS_DOMAIN_NAME_RADIO_BUTTON).click();
    }

    public void inputDomainName(String domainName) {
//        Utils.waitElement(DOMAIN_SEARCH_FIELD);
        driver.findElement(DOMAIN_SEARCH_FIELD).sendKeys(domainName);
    }

    public void clearDomainInputField() {
        driver.findElement(DOMAIN_SEARCH_FIELD).clear();
    }

    public String getRegisterNewDomainPrice() {
        return driver.findElement(DOMAIN_PRICE).getText();
    }

    public String getValidationErrorMessage() {
//        Utils.waitElement(DOMAIN_NAME_VALIDATION_ERROR);
        return driver.findElement(DOMAIN_NAME_VALIDATION_ERROR).getText();
    }

    public void clickOnPage() {
//        Utils.waitElement(CLICK);
        driver.findElement(CLICK).click();
    }

//    public void waitDomainAvailableTickStatus() {
//        Utils.waitElement(DOMAIN_AVAILABLE_TICK);
//    }
//
//    public void waitPage() {
//        Utils.waitElement(CLICK);
//    }

    public boolean getDomainAvailableTickStatus() {
        return driver.findElement(DOMAIN_AVAILABLE_TICK).isEnabled();
    }

    public void clickContinueOrderButton() {
//        Utils.waitElement(CONTINUE_ORDER_BUTTON);
        driver.findElement(CONTINUE_ORDER_BUTTON).click();
    }

    public String getProductName() {
        return driver.findElement(PRODUCT_NAME_TITLE_TEXT).getText();
    }

    public String getPlanName() {
        return driver.findElement(PLAN_NAME_TEXT).getText();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

}
