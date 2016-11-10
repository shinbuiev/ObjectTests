package Pages;

import DataProviders.DataProviders;
import Listeners.EventHandler;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;
import java.util.List;

import static DataProviders.DataProviders.corrDomainGenerator;
import static DataProviders.DataProviders.failedDomainNames;
import static Listeners.EventHandler.*;
import static Listeners.EventHandler.LOG;
import static org.testng.Assert.*;


/**
 * Created by Dmitriy.F on 03.11.2016.
 */
public  class OrderPage extends BasePage {
    private EventFiringWebDriver eventDriver;


    @CacheLookup
    @FindBy(className = "main-title")
    private WebElement mainTitle;
    @CacheLookup
    @FindBy(css = "._between>._hover>.order_table_row_js")
    private List<WebElement> termFields;
    @CacheLookup
    @FindBy(css = ".item-name>span")
    private List<WebElement> termDate;
    @CacheLookup
    @FindBy(css = ".g-custom-radio>.product_element")
    private List<WebElement> inputsTermRadiobuttons;
    @CacheLookup
    @FindBy(xpath = ".//span[2][@class='price-promo']")
    private List<WebElement> termPromoCost;
    @CacheLookup
    @FindBy(css = "span.price-now:first-of-type")
    private List<WebElement> termCost;
    @CacheLookup
    @FindBy(className = "plan-title-square")
    private WebElement planName;
    @CacheLookup
    @FindBy(className = "select-server-box")
    private List<WebElement> yourLocation;
    @CacheLookup
    @FindBy(className = "g-custom-checkbox")
    private List<WebElement>addonsCheckboxes;
    @CacheLookup
    @FindBy(css = ".bold.item-name")
    private List<WebElement>addonsDescription;
    @CacheLookup
    @FindBy(xpath = ".//*[@id='connect_to']/div[2]/div/div[2]/div[1]/div/div")
    private List<WebElement>own_new_domainRadiobutton;
    @CacheLookup
    @FindBy(id = "search_domain_input")
    private WebElement own_new_domainField;
    @CacheLookup
    @FindBy(className = "website_protection_domain")
    private WebElement webSiteProtectionField;
    @CacheLookup
    @FindBy(css = "span#total")
    private WebElement total;
    @CacheLookup
    @FindBy(className = "button_continue_order")
    private WebElement continueOrderButton;
    @CacheLookup
    @FindBy(css = ".input>.requiredField")
    private WebElement errorField;

    public OrderPage(EventFiringWebDriver eventDriver) {
        super(eventDriver);
        this.eventDriver=eventDriver;
    }

    public void chooseLinuxLocation(){
        randomClick(yourLocation);
    }

    public void checkingTerm() {
        int count = 0;
        int term = 0;
        double cost = 0;
        int expectedTotal = 0;
        int actualTotal = 0;
        while (count < termFields.size()) {
            termFields.get(count).click();
            assertTrue(inputsTermRadiobuttons.get(count).isSelected());
            LOG.info("RadioButton \""+termDate.get(count).getText()+"\" month is checked");
            term = Integer.parseInt(termDate.get(count).getText());
            if (termPromoCost.get(count).getText().equals("")) {
            cost = Double.parseDouble(termCost.get(count).getText().replace("$", "").replace("/mo", "").trim());
            } else
            cost = Double.parseDouble(termPromoCost.get(count).getText().replace("$", "").trim().replace("/mo", "").trim());
            expectedTotal = (int) Math.round(term * cost);
            actualTotal = Integer.parseInt(total.getText().trim().replace(".00", "").replace(",", ""));
            assertEquals(actualTotal, expectedTotal, "Not equals");
            LOG.info("Expected total : "+expectedTotal+"$ equals Actual total: "+actualTotal+"$");
            count++;
        }
    }

    public void chooseAddons(){
        for (int x = (int) (Math.random()*(addonsCheckboxes.size()));x<addonsCheckboxes.size();x++) {
            addonsCheckboxes.get(x).click();
        }
    }

    public void chooseNewDomain(){
        own_new_domainRadiobutton.get(1).click();
    }

    public void fillCorrectDomainName(){
        try {
            own_new_domainField.clear();
            own_new_domainField.sendKeys(corrDomainGenerator());
        }catch (NoSuchElementException e){
            webSiteProtectionField.clear();
            webSiteProtectionField.sendKeys(corrDomainGenerator());
        }
    }

    public void fillFailedDomainName(){
    for(int x=0;x<failedDomainNames.length;x++){
        try {
            own_new_domainField.clear();
            own_new_domainField.sendKeys(failedDomainNames[x]);
            own_new_domainField.submit();
        }catch (NoSuchElementException e){
            webSiteProtectionField.clear();
            webSiteProtectionField.sendKeys(failedDomainNames[x]);
            webSiteProtectionField.submit();
        }
        assertTrue(isErrorAppear());
    }
    }

    public boolean isErrorAppear(){
        boolean appear=false;
        try {
            waitForElement(errorField);
            return appear=true;
        }catch (Exception e){
           takeScreen(eventDriver,"Domain name validation error message not appear!");
           return appear=false;
        }
    }

    public RegisterPage  orderProduct(){
        continueOrderButton.click();
        continueOrderButton.click();
        return new RegisterPage(eventDriver);
    }

}
