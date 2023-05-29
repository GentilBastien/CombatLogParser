package org.bastien.addon.parser.impl;

public class ChargesParser extends RegExpNumberParser {

    @Override
    public String getRegex() {
        return "\\((\\d+) charges \\{836045448953667}\\)";
    }
}
