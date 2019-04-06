package poker.ignition;

import com.google.common.base.MoreObjects;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Session {
    private final String id;
    private final LocalDateTime startTime;
    private final SessionType type;
    private final long tableNumber;
    private final long cashIn;
    private final long cashOut;
    private final List<Hand> hands;

    public Session(String id, LocalDateTime startTime, SessionType type,
                   long cashIn, long cashOut,
                   long tableNumber, List<Hand> hands) {
        this.id = id;
        this.startTime = startTime;
        this.type = type;
        this.tableNumber = tableNumber;
        this.cashIn = cashIn;
        this.cashOut = cashOut;
        this.hands = hands;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public SessionType getType() {
        return type;
    }

    public long getTableNumber() {
        return tableNumber;
    }

    public long getCashIn() {
        return cashIn;
    }

    public long getCashOut() {
        return cashOut;
    }

    public List<Hand> getHands() {
        return hands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return tableNumber == session.tableNumber &&
                cashIn == session.cashIn &&
                cashOut == session.cashOut &&
                Objects.equals(id, session.id) &&
                Objects.equals(startTime, session.startTime) &&
                type == session.type &&
                Objects.equals(hands, session.hands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, type, tableNumber, cashIn, cashOut, hands);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("startTime", startTime)
                .add("type", type)
                .add("tableNumber", tableNumber)
                .add("cashIn", cashIn)
                .add("cashOut", cashOut)
                .add("hands", hands)
                .toString();
    }
}
