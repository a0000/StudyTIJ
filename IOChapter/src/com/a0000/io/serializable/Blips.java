package com.a0000.io.serializable;

import com.a0000.io.utils.Print;

import java.io.*;

class Blip1 implements Externalizable {

    private static final long serialVersionUID = -6116098291665107097L;

    public Blip1() {
        Print.print("Blip1 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        Print.print("Blip1.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Print.print("Blip1.readExternal");
    }
}
class Blip2 implements Externalizable {

    private static final long serialVersionUID = -8240935849041346931L;

    Blip2() {
        Print.print("Blip2 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        Print.print("Blip2.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Print.print("Blip2.readExternal");
    }
}
/**
 * Created by 100 on 2015/2/1.
 * Simple use of Externalizable & a pitfall.
 */
public class Blips {
    public static void main(String[] args) throws Exception{
        Print.print("Constructing objects:");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();
        String fileName = "Blips.out";
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(fileName));
        Print.print("Saving objects:");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();
        // Now get them back;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        Print.print("Recovering b1:");
        b1 = (Blip1) in.readObject();
        // OOPS! Throws an exception:
//        print("Recovering b2:");
//        b2 = (Blip2) in.readObject();
    }
}
