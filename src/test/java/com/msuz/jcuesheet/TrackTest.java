/*
 * Copyright Michael Suzzi, 2022
 *  
 * Tests for Track class
 */

package com.msuz.jcuesheet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import com.msuz.jcuesheet.EnumConstants.*;
import com.msuz.jcuesheet.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class TrackTest {

    private String[] dataTypeStrings = {
        "AUDIO",
        "CDG",
        "MODE1/2048",
        "MODE1/2352",
        "MODE2/2048",
        "MODE2/2324",
        "MODE2/2336",
        "MODE2/2352",
        "CDI/2336",
        "CDI/2352"
    };
    private String[] dataFormatStrings = {
        "WAVE",
        "MP3",
        "AIFF",
        "BINARY",
        "MOTOROLA"
    };

    private static <T extends Enum<T>> T enumFromVal(Class<T> eenum, String value) {
        for (T e : eenum.getEnumConstants()) {
            if (e.name().equals(value))
                return e;
        }
        return null;
    }

    private Track createTrack(DataType type, DataFormat format) {
        Track track = new Track(type, format);
        return track;
    }

    @RepeatedTest(10)
    @DisplayName("Track constructor correctly sets all possible data types")
    void testConstructorDataTypes() {
        for (int i = 0; i < dataTypeStrings.length; ++i) {
            assertEquals(dataTypeStrings[i],
                createTrack(enumFromVal(DataType.class, dataTypeStrings[i]), DataFormat.WAVE).getDataType(),
                "Type string should return from getDataType()");
        }
    }

    @RepeatedTest(5)
    @DisplayName("Track constructor correctly sets all possible data formats")
    void testConstructorDataFormats() {
        for (int i = 0; i < dataFormatStrings.length; ++i) {
            assertEquals(dataFormatStrings[i],
                createTrack(DataType.AUDIO, enumFromVal(DataFormat.class, dataFormatStrings[i])).getDataFormat(),
                "Format string should return from getDataFormat()");
        }
    }
    
}
