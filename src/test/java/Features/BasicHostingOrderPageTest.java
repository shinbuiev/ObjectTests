//package UtilsForTest;
//
//import org.testng.annotations.Test;
//
///**
// * Created by Sergiy.K on 18-Oct-16.
// */
//public class BasicHostingOrderPageTest extends BasicOrderPageTest {
//    private String domainWithTelTld = "myfirstByInCrazy.tel";
//    OrderPage orderPage = new OrderPage(driver);
//
//    @Test
//    public void byProductWithTelTldRegisterNewDomainTest() {
//        goToHostingPage(BASE_URL);
//        orderPage.pageDown();
//        orderPage.clearDomainInputField();
//        orderPage.clickOnPage();
//        orderPage.inputDomainName(domainWithTelTld);
//        orderPage.clickRegisterNewDomain();
//        orderPage.clickOnPage();
//        orderPage.waitPrice();
//        orderPage.clickContinueOrderButton();
//        orderPage.pageDown();
//        orderPage.waitPage();
//        checkErrorForTelTld();
//
//    }
//
//    @Test
//    public void byProductWithTelTldOwnDomainTest() {
//        goToHostingPage(BASE_URL);
//        orderPage.pageDown();
//        orderPage.clearDomainInputField();
//        orderPage.inputDomainName(domainWithTelTld);
//        orderPage.clickContinueOrderButton();
//        orderPage.pageDown();
//        checkErrorForTelTld();
//    }
//
//    @Test
//    public void PlanOption12MonthTest() {
//        goToPage(BASE_URL);
//        rememberPriceBeforeAction();
//        orderPage.selectOption("12");
//        rememberPriceAfterAction();
//        comparePrices();
//    }
//
//    @Test
//    public void PlanOption24MonthTest() {
//        goToPage(BASE_URL);
//        rememberPriceBeforeAction();
//        orderPage.selectOption("24");
//        rememberPriceAfterAction();
//        comparePrices();
//    }
//
//    @Test
//    public void PlanOption36MonthTest() {
//        goToPage(BASE_URL);
//        rememberPriceBeforeAction();
//        orderPage.selectOption("36");
//        rememberPriceAfterAction();
//        comparePrices();
//    }
//
//    @Test
//    public void PlanOption120MonthTest() {
//        goToPage(BASE_URL);
//        rememberPriceBeforeAction();
//        orderPage.selectOption("120");
//        rememberPriceAfterAction();
//        comparePrices();
//    }
//
//    @Test
//    public void AddAddonWebAnalyticsTest() {
//        goToPage(BASE_URL);
//        rememberPriceBeforeAction();
//        orderPage.addAddon("Web Analytics");
//        rememberPriceAfterAction();
//        comparePrices();
//    }
//
//    @Test
//    public void AddAddonPremiumEmailProtectionTest() {
//        goToPage(BASE_URL);
//        rememberPriceBeforeAction();
//        orderPage.addAddon("Premium Email Protection");
//        rememberPriceAfterAction();
//        comparePrices();
//    }
//
//    @Test
//    public void AddAddonTrafficBoosterTest() {
//        goToPage(BASE_URL);
//        rememberPriceBeforeAction();
//        orderPage.addAddon("Traffic Booster");
//        rememberPriceAfterAction();
//        comparePrices();
//    }
//
//    @Test
//    public void AddAddonSecureWebHostingTest() {
//        goToPage(BASE_URL);
//        rememberPriceBeforeAction();
//        orderPage.addAddon("Secure Web Hosting");
//        rememberPriceAfterAction();
//        comparePrices();
//    }
//
//    @Test
//    public void AddAddonMailingListManagerTest() {
//        goToPage(BASE_URL);
//        rememberPriceBeforeAction();
//        orderPage.addAddon("Mailing List Manager");
//        rememberPriceAfterAction();
//        comparePrices();
//    }
//}
