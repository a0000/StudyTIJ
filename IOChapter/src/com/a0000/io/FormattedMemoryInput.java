package com.a0000.io;

import java.io.*;

/**
 * Created by Administrator on 2015/1/15.
 */
public class FormattedMemoryInput {
    public static void main(String[] args) {
        File file = new File(".\\IOChapter\\src\\a0000\\com\\io\\FormattedMemoryInput.java");

        System.out.println(file.exists()+":"+file.getAbsoluteFile());
        String filename = file.getAbsolutePath();
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read(filename).getBytes()));
            while (true) {
                System.out.print((char) in.readByte());
            }
        } catch (EOFException ee) {
            System.out.println("End of stream");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
