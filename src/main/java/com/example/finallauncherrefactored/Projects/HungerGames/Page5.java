package com.example.finallauncherrefactored.Projects.HungerGames;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class Page5 extends Page
{
    Page5(Story story)
    {
        super(story);
        image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("HungerGames/edward.jpg")));

    }

    void read()
    {
        System.out.println(
        "They can see my true skin. The skin of a killer. They stare at my glistening skin as the sun rays hit me perfectly. I hope they know I am not a vegetarian anymore, only for my girl Bella. \n\n" +
        "(Edward runs at lightning speed only to catch the eye of a flirty Alex, he ignores her.) (Smoldering at the sight of scrawny flesh of skinny McLovin. Averted, he sees a rabbit, yummy.) \n\n"

        );
        
        
    }

}