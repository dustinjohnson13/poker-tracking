package poker.domain;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class HoleCards {

    private final Card one;
    private final Card two;

    public HoleCards(Card one, Card two) {
        this.one = one;
        this.two = two;
    }

    public Card getOne() {
        return one;
    }

    public Card getTwo() {
        return two;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoleCards holeCards = (HoleCards) o;
        return one == holeCards.one &&
                two == holeCards.two;
    }

    @Override
    public int hashCode() {
        return Objects.hash(one, two);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("one", one)
                .add("two", two)
                .toString();
    }
}
