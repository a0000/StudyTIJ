package faw.com.io;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Administrator on 2015/1/14.
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException {
        String filename = "E:\\work\\tij_study\\IOChapter\\src\\faw\\com\\io\\MemoryInput.java";
        StringReader in = new StringReader(BufferedInputFile.read(filename));
        int c;
        while ((c = in.read())!=-1){
            System.out.print((char)c);
        }
    }
}
