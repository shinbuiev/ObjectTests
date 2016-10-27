package Objects;

/**
 * Created by Sergiy.K on 25-Oct-16.
 */
public class Term {
    private String term;

    public Term(String term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return term;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Term term1 = (Term) o;

        return term.equals(term1.term);
    }

    @Override
    public int hashCode() {
        return term.hashCode();
    }
}
