package poker.util;

import static java.lang.Math.round;

public final class CurrencyUtil {

    private CurrencyUtil() {
    }

    /**
     * Returns the number of pennies from a string such as $0.35 or 1.35 (dollar sign ignored).
     */
    public static long penniesFromDollarsCentsString(String string) {
        if (string.startsWith("$")) {
            string = string.substring(1);
        }

        return round(Double.parseDouble(string) * 100);
    }
}
