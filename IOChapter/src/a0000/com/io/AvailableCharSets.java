package a0000.com.io;

import a0000.com.io.utils.Print;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;
import static a0000.com.io.utils.Print.*;

/**
 * Created by 100 on 2015/1/21.
 */
public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String, Charset> charSets = Charset.availableCharsets();
        Iterator<String> it = charSets.keySet().iterator();
        while (it.hasNext()) {
            String csName = it.next();
            printnb(csName);
            Iterator<String> aliases = charSets.get(csName).aliases().iterator();
            if(aliases.hasNext()) {
                printnb(": ");
            }
            while (aliases.hasNext()) {
                printnb(aliases.next());
                if(aliases.hasNext()) {
                    printnb(", ");
                }
            }

            print();
        }
    }
}
