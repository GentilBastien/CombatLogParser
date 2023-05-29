package org.bastien.addon.parser.impl;

public class EncochedNumberParser extends RegExpNumberParser {

    @Override
    public String getRegex() {
        return "<(-?\\d+)>";
    }
}
