package org.bastien.addon.model.parser.impl;

import org.bastien.addon.model.constant.Event;

import java.util.regex.Matcher;

public class EventParser extends RegExpParser<Event> {

    @Override
    public Event parse(String source) {
        if (source.equals("[]"))
            return null;
        final Matcher matcher = pattern.matcher(source);
        if (matcher.find() && matcher.find()) {
            long eventId = Long.parseLong(matcher.group(1));
            return Event.from(eventId);
        }
        throw new RuntimeException("Event has not been parsed -> " + source);
    }

    @Override
    public String getRegex() {
        return "\\{(\\d+)}";
    }
}
