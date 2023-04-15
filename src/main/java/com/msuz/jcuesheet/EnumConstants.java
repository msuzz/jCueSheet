/*
 * Copyright Michael Suzzi, 2022
 *  
 * This class stores enum types containing important string constants
 */

package com.msuz.jcuesheet;

public final class EnumConstants {
    static enum DataType {
        AUDIO("AUDIO"),             // Audio/Music (2352)
        CDG("CDG"),                 // Karaoke CD+G (2448)
        MODE1_2048("MODE1/2048"),   // CDROM Mode1 Data (cooked)
        MODE1_2352("MODE1/2352"),   // CDROM Mode1 Data (raw)
        MODE2_2048("MODE2/2048"),   // *Not in original Cue spec
        MODE2_2324("MODE2/2324"),   // *Not in original Cue spec
        MODE2_2336("MODE2/2336"),   // CDROM-XA Mode2 Data (2336)
        MODE2_2352("MODE2/2352"),   // CDROM-XA Mode2 Data (Playstation/Video CD)
        CDI_2336("CDI/2336"),       // Philips CD-i Mode2 Data (2336)
        CDI_2352("CDI/2352");       // Philips CD-i Mode2 Data (2352)

        private final String enum_name;
        DataType(String enum_name) { this.enum_name = enum_name; }
        public String toString() { return enum_name; };
        public static String getEnumFromVal(String value) {
            for(DataType e : DataType.values()) {
                if (e.name().equals(value))
                    return e.name();
            }
            return null;
        }
    }

    static enum DataFormat {
        WAVE("WAVE"),           // For any lossless format
        MP3("MP3"),
        AIFF("AIFF"),
        BINARY("BINARY"),       // Little-endian 
        MOTOROLA("MOTOROLA");   // Big-endian

        private final String enum_name;
        DataFormat(String enum_name) { this.enum_name = enum_name; }
        public String toString() { return enum_name; };
        public static String getEnumFromVal(String value) {
            for(DataFormat e : DataFormat.values()) {
                if (e.name().equals(value))
                    return e.name();
            }
            return null;
        }
    }

    static enum TrackFlags {
        DCP("DCP"),     // Digital copy permitted
        _4CH("4CH"),    // Four channel audio
        PRE("PRE"),     // Pre-emphasis enabled (audio tracks only)
        SCMS("SCMS");   // Serial copy management system

        private final String enum_name;
        TrackFlags(String enum_name) { this.enum_name = enum_name; }
        public String toString() { return enum_name; };
        public static String getEnumFromVal(String value) {
            for(TrackFlags e : TrackFlags.values()) {
                if (e.name().equals(value))
                    return e.name();
            }
            return null;
        }
    }
}
