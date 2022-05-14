package com.example.finallauncherrefactored.Projects.MP3Player;

import javafx.scene.media.MediaPlayer;

import java.util.Collections;
class Player
{
    MediaPlayer mediaPlayer;
    boolean songPlaying;
    Playlist tracklist = new Playlist();
    int track;
    
    Player()
    {
        this.songPlaying = false;
        this.track = 0;
        
        mediaPlayer = new MediaPlayer(tracklist.playlist.get(track).songFile);
        
    }
    
    void Play()
    {
        if(this.songPlaying == false)
        {
            songPlaying = true;
            //mediaPlayer = new MediaPlayer(tracklist.playlist.get(track).songFile);
            mediaPlayer.play();
        }
        
        else if(this.songPlaying == true)
        {
            songPlaying = false;
            mediaPlayer.pause();
        }
    }
    

    
    void Shuffle()
    {
        mediaPlayer.pause();
        this.songPlaying = false;
        Collections.shuffle(tracklist.playlist);
        this.track = 0;
        mediaPlayer = new MediaPlayer(tracklist.playlist.get(track).songFile);
        mediaPlayer.play();
    }
    
    void skipForeward()
    {
        mediaPlayer.pause();
        this.track ++;
        mediaPlayer = new MediaPlayer(tracklist.playlist.get(track).songFile);
        mediaPlayer.play();
    }
    
    void skipBack()
    {
        mediaPlayer.pause();
        this.track --;
        mediaPlayer = new MediaPlayer(tracklist.playlist.get(track).songFile);
        mediaPlayer.play();
    }
    
    Song getCurrentSong()
    {
        return tracklist.playlist.get(track);
    }
}