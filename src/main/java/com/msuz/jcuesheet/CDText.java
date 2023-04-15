/*
 * Copyright Michael Suzzi, 2022
 *  
 * Handles CD-Text metadata fields for Track and CueSheet objects.
 * Documentation for CD-Text is very sparse and conflicting, so
 * the fields used here may not be consistent with all sources.
 * 
 * TODO: Allow use of field name rather than int to set/get CDTEXT
 * TODO: Constructor taking binary CDTEXTFILE (.CDT) and populating fields
 */

package com.msuz.jcuesheet;

import java.util.EnumMap;
import java.util.Map;

public class CDText {
    // https://www.gnu.org/software/libcdio/cd-text-format.html
    // See 2.2 CDRWIN Cue Sheet with CD Text
    private static enum Field {
        TITLE,
        PERFORMER,
        SONGWRITER,
        ARRANGER,
        COMPOSER,
        GENRE,
        MESSAGE,
        ISRC,
        DISCID, // only allowed for CueSheet objects
        CATALOG, // UPC_EAN, only allowed for CueSheet objects
    }
    private Map<Field, String> fieldMap; // Map Field enum to user-specified values

    // Constructor
    CDText() { 
        this.fieldMap = new EnumMap<>(Field.class);
    }

    // Set value of one of the CDTEXT fields to specified string
    public void set(int field, String str) {
        try {
            fieldMap.put(Field.values()[field], str);
        } catch (Exception e) {
            throw e;
        }
    }

    // Return value of CDTEXT field
    public String get(int field) {
        String ret = "";

        try {
            ret = fieldMap.get(Field.values()[field]);
        } catch (Exception e) {
            throw e;
        }
        return ret;
    }

    // Number of possible fields
    public int numFields() { return Field.values().length; }

    // Return formatted string containing all keys and fields
    public String toString() {
        String out = "";
        Field[] keys = Field.values();

        for (int i = 0; i < Field.values().length; i++) {
            if (fieldMap.get(keys[i]) != null) {
                out += Field.values()[i]; out += " ";  // Print key name
                out += fieldMap.get(keys[i]); out += "\n"; // Print key value
            }
        }
        return out;
    }
}
