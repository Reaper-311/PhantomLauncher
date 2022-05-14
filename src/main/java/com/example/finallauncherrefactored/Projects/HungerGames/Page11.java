package com.example.finallauncherrefactored.Projects.HungerGames;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class Page11 extends Page
{
    Page11(Story story)
    {
        super(story);
        image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("HungerGames/cryinglovin.jpg")));

    }

    void read()
    {
        System.out.println(
        
       "The tributes left Mclovin and Edward must now battle it out with each other." +
       "McLovin cannot cope with the fact he will have to go against a human disco ball with lightning speed. Terrified, Mclovin runs towards the cornucopia in search of a weapon to use against Edward. He looks around and sees all the weapons. He goes for an axe, but unfortunately it is too heavy for him. \n\n" +

       "Edward inches closer and closer. Until finally he gets face to face with Mclovin. \n\n" +
 
       "Mister Vampire Please dont kill me. Please I promise I dont taste good. \n\n" + 
       "McLovin covers his face in fear. Suddenly he remembers his ID is in his pocket. He grabs the ID and throws it at his face. It hits Edward in the neck, slicing through his neck. Instantly killing him. \n\n" +


       "Mclovin is in shock. Did he just kill  a vampire? \n\n" +
        "But most importantly, Did he just win ?\n\n" +

       "Yes, he did. \n\n" +


       "Mclovin, congratulations you have completed your task. You are the winner. \n\n" +

       "Omg, omg, no way. How. Me. I won. \n\n"+

       "Mclovin is flown to the Victors Village. Where he lives amongst the winners. He lives in luxury. But he hopes to move to Hawaii once he turns 21 with his best friends. \n\n" 



       );
    }
}