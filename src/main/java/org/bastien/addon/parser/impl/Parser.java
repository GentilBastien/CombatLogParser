package org.bastien.addon.parser.impl;

public interface Parser<T> {
    T parse(String source);
}
