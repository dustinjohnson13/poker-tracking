package poker.util;

import static java.lang.Math.round;

public final class CurrencyUtil {

    private CurrencyUtil() {
    }

    /**
     * Returns the number of pennies from a string such as $0.35, 1.35, or 1,500 (dollar sign and comma ignored).
     */
    public static long penniesFromDollarsCentsString(String string) {
        if (string.startsWith("$")) {
            string = string.substring(1);
        }

        String noCommas = string.replaceAll(",", "");
        return round(Double.parseDouble(noCommas) * 100);
    }
}
