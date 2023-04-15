/*
 * Copyright Michael Suzzi, 2022
 *  
 * Defines the Timestamp class, which stores times in the CUE format of
 * minutes (0-59), seconds (0-59) and frames (0-74)
 */

package com.msuz.jcuesheet;

public class Timestamp {
    // These fields are limited to <99 size, so use bytes
    private byte minute;
    private byte second;
    private byte frame;

    public Timestamp(int minute, int second, int frame) {
        this.minute = (byte) minute;
        this.second = (byte) second;
        this.frame = (byte) frame;
    }

    public void setMinute(int minute) {
        // Some CD-R99 writers can store 99:05 minutes
        if (minute < 0 || minute > 99) {
            throw new IllegalArgumentException(
                "Minute must be within the range 0-99");
        }

        this.minute = (byte) minute;
    }

    public void setSecond(int second) {
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException(
                "Second must be within the range 0-59");
        }

        this.second = (byte) second;
    }

    public void setFrame(int frame) {
        if (frame < 0 || frame > 74) {
            throw new IllegalArgumentException(
                "Frame must be within the range 0-74");
        }

        this.frame = (byte) frame;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getFrame() {
        return frame;
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", minute, second, frame);
    }
}
