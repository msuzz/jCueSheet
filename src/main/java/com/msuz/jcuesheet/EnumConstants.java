/*
 * Copyright Michael Suzzi, 2022
 *  
 * This class stores enum types containing important string constants.
 * 
 * The toString() method returns the string name of each enum and is used for
 * printing the cue sheet. This is needed (instead of just using name()) because
 * Java does not support slashes in enum names.
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

        private final String nameString;
        DataType(String nameString) { this.nameString = nameString; }
        public String toString() { return nameString; };
    }

    static enum DataFormat {
        WAVE("WAVE"),           // For any lossless format
        MP3("MP3"),
        AIFF("AIFF"),
        BINARY("BINARY"),       // Little-endian 
        MOTOROLA("MOTOROLA");   // Big-endian

        private final String nameString;
        DataFormat(String nameString) { this.nameString = nameString; }
        public String toString() { return nameString; };
        }

    static enum TrackFlags {
        DCP("DCP"),     // Digital copy permitted
        _4CH("4CH"),    // Four channel audio
        PRE("PRE"),     // Pre-emphasis enabled (audio tracks only)
        SCMS("SCMS");   // Serial copy management system

        private final String nameString;
        TrackFlags(String nameString) { this.nameString = nameString; }
        public String toString() { return nameString; };
    }
}
