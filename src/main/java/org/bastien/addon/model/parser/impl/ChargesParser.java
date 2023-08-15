package org.bastien.addon.model.parser.impl;

public class ChargesParser extends RegExpNumberParser {

    @Override
    public String getRegex() {
        return "\\((\\d+) charges \\{836045448953667}\\)";
    }
}
