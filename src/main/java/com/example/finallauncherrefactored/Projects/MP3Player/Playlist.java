package com.example.finallauncherrefactored.Projects.MP3Player;

import com.example.finallauncherrefactored.Main;

import java.util.ArrayList;

class Playlist
{
    ArrayList<Song> playlist = new ArrayList<Song>();
    
    Playlist()
    {
        //Song = new Song ("" , pathToSongsFolder + "");
        //.addArtist("");
        //.addCover(pathToCoversFolder + "");
        //.addTime("");
        
        
        
        String pathToSongsFolder = Main.class.getResource("MP3Player/songs/").toExternalForm();
        String pathToCoversFolder = Main.class.getResource("MP3Player/covers/").toExternalForm();
        
        
        Song Poppin = new Song ("Poppin" , pathToSongsFolder + "Poppin.mp3");
        Poppin.addArtist("Yeat");
        Poppin.addCover(pathToCoversFolder + "poppin.png");
        Poppin.addTime("2:47");
        
        Song FerrisWheel = new Song ("Ferris Wheel" , pathToSongsFolder + "FerrisWheel.mp3");
        FerrisWheel.addArtist("Young Nudy");
        FerrisWheel.addCover(pathToCoversFolder + "ferris wheel.jpeg");
        FerrisWheel.addTime("2:59");
        
        Song DevilInANewDress = new Song ("Devil in a New Dress" , pathToSongsFolder + "DevilInANewDress.mp3");
        DevilInANewDress.addArtist("Kanye West");
        DevilInANewDress.addCover(pathToCoversFolder + "diand.jpg");
        DevilInANewDress.addTime("5:52");
        
        Song DontPlayThat = new Song ("Dont Play That" , pathToSongsFolder + "DontPlayThat.mp3");
        DontPlayThat.addArtist("King Von ft. 21 Savage");
        DontPlayThat.addCover(pathToCoversFolder + "dont play that.jpeg");
        DontPlayThat.addTime("2:14");
        
        Song DripTooHard = new Song ("Drip Too Hard" , pathToSongsFolder + "DripTooHard.mp3");
        DripTooHard.addArtist("Lil Baby & Gunna");
        DripTooHard.addCover(pathToCoversFolder + "Drip_Too_Hard.jpg");
        DripTooHard.addTime("2:26");
        
        Song ENEMIES = new Song ("ENEMIES" , pathToSongsFolder + "ENEMIES.mp3");
        ENEMIES.addArtist("DC the Don");
        ENEMIES.addCover(pathToCoversFolder + "ENEMIES.png");
        ENEMIES.addTime("3:26");
        
        Song Fetti = new Song ("Fetti" , pathToSongsFolder + "Fetti.mp3");
        Fetti.addArtist("Playboi Carti");
        Fetti.addCover(pathToCoversFolder + "fetti.jpg");
        Fetti.addTime("5:17");        
        
        Song Hit = new Song ("Hit" , pathToSongsFolder + "Hit.mp3");
        Hit.addArtist("Kenny Mason");
        Hit.addCover(pathToCoversFolder + "hit.jpg");
        Hit.addTime("2:38");
        
        Song SauceItUp = new Song ("Sauce It Up" , pathToSongsFolder + "SauceItUp.mp3");
        SauceItUp.addArtist("Lil Uzi Vert");
        SauceItUp.addCover(pathToCoversFolder + "sauce it up.jpeg");
        SauceItUp.addTime("3:27");
        
        Song WelcomeToTheParty = new Song ("Welcome To The Party" , pathToSongsFolder + "WelcomeToTheParty.mp3");
        WelcomeToTheParty.addArtist("Pop Smoke");
        WelcomeToTheParty.addCover(pathToCoversFolder + "ww.jpg");
        WelcomeToTheParty.addTime("3:35");
        
        Song Poof = new Song ("Poof" , pathToSongsFolder + "Poof.mp3");
        Poof.addArtist("Pi'erre Bourne");
        Poof.addCover(pathToCoversFolder + "poof.png");
        Poof.addTime("3:03");
        
        Song PUFFINONZOOTIES = new Song ("PUFFIN ON ZOOTIEZ" , pathToSongsFolder + "PP.mp3");
        PUFFINONZOOTIES.addArtist("Future");
        PUFFINONZOOTIES.addCover(pathToCoversFolder + "puffin.png");
        PUFFINONZOOTIES.addTime("2:53");
        
        Song Sky = new Song ("Sky" , pathToSongsFolder + "Sky.mp3");
        Sky.addArtist("Playboi Carti");
        Sky.addCover(pathToCoversFolder + "sky.png");
        Sky.addTime("3:13");
        
        Song Familyties = new Song ("family ties" , pathToSongsFolder + "ft.mp3");
        Familyties.addArtist("Baby Keem ft Kendrick Lamar");
        Familyties.addCover(pathToCoversFolder + "familyties.png");
        Familyties.addTime("4:12");
        
        Song STATS = new Song ("STATS" , pathToSongsFolder + "STATS.mp3");
        STATS.addArtist("Baby Keem");
        STATS.addCover(pathToCoversFolder + "stats.png");
        STATS.addTime("2:50");
        
        //Song tG = new Song ("3G" , pathToSongsFolder + "tg.mp3");
        //tG.addArtist("Yeat ft. Lil Uzi Vert");
        //tG.addCover(pathToCoversFolder + "tg.png");
        //tG.addTime("2:51");
        
        Song BadMan = new Song ("Bad Man(Smooth Criminal" , pathToSongsFolder + "bm.mp3");
        BadMan.addArtist("Polo G");
        BadMan.addCover(pathToCoversFolder + "bm.png");
        BadMan.addTime("1:46");
        
        playlist.add (Poppin);
        playlist.add (FerrisWheel);
        playlist.add (DevilInANewDress);
        playlist.add (DontPlayThat);
        playlist.add (DripTooHard);
        playlist.add (ENEMIES);
        playlist.add (Fetti);
        playlist.add (Hit);
        playlist.add (SauceItUp);
        playlist.add (WelcomeToTheParty);
        playlist.add (Poof);
        playlist.add (PUFFINONZOOTIES);
        playlist.add (Sky);
        playlist.add (Familyties);
        playlist.add (STATS);
        //playlist.add (tG);
        playlist.add (BadMan);
    }

    
    

}