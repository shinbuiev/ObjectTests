package Tests;

import DataProviders.DataProviders;
import Pages.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by root on 06.11.16.
 */
public class SiteProtectionTests extends BaseTest{


    @BeforeTest
    public void start(){
        initial();
    }

    @BeforeMethod
    public void getPage(){
        getEventDriver().get("https://www.crazydomains.com.au/website-protection/");
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "provider")
    public void successProductBuy(String os,int planNumber,String domainName){
        BuyPage buyPage=new BuyPage(getEventDriver());
        OrderPage orderPage=buyPage.buyPlan(planNumber*0);
        orderPage.checkingTerm();
        orderPage.fillFailedDomainName();
        orderPage.fillCorrectDomainName();
        RegisterPage registerPage=orderPage.orderProduct();
        ShoppingCartPage shoppingCartPage=registerPage.goToShoppingCart();
        shoppingCartPage.emptyShoppingCart();
    }


    @AfterTest
    public void testEnding(){
        getDriver().quit();
    }
}
