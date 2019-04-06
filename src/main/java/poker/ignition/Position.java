package poker.ignition;

import java.util.Optional;

public enum Position {
    UTG_1("UTG+1"), UTG_2("UTG+2"), UTG_3("UTG+3"), UTG_4("UTG+4"), UTG_5("UTG+5"), UTG("UTG"),
    DEALER("Dealer"), SMALL_BLIND("Small Blind"), BIG_BLIND("Big Blind");

    private final String textPrefix;

    Position(String textPrefix) {
        this.textPrefix = textPrefix;
    }

    public static Optional<Position> fromLine(String line) {

        for (Position position : Position.values()) {
            // Make sure we don't inadvertently match UTG for any positions after it
            if (line.contains("UTG+") && position == UTG) {
                continue;
            }

            if (line.contains(position.textPrefix)) {
                return Optional.of(position);
            }
        }

        return Optional.empty();
    }
}