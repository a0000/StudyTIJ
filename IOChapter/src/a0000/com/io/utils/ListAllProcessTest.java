package a0000.com.io.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2015/1/19.
 */
public class ListAllProcessTest {
    public static void main(String[] args) {
        BufferedReader br = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("tasklist");
            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line = null;
            System.out.println("列出所有正在运行的进程信息：");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(process!=null){
                process.destroy();
            }
        }
    }
}
