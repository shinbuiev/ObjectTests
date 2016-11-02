//package UtilsForTest;
//
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.testng.Assert;
//import org.testng.ITestResult;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.Test;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
//
//
///**
// * Created by Sergiy.K on 18-Oct-16.
// */
//
////public class BasicOrderPageTest extends BasicTest implements WebHostingOrderTestInterface
//{
//
//    private static final By HEADER_DROP_DOWN = By.xpath("//*[@id='cart_control']/span[2]");
//    private static final By VIEW_CART_BUTTON = By.xpath("//*[@id=\"top_cart_buttons\"]/a[1]");
//    private static final By EMPTY_CART = By.xpath("//*[@id=\"domain_table_total_row\"]/div[2]/a");
//    private static final By DELETE_ALL_BUTTON = By.xpath("//*[@id='cart_reset']");
//    private static final By HOME_PAGE = By.xpath("//*[@id='all']/div[@class='mainHomeContainer']");
//    private static final By HEADER_USER_NAME = By.xpath("//*[@id='cart_control']/span[@class='welcome']");
//
//
//    private String domain = "myfirstbuyincrazy.com.au";
//    private String secondDomain = "mysecondbuyincrazy.com.au";
//    private String incorrectDomain = "myfirstbuyincrazy";
//    private String domainWithWrongTld = "myfirstbuyincrazy.none";
//
//    private OrderPage orderPage;
//    private Price priceBefore = new Price();
//    private Price priceAfter = new Price();
//
//    //for connect to block (validation and by hosting)
//    @Test
//    public void emptyDomainFieldOwnDomainTest() {
//        goToPage(BASE_URL);
//        orderPage.pageDown();
//        orderPage.clearDomainInputField();
//        orderPage.clickOnPage();
//        orderPage.clickIownThisDomain();
//        orderPage.clickContinueOrderButton();
//        checkErrorMessage();
//    }
//
//    @Test
//    public void emptyDomainFieldRegisterNewDomainTest() {
//        goToPage(BASE_URL);
//        orderPage.pageDown();
//        orderPage.clearDomainInputField();
//        orderPage.clickOnPage();
//        orderPage.clickRegisterNewDomain();
//        orderPage.clickOnPage();
//        orderPage.clickContinueOrderButton();
//        checkErrorMessage();
//    }
//
//    @Test
//    public void domainWithWrongTldOwnDomainNameTest() {
//        goToPage(BASE_URL);
//        orderPage.pageDown();
//        orderPage.clearDomainInputField();
//        orderPage.clickOnPage();
//        orderPage.clickIownThisDomain();
//        orderPage.inputDomainName(domainWithWrongTld);
//        orderPage.clickOnPage();
//        checkStatusDomainAvailableTickStatus();
//    }
//
//    @Test
//    public void domainWithWrongTldRegisterNewDomainTest() {
//        goToPage(BASE_URL);
//        orderPage.pageDown();
//        orderPage.clearDomainInputField();
//        orderPage.clickOnPage();
//        orderPage.clickRegisterNewDomain();
//        orderPage.inputDomainName(domainWithWrongTld);
//        orderPage.clickOnPage();
//        orderPage.clickContinueOrderButton();
//        checkErrorMessage();
//    }
//
//    @Test
//    public void incorrectDomainNameOwnDomainTest() {
//        goToPage(BASE_URL);
//        orderPage.pageDown();
//        orderPage.clearDomainInputField();
//        orderPage.clickOnPage();
//        orderPage.clickIownThisDomain();
//        orderPage.inputDomainName(incorrectDomain);
//        orderPage.clickContinueOrderButton();
//        checkErrorMessage();
//    }
//
//    @Test
//    public void incorrectDomainNameRegisterNewDomainTest() {
//        goToPage(BASE_URL);
//        orderPage.pageDown();
//        orderPage.clearDomainInputField();
//        orderPage.clickOnPage();
//        orderPage.clickRegisterNewDomain();
//        orderPage.inputDomainName(incorrectDomain);
//        orderPage.clickContinueOrderButton();
//        checkErrorMessage();
//    }
//
//    @Test
//    public void byProductWithOwnDomainTest() {
//        goToHostingPage(BASE_URL);
//        orderPage.pageDown();
//        orderPage.clearDomainInputField();
////        orderPage.clickOnPage();
//        orderPage.clickIownThisDomain();
//        orderPage.inputDomainName(secondDomain);
//        orderPage.clickOnPage();
//        orderPage.clickContinueOrderButton();
//        checkIsRegisterPageCurrentURL();
//        clearCartAndLogoutAfterTest();
//    }
//
//    @Test
//    public void byProductWithRegisterNewDomainTest() {
//        goToHostingPage(BASE_URL);
//        orderPage.pageDown();
//        orderPage.clearDomainInputField();
//        orderPage.clickOnPage();
//        orderPage.clickRegisterNewDomain();
//        orderPage.inputDomainName(secondDomain);
//        orderPage.clickOnPage();
//        orderPage.waitPrice();
//        orderPage.clickContinueOrderButton();
//        checkIsShoppingCartCurrentURL();
//        clearCartAndLogoutAfterTest();
//    }
//
//    public void goToHostingPage(String url) {
//        driver.get(url);
//        orderPage = new OrderPage(driver);
//    }
//
//    public void goToPage(String url) {
//        if (!driver.getCurrentUrl().equals(url)) {
//            driver.get(url);
//        }
//        orderPage = new OrderPage(driver);
//    }
//
//    public void checkErrorForTelTld() {
//        Assert.assertEquals(orderPage.getValidationErrorMessage(), ".TEL domains cannot be used with this product. Please try another");
//    }
//
//    public void checkErrorMessage() {
//        Assert.assertTrue(orderPage.getValidationErrorMessage().equals("Domain name is invalid") ||
//                orderPage.getValidationErrorMessage().equals("Domain name is required") ||
//                orderPage.getValidationErrorMessage().equals("Requested domain name is invalid"));
//    }
//
//    public void checkStatusDomainAvailableTickStatus() {
//        Assert.assertTrue(orderPage.getDomainAvailableTickStatus());
//    }
//
//    public void checkIsShoppingCartCurrentURL() {
//        Assert.assertEquals(orderPage.getCurrentURL(), BASE_URL + "shopping-cart/");
//    }
//
//    public void checkIsRegisterPageCurrentURL() {
//        Assert.assertTrue(orderPage.getCurrentURL().contains("register/"));
//    }
//
//    public void rememberPriceAfterAction() {
//        priceAfter.setPrice(orderPage.getTotalPrice());
//    }
//
//    public void rememberPriceBeforeAction() {
//        priceBefore.setPrice(orderPage.getTotalPrice());
//    }
//
//    public void comparePrices() {
//        Assert.assertFalse(priceAfter.equals(priceBefore));
//    }
//
//
//
//    @Override
//    public void checkPageUrl(String expectedURL) {
//        Assert.assertEquals(orderPage.getCurrentURL(), expectedURL);
//    }
//
//    @Override
//    public void checkProductNameTitleText(String expectedNameOfProduct) {
//        Assert.assertEquals(orderPage.getProductName(), expectedNameOfProduct);
//    }
//
//    @Override
//    public void checkPlanNameText(String expectedNameOfPlan) {
//        Assert.assertEquals(orderPage.getPlanName(), expectedNameOfPlan);
//    }
//
//    @Override
//    public void checkCountOfPlanOptions(int expectCountOfOptions) {
//        Assert.assertTrue(orderPage.getOptionCount() == expectCountOfOptions);
//    }
//
//    @Override
//    public void checkCountOfAddons(int expectCountOfAddons) {
//        Assert.assertTrue(orderPage.getAddonCount() == expectCountOfAddons);
//    }
//
//
//    public void clearCartAndLogoutAfterTest() {
//        try {
//            //*[@id='cart_control']/span[2]
//            if (!driver.findElement(By.xpath("//*[@id=\"cart_control\"]/span")).getText().equals("0 items")) {
//                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//                driver.findElement(HEADER_DROP_DOWN).clickOnPage();
//                driver.findElement(VIEW_CART_BUTTON).clickOnPage();
//                driver.findElement(EMPTY_CART).clickOnPage();
//                waitElement(DELETE_ALL_BUTTON);
//                driver.findElement(DELETE_ALL_BUTTON).clickOnPage();
//                //verify that the cart is empty
//                waitUntilTextPresentInElement(HEADER_DROP_DOWN, "0 items");
//                driver.get(BASE_URL + "logout/");
//                waitElement(HOME_PAGE);
//                waitUntilElementWillBeInvisible(HEADER_USER_NAME);
//            }
//
//        } catch (Exception ElementNotSelectableException) {
//            LogForTest.info("Can't clear shopping cart");
//        }
//        driver.manage().deleteAllCookies();
//    }
//
//    @AfterMethod //If any test crashed - take screenshot and write ERROR message in log file
//    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
//        driver.manage().deleteAllCookies();
//        if (testResult.getStatus() == ITestResult.FAILURE) {
//            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            String fn = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date()).toString();
//            FileUtils.copyFile(scrFile, new File("C:\\Automation\\Screenshot\\CrazyWindowsHosting\\Economy"
//                    + fn + testResult.getName() + " " + setScreenshotName() + ".jpg"));
//            LogForTest.error("Test " + testResult.getName() + " ERROR !");
//        }
//    }
//
//}
