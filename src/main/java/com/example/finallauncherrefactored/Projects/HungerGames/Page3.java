package com.example.finallauncherrefactored.Projects.HungerGames;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class Page3 extends Page
{
    Page3(Story story)
    {
        super(story);
        image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("HungerGames/alexprof.jpg")));

    }

    void read()
    {
        System.out.println(
        "In the thickest part of the jungle, lands Alex Russo. The sassy witch from New York is definitely out of her element. \n\n" +
        "Oh, come on, not more running. Great Dad is going to kill me, ugh. Wheres Justin when you actually need him. They better not make me run or actually try to kill them. ( Sits down  when shes suppose to run and begins pensively inspecting her fresh manicure..) \n\n"


        
        );
        
        
    }

}