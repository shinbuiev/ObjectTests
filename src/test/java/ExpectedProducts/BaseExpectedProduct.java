package ExpectedProducts;

import Objects.Addon;
import Objects.Plan;
import Objects.Term;

import java.util.ArrayList;

/**
 * Created by geser on 30.10.16.
 */
public abstract class BaseExpectedProduct {
    //add here base methods names
    public abstract String getProductMainPage();
    public abstract String getProductName();
    public abstract ArrayList<Term> getProductTerms();
    public abstract ArrayList<Plan> getProductPlans();
    public abstract ArrayList<Addon> getProductAddons();

}
