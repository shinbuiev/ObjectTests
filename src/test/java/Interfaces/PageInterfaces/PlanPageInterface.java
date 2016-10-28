package Interfaces.PageInterfaces;

import Objects.Plan;
import Products.WebHostingProduct;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public interface PlanPageInterface {
    public void gotoPage(String url, String productName);
    public void selectPlan(String planName);
    public Plan getPlan();

}
