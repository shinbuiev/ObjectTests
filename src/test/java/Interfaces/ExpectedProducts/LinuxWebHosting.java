package Interfaces.ExpectedProducts;

import Objects.Addon;
import Objects.Plan;
import Objects.Product;
import Objects.Term;

import java.util.ArrayList;

/**
 * Created by Sergiy.K on 25-Oct-16.
 */
public class LinuxWebHosting {

    public String getProductName() {
        return "Web Hosting";
    }

    public ArrayList<Term> getTerms() {
        ArrayList<Term> terms = new ArrayList<Term>() {{
            add(new Term("12"));
            add(new Term("24"));
            add(new Term("36"));
            add(new Term("120"));
        }};
        return terms;
    }

    public ArrayList<Plan> getPlans() {
        ArrayList<Plan> plans = new ArrayList<Plan>() {{
            add(new Plan("Economy"));
            add(new Plan("Premium"));
            add(new Plan("Unlimited"));
        }};
        return plans;
    }

    public ArrayList<Addon> getAddons() {
        final ArrayList<Addon> addons = new ArrayList<Addon>() {{
            add(new Addon("Traffic Booster"));
            add(new Addon("Web Analytics"));
            add(new Addon("Premium Email Protection"));
            add(new Addon("Secure Web Hosting"));
            add(new Addon("Mailing List Manager"));
        }};
        return addons;
    }

    public Product getLinuxHosting() {
        Product linuxWebHosting = new Product("Web Hosting");
        linuxWebHosting.setAddons(getAddons());
        linuxWebHosting.setPlans(getPlans());
        linuxWebHosting.setTerms(getTerms());
        return linuxWebHosting;
    }

}
