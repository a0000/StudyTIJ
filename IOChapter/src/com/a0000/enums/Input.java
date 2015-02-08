package com.a0000.enums;

import java.io.RandomAccessFile;
import java.util.Random;

/**
 * Created by Administrator on 2015/2/8.
 */
public enum Input {

    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABORT_TRANSACTION {
        @Override
        public int amount() { // Disallow
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP {
        @Override
        public int amount() { // Disallow
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };

    int value;

    Input(int value) {
        this.value = value;
    }
    Input() {}

    int amount() {return value;} // In cents

    static Random rand = new Random(47);
    public static Input randomSelection() {
        // Don't include STOP;
        return values()[rand.nextInt(values().length - 1)];
    }
}
