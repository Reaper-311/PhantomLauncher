package com.example.finallauncherrefactored.Projects.HungerGames;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class Page8 extends Page
{
    Page8(Story story)
    {
        super(story);
        image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("HungerGames/horse.jpg")));

    }

    void read()
    {
        System.out.println(
        


         "Tributes remaining: 4 and One dead horse \n\n" +

         "Tributes remaining: 4 One dead horse \n\n" +


         "Tributes remaining: 4 One dead horse \n\n" +


        "Dang flabbit my horse. Robby Ray is devastated about his horse. He walks along thee river banks longing for revenge on the man that murdered his horse. Sweet niblets, they dont got vampires in Kentucky. \n\n" +
        


        "Still walking along the river banks Robby Ray find himself face to face with the sparkly murdered. You blinded my horse. You made me put him down. You will pay. You will feel the hands of rage from Kentucky on your disco ball skin. (Robby Ray charges towards Edward.Edward, not fazed by the crazy, tries to lightly push him off, but instead full throttle him. Robby Ray is sent flying. Probably made it to Kentucky." + 

        "Still walking along the river banks Robby Ray find himself face to face with the sparkly murdered. “You blinded my horse. You’ve made me put him down. You will pay. You will feel the hands of rage from Kentucky on your disco ball skin. (Robby Ray charges towards Edward.Edward, not fazed by the crazy, tries to lightly push him off, but instead full throttles him. Robby Ray is sent flying. He probably made it to Kentucky." + 


        "Still walking along the river banks Robby Ray find himself face to face with the sparkly murdered. “You blinded my horse. You’ve made me put him down. You will pay. You will feel the hands of rage from Kentucky on your disco ball skin. (Robby Ray charges towards Edward.Edward, not fazed by the crazy, tries to lightly push him off, but instead full throttles him. Robby Ray is sent flying. He probably made it to Kentucky." + 

  
        "Robby Ray is deceased"
        );
        
        
    }

}