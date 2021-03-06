package poker.ignition;

import com.google.common.base.MoreObjects;
import poker.domain.HoleCards;

import java.util.Objects;

public class Seat {

    private final int number;
    private final Position position;
    private final HoleCards holeCards;
    private final boolean me;
    private long stack;
    private final long profitLoss;
    private final long cashDeposit;

    public Seat(int number, Position position, HoleCards holeCards,
                boolean me, long stack, long profitLoss, long cashDeposit) {
        this.number = number;
        this.position = position;
        this.holeCards = holeCards;
        this.me = me;
        this.stack = stack;
        this.profitLoss = profitLoss;
        this.cashDeposit = cashDeposit;
    }

    public int getNumber() {
        return number;
    }

    public Position getPosition() {
        return position;
    }

    public HoleCards getHoleCards() {
        return holeCards;
    }

    public boolean isMe() {
        return me;
    }

    public long getStack() {
        return stack;
    }

    public long getProfitLoss() {
        return profitLoss;
    }

    public long getCashDeposit() {
        return cashDeposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return number == seat.number &&
                me == seat.me &&
                stack == seat.stack &&
                profitLoss == seat.profitLoss &&
                cashDeposit == seat.cashDeposit &&
                position == seat.position &&
                Objects.equals(holeCards, seat.holeCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, position, holeCards, me, stack, profitLoss, cashDeposit);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("number", number)
                .add("position", position)
                .add("holeCards", holeCards)
                .add("me", me)
                .add("stack", stack)
                .add("profitLoss", profitLoss)
                .add("cashDeposit", cashDeposit)
                .toString();
    }
}