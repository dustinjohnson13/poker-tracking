package poker.ignition;

import com.google.common.base.MoreObjects;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private String id;
    private LocalDateTime startTime;
    private SessionType type;
    private long smallBlind;
    private long bigBlind;
    private long tableNumber;

    public Session(String id, LocalDateTime startTime, SessionType type, long smallBlind, long bigBlind, long tableNumber) {
        this.id = id;
        this.startTime = startTime;
        this.type = type;
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
        this.tableNumber = tableNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return smallBlind == session.smallBlind &&
                bigBlind == session.bigBlind &&
                tableNumber == session.tableNumber &&
                Objects.equals(id, session.id) &&
                Objects.equals(startTime, session.startTime) &&
                type == session.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, type, smallBlind, bigBlind, tableNumber);
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
                .toString();
    }
}
