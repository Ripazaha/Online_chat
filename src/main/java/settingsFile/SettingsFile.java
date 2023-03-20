package settingsFile;

import log.RecordingLog;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SettingsFile {
    private static SettingsFile record;
    private static final RecordingLog recordingLog = RecordingLog.getInstance();
    private static final String storageLog = "./SettingsFiles/settings.txt";
    private int port;
    private String host;

    private SettingsFile() {
        try (FileReader fileReader = new FileReader(storageLog)) {
            Properties props = new Properties();
            props.load(fileReader);

            port = Integer.parseInt(props.getProperty("port"));
            host = props.getProperty("host");
        } catch (IOException e) {
            recordingLog.log("Ошибка в конструкторе у класса " + SettingsFile.class.getName());
            e.printStackTrace();
        }
    }

    public static SettingsFile getInstance() {
        if (record == null) {
            record = new SettingsFile();
        }
        return record;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }
}
