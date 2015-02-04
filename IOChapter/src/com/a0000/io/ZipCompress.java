package com.a0000.io;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;
import static com.a0000.io.utils.Print.*;

/**
 * Created by 100 on 2015/2/1.
 * Uses Zip compression to compress any
 * number of files given on the command line.
 * {Args: ZipCompress.java}
 */
public class ZipCompress {
    public static void main(String[] args) throws Exception{
        String fileName = "test.zip";
        FileOutputStream f = new FileOutputStream(fileName);
        CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(csum);
        BufferedOutputStream out = new BufferedOutputStream(zos);
        zos.setComment("A test of Java Zipping");
        // No corresponding getComment(), though.
        for (String arg : args) {
            print("Writing file " + arg);
            BufferedReader in = new BufferedReader(new FileReader(arg));
            zos.putNextEntry(new ZipEntry(arg));
            int c;
            while ((c=in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.flush();
        }
        out.close();
        // Checksum valid only after the file has been closed!
        print("Checksum:" + csum.getChecksum().getValue());
        // Now extract the files:
        print("Reading file");
        FileInputStream fi = new FileInputStream(fileName);
        CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
        ZipInputStream in2 = new ZipInputStream(csumi);
        BufferedInputStream bis = new BufferedInputStream(in2);
        ZipEntry ze;
        while ((ze=in2.getNextEntry()) != null) {
            print("Reading file" + ze);
            int x;
            while ((x=bis.read()) != -1) {
                System.out.write(x);
            }
        }
        if(args.length == 1) {
            print("Checksum: " + csumi.getChecksum().getValue());
            bis.close();
            // Alternative way to open and read Zip files;
            ZipFile zf = new ZipFile(fileName);
            Enumeration<? extends ZipEntry> e = zf.entries();
            while (e.hasMoreElements()) {
                ZipEntry ze2 = e.nextElement();
                print("File: " + ze2);
                // ... and extract the data as before
            }
            /* if(args.length == 1) */

        }
    }
}
