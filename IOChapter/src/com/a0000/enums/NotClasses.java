package com.a0000.enums;

import static com.a0000.io.utils.Print.*;

enum LikeClasses {
    WINKEN {
        @Override
        void behavior() {
            print("Behavior1");
        }
    },
    BLINKEN {
        @Override
        void behavior() {
            print("Behavior2");
        }
    },
    NDD {
        @Override
        void behavior() {
            print("Behavior3");
        }
    };
    abstract void behavior();
}

/**
 * Created by Administrator on 2015/2/7.
 */
public class NotClasses {
//    void f1(LikeClasses.WINKEN instance) {}
}
