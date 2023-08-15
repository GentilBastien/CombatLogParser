package org.bastien.addon.model.parser.impl;

public class ParenthesisNumberParser extends RegExpNumberParser {

    @Override
    public String getRegex() {
        return "\\((\\d+)\\)";
    }
}
