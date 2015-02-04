package com.a0000.io;

import com.a0000.io.utils.Print;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by Administrator on 2015/1/22.
 */
public class UsingBuffers {
    private static void symmetricScramble(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args) {
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);
        Print.print(cb.rewind());
        symmetricScramble(cb);
        Print.print(cb.rewind());
        symmetricScramble(cb);
        Print.print(cb.rewind());
    }
}
