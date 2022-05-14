package com.example.finallauncherrefactored.Projects.HungerGames;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class Page2 extends Page
{
    
    Page2(Story story)
    {
        super(story);
        image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("HungerGames/billyprof.jpg")));

    }
    
    void read()
    {
        System.out.println("Dang Flabbit !! Why in tarnation am I stuck in the middle of this Jungle? Even the swamps of Kentucky aint as humid as this. (Feels his hair become damp with the moisture of the jungle.) \n\n" + "This darn tootin weather is messing up my mullet. Wheres my horse? \n\n" + "( Billy begins to walk in a cowboyish manner to find his horse)." +
        "Billy Ray has seen his fair share of outdoors, but this was a bit much even for him. His manly instinct tells him something is off as if somebody is watching him. Because everybody is. \n\n"
         );

    } 
    
}