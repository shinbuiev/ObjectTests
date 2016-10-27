package Objects;

import java.util.ArrayList;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class Plan {
    private String planName;
    private String orderPageUrl;
    private String planPageUrl;
    private Term term;
    private ArrayList<Term> terms;
    private ArrayList<Addon> addons;

    //constructors

    public Plan(String planName) {
        this.planName = planName;
    }

    public Plan(String planName, Term term) {
        this.term = term;
        this.planName = planName;
    }

    public Plan(String planName, String orderPageUrl) {
        this.orderPageUrl = orderPageUrl;
        this.planName = planName;
    }

    public Plan(String planName, String Url, ArrayList<Term> terms, ArrayList<Addon> addons) {
        this.planName = planName;
        this.orderPageUrl = Url;
        this.terms = terms;
        this.addons = addons;
    }

    public void setPlanPageUrl(String planPageUrl) {
        this.planPageUrl = planPageUrl;
    }
    public String getPlanPageUrl() {
        return planPageUrl;
    }


    public ArrayList<Addon> getAddons() {
        return addons;
    }

    public void setAddons(ArrayList<Addon> addons) {
        this.addons = addons;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }


    public String getOrderPageUrl() {
        return orderPageUrl;
    }

    public void setOrderPageUrl(String orderPageUrl) {
        this.orderPageUrl = orderPageUrl;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String planToString() {
        String result = "For " + planName + " plan " + "\n";
//        result = result + getOrderPageUrl() + "\n";
        result = result + "Terms:" + "\n";
        for (int i = 0; i < terms.size(); i++) {
            result = result + terms.get(i) + "\n";
        }
        result = result + "Addons: " + "\n";
        for (int i = 0; i < addons.size(); i++) {
            result = result + addons.get(i) + "\n";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plan plan = (Plan) o;

        if (!this.planName.equals(plan.planName)) return false;
//
//        if ((this.planPageUrl!=null)&& pl&&(!this.orderPageUrl.equals(plan.orderPageUrl))) return false;
//        if (!this.planPageUrl.equals(plan.planPageUrl)) return false;
//        if (!this.term.equals(plan.term)) return false;
//        if (!this.terms.equals(plan.terms)) return false;
//        if (!this.addons.equals(plan.addons)) return false;
        //дописати!!!!
        else return true;

    }

    @Override
    public int hashCode() {
        int result = planName.hashCode();
        result = 31 * result + (orderPageUrl != null ? orderPageUrl.hashCode() : 0);
        result = 31 * result + (planPageUrl != null ? planPageUrl.hashCode() : 0);
        result = 31 * result + (term != null ? term.hashCode() : 0);
        result = 31 * result + (terms != null ? terms.hashCode() : 0);
        result = 31 * result + (addons != null ? addons.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return planName;
    }

}
