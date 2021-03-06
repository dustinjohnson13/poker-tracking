package poker.ignition;

import poker.domain.Card;
import poker.domain.HoleCards;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static poker.util.CurrencyUtil.penniesFromDollarsCentsString;

public class HandParser {

    private static final Pattern ID_PATTERN = Pattern.compile("Hand #(\\d+)", CASE_INSENSITIVE);
    private static final Pattern SEAT_PATTERN = Pattern.compile("Seat (\\d+):(.*?)\\s\\(\\$?(.*?)\\sin chips\\)", CASE_INSENSITIVE);
    private static final Pattern RAISES_PATTERN = Pattern.compile(".*? : Raises (\\$(.*?)) to (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern ALL_IN_RAISES_PATTERN = Pattern.compile(".*? : All-in\\(raise\\) (\\$(.*?)) to (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern CALLS_PATTERN = Pattern.compile(".*? : Calls (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern BETS_PATTERN = Pattern.compile(".*? : Bets (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern ALL_IN_PATTERN = Pattern.compile(".*? : All-in (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern RETURNS_UNCALLED_PORTION_OF_BET_PATTERN = Pattern.compile(".*? : Return uncalled portion of bet (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern POT_DISTRIBUTION_PATTERN = Pattern.compile("Seat\\+\\d+: .*? (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern POST_SMALL_BLIND_PATTERN = Pattern.compile(".*?:.*?Small Blind (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern POST_BIG_BLIND_PATTERN = Pattern.compile(".*?:.*?Big blind (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern POST_CHIP_PATTERN = Pattern.compile(".*? : Posts chip (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern BLIND_LEVEL_PATTERN = Pattern.compile(".*? Level \\d+ \\((\\d+)/(\\d+)\\).*", CASE_INSENSITIVE);
    private static final Pattern TABLE_DEPOSIT_PATTERN = Pattern.compile(".*? : Table deposit (\\$(.*?)\\s)", CASE_INSENSITIVE);
    private static final Pattern CARD_DEALT_PATTERN = Pattern.compile(".*? : Card dealt to a spot \\[(.*?) (.*?)]", CASE_INSENSITIVE);

    public Hand parse(String hand, Function<String, Blinds> determineBlinds) {
        Matcher idMatcher = ID_PATTERN.matcher(hand);
        if (!idMatcher.find()) {
            throw new IllegalArgumentException(String.format("No hand id in %s", hand));
        }
        long id = Long.parseLong(idMatcher.group(1));

        Map<Position, Long> blindsByPosition = resolveBlinds(hand);
        Map<Position, Long> profitLoss = resolveStackAdjustments(hand);
        Map<Position, Long> cashDepositsByPosition = resolveTableDeposits(hand);
        Map<Position, HoleCards> holeCardsByPosition = resolveHoleCards(hand);

        Matcher seatMatcher = SEAT_PATTERN.matcher(hand);
        List<Seat> seats = new ArrayList<>();
        while (seatMatcher.find()) {
            int seatNumber = Integer.parseInt(seatMatcher.group(1));
            String positionDescription = seatMatcher.group(2).trim();
            boolean me = positionDescription.contains("[ME]");

            Position position = getPositionOrThrow(positionDescription);

            HoleCards holeCards = holeCardsByPosition.get(position);
            long stack = penniesFromDollarsCentsString(seatMatcher.group(3));

            long stackAdjustments = blindsByPosition.getOrDefault(position, 0L) + profitLoss.getOrDefault(position, 0L);
            long cashDeposits = cashDepositsByPosition.getOrDefault(position, 0L);

            seats.add(new Seat(seatNumber, position, holeCards, me, stack, stackAdjustments, cashDeposits));
        }

        Blinds blinds = determineBlinds.apply(hand);

        return new Hand(id, new Blinds(blinds.getSmall(), blinds.getBig()), seats);
    }

    private Map<Position, HoleCards> resolveHoleCards(String hand) {
        Map<Position, HoleCards> map = new EnumMap<>(Position.class);
        Matcher m = CARD_DEALT_PATTERN.matcher(hand);
        while (m.find()) {
            Position position = getPositionOrThrow(m.group());
            Card first = Card.parse(m.group(1));
            Card second = Card.parse(m.group(2));

            map.put(position, new HoleCards(first, second));
        }
        return map;
    }

    private Map<Position, Long> resolveBlinds(String hand) {
        int startOfHoleCards = hand.indexOf("*** HOLE CARDS ***");
        String preDeal = hand.substring(0, startOfHoleCards);
        Map<Position, Long> map = new EnumMap<>(Position.class);

        processAdjustments(map, preDeal, POST_SMALL_BLIND_PATTERN, 2, true);
        processAdjustments(map, preDeal, POST_BIG_BLIND_PATTERN, 2, true);
        processAdjustments(map, preDeal, POST_CHIP_PATTERN, 2, true);

        return map;
    }

    private Map<Position, Long> resolveStackAdjustments(String hand) {
        Map<Position, Long> map = new EnumMap<>(Position.class);

        processAdjustments(map, hand, RAISES_PATTERN, 2, true);
        processAdjustments(map, hand, ALL_IN_RAISES_PATTERN, 2, true);
        processAdjustments(map, hand, BETS_PATTERN, 2, true);
        processAdjustments(map, hand, CALLS_PATTERN, 2, true);
        processAdjustments(map, hand, ALL_IN_PATTERN, 2, true);
        processAdjustments(map, hand, RETURNS_UNCALLED_PORTION_OF_BET_PATTERN, 2, false);
        processAdjustments(map, hand, POT_DISTRIBUTION_PATTERN, 2, false);

        return map;
    }

    private Map<Position, Long> resolveTableDeposits(String hand) {
        Map<Position, Long> map = new EnumMap<>(Position.class);
        processAdjustments(map, hand, TABLE_DEPOSIT_PATTERN, 2, false);

        return map;
    }

    private void processAdjustments(Map<Position, Long> adjustmentsByPosition, String hand, Pattern adjustmentsPattern, int valueGroup, boolean negate) {
        Matcher m = adjustmentsPattern.matcher(hand);
        while (m.find()) {
            Position position = getPositionOrThrow(m.group());
            long adjustment = penniesFromDollarsCentsString(m.group(valueGroup).trim());
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

    static Blinds blindsFromHandTitle(String hand) {
        Matcher m = BLIND_LEVEL_PATTERN.matcher(hand);
        if (!m.find()) {
            throw new IllegalArgumentException(String.format("Unable to determine blind levels in '%s'", hand));
        }
        long smallBlind = Long.parseLong(m.group(1));
        long bigBlind = Long.parseLong(m.group(2));

        return new Blinds(smallBlind, bigBlind);
    }
}