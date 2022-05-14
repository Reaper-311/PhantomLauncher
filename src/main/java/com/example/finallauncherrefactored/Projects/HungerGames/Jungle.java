package com.example.finallauncherrefactored.Projects.HungerGames;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class Jungle extends Page
{

    Jungle(Story story)
    {
        super(story);
        image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("HungerGames/jungle.jpg")));

    }

    void read()
    {
        System.out.println(



        " Seven unlucky Tributes have been dropped in an uncharted jungle. Only one can regin victorius.\n\n" +

        
        " Alex Russo, Shrek,Regina George, Edward Cullen,Penelope Garcia, McLovin', Bill Ray Cyrus anxiously wait as the timer ticks down. \n\n" +
        " The jungle is so quiet. Everyone can hear each others heartbeat. \n\n" +
        
        "5 \n\n" + "4 \n\n" + "3 \n\n" + "2 \n\n" + "1... \n\n" 

        
        );
    }

}