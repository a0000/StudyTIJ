package com.a0000.io.serializable;

import java.io.*;

/**
 * Created by Administrator on 2015/2/2.
 * Controlling serialization by adding your own
 * writeObject() and readObject() methods.
 */
public class SerialCtl implements Serializable {
    private static final long serialVersionUID = -3972576865272732319L;

    private String a;
    private transient String b;
    public SerialCtl(String aa, String bb) {
        a = "Not Transient: " + aa;
        b = "Transient: " + bb;
    }

    @Override
    public String toString() {
        return a + "\n" + b;
    }
    private void writeObject(ObjectOutputStream stream) throws Exception{
        stream.defaultWriteObject();
        stream.writeObject(b);
    }

    private void readObject(ObjectInputStream stream) throws Exception {
        stream.defaultReadObject();
        b = (String) stream.readObject();
    }

    public static void main(String[] args) throws Exception {
        SerialCtl sc = new SerialCtl("Test1", "Test2");
        System.out.println("Before:\n" +sc);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(buf);
        o.writeObject(sc);
        // Now get it back;
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
        SerialCtl sc2 = (SerialCtl) in.readObject();
        System.out.println("After:\n" + sc2);
    }
}
