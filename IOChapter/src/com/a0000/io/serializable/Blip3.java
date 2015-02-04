package com.a0000.io.serializable;

import java.io.*;

/**
 * Created by Administrator on 2015/2/2.
 * Reconstructing an externalizable object
 */
public class Blip3 implements Externalizable {
    private static final long serialVersionUID = 5382250673866887461L;

    private int i;
    private String s;
    public Blip3(){
        print("Blip3 Constructor");
        // s, i not initialized
    }

    public Blip3(String x, int a) {
        print("Blip3(String x, int a)");
        s = x;
        i = a;
        // s & i initialized only in non-default constructor.
    }

    @Override
    public String toString() {
        return s + i;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        print("Blip3.writeExternal");
        // You must do this;
        out.writeObject(s);
        out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        print("Blip3.readExternal");
        // You must do this;
        s = (String) in.readObject();
        i = in.readInt();
    }

    public static void main(String[] args) throws Exception{
        print("Constructing objects:");
        Blip3 b3 = new Blip3("A String ", 47);
        print(b3);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blip3.out"));
        print("Saving object:");
        o.writeObject(b3);
        o.close();
        // Now get it back;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blip3.out"));
        print("Recovering b3:");
        b3 = (Blip3) in.readObject();
        print(b3);
    }
}
