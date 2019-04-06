package poker.ignition;

import poker.util.CurrencyUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static poker.util.CurrencyUtil.penniesFromDollarsCentsString;

public class SessionParser {

    private static final DateTimeFormatter SESSION_START_FORMATTER = DateTimeFormatter.ofPattern("'HH'yyyyMMdd-HHmmss");

    /**
     * Parses session filenames in the format of HH20190322-143439 - 3568450 - RING - $0.02-$0.05 - HOLDEM - NL - TBL No.11131131.txt
     */
    public Session parse(String filename) {
        String[] parts = filename.split("\\s");

        String id = parts[0];
        LocalDateTime startTime = LocalDateTime.parse(parts[0], SESSION_START_FORMATTER);
        SessionType type = SessionType.valueOf(parts[4]);

        String[] blinds = parts[6].split("-");
        long smallBlind = penniesFromDollarsCentsString(blinds[0]);
        long bigBlind = penniesFromDollarsCentsString(blinds[1]);

        long tableNumber = Long.parseLong(parts[13].substring(3, 11), 10);

        return new Session(id, startTime, type, smallBlind, bigBlind, tableNumber);
    }
}
