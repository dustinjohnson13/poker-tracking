package poker.report;

import com.google.common.io.Files;
import poker.ignition.Session;
import poker.ignition.SessionParser;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class ProfitLossReporter {

    private final File historyFileDir;
    private final long initialDeposit;

    public ProfitLossReporter(File historyFileDir, long initialDeposit) {
        this.historyFileDir = historyFileDir;
        this.initialDeposit = initialDeposit;
    }

    String getReport() throws IOException {

        SortedSet<Session> sessions = new TreeSet<>(Comparator.comparing(Session::getStartTime));

        for (File file : historyFileDir.listFiles()) {
            if (!file.isFile()) {
                continue;
            }
            Session session = new SessionParser().parse(file.getName(), new String(Files.toByteArray(file)));
            sessions.add(session);
        }

        StringBuilder sb = new StringBuilder();
        long bankroll = initialDeposit;
        for (Session session : sessions) {
            bankroll += session.getProfitLoss();
            sb.append(bankroll).append(" ")
                    .append(session.getStartTime())
                    .append("\n");
        }

        return sb.toString();
    }

    public void writeReport() throws IOException {
        System.out.println(getReport());
    }
}
