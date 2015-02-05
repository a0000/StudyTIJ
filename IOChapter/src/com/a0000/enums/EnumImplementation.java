package com.a0000.enums;

import net.mindview.util.Generator;

import java.util.Random;

enum CartoonCharacter implements Generator<CartoonCharacter> {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
    private Random rand = new Random(47);
    public CartoonCharacter next() {
        return values()[rand.nextInt(values().length)];
    }
}
/**
 * Created by 100 on 2015/2/5.
 * An enum can implement an interface
 */
public class EnumImplementation {
    public static <T> void printNext(Generator<T> rg) {
        System.out.print(rg.next() + ", ");
    }

    public static void main(String[] args) {
        // Choose any instance;
        CartoonCharacter cc = CartoonCharacter.BOB;
        for (int i=0; i<10; i++) {
            printNext(cc);
        }
    }
}
