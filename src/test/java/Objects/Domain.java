package Objects;

/**
 * Created by Sergiy.K on 27-Oct-16.
 */
public class Domain {
    private String name;
    private String domainName;
    private String domainTld;
    private Price domainPrice;

    //  need to remake this class!!!

    public Domain(String name) {
        this.name = name;
    }

    public Domain(String name, Price domainPrice) {
        this.name = name;
        this.domainPrice = domainPrice;
    }

    public Price getDomainPrice() {
        return domainPrice;
    }

    public String getDomainTld() {
        if (name.contains("."))
            return name.split(".")[1].toLowerCase();
        else return "domain have not tld";
    }

    public String getDomainName() {
//        if (name.contains("."))
//            return name.split(".")[0].toLowerCase();
//        else return name;
        return name.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;

        Domain domain = (Domain) o;

        return this.name.equals(domain.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name.toLowerCase();
    }
}
