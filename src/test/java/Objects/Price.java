package Objects;

/**
 * Created by Sergiy.K on 21-Oct-16.
 */
public class Price {
    private String price;
    public Price (String price){
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price1 = (Price) o;

        if (this.price.equals(price1.price)) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return price != null ? price.hashCode() : 0;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return price;
    }
}
