package com.a0000.enums;

/**
 * Created by Administrator on 2015/2/6.
 */
public class Meal {
    public static void main(String[] args) {
        for (int i=0; i<5; i++) {
            for (Course course : Course.values()) {
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("-----------------");
        }
    }
}
