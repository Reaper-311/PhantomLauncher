package com.example.finallauncherrefactored.Projects.HungerGames;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class Page9 extends Page
{
    Page9(Story story)
    {
        super(story);
        image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("HungerGames/Edfight.jpg")));

    }

    void read()
    {
        System.out.println(
        
        "Tributes remaining: 3 \n\n" +

        "In the jungle there now remains 3 tributes, Mclovin, Alex Russo, and Edward Cullen. Now they must fight against each other to see who will reign victorious. \n\n" +

        "Deep in the jungle in a deserted cave, Alex Russo wakes up from her much needed nap. She suddenly hears an announcement. \n\n" +

        "Alex Russo, Mclovin, and Edward Cullen. You are the remaining 3 tributes. You have made it this far now you must fight against each other. There can only be one winner. \n\n" +

        "Alex is not happy with the announcement.She did not realize she would ever have to kill another. This isnt for her, she is a simple girl, she misses simple things, hamburgers and Mason. She contemplates her next move. \n\n" + 

        "Mclovin wanders aimlessly through the jungle, struggling to cope with his own actions. He hears the announcement and is terrified. He is terrified he cannot kill either. He accidentally killed shrek. He would never mean to do that. Mclovin sits next to a tree and begins to weep. \n\n" +

        "Edward does not react like the other tributes. He is stone cold. He has no heart. \n\n"

        );
        
        
    }

}