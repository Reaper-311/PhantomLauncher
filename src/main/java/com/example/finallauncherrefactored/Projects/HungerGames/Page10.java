package com.example.finallauncherrefactored.Projects.HungerGames;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class Page10 extends Page
{
    Page10(Story story)
    {
        super(story);
        image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("HungerGames/Mclovinid.jpg")));

    }

    void read()
    {
        System.out.println(
        
        "The next day, 3 tributes still remain. \n\n" +


       "Alex, Mclovin, Edward are all sent back to the cornucopia for the final round. \n\n" +

       "Tributes,Welcome to the final round, Congratulations for making it this far, or apologies. Now its time for one of you to be crowned. And two of you will die. You’ll have three hours to kill each other. Your time starts now. \n\n" +


       "Alex stands in terror as she stares at Edward with his psychopathic eyes. The other side of her is McLovin, who is covered in snot and tears. Oh, No. This is too much work. I am not killing anyone. Wait, I will just use my magic to take me back home. Why didnt I think of that before. She zaps out back to her New York apartment. \n\n" +


       "Alex Russo not deceased but eliminated.\n\n" 
       
       


        );
        
    }
}