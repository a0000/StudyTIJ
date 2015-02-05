package com.a0000.enums;

import java.util.Set;

enum Search { HITHER, YON }
/**
 * Created by Administrator on 2015/2/5.
 */
public class UpcastEnum {
    public static void main(String[] args) {
        Search[] vals = Search.values();
        Enum e = Search.HITHER; // Upcast
        // e.values(); // Novalues() in enum
        for (Enum en : e.getClass().getEnumConstants()) {
            System.out.println(en);
        }

    }
}
