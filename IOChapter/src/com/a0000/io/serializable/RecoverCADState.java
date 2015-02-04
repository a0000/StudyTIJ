package com.a0000.io.serializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by Administrator on 2015/2/3.
 * Restoring the state of the pretend CAD system.
 * {RunFirst: StoreCADState}
 */
public class RecoverCADState {
    public static void main(String[] args)throws Exception{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("CADState.out"));
        // Read in the same order they wer written;
        List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>) in.readObject();
        Line.deserializeStaticState(in);
        List<Shape> shapes = (List<Shape>) in.readObject();
        System.out.println(shapes);
    }
}
