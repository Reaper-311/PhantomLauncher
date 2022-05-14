package com.example.finallauncherrefactored.Projects.HungerGames;

abstract class Character
{
    String name;
    boolean isAlive;
    boolean hasOnion;
    boolean hasWand;
    boolean hasSpeed; 
    boolean hasHorse;
    boolean canStretch;
    boolean hasID;
    boolean hasChocolateThunder;
    Story story;
    
    Character(Story story)
    {
        this.name = "";
        this.isAlive = true;
        this.story = story;
    }
}