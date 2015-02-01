package a0000.com.io;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import static a0000.com.io.utils.Print.*;

/**
 * Created by 100 on 2015/1/22.
 */
public class LargeMappedFiles {
    static int length = 0x8000000;

    public static void main(String[] args) throws Exception {
        MappedByteBuffer out = new RandomAccessFile("test.dat", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++) {
            out.put((byte)'x');
        }
        print("Finished writing");
        for (int i=length/2; i<length/2 +6;i++) {
            printnb((char)out.get(i));
        }
    }
}
