package com.a0000.enums;

import java.util.EnumMap;
import java.util.Map;

import static com.a0000.io.utils.Print.*;
import static com.a0000.enums.AlarmPoints.*;

interface Command {
    void action();
}
/**
 * Created by Administrator on 2015/2/7.
 */
public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> em = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
        em.put(KITCHEN, new Command() {
            @Override
            public void action() {
                print("Kitchen fire!");
            }
        });
        em.put(BATHROOM, new Command() {
            @Override
            public void action() {
                print("Bathroom alert!");
            }
        });
        for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
            printnb(e.getKey() + ":");
            e.getValue().action();
        }
        try {
            em.get(UTILITY).action();
        } catch (Exception e) {
            print(e);
        }
    }
}
