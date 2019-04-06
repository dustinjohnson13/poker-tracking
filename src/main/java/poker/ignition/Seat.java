package poker.ignition;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class Seat {

    private final int number;
    private final Position position;
    private final boolean me;
    private long stack;
    private long stackChange;

    public Seat(int number, Position position, boolean me, long stack, long stackChange) {
        this.number = number;
        this.position = position;
        this.me = me;
        this.stack = stack;
        this.stackChange = stackChange;
    }

    public int getNumber() {
        return number;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isMe() {
        return me;
    }

    public long getStack() {
        return stack;
    }

    public long getStackChange() {
        return stackChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return number == seat.number &&
                me == seat.me &&
                stack == seat.stack &&
                stackChange == seat.stackChange &&
                position == seat.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, position, me, stack, stackChange);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("number", number)
                .add("position", position)
                .add("me", me)
                .add("stack", stack)
                .add("stackChange", stackChange)
                .toString();
    }
}