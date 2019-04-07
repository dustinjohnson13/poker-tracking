package poker.util;

import com.google.common.io.Files;
import poker.ignition.SessionParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DataSanitizer {

    private static final Pattern FILENAME_PATTERN = Pattern.compile("(HH\\d+-\\d+) - (\\d+) - (STT|RING|ZONE) - (.*)No\\.(\\d+)\\.txt");

    private final File historyFileDir;
    private long handId = generateRandomDigits(10);

    public DataSanitizer(File historyFileDir) {
        this.historyFileDir = historyFileDir;
    }

    public void sanitizeFiles() throws IOException {

        Random random = new Random();
        File sanitizedDir = new File(historyFileDir, "sanitized");
        sanitizedDir.mkdirs();

        for (File file : historyFileDir.listFiles()) {
            if (!file.isFile()) {
                continue;
            }
            Matcher m = FILENAME_PATTERN.matcher(file.getName());
            if (!m.matches()) {
                throw new IllegalArgumentException("Unsupported filename: " + file.getName());
            }

            String time = m.group(1);
            String id = m.group(2);
            String type = m.group(3);
            String specifics = m.group(4);
            String number = m.group(5);

            String newTime = LocalDateTime.parse(time, SessionParser.SESSION_START_FORMATTER)
                    .minusMinutes(random.nextInt(60))
                    .format(SessionParser.SESSION_START_FORMATTER);
            String newId = "" + generateRandomDigits(id.length());
            String newNumber = "" + generateRandomDigits(number.length());

            String newFilename = newTime + " - " + newId + " - " + type + " - " + specifics + "No." + newNumber + ".txt";

            List<String> fileLines = Files.readLines(file, Charset.defaultCharset());
            List<String> newLines = new ArrayList<>(fileLines.size());

            for (String line : fileLines) {
                newLines.add(line.replaceAll("#" + id, "#" + newId)
                        .replaceAll("#" + number, "#" + newNumber)
                        .replaceAll("Hand #\\d+", "Hand #" + handId++));
            }

            Files.write(newLines.stream().collect(Collectors.joining("\n")).getBytes(),
                    new File(sanitizedDir, newFilename));
        }
    }

    private static int generateRandomDigits(int length) {
        int m = (int) Math.pow(10, length - 1);
        return m + new Random().nextInt(9 * m);
    }

    public static void main(String[] args) throws IOException {
        new DataSanitizer(new File(args[0])).sanitizeFiles();
    }

}
