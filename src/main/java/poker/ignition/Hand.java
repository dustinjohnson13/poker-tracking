package poker.ignition;

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Hand {

    private final long id;
    private final long smallBlind;
    private final long bigBlind;
    private final List<Seat> seats;

    public Hand(long id, long smallBlind, long bigBlind, List<Seat> seats) {
        this.id = id;
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
        this.seats = seats;
    }

    public long getId() {
        return id;
    }

    public long getSmallBlind() {
        return smallBlind;
    }

    public long getBigBlind() {
        return bigBlind;
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
                smallBlind == hand.smallBlind &&
                bigBlind == hand.bigBlind &&
                Objects.equals(seats, hand.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, smallBlind, bigBlind, seats);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("smallBlind", smallBlind)
                .add("bigBlind", bigBlind)
                .add("seats", seats)
                .toString();
    }
}