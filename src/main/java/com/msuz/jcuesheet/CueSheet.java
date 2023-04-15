/*
 * Copyright Michael Suzzi, 2022
 *  
 * Main CueSheet object, composed of optional CD-Text metadata, any number of
 * tracks, and a track offset
 * 
 * TODO: Replace title and performer members with CDText object
 */

package com.msuz.jcuesheet;

import com.msuz.jcuesheet.EnumConstants.*;
import java.util.ArrayList;

public class CueSheet {

    private String title;
    private String performer;
    private ArrayList<Track> trackList;
    private ArrayList<String[]> remList;
    private int trackOffset;

    // Default constructor
    public CueSheet() {this("");}

    // Single-argument constructor
    public CueSheet(String title) {this(title, "");}

    // Full 2-argument constructor
    public CueSheet(String title, String performer) {
        this.title = title;
        this.performer = performer;
        this.trackList = new ArrayList<Track>();
        this.remList = new ArrayList<String[]>();
        this.trackOffset = 1;   // 1 is by far the most common, but can be 0
    }

    // Set TITLE field of CueSheet
    public void setTitle(String title) {
        // Limited length and char set allowed
        if (title.length() > 80) {
            throw new IllegalArgumentException(
                "Maximum length of performer field is 80 characters");
        }
        this.title = title;
    }

    // Set PERFORMER field of CueSheet
    public void setPerformer(String performer) {
        // Limited length and char set allowed
        if (performer.length() > 80) {
            throw new IllegalArgumentException(
                "Maximum length of performer field is 80 characters");
        }
        this.performer = performer;
    }

    // Set the number/index of the first Track object
    public void setTrackOffset(int first) {
        if (first == 0 || first == 1) {
            trackOffset = first;
        } else {
            throw new IllegalArgumentException(
                "First track must be either 0 or 1");
        }
    }

    /*
     * Add new remark (REM) to remList 2D array.
     *
     * each remList entry is a 2-element array containing the name and content
     * of the remark respectively
     */ 
    public void addRem(String name, String content) {
        String[] newRem = {name, content};

        if (remList.add(newRem) != true) {
            throw new ArrayStoreException(
                String.format("Failed to add remark \"%s %s\" to Cue sheet", name, content));
        }
    }

    // Create new Track object at next available index
    public void addTrack(DataType type, DataFormat format) {addTrack(type, format, "");}

    public void addTrack(DataType type, DataFormat format, String file) {
        if (trackList.size() == 99) {
            throw new IllegalStateException(
                "Maximum cue sheet track count is 99");
        }

        Track newTrack;
        newTrack = new Track(type, format, file);
        
        addTrackHelper(newTrack);
    }

    // Private helper for addTrack
    private void addTrackHelper(Track newTrack) {
        if (trackList.add(newTrack) != true) {
            throw new ArrayStoreException(
                String.format("Failed to add track \"%s\" to Cue sheet", newTrack));
        }
    }

    // Shifts element at tnum to right and inserts track at tnum
    public void insertTrack(Track newTrack, int tnum) {
        if (trackList.size() == 99) {
            throw new IllegalStateException(
                "Maximum cue sheet track count is 99");
        }
        trackList.add(tnum-trackOffset, newTrack);
    }

    // Remove Track object at specified index from CueSheet and returns it
    public Track removeTrack(int tnum) {return trackList.remove(tnum-trackOffset);}

    // Return value of TITLE field
    public String getTitle() {return title;}

    // Return value of PERFORMER field
    public String getPerformer() {return performer;}

    // Return Track object at specified index
    public Track getTrack(int tnum) {return trackList.get(tnum-trackOffset);}

    // Return number of tracks
    public int numTracks() {return trackList.size();}

    // Return REM strings for cue printing
    public String getRemString() {
        String out = "";

        // Remarks list is a 2D array where element 0 stores remark name
        // and element 1 stores the remark itself
        for (String[] rem : remList) {
            out += "REM ";
            out += rem[0]; out += " ";
            out += rem[1]; out += "\n";
        }

        return out;
    }

    // Converts entire CueSheet object into string data, ready for saving to file
    public String toString() {
        String outString = "";

        outString += this.getRemString();           // REM lines

        // Track numbers are stored by array positions in CueSheet class
        for (int i = 0; i < trackList.size(); i++) {
            Track current = trackList.get(i);
  
            outString += current.getFileString();   // FILE line

            // TRACK line
            outString += "  TRACK ";
            outString += String.format("%02d ", i+trackOffset);
            outString += current.getDataType(); outString += "\n";

            outString += current.getFlagsString();  // FLAGS line
            outString += current.getIndexString();  // INDEX lines
        }
        return outString;
    }

    // TODO: Converts raw string input data into a CueSheet object.
    public static CueSheet fromString(String stringData) {
        // Converts String to CueSheet
        // big long complex function
        CueSheet outCue = new CueSheet();
        return outCue;
    }
}
