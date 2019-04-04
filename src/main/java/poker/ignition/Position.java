package poker.ignition;

import javafx.geometry.Pos;

import java.util.Optional;

public enum Position {
    UTG_1("UTG+1"), UTG_2("UTG+2"), UTG("UTG"), DEALER("Dealer"), SMALL_BLIND("Small Blind"), BIG_BLIND("Big Blind");

    private final String textPrefix;

    Position(String textPrefix) {
        this.textPrefix = textPrefix;
    }

    public static Optional<Position> fromLine(String line) {

        for (Position position : Position.values()) {
            if (line.contains(position.textPrefix)) {
                return Optional.of(position);
            }
        }

        return Optional.empty();
    }
}