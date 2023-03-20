package log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecordingLog {
    private static RecordingLog record;
    private static final String storageLog = "./src/main/java/log/log.txt";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private RecordingLog() {
    }

    public static RecordingLog getInstance() {
        if (record == null) {
            record = new RecordingLog();
        }
        return record;
    }

    public boolean log(String message) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(storageLog, true));
            writer.write(LocalDateTime.now().format(formatter) + " " + message + "\n");
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
