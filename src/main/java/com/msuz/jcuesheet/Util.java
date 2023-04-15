/*
 * Copyright Michael Suzzi, 2022
 *  
 * Utility functions
 */

package com.msuz.jcuesheet;

public class Util {
    // Lets us get the enum entry name from its value/string
    public static <T extends Enum<T>> T enumFromVal(Class<T> eenum, String value) {
        for (T e : eenum.getEnumConstants()) {
            if (e.toString().equals(value))
                return e;
        }
        return null;
    }
}
