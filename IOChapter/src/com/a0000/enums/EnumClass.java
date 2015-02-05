package com.a0000.enums;

import static com.a0000.io.utils.Print.*;

/**
 * Created by Administrator on 2015/2/4.
 * Capabilities of the Enum class
 */

public class EnumClass {
    public static void main(String[] args) {
        for (Shrubbery s : Shrubbery.values()) {
            print(s + " ordinal: " + s.ordinal());
            printnb(s.compareTo(Shrubbery.CRAWLING) + " ");
            printnb(s.equals(Shrubbery.CRAWLING) + " ");
            print(s == Shrubbery.CRAWLING);
            print(s.getDeclaringClass());
            print(s.name());
            print("---------------------");
        }
        // Produce an enum value from a string name;
        for (String s : "HANGING CRAWLING GROUND".split(" ")) {
            Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
            print(shrub);
        }
    }
}

enum Shrubbery {
    GROUND, CRAWLING, HANGING
}