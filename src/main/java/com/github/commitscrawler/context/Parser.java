package com.github.commitscrawler.context;

public interface Parser<T, V> {
    T parse(V value);
}
