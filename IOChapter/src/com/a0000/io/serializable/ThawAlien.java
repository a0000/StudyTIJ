package com.a0000.io.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by 100 on 2015/2/1.
 * Try to recover a serialized file without the
 * class of object that's stored in that file.
 */
public class ThawAlien {
    public static void main(String[] args) throws Exception{
        String filePath = "x.file";
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(filePath)));
        Object mystery = in.readObject();
        System.out.println(mystery.getClass());
    }
}
