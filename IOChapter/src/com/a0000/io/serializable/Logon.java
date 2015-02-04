package com.a0000.io.serializable;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/2/2.
 * Demonstrates the "transient" keyword.
 */
public class Logon implements Serializable {
    private static final long serialVersionUID = -6090484425782957496L;
    private Date date = new Date();
    private String username;
    private transient String password;
    public Logon(String name, String pwd) {
        username = name;
        password = pwd;
    }

    @Override
    public String toString() {
        return "logon info: \n  username: " + username +
                "\n  date: " + date + "\n password: " + password;
    }

    public static void main(String[] args)throws Exception{
        Logon a = new Logon("Hulk", "myLittlePony");
        print("logon a = " + a);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Logon.out"));
        o.writeObject(a);
        o.close();
        TimeUnit.SECONDS.sleep(1); // Delay
        // Now get them back:
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Logon.out"));
        print("Recovering object at " +new Date());
        a = (Logon)in.readObject();
        print("logon a = " + a);
    }
}
