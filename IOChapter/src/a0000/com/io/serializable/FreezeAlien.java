package a0000.com.io.serializable;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by 100 on 2015/2/1.
 * Create a serialized output file.
 */
public class FreezeAlien {
    public static void main(String[] args) throws Exception{
        String fileName = "x.file";
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
        Alien quellek = new Alien();
        out.writeObject(quellek);
    }
}
