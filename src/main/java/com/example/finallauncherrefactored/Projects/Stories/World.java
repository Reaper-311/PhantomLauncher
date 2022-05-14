package com.example.finallauncherrefactored.Projects.Stories;

class World
{
    //Events
    CollegeLife collegeLife;
    FloralFriday floralFriday;
    ChooseEvent choose;
    
    
    Event currentEvent;
    Player player;
    
    World()
    {
        choose = new ChooseEvent(this);
        collegeLife = new CollegeLife(this);
        floralFriday = new FloralFriday(this);
        
        
        this.currentEvent = choose;
        player = new Player(this);
    }
    
    void moveTo(Event event)
    {
        this.currentEvent = event;
    }
    
}
