package com.example.finallauncherrefactored.Projects.Stories;

import javafx.scene.image.Image;

public abstract class Event
{
    World world;
    int currentEvent = 0;
    Image currentImage;
    
    Event(World world)
    {
        this.world = world;
    }
    
    /**
     * this is our version of scriptReader
     */
    abstract void playEvent(String lastOption);
    
    void setImage(Image i)
    {
        currentImage = i;
    }
}