package Interfaces.ExpectedProducts;

import Objects.*;

import java.util.ArrayList;

/**
 * Created by Sergiy.K on 26-Oct-16.
 */
public class LinuxHosting {

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
        final Product product = new Product("Web Hosting");
        final Plan economy = new Plan("Economy");
        final Plan premium = new Plan("Premium");
        final Plan unlimited = new Plan("Unlimited");

        economy.setPlanPageUrl("https://www.crazydomains.com.au/web-hosting/order-economy-linux-hosting/");
        premium.setPlanPageUrl("https://www.crazydomains.com.au/web-hosting/order-premium-linux-hosting/");
        unlimited.setPlanPageUrl("https://www.crazydomains.com.au/web-hosting/order-unlimited-linux-hosting/");

        ArrayList<Term> terms = new ArrayList<Term>() {{
            add(new Term("12"));
            add(new Term("24"));
            add(new Term("36"));
            add(new Term("120"));
        }};

        economy.setTerms(terms);
        premium.setTerms(terms);
        unlimited.setTerms(terms);


        final ArrayList<Addon> addons = new ArrayList<Addon>() {{
            add(new Addon("Traffic Booster"));
            add(new Addon("Web Analytics"));
            add(new Addon("Premium Email Protection"));
            add(new Addon("Secure Web Hosting"));
            add(new Addon("Mailing List Manager"));
        }};

        economy.setAddons(addons);
        premium.setAddons(addons);
        unlimited.setAddons(addons);

        product.setPlans(new ArrayList<Plan>() {{
            add(economy);
            add(premium);
            add(unlimited);
        }});
        return product;
    }

}
