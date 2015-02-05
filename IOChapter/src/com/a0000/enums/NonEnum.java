package com.a0000.enums;

/**
 * Created by Administrator on 2015/2/5.
 */
public class NonEnum {
    public static void main(String[] args) {
        Class<Integer> intClass = Integer.class;
        System.out.println(intClass.getEnumConstants()+ "---");
    }
}
