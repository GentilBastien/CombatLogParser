package org.bastien.addon.model.parser.impl;

public class AbsorbedParser extends RegExpNumberParser {

    @Override
    public Long parse(String source) {
        try {
            return super.parse(source);
        } catch (RuntimeException e) {
            //Warning : Absorbed parser did not find the absorbed damage amount in this source
            return 0L;
        }
    }

    @Override
    public String getRegex() {
        return "\\((\\d+) absorbed \\{836045448945511}\\)";
    }
}
