package org.bastien.addon.model.parser.impl;

public class ThreatNumberParser extends RegExpNumberParser {

    @Override
    public Long parse(String source) {
        try {
            return super.parse(source);
        } catch (RuntimeException e) {
            return 0L;
        }
    }

    @Override
    public String getRegex() {
        return "<(-?\\d+)>";
    }
}
