package com.example.finallauncherrefactored.Projects.MP3Player;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
class Song
{
    String name;
    String artist;
    String time;
    Image cover;
    Media songFile;
    

    Song(String name, String songFileName)
    {
        this.name = name;
        this.songFile = new Media(songFileName);
        
    }
    
    void addArtist(String artist)
    {
        this.artist = artist;
    }
    
     void addCover(String imageFile)
    {
        this.cover = new Image(imageFile);
    }

    void addTime(String time)
    {
        this.time = time;
    }

}