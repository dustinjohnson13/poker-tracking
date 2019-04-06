package poker.ignition;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class Blinds {

    private final long small;
    private final long big;

    public Blinds(long small, long big) {
        this.small = small;
        this.big = big;
    }

    public long getSmall() {
        return small;
    }

    public long getBig() {
        return big;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blinds blinds = (Blinds) o;
        return small == blinds.small &&
                big == blinds.big;
    }

    @Override
    public int hashCode() {
        return Objects.hash(small, big);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("small", small)
                .add("big", big)
                .toString();
    }
}
