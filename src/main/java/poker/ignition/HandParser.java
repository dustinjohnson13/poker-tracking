package poker.ignition;

import poker.util.CurrencyUtil;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static poker.util.CurrencyUtil.penniesFromDollarsCentsString;

public class HandParser {

    private static final Pattern ID_PATTERN = Pattern.compile("Hand #(\\d+)", CASE_INSENSITIVE);
    private static final Pattern SEAT_PATTERN = Pattern.compile("Seat (\\d+):(.*?)\\s\\(\\$(.*?)\\sin chips\\)", CASE_INSENSITIVE);
    private static final Pattern RAISES_PATTERN = Pattern.compile(".*? : Raises (\\$(.*?)\\s) to (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern CALLS_PATTERN = Pattern.compile(".*? : Calls (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern POT_DISTRIBUTION_PATTERN = Pattern.compile("Seat\\+\\d+: .*? (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern POST_SMALL_BLIND_PATTERN = Pattern.compile("Small Blind.*?:.*?Small Blind (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern POST_BIG_BLIND_PATTERN = Pattern.compile("Big Blind.*?:.*?Big blind (\\$(.*?)\\s)", CASE_INSENSITIVE);

    public Hand parse(String hand) {
        Matcher idMatcher = ID_PATTERN.matcher(hand);
        if (!idMatcher.find()) {
            throw new IllegalArgumentException(String.format("No hand id in %s", hand));
        }
        long id = Long.parseLong(idMatcher.group(1));

        Map<Position, Long> stackAdjustmentsByPosition = resolveStackAdjustments(hand);

        Matcher seatMatcher = SEAT_PATTERN.matcher(hand);
        List<Seat> seats = new ArrayList<>();
        while (seatMatcher.find()) {
            int seatNumber = Integer.parseInt(seatMatcher.group(1));
            String positionDescription = seatMatcher.group(2).trim();
            boolean me = positionDescription.contains("[ME]");

            Position position = getPositionOrThrow(positionDescription);

            long stack = penniesFromDollarsCentsString(seatMatcher.group(3));

            seats.add(new Seat(seatNumber, position, me, stack, stackAdjustmentsByPosition.getOrDefault(position, 0L)));
        }

        return new Hand(id, seats);
    }

    private Map<Position, Long> resolveStackAdjustments(String hand) {
        Map<Position, Long> map = new EnumMap<>(Position.class);

        processAdjustments(map, hand, POST_SMALL_BLIND_PATTERN, 2, true);
        processAdjustments(map, hand, POST_BIG_BLIND_PATTERN, 2, true);
        processAdjustments(map, hand, RAISES_PATTERN, 4, true);
        processAdjustments(map, hand, CALLS_PATTERN, 2, true);
        processAdjustments(map, hand, POT_DISTRIBUTION_PATTERN, 2, false);

        return map;
    }

    private void processAdjustments(Map<Position, Long> adjustmentsByPosition, String hand, Pattern adjustmentsPattern, int valueGroup, boolean negate) {
        Matcher m = adjustmentsPattern.matcher(hand);
        while (m.find()) {
            Position position = getPositionOrThrow(m.group());
            long adjustment = penniesFromDollarsCentsString(m.group(valueGroup));
            if (negate) {
                adjustment = -adjustment;
            }

            if (adjustmentsByPosition.containsKey(position)) {
                adjustment += adjustmentsByPosition.get(position);
            }
            adjustmentsByPosition.put(position, adjustment);
        }
    }

    private Position getPositionOrThrow(String positionDescription) {
        Optional<Position> position = Position.fromLine(positionDescription);
        if (!position.isPresent()) {
            throw new IllegalArgumentException(String.format("No position found in %s", positionDescription));
        }
        return position.get();
    }
}