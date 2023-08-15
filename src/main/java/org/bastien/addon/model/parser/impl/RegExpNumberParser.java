package org.bastien.addon.model.parser.impl;

import java.util.regex.Matcher;

public abstract class RegExpNumberParser extends RegExpParser<Long> {

    @Override
    public Long parse(String source) {
        final Matcher matcher = pattern.matcher(source);
        if (!matcher.find())
            throw new RuntimeException("RegExpNumberParser did not match this source -> " + source);
        return Long.parseLong(matcher.group(1));
    }

    @Override
    public abstract String getRegex();
}
