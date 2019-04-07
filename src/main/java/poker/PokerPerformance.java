package poker;

import poker.report.ProfitLossReporter;

import java.io.File;
import java.io.IOException;

public class PokerPerformance {

    /**
     * Run reports.
     *
     * @param args [0] = path to the Ignition Hand History directory, such as "~/Ignition Casino Poker/Hand History/111222333444"
     *             [1] = initial account deposit, in pennies
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        File dir = new File(args[0]);

        new ProfitLossReporter(dir, Long.parseLong(args[1])).writeReport();
    }

}
