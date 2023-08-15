package org.bastien.addon.model.parser.impl;

public class AbsorbedParser extends RegExpNumberParser {

    @Override
    public String getRegex() {
        return "\\((\\d+) absorbed \\{836045448945511}\\)";
    }
}
