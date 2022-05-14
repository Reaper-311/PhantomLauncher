package com.example.finallauncherrefactored.Projects.HungerGames;

import javafx.scene.image.Image;

abstract class Page
{
    // The image that will be displayed on the facing page:
    Image image;
    // A reference to the main story.
    Story story;
    
    Page(Story story)
    {
        this.story = story;
    }
    
    // All pages must implement a "read" method that prints the text of the page.
    abstract void read();
        
}
