/*
 * Copyright Michael Suzzi, 2022
 *  
 * Tests for Track class
 */

package com.msuz.jcuesheet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import com.msuz.jcuesheet.EnumConstants.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TrackTest {
    // Convenience method
    private Track createTrack(DataType type, DataFormat format) {
        Track track = new Track(type, format);
        return track;
    }

    @DisplayName("Track constructor correctly sets all possible data types")
    @ParameterizedTest
    @ValueSource(strings = {
        "AUDIO",
        "CDG",
        "MODE1_2048",
        "MODE1_2352",
        "MODE2_2048",
        "MODE2_2324",
        "MODE2_2336",
        "MODE2_2352",
        "CDI_2336",
        "CDI_2352"
    })
    void testConstructorDataTypes(String dataType) {
        Track testTrack = createTrack(Util.enumFromVal(DataType.class, dataType), DataFormat.WAVE);
        assertEquals(dataType.replace('_','/'), testTrack.getDataType(), "Type string should return from getDataType()");
        System.out.println("SUCCESS: " + dataType + " input produces " + testTrack.getDataType() + " output string");
    }

    @DisplayName("Track constructor correctly sets all possible data formats")
    @ParameterizedTest
    @ValueSource(strings = {
        "WAVE",
        "MP3",
        "AIFF",
        "BINARY",
        "MOTOROLA"
    })
    void testConstructorDataFormats(String dataFormat) {
        Track testTrack = createTrack(DataType.AUDIO, Util.enumFromVal(DataFormat.class, dataFormat));
        assertEquals(dataFormat, testTrack.getDataFormat(), "Format string should return from getDataFormat()");
        System.out.println("SUCCESS: " + dataFormat + " input produces " + testTrack.getDataFormat() + " output string");
    }
    
}
