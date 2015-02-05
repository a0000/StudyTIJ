package com.a0000.enums;
import static com.a0000.io.utils.Print.*;

// Define an enum type;
enum Signal { GREEN, YELLOW, RED }
/**
 * Created by Administrator on 2015/2/5.
 * Enumsin switch statements.
 */
public class TranfficLight {
    Signal color = Signal.RED;
    public void change(){
        switch (color) {
            // Note that you don't have to say Signal.RED
            // in the case statement;
            case RED:   color = Signal.GREEN;
                break;
            case GREEN: color = Signal.YELLOW;
                break;
            case YELLOW:color = Signal.RED;
                break;
        }
    }

    @Override
    public String toString() {
        return "The traffic light is " + color;
    }

    public static void main(String[] args) {
        TranfficLight t = new TranfficLight();
        for (int i=0; i<7; i++) {
            print(t);
            t.change();
        }
    }
}
