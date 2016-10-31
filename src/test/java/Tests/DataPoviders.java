package Tests;

import Interfaces.ExpectedProducts.LinuxWebHosting;
import Interfaces.ExpectedProducts.WindowsWebHosting;
import org.testng.annotations.DataProvider;

/**
 * Created by root on 31.10.16.
 */
public class DataPoviders {
    public static WindowsWebHosting windowsWebHosting=new WindowsWebHosting();

    @DataProvider(name = "userType")
  public static Object[][] getExpectedProduct(){
              return new Object[][]{
                      { windowsWebHosting.getProductPlans().get(0).getOrderPageUrl()},
                              { windowsWebHosting.getProductPlans().get(1).getOrderPageUrl()},
                              { windowsWebHosting.getProductPlans().get(2).getOrderPageUrl()}
                        //      { linuxWebHosting.getProductPlans().get(0).getOrderPageUrl()},
                          //    { linuxWebHosting.getProductPlans().get(1).getOrderPageUrl()},
                            //  { linuxWebHosting.getProductPlans().get(2).getOrderPageUrl()},
                       };
          }

}
