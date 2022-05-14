package com.example.finallauncherrefactored.Projects.Stories;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class ChooseEvent extends Event
{
    Image title = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/title.png")));
    
    ChooseEvent(World world)
    {
        super(world);
        currentEvent = 1;
    }
    
    void playEvent(String lastOption)
    {
        if (currentEvent == 1)
        {
            setImage(title);
            System.out.println("Welcome to Stories! You get to deicde which storyline to live!");
            System.out.println("Do you want to live the college life or the floral career?");
            System.out.println("Type the word in all caps of the choice you want. Then you will hit enter one more time...");
            System.out.println("Choose FLOWER or COLLEGE");
            currentEvent = 2;
        }
        else if (currentEvent == 2)
        {
            if (lastOption.toUpperCase().equals("FLOWER"))
            {
                System.out.println("You selected Floral Friday!");
                world.moveTo(world.floralFriday);
            }
            if (lastOption.toUpperCase().equals("COLLEGE"))
            {
                world.moveTo(world.collegeLife);
            }
        }
    }
}