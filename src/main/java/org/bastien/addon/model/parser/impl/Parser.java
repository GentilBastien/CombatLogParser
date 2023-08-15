package org.bastien.addon.model.parser.impl;

public interface Parser<T> {
    T parse(String source);
}
