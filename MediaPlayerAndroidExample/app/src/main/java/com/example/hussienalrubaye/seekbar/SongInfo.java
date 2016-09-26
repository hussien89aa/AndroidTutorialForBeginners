package com.example.hussienalrubaye.seekbar;

/**
 * Created by hussienalrubaye on 9/13/15.
 */
public class SongInfo {
    public  String Path;
    public  String song_name;
    public  String album_name;
    public  String artist_name;
    public  SongInfo(String Path,String song_name,String album_name,
                     String artist_name){
        this.Path=Path;
        this.song_name=song_name;
        this.album_name=album_name;
        this.artist_name=artist_name;
    }

}
