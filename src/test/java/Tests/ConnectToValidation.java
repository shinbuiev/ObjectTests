package Tests;

import EmailNotification.ErrorMessage;
import Pages.BaseOrderPage;
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

//    public ConnectToValidation(EventFiringWebDriverWrapper driver) {
//        this.driver = driver;
////        orderPage = new BaseOrderPage(driver);
//    }
    public ConnectToValidation(BaseOrderPage orderPage){
        this.orderPage = orderPage;
    }

    public void emptyDomainFieldOwnDomainTest() {
        orderPage.pageEnd();
        orderPage.clickIownThisDomain();
        orderPage.clearDomainInputField();
        orderPage.clickOnPage();
        orderPage.clickContinueOrderButton();
        checkErrorMessage("", orderPage.getDomainOwner());
    }

    public void emptyDomainFieldRegisterNewDomainTest() {
        orderPage.pageEnd();
        orderPage.clickRegisterNewDomain();
        orderPage.inputDomainName(domain);
        orderPage.clickOnPage();  //here need to add wait green light))!!!!!
        orderPage.clearDomainInputField();
        orderPage.clickOnPage();
        orderPage.clickContinueOrderButton();
        checkErrorMessage("", orderPage.getDomainOwner());
    }

    public void domainWithWrongTldOwnDomainNameTest() {  //fail here
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

        checkErrorMessage(domainWithWrongTld, orderPage.getDomainOwner());
    }

    public void domainWithWrongTldRegisterNewDomainTest() { //fail here
        orderPage.pageEnd();
        orderPage.clickRegisterNewDomain();
        orderPage.inputDomainName(domain);
        orderPage.clickOnPage();
        orderPage.clearDomainInputField();

        orderPage.inputDomainName(domainWithWrongTld);
        orderPage.clickOnPage();
//        orderPage.clickContinueOrderButton();
//        orderPage.pageEnd();

        checkErrorMessage(domainWithWrongTld, orderPage.getDomainOwner());
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
        checkErrorMessage(incorrectDomain, orderPage.getDomainOwner());
    }

    public void incorrectDomainNameRegisterNewDomainTest() {
        orderPage.pageEnd();
        orderPage.clickRegisterNewDomain();
        orderPage.inputDomainName(domain);
        orderPage.clickOnPage();
        orderPage.clearDomainInputField();
        orderPage.inputDomainName(incorrectDomain);
        orderPage.clickContinueOrderButton();
        checkErrorMessage(incorrectDomain, orderPage.getDomainOwner());
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

