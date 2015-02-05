package com.a0000.enums;

import sun.security.krb5.internal.crypto.Des;

import static com.a0000.enums.Food.*;
/**
 * Created by 100 on 2015/2/5.
 */
public class TypeOfFood {
    public static void main(String[] args) {
        Food food = Appetizer.SALAD;
        food = MainCourse.LASAGNE;
        food = Dessert.GELATO;
        food = Coffee.CAPPUCCINO;
    }
}
