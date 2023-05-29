package org.bastien.addon.parser.impl;

import org.bastien.addon.parser.impl.TimestampParser;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TimestampParserTest {

    @Test
    public void parseFullTime() {
        TimestampParser parser = new TimestampParser();
        LocalTime actual = parser.parse("[18:33:20.480]");
        LocalTime expected = LocalTime.of(18, 33, 20).plusNanos(480_000_000);
        assertEquals(expected, actual);
        assertDoesNotThrow(() -> new RuntimeException());
    }

    @Test
    public void parseTimeWithEmptyNanos() {
        TimestampParser parser = new TimestampParser();
        LocalTime actual = parser.parse("[18:48:16.000]");
        LocalTime expected = LocalTime.of(18, 48, 16);
        assertEquals(expected, actual);
        assertDoesNotThrow(() -> new RuntimeException());
    }

    @Test
    public void parseWrongTime() {
        TimestampParser parser = new TimestampParser();
        assertThrows(RuntimeException.class, () -> parser.parse("[38:58:16.000]"));
    }
}
