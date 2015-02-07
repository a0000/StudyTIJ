package com.a0000.enums;

import java.util.EnumSet;

import static com.a0000.io.utils.Print.*;

/**
 * Created by Administrator on 2015/2/7.
 */
public class CarWash {
    public enum Cycle {
        UNDEBODY {
            @Override
            void action() {
                print("Spraying the underbody");
            }
        },
        WHEELWASH {
            @Override
            void action() {
                print("Washing the wheels");
            }
        },
        PREWASH {
            @Override
            void action() {
                print("Loosening the dirt");
            }
        },
        BASIC {
            @Override
            void action() {
                print("The basic wash");
            }
        },
        HOTWAX {
            @Override
            void action() {
                print("Applying hot wax");
            }
        },
        RINSE {
            @Override
            void action() {
                print("Rinsing");
            }
        },
        BLOWDRY {
            @Override
            void action() {
                print("Blowing dry");
            }
        };
        abstract void action();
    }

    EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE);

    public void add(Cycle cycle) {
        cycles.add(cycle);
    }

    public void washCar() {
        for (Cycle c : cycles) {
            c.action();
        }
    }

    @Override
    public String toString() {
        return cycles.toString();
    }

    public static void main(String[] args) {
        CarWash wash = new CarWash();
        print(wash);
        wash.washCar();
        // Order of addition is unimportant;
        wash.add(Cycle.BLOWDRY);
        wash.add(Cycle.BLOWDRY); // Duplicates ignored
        wash.add(Cycle.RINSE);
        wash.add(Cycle.HOTWAX);
        print(wash);
        wash.washCar();
    }
}
