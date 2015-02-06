package com.a0000.enums;

import java.util.EnumSet;
import static com.a0000.io.utils.Print.*;
import static com.a0000.enums.AlarmPoints.*;
/**
 * Created by Administrator on 2015/2/6.
 * operations on EnumSets
 */
public class EnumSets {
    public static void main(String[] args) {
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);// Empty set
        points.add(BATHROOM);
        print("-- 1 --" + points);
        points.addAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        print("-- 2 --" + points);
        points = EnumSet.allOf(AlarmPoints.class);
        points.removeAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        print("-- 3 --" + points);
        points.removeAll(EnumSet.range(OFFICE1, OFFICE4));
        print("-- 4 --" + points);
        points = EnumSet.complementOf(points);
        print("-- 5 --" + points);

    }
}
