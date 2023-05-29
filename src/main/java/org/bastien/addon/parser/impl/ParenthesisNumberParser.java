package org.bastien.addon.parser.impl;

public class ParenthesisNumberParser extends RegExpNumberParser {

    @Override
    public String getRegex() {
        return "\\((\\d+)\\)";
    }
}
