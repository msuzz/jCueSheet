# jCueSheet
Simple Java library for parsing [cue sheets](https://wiki.hydrogenaud.io/index.php?title=Cue_sheet) for use with CD imaging software and music players.

# Status
Work in progress. Basic functionality for creating cue sheets and outputting to a string is demonstrated in the file JCueSheetExample.java, which constructs a simple cue sheet:

    FILE "Test1.wav" WAVE
      TRACK 01 AUDIO
        INDEX 00 00:00:00
      TRACK 02 MODE1/2352
        FLAGS 4CH DCP
        INDEX 69 00:00:00
        INDEX 70 02:03:57
    FILE "Test2.mp3" MP3
      TRACK 03 CDG
        INDEX 00 00:00:00

# TODO
- Change Track and CueSheet objects to use new CDText class for metadata
- Complete tests for all functionality
- Proper documentation
