package log;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecordingLogTest {
    private static final RecordingLog log = RecordingLog.getInstance();
    private static final String MESSAGE = "Test message LoggerTest";

    @Test
    void getInstance() {
        assertEquals(RecordingLog.getInstance(), log);
    }

    @Test
    void log() {
        assertTrue(log.log(MESSAGE));
    }
}
