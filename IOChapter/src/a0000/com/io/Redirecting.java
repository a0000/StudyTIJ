package a0000.com.io;

import java.io.*;

/**
 * Created by Administrator on 2015/1/18.
 */
public class Redirecting {
    public static void main(String[] args) throws IOException {
        PrintStream console = System.out;
        String filePath = ".\\IOChapter\\src\\a0000\\com\\io\\Redirecting.java";
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.out")));
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        out.close();
        System.setOut(console);
    }
}
