package Objects;

import Interfaces.ProductInterface;

import java.util.ArrayList;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class Addon {
    private String addonName;
    private Price addonPrice;
    private Term addonTerm;


    public Addon(String addonName) {
        this.addonName = addonName;
    }

    public Addon(String addonName, Term addonTerm) {
        this.addonTerm = addonTerm;
        this.addonName = addonName;
    }

    public String getAddonName() {
        return addonName;
    }

    public Term getAddonTerm() {
        return addonTerm;
    }

    public void setAddonTerm(Term addonTerm) {
        this.addonTerm = addonTerm;
    }
    //
//    public void setAddonName(String addonName) {
//        this.addonName = addonName;
//    }
//
//    public String getAddonPrice() {
//        return addonPrice;
//    }
//
//    public void setAddonPrice(String addonPrice) {
//        this.addonPrice = addonPrice;
//    }
//
//    public String getAddonTerm() {
//        return addonTerm;
//    }
//
//    public void setAddonTerm(String addonTerm) {
//        this.addonTerm = addonTerm;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Addon addon = (Addon) o;

        if (!this.addonName.equals(addon.addonName)) return false;
        else return true;
//        if (!this.addonTerm.equals(addon.addonTerm)) return false;
//        if (!this.addonPrice.equals(addonPrice)) return false;
//        if (addonPrice != null ? !addonPrice.equals(addon.addonPrice) : addon.addonPrice != null) return false;
//        return addonTerm != null ? addonTerm.equals(addon.addonTerm) : addon.addonTerm == null;
    }

    @Override
    public int hashCode() {
        int result = addonName.hashCode();
        result = 31 * result + (addonPrice != null ? addonPrice.hashCode() : 0);
        result = 31 * result + (addonTerm != null ? addonTerm.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        if (addonPrice != null) {
            return addonName + addonPrice;
        }
        if (addonPrice != null && addonTerm != null) {
            return addonName +" " +  addonPrice + " " + addonTerm;
        }
        if (addonTerm != null)
            return addonName + " for " + addonTerm;
        else return addonName;

    }
}
