package com.a0000.concurrent;

/**
 * Created by Administrator on 2015/2/20.
 * {Ecec: javap -c Atomicity}
 */
public class Atomicity {
    int i;
    void f1(){i++;}
    void f2(){ i += 3; }

}
