package com.a0000.io.serializable;

import com.a0000.io.utils.Print;

import java.util.prefs.Preferences;

/**
 * Created by Administrator on 2015/2/3.
 */
public class PreferencesDemo {
    public static void main(String[] args) throws Exception{
        Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
        prefs.put("Location", "oz");
        prefs.put("Footwear", "Ruby Slippers");
        prefs.putInt("Companions", 4);
        prefs.putBoolean("Are there witches?", true);
        int usageCount = prefs.getInt("UsageCount", 0);
        usageCount++;
        prefs.putInt("UsageCount", usageCount);
        for (String key : prefs.keys()) {
            Print.print(key + ": " + prefs.get(key, null));
        }
        // You must always provide a default value;
        Print.print("How many companions does Dorothy have? " + prefs.getInt("Companions", 0));
    }
}
