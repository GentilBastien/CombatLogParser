package org.bastien.addon.model.parser.impl;

public class EncochedNumberParser extends RegExpNumberParser {

    @Override
    public String getRegex() {
        return "<(-?\\d+)>";
    }
}
