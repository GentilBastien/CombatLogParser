package org.bastien.addon.parser.impl;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class TimestampParser implements Parser<LocalTime> {

    @Override
    public LocalTime parse(String source) {
        try {
            return LocalTime.parse(source.substring(1, source.length() - 1));
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid timestamp " + source, e);
        }
    }
}
