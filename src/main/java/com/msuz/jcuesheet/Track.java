/*
 * Copyright Michael Suzzi, 2022
 * 
 * Track object, consisting of a data type, data format, optional CD-Text
 * metadata, optional pre/postgap information, a list of indices and an
 * optional list of flags.
 * 
 * TODO: Replace title and performer members with CDText object
 */

package com.msuz.jcuesheet;

import com.msuz.jcuesheet.EnumConstants.*;
import java.util.ArrayList;

public class Track {

    private DataType dataType;
    private DataFormat dataFormat;
    private String file;
    private String title;
    private String performer;
    private Timestamp pregap;
    private Timestamp postgap;
    private ArrayList<Timestamp> indices;
    private ArrayList<TrackFlags> flags;
    private int indexOffset; // The # of the first index in array is 0+indexOffset

    public Track(DataType type, DataFormat format) { this(type, format, ""); }

    public Track(DataType type, DataFormat format, String file) {
        this.dataType = type;
        this.dataFormat = format;
        this.file = file;
        this.title = "";
        this.performer = "";
        this.pregap = null;
        this.postgap = null;
        this.indices = new ArrayList<Timestamp>();
        this.flags = new ArrayList<TrackFlags>();
        this.indexOffset = 0;
        this.addIndex(00, 00, 00);
    }

    // Set the FILE
    public void setFile(String file) { this.file = file; }

    // Set TITLE field of Track
    public void setTitle(String title) {
        // Limited length and char set allowed
        if (title.length() > 80) {
            throw new IllegalArgumentException(
                "Maximum length of performer field is 80 characters");
        }

        this.title = title;
    }

    // Set PERFORMER field of Track
    public void setPerformer(String performer) {
        // Limited length and char set allowed
        if (performer.length() > 80) {
            throw new IllegalArgumentException(
                "Maximum length of performer field is 80 characters");
        }

        this.performer = performer;
    }

    // Set the length of the PREGAP
    public void setPregap(int minute, int second, int frame) {
        this.pregap = new Timestamp(minute, second, frame);
    }

    // Remove PREGAP and return associated Timestamp
    public Timestamp removePregap() {
        if (pregap == null) {
            // TODO: change exception to a better fit
            throw new IllegalArgumentException(
                "No pregap defined");
        }

        Timestamp ret = this.pregap;
        this.pregap = null;
        return ret;
    }

    // Set the length of the POSTGAP
    public void setPostgap(int minute, int second, int frame) {
        this.postgap = new Timestamp(minute, second, frame);
    }

    // Remove POSTGAP and return associated Timestamp
    public Timestamp removePostgap() {
        if (postgap == null) {
            // *fixme to correct exception
            throw new IllegalArgumentException(
                "No postgap defined");
        }

        Timestamp ret = this.postgap;
        this.postgap = null;
        return ret;
    }

    // Specifies which index to start counting from
    public void setIndexOffset(int first) {
        if (first < 0 || first > 99) {
            throw new IllegalArgumentException(
                "Index must be between 0 and 99 inclusive");
        }

        this.indexOffset = first;
    }

    // Add a new index with provided timestamp. # is specified by indexes.size() + indexOffset
    public void addIndex(int minute, int second, int frame) {
        indices.add(new Timestamp(minute, second, frame));
    }

    // Remove specified INDEX and return associated Timestamp
    public Timestamp removeIndex(int index) {
        Timestamp removed = indices.get(index);
        indices.remove(index);

        return removed;
    }

    // Add FLAG entry
    public void addFlag(TrackFlags f) { flags.add(f); }

    /*
    public TrackFlags removeFlag(TrackFlags f) {
        if (flags.size() == 0) {
            // *fixme to correct exception
            throw new IllegalArgumentException(
                "No flags specified");
        }

        
    }
    */

    // Return track data type as string
    public String getDataType() { return dataType.toString(); }

    // Return track data format as string
    public String getDataFormat() { return dataFormat.toString(); }

    // Return filename specified for track
    // Will return empty string if unspecified
    public String getFile() { return file; }

    // Return FILE string for cue printing
    // Will be empty string if unspecified
    public String getFileString() {
        String fileString;

        if (getFile() != "") {
            fileString = String.format("FILE \"%s\" %s\n", file, dataFormat);
        } else {
            fileString = "";
        }

        return fileString;
    }

    // Return value of TITLE field
    public String getTitle() {
        return title;
    }

    // Return value of PERFORMER field
    public String getPerformer() {
        return performer;
    }

    // Return Timestamp of PREGAP
    public Timestamp getPregap() {
        if (pregap == null) {
            // *fixme to correct exception
            throw new IllegalArgumentException(
                "No pregap defined");
        }

        return pregap;
    }

    // Return Timestamp of POSTGAP
    public Timestamp getPostgap() {
        if (postgap == null) {
            // *fixme to correct exception
            throw new IllegalArgumentException(
                "No postgap defined");
        }
        
        return postgap;
    }

    // NOTE: indexOffset will be taken into account with getIndex()!
    public Timestamp getIndex(int index) {
        if (index-indexOffset < 0) {
            throw new IndexOutOfBoundsException(
                String.format("Specified index (%d) is out of bounds; first index is %d\n",
                               index-indexOffset, indexOffset));
        }

        return indices.get(index-indexOffset);
    }

    // Return index offset - # of first index
    public int getIndexOffset() { return indexOffset; }

    // Return INDEX string for cue printing
    public String getIndexString() {
        String out = "";
        // INDEX lines - getIndexString() method handles index number offset
        for (int j = indexOffset; j < this.numIndices() + indexOffset; j++) { 
            out += String.format("    INDEX %02d %s\n", j, this.getIndex(j));
        }

        return out;
    }

    // Return FLAGS string for cue printing
    public String getFlagsString() {
        if (this.numFlags() == 0) {
            return ""; // No flags specified
        }

        String out = "    FLAGS";

        for (TrackFlags f : flags) {
            out += String.format(" %s", f.toString());
        }

        out += String.format("\n");
        return out;
    }

    public int numIndices() { return indices.size(); }

    public int numFlags() { return flags.size(); }
}
