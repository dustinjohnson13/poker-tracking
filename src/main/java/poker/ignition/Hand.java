package poker.ignition;

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Hand {

    private final long id;
    private final Blinds blinds;
    private final List<Seat> seats;

    public Hand(long id, Blinds blinds, List<Seat> seats) {
        this.id = id;
        this.blinds = blinds;
        this.seats = seats;
    }

    public long getId() {
        return id;
    }

    public Blinds getBlinds() {
        return blinds;
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
                Objects.equals(blinds, hand.blinds) &&
                Objects.equals(seats, hand.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blinds, seats);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("blinds", blinds)
                .add("seats", seats)
                .toString();
    }
}