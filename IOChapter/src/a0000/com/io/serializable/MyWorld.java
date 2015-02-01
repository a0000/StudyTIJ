package a0000.com.io.serializable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static a0000.com.io.utils.Print.*;

/**
 * Created by Administrator on 2015/2/2.
 */
public class MyWorld {

    public static void main(String[] args)throws Exception{
        House house = new House();
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Bosco the dog", house));
        animals.add(new Animal("Ralph the hamster", house));
        animals.add(new Animal("Molly the cat", house));
        print("animals: " + animals);
        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals); // Write a 2nd set
        // Write to a different stream;
        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);
        // Now get them back;
        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));
        List<Animal>
                animals1 = (List<Animal>) in1.readObject(),
                animals2 = (List<Animal>) in1.readObject(),
                animals3 = (List<Animal>) in2.readObject();
        print("naimals1: " + animals1);
        print("naimals2: " + animals2);
        print("naimals3: " + animals3);
    }
}


class House implements Serializable{}

class Animal implements Serializable {
    private String name;
    private House preferredHouse;
    Animal(String nm, House h) {
        name = nm;
        preferredHouse = h;
    }

    @Override
    public String toString() {
        return name + "[" + super.toString() + "]," +preferredHouse + "\n";
    }
}