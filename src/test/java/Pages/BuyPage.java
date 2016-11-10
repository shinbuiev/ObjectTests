package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

/**
 * Created by root on 03.11.16.
 */
public  class BuyPage extends BasePage {
    private EventFiringWebDriver eventDriver;

    @CacheLookup
    @FindBy(className = "withLoading")
    private List<WebElement> buyPlanButton;
    @CacheLookup
    @FindBy(className = "linux")
    private WebElement linuxButton;
    @CacheLookup
    @FindBy(className = "windows")
    private WebElement windowsButton;
    @CacheLookup
    @FindBy(className = "menuItemContent")
    private List<WebElement> trafficBoosPages;
    @CacheLookup
    @FindBy(className = "more_info_btn")
    private WebElement moreButton;
    @CacheLookup
    @FindBy(className = "menuItemContent")
    private List<WebElement> webSiteBuilderOption;
    @CacheLookup
    @FindBy(className = "selectWrap")
    private List<WebElement> webSiteBuilderDropdown;


    public BuyPage(EventFiringWebDriver eventDriver) {
        super(eventDriver);
        this.eventDriver = eventDriver;
    }

    public OrderPage buyPlan(int plan) {
        try {
            buyPlanButton.get(plan).click();
        }catch (Exception e){
            buyPlanButton.get(plan+1).click();
        }
        return new OrderPage(eventDriver);
    }

    //---------Web HOSTING SECTION-----------------------------
    public BuyPage selectHostingOs(String osName) {
        if (osName.equals("linux")) {
            linuxButton.click();
        } else if (osName.equals("windows")) {
            windowsButton.click();
        }
        return this;
    }
    //---------Web HOSTING SECTION-----------------------------


    //---------Web BUILDER SECTION-----------------------------
    public void selectBuilder(String sw, int plan) {
        if (sw.equals("windows")) {
            webSiteBuilderDropdown.get(plan / 2).click();
            webSiteBuilderOption.get(1).click();
        }

    }
    //---------Web BUILDER SECTION-----------------------------
}
