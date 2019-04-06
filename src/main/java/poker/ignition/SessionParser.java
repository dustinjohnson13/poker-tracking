package poker.ignition;

import com.google.common.base.Strings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.google.common.collect.Iterables.getLast;
import static poker.ignition.SessionType.STT;
import static poker.util.CurrencyUtil.penniesFromDollarsCentsString;

public class SessionParser {

    private static final DateTimeFormatter SESSION_START_FORMATTER = DateTimeFormatter.ofPattern("'HH'yyyyMMdd-HHmmss");
    private static final String IGNITION_HAND_MARKER = "Ignition Hand #";
    private static final Pattern TOURNAMENT_COST_PATTERN = Pattern.compile("Holdem - \\$(\\d+)-\\$(.*?) ");
    private static final Pattern TOURNAMENT_PRIZE_CASH_PATTERN = Pattern.compile(".*? \\[ME] : Prize Cash \\[\\$(.*?)]");

    /**
     * Parses session filenames in the format of HH20190322-143439 - 3568450 - RING - $0.02-$0.05 - HOLDEM - NL - TBL No.11131131.txt.
     * File contents are parsed into hands, and summarized in the session.
     */
    public Session parse(String filename, String fileContents) {
        String[] parts = filename.split("\\s");

        String id = parts[0];
        LocalDateTime startTime = LocalDateTime.parse(parts[0], SESSION_START_FORMATTER);
        SessionType type = SessionType.valueOf(parts[4]);

        long cashIn = 0L;
        long cashOut = 0L;
        List<Hand> hands;
        if (type == STT) {
            hands = parseHands(fileContents, HandParser::blindsFromHandTitle);

            Matcher m = TOURNAMENT_COST_PATTERN.matcher(filename);
            if (!m.find()) {
                throw new IllegalArgumentException(String.format("Unable to determine tournament cost in '%s'!", filename));
            }

            long tournamentBuyIn = penniesFromDollarsCentsString(m.group(1));
            long tournamentFee = penniesFromDollarsCentsString(m.group(2));
            cashIn = tournamentBuyIn + tournamentFee;
            cashOut = -cashIn;

            m = TOURNAMENT_PRIZE_CASH_PATTERN.matcher(fileContents);
            if (m.find()) {
                cashOut = penniesFromDollarsCentsString(m.group(1));
            }
        } else {
            String[] blindsString = parts[6].split("-");
            long smallBlind = penniesFromDollarsCentsString(blindsString[0]);
            long bigBlind = penniesFromDollarsCentsString(blindsString[1]);
            Blinds blinds = new Blinds(smallBlind, bigBlind);

            hands = parseHands(fileContents, (hand) -> blinds);

            if (!hands.isEmpty()) {
                Hand firstHand = hands.iterator().next();
                Seat mySeat = firstHand.getMySeat();
                cashIn = mySeat.getStack();

                Hand lastHand = getLast(hands);
                Seat alsoMySeat = lastHand.getMySeat();
                cashOut = alsoMySeat.getStack() + alsoMySeat.getProfitLoss();
            }
        }

        String number = parts[type == STT ? 14 : 13].substring(3).replaceAll("\\.txt", "");
        long tableNumber = Long.parseLong(number, 10);

        return new Session(id, startTime, type, cashIn, cashOut, tableNumber, hands);
    }

    private List<Hand> parseHands(String fileContents, Function<String, Blinds> determineBlinds) {
        HandParser handParser = new HandParser();
        String[] splitIntoHands = fileContents.split(IGNITION_HAND_MARKER);

        return Arrays.stream(splitIntoHands)
                .filter(it -> !Strings.isNullOrEmpty(it))
                .map(it -> handParser.parse(IGNITION_HAND_MARKER + it, determineBlinds))
                .collect(Collectors.toList());
    }
}
