package Objects;

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

    public Addon(String addonName, Term addonTerm, Price addonPrice) {
        this.addonTerm = addonTerm;
        this.addonName = addonName;
        this.addonPrice = addonPrice;
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

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;

        boolean result = false;
        Addon addon = (Addon) o;
        if (this.addonName.equals(addon.addonName)) return true;
        if ((this.addonTerm != null) && (addon.addonTerm == null)) return false;
        if ((this.addonTerm != null) && (addon.addonTerm != null)) {
            if (this.addonTerm.equals(addon.addonTerm)) return true;
        } else return false;
        if ((this.addonPrice != null) & (addon.addonPrice == null)) return false;
        if ((this.addonPrice != null) & (addon.addonPrice != null)) {
            if ((this.addonPrice.equals(addon.addonPrice == null))) return true;
        } else return false;
        if ((this.addonTerm != null) && (addon.addonTerm == null)) return false;
        else return false;
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
            return "Addon: " + addonName + " price: " + addonPrice;
        }
        if (addonPrice != null && addonTerm != null) {
            return "Addon: " + addonName + " price: " + addonPrice + " term: " + addonTerm;
        }
        if (addonTerm != null)
            return "Addon: " + addonName + " term: " + addonTerm;
        else return addonName;
    }

}
