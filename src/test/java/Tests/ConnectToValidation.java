package Tests;

import EmailNotification.ErrorMessage;
import Pages.BaseOrderPage;
import Pages.WebHosting.HostingOrderPage;
import Utils.EventFiringWebDriverWrapper;
import java.util.ArrayList;

/**
 * Created by Sergiy.K on 07-Nov-16.
 */

public class ConnectToValidation {

    private static ArrayList<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();
    private String domainWithTelTld = "myfirstByInCrazy.tel";
    private String domain = "myfirstbuyincrazy.com.au";
    private String secondDomain = "mysecondbuyincrazy.com.au";
    private String incorrectDomain = "myfirstbuyincrazy";
    private String domainWithWrongTld = "myfirstbuyincrazy.none";
    private EventFiringWebDriverWrapper driver;
    private BaseOrderPage orderPage;

    public ConnectToValidation(EventFiringWebDriverWrapper driver) {
        this.driver = driver;
        orderPage = new BaseOrderPage(driver);
    }

    public void emptyDomainFieldOwnDomainTest() {
        orderPage.pageEnd();
        orderPage.clearDomainInputField();
        orderPage.clickOnPage();
        orderPage.clickContinueOrderButton();
        checkErrorMessage("", orderPage.getConnectToSelectedRadioButton());
    }

    public void emptyDomainFieldRegisterNewDomainTest() {
        orderPage.pageEnd();
        orderPage.clickRegisterNewDomain();
        orderPage.inputDomainName(domain);
        orderPage.clickOnPage();  //here need to add wait green light))!!!!!
        orderPage.clearDomainInputField();
        orderPage.clickOnPage();
        orderPage.clickContinueOrderButton();
        checkErrorMessage("", orderPage.getConnectToSelectedRadioButton());
    }

    public void domainWithWrongTldOwnDomainNameTest() {
        orderPage.pageEnd();
        orderPage.clickIownThisDomain();
        orderPage.inputDomainName(domain);
        orderPage.clickOnPage();
        orderPage.clearDomainInputField();

        orderPage.inputDomainName(domainWithWrongTld);
        orderPage.clickOnPage();
        orderPage.clickContinueOrderButton();
        orderPage.pageEnd();
        orderPage.waitErrorMessage();

        checkErrorMessage(domainWithWrongTld, orderPage.getConnectToSelectedRadioButton());
    }

    public void domainWithWrongTldRegisterNewDomainTest() {
        orderPage.pageEnd();
        orderPage.clickRegisterNewDomain();
        orderPage.inputDomainName(domain);
        orderPage.clickOnPage();
        orderPage.clearDomainInputField();

        orderPage.inputDomainName(domainWithWrongTld);
        orderPage.clickOnPage();
//        orderPage.clickContinueOrderButton();
//        orderPage.pageEnd();

        checkErrorMessage(domainWithWrongTld, orderPage.getConnectToSelectedRadioButton());
    }

    public void incorrectDomainNameOwnDomainTest() {
        orderPage.pageEnd();
        orderPage.clickIownThisDomain();
        orderPage.inputDomainName(domain);
        orderPage.clickOnPage();
        orderPage.clearDomainInputField();
        orderPage.clickOnPage();
        orderPage.inputDomainName(incorrectDomain);
        orderPage.clickOnPage();
        orderPage.clickContinueOrderButton();
        checkErrorMessage(incorrectDomain, orderPage.getConnectToSelectedRadioButton());
    }

    public void incorrectDomainNameRegisterNewDomainTest() {
        orderPage.pageEnd();
        orderPage.clickRegisterNewDomain();
        orderPage.inputDomainName(domain);
        orderPage.clickOnPage();
        orderPage.clearDomainInputField();
        orderPage.inputDomainName(incorrectDomain);
        orderPage.clickContinueOrderButton();
        checkErrorMessage(incorrectDomain, orderPage.getConnectToSelectedRadioButton());
    }

    private void checkErrorMessage(String domainName, String owner) { //need to remake logic maybe transfer to order page
        switch (orderPage.getValidationErrorMessage()) {
            case "Domain name is invalid":
                break;
            case "Domain name is required":
                break;
            case "Requested domain name is invalid":
                break;

            default: {
                if (orderPage.getValidationErrorMessage() != null || !orderPage.getValidationErrorMessage().equals(""))
                    errorMessageList.add(new ErrorMessage("Validation Error Block ConnectTo on page: " + ", if click "
                            + owner + " and input domain name: " + domainName + ", find this error " + orderPage.getValidationErrorMessage()));
                else
                    errorMessageList.add(new ErrorMessage("Validation Error Block ConnectTo on page: " + ", if click "
                            + owner + " and input domain name: " + domainName));
            }
        }
    }

    public ArrayList<ErrorMessage> getErrorMessageList() {
        return errorMessageList;
    }

}

