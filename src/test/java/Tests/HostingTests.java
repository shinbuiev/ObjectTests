package Tests;

import DataProviders.DataProviders;
import Pages.BuyPage;
import Pages.OrderPage;
import Pages.RegisterPage;
import Pages.ShoppingCartPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 * Created by Dmitriy.F on 02.11.2016.
 */
public class HostingTests extends BaseTest {


    @BeforeTest
    public void start(){
        initial();
    }

    @BeforeMethod
    public void getPage(){
        getEventDriver().get("https://www.crazydomains.com.au/web-hosting/");
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "provider")
    public void successProductBuy(String os,int planNumber,String domainName){
        BuyPage buyPage=new BuyPage(getEventDriver());
        buyPage.selectHostingOs(os);
        OrderPage orderPage=buyPage.buyPlan(planNumber);
        orderPage.checkingTerm();
        orderPage.chooseLinuxLocation();
        orderPage.chooseAddons();
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

