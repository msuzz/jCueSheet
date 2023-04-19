/*
 * Copyright Michael Suzzi, 2022
 *  
 * Utility functions
 */

package com.msuz.jcuesheet;

public class Util {
    // Takes a string as input and returns the enum with matching string value
    public static <T extends Enum<T>> T enumFromVal(Class<T> eenum, String value) {
        for (T e : eenum.getEnumConstants()) {
            if (e.name().equals(value))
                return e;
        }
        return null;
    }
}
