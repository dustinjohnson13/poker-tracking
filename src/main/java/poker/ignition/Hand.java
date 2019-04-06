package poker.ignition;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.MoreObjects.toStringHelper;

public class Hand {

    private final long id;
    private final List<Seat> seats;

    public Hand(long id, List<Seat> seats) {
        this.id = id;
        this.seats = seats;
    }

    public long getId() {
        return id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    /**
     * Gets the player's seat, or throws an exception if not found.
     */
    public Seat getMySeat() {
        Optional<Seat> mySeat = seats.stream().filter(Seat::isMe).findFirst();
        if (!mySeat.isPresent()) {
            throw new IllegalStateException(String.format("Unable to find my seat in hand %s", getId()));
        }
        return mySeat.get();
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