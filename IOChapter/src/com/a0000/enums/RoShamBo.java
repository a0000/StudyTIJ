package com.a0000.enums;

/**
 * Created by 100 on 2015/2/8.
 * Common tools for RoShamBo examples.
 */
public class RoShamBo {
    public static <T extends Competitor<T>> void match(T a, T b) {
        System.out.println(a + " vs. " + b + ": " + a.compete(b));
    }

    public static <T extends Enum<T> & Competitor<T>> void play(Class<T> rsbClass, int size) {
        for (int i=0; i<size; i++) {
            match(Enums.random(rsbClass), Enums.random(rsbClass));
        }
    }
}
