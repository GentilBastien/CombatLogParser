package org.bastien.addon.parser.impl;

import org.bastien.addon.model.constant.Event;
import org.bastien.addon.parser.impl.EventParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventParserTest {

    @Test
    public void parseEvent() {
        EventParser parser = new EventParser();
        Event actual = parser.parse("[Event {836045448945472}: ExitCombat {836045448945490}]");
        Event expected = Event.EXIT_COMBAT;
        assertEquals(expected, actual);
    }

    @Test
    public void parseNotExistingEvent() {
        EventParser parser = new EventParser();
        assertThrows(RuntimeException.class, () -> parser.parse("[Event {836045448945472}: Siuu {987654321}]"));
    }
}
