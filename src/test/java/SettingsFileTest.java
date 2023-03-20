import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import settingsFile.SettingsFile;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SettingsFileTest {
    private static final SettingsFile settingsFile = SettingsFile.getInstance();
    protected int port;
    protected String host;

    @BeforeEach
    void setUp() {
        final String path = "./SettingsFiles/settings.txt";
        try (FileReader fileReader = new FileReader(path)) {
            Properties props = new Properties();
            props.load(fileReader);

            port = Integer.parseInt(props.getProperty("port"));
            host = props.getProperty("host");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getInstance() {
        assertEquals(SettingsFile.getInstance(), settingsFile);
    }

    @Test
    void getPort() {
        assertEquals(settingsFile.getPort(), port);
    }

    @Test
    void getHost() {
        assertEquals(settingsFile.getHost(), host);
    }
}
