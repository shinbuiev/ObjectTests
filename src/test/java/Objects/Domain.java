package Objects;

/**
 * Created by Sergiy.K on 27-Oct-16.
 */
public class Domain {
    private String name;
    private String domainName;
    private String domainTld;


    public Domain(String name){
        this.name = name;
    }

    public String getDomainTld() {
        if (name.contains("."))
            return name.split(".")[1].toLowerCase();
        else return "domain have not tld";
    }

    public String getDomainName() {
        if (name.contains("."))
            return name.split(".")[0].toLowerCase();
        else return name;
    }

    @Override
    public String toString() {
        return name.toLowerCase();
    }
}
