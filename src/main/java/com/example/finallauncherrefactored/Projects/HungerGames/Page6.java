package com.example.finallauncherrefactored.Projects.HungerGames;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class Page6 extends Page
{
    Page6(Story story)
    {
        super(story);
        image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("HungerGames/penelopeprof.jpg")));

    }

    void read()
    {
        System.out.println(
        
        "Um, Um, Derek ? Where are you ? This isnt Quantico. (Looks aggressively for her laptop. Its nowhere to be found.) No wifi? I am not supposed to be in the field. Ok, think Garcia, what would Derek do ?? Oh god, I am dying. Is that a freakin vampire ! (Quickly skips away because she has no athletic ability only the power of coding, thank god for Mr.Haney) \n\n" +
        "Penelope is frightened. She was told this was an important mission that she alone has to do. She cant let her team down. Still, she cant help but wonder if this mission was better suited for Emily or JJ. \n\n" 


        );
        
        
    }

}