package com.example.finallauncherrefactored.Projects.HungerGames;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class Page4 extends Page
{
    Page4(Story story)
    {
        super(story);
        image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("HungerGames/McLovin.jpg")));

    }

    void read()
    {
        System.out.println(
        
        "McLovin finds himself in a lagoon, he doesnt really know how he got himself in there either. The humid jungle air fills his lungs- he really wishes he brought his inhaler. \n\n" +
        "Oh no, oh no, oh no. ( Breaks into uncontrollable wails.). I knew I should have read that survival book. I am gonna die, I am gonna die. (Begins hyperventilating.) ( Mclovin seems to be already at the bottom of the food chain compared to the other tributes. How will he make it out alive? He has no street smarts.)\n\n"

        );
        
        
    }

}