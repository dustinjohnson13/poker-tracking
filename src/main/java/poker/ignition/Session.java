package poker.ignition;

import com.google.common.base.MoreObjects;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Session {
    private final String id;
    private final LocalDateTime startTime;
    private final SessionType type;
    private final long smallBlind;
    private final long bigBlind;
    private final long tableNumber;
    private final long stack;
    private final long stackChange;
    private final List<Hand> hands;

    public Session(String id, LocalDateTime startTime, SessionType type,
                   long smallBlind, long bigBlind,
                   long stack, long stackChange,
                   long tableNumber, List<Hand> hands) {
        this.id = id;
        this.startTime = startTime;
        this.type = type;
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
        this.tableNumber = tableNumber;
        this.stack = stack;
        this.stackChange = stackChange;
        this.hands = hands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return smallBlind == session.smallBlind &&
                bigBlind == session.bigBlind &&
                tableNumber == session.tableNumber &&
                stack == session.stack &&
                stackChange == session.stackChange &&
                Objects.equals(id, session.id) &&
                Objects.equals(startTime, session.startTime) &&
                type == session.type &&
                Objects.equals(hands, session.hands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, type, smallBlind, bigBlind, tableNumber, stack, stackChange, hands);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("startTime", startTime)
                .add("type", type)
                .add("smallBlind", smallBlind)
                .add("bigBlind", bigBlind)
                .add("tableNumber", tableNumber)
                .add("stack", stack)
                .add("stackChange", stackChange)
                .add("hands", hands)
                .toString();
    }
}
