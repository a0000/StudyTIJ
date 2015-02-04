package com.a0000.io;

import com.a0000.io.utils.Print;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * Created by 100 on 2015/1/21.
 */
public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String, Charset> charSets = Charset.availableCharsets();
        Iterator<String> it = charSets.keySet().iterator();
        while (it.hasNext()) {
            String csName = it.next();
            Print.printnb(csName);
            Iterator<String> aliases = charSets.get(csName).aliases().iterator();
            if(aliases.hasNext()) {
                Print.printnb(": ");
            }
            while (aliases.hasNext()) {
                Print.printnb(aliases.next());
                if(aliases.hasNext()) {
                    Print.printnb(", ");
                }
            }

            Print.print();
        }
    }
}
