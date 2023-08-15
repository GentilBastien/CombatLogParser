package org.bastien.addon.model.parser.impl;

import java.util.regex.Pattern;

public abstract class RegExpParser<T> implements Parser<T> {
    protected final Pattern pattern;

    protected RegExpParser() {
        this.pattern = Pattern.compile(getRegex());
    }

    @Override
    public abstract T parse(String source);

    public abstract String getRegex();
}