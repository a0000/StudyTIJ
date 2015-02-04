package com.a0000.io;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Administrator on 2015/1/14.
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException {
        String filename = ".\\IOChapter\\src\\a0000\\com\\io\\MemoryInput.java";
        StringReader in = new StringReader(BufferedInputFile.read(filename));
        int c;
        while ((c = in.read())!=-1){
            System.out.print((char)c);
        }
    }
}
