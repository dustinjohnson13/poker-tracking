package poker.ignition;

import com.google.common.base.Strings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Iterables.getLast;
import static poker.util.CurrencyUtil.penniesFromDollarsCentsString;

public class SessionParser {

    private static final DateTimeFormatter SESSION_START_FORMATTER = DateTimeFormatter.ofPattern("'HH'yyyyMMdd-HHmmss");
    private static final String IGNITION_HAND_MARKER = "Ignition Hand #";

    /**
     * Parses session filenames in the format of HH20190322-143439 - 3568450 - RING - $0.02-$0.05 - HOLDEM - NL - TBL No.11131131.txt.
     * File contents are parsed into hands, and summarized in the session.
     */
    public Session parse(String filename, String fileContents) {
        String[] parts = filename.split("\\s");

        String id = parts[0];
        LocalDateTime startTime = LocalDateTime.parse(parts[0], SESSION_START_FORMATTER);
        SessionType type = SessionType.valueOf(parts[4]);

        String[] blinds = parts[6].split("-");
        long smallBlind = penniesFromDollarsCentsString(blinds[0]);
        long bigBlind = penniesFromDollarsCentsString(blinds[1]);

        String number = parts[13].substring(3).replaceAll("\\.txt", "");
        long tableNumber = Long.parseLong(number, 10);
        long stack = 0L;
        long stackChange = 0L;

        List<Hand> hands = parseHands(fileContents, smallBlind, bigBlind);
        if (!hands.isEmpty()) {
            Hand firstHand = hands.iterator().next();
            Seat mySeat = firstHand.getMySeat();
            stack = mySeat.getStack();

            Hand lastHand = getLast(hands);
            Seat alsoMySeat = lastHand.getMySeat();
            stackChange = alsoMySeat.getStack() - stack + alsoMySeat.getStackChange();
        }

        return new Session(id, startTime, type, tableNumber, stack, stackChange, hands);
    }

    private List<Hand> parseHands(String fileContents, long smallBlind, long bigBlind) {
        HandParser handParser = new HandParser();
        String[] splitIntoHands = fileContents.split(IGNITION_HAND_MARKER);

        return Arrays.stream(splitIntoHands)
                .filter(it -> !Strings.isNullOrEmpty(it))
                .map(it -> handParser.parse(IGNITION_HAND_MARKER + it, smallBlind, bigBlind))
                .collect(Collectors.toList());
    }
}
