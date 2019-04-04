package poker.ignition;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

public class Hand {

    private final long id;
    private final List<Seat> seats;

    public Hand(long id, List<Seat> seats) {
        this.id = id;
        this.seats = seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return id == hand.id &&
                Objects.equals(seats, hand.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seats);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("id", id)
                .add("seats", seats)
                .toString();
    }
}