package com.example.finallauncherrefactored.Projects.HungerGames;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class Page7 extends Page
{
    Page7(Story story)
    {
        super(story);
        image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("HungerGames/scaryjungle.jpg")));

    }

    void read()
    {
        System.out.println(
        
        "Tributes remaining: 5 \n\n" +

        "After a sleepless night the tributes awaken. All scattered throughout the jungle in search of food. \n\n" +

        "Mclovin carelessly walks around the jungle looking for his breakfast. Suddenly he comes across a bush full of ripe berries. They look delicious. Mclovin proceeds to grab a handful. But the blue Shrek comes dashing screaming, Boi!! Get out !! This me swamp. Leave the berries here! McLovin, trying to make an ally, gives him the berries and runs away. \n\n" +

        "But he remembers the berries were poisonous and if consumed would lead to death. OH GOD. OH NO. I KILLED SHREKK. He runs as fast as his skinny legs will take him. But he gets a cramp. And cannot make it in time. \n\n" +
        
        "Shrek is deceased. Berries have been consumed. Mclovin has killed the ogre. \n\n" +

        "Alex has found herself a cave to nap in, she proceeds to make a vacation getaway. She wishes Mason, her hunky British werewolf boyfriend were there. But her magic can only do so much. \n\n" +

        "Edward across the jungle is spiraling. He cannot fathom that he has killed an innocent soul. He punishes himself. He stands in the sun thinking to himself, skin of  a killer. But he accidently blinds someone with his glistening skin. Robby Rays horse has been blinded. Unfortunately, Robby Ray makes the heart wrenching choice of putting the horse out of its misery. \n\n"


        );
        
        
    }

}