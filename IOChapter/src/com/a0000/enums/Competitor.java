package com.a0000.enums;

/**
 * Created by 100 on 2015/2/8.
 * Switching one enum on another.
 */
public interface Competitor<T extends Competitor<T>> {
    Outcome compete(T competitor);
}
