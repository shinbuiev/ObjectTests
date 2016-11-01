package Pages;

import EmailNotification.Email;
import EmailNotification.ErrorMessage;
import Interfaces.ExpectedProducts.LinuxWebHosting;
import Interfaces.ExpectedProducts.WindowsWebHosting;
import Objects.Product;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public abstract class BasePage {
    private java.lang.String errors = "";
    private LinuxWebHosting linuxWebHosting = new LinuxWebHosting();
    private WindowsWebHosting windowsWebHosting = new WindowsWebHosting();
    private static ArrayList<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();
    public Product productBefore;
    public Product productAfter;
    @FindBy(xpath = "/html/body")
    private WebElement CLICK;

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public void rememberProductBefore(BasePage page) {
        productBefore = page.getProduct();
        System.out.println("Reember product before ");
    }

    public void pageEnd() {
        CLICK.sendKeys(Keys.END);
    }

    public void pageDown() {
        CLICK.sendKeys(Keys.PAGE_DOWN);
    }

    public abstract Product getProduct();

    public String getCurrentUrl() {
        return driver.getCurrentUrl();


    }

    public void rememberProductAfter(BasePage page) {
        productAfter = page.getProduct();
    }
    public void checkProductSpecification(BasePage page) {
        if (!errors.equals("")) {
            errors = errors + "\n";
        }
        errors = errors + linuxWebHosting.isProduct(page.getProduct());
    }



    public void comparePlanPageAndOrderPageProducts(){

        productBefore.comparePlanPageOrderPageProductsAndGetErrors(productAfter);
        if (productBefore.getErrorMessages().size() > 0){
            errorMessageList.addAll(productBefore.getErrorMessages());
        }
    }

    public void compareProductsOrderPageAndShoppingCart() {
        productBefore.getErrorShoppingCartPage(productAfter);
        if (productBefore.getErrorMessages().size() > 0){
            errorMessageList.addAll(productBefore.getErrorMessages());
        }
    }

    public static void errorSending(){
        if (errorMessageList.size() > 0)
        {
            Email email = new Email();
            try {
                email.execute("Result for Web Hosting buy test ", errorMessageList);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("can't send email  \n" + e.getMessage());
            }
        }
    }
    }


