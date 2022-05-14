package com.example.finallauncherrefactored.Projects.HungerGames;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class Weapons extends Page
{
    Weapons(Story story)
    {
        super(story);
        image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("HungerGames/corn.jpg")));

    }

    void read()
    {
        System.out.println(
        "As soon as the countdown hit, all the tributes scatter to the cornucopia to get their weapons. Scattering one  by one they all run down through the humid jungle. No one seems content to be there. The abundantly filled cornucopia was filled with personalized weapons. From an onion to a wand. All their tributes have their own designated item. \n\n" +
        "Shrek find his onion, he goes to find his home, the swamp. \n\n" +


        "Robby Ray finds his horse and he gallops away into the jungle. \n\n" +


        "Alex Russo after finding her wand, she tries to run but then decides it much. So, instead she will take a nap. \n\n" +


        "McLovin finds his ID, and wished he went with Muhammad. He runs away wailing in search of shelter. \n\n" +


        "Edward needs no item, as he is speed. He dashes towards the trees. \n\n" +
        
        "Penelope discovers her chocolate thunder, her love Derek Morgan. They ran off and were never to be seen. We still wonder where they went. \n\n" +


        "Regina finds her burnbook, and proceeds to insult everybody. But unfortunately while turning the page she gets a paper cut. BLOOD IS SPILT. Edward smells from meters away and snatchesup Regina and her Louis Vuitton, and she is devoured. That wasnt very vegetarian of Edward \n\n"


        );
        
        
    }

}