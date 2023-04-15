/*
 * Copyright Michael Suzzi, 2022
 *  
 * Simple user test of CueSheet and related classes 
 * Run in VSCode with "Run Java", not "Run Code"
 */

package com.msuz.jcuesheet;

import com.msuz.jcuesheet.EnumConstants.*;

public class JCueSheetExample {
    public static void main(String[] args) {

        CueSheet testCue = new CueSheet();

        System.out.printf("Adding a couple of tracks...");
        testCue.addTrack(DataType.AUDIO, DataFormat.WAVE, "Test1.wav");
        testCue.addTrack(DataType.MODE1_2352, DataFormat.BINARY);
        testCue.addTrack(DataType.CDG, DataFormat.MP3, "Test2.mp3");

        System.out.printf(" Done\nAdding indexes and track flags...");
        testCue.getTrack(2).addIndex(02, 03, 57);
        testCue.getTrack(2).addFlag(TrackFlags._4CH);
        testCue.getTrack(2).addFlag(TrackFlags.DCP);
        testCue.getTrack(2).setIndexOffset(69);

        System.out.printf(" Done\nPrinting cue sheet...\n\n");
        System.out.printf("%s\n", testCue);
    }
}
