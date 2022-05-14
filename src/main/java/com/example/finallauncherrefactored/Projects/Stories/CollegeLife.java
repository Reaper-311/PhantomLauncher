package com.example.finallauncherrefactored.Projects.Stories;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;


class CollegeLife extends Event
{
    boolean hasCoffee; 
    Image collegedorm = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/collegedorm.jpeg")));
    Image coffeeshop = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/coffeeshop.jpeg")));
    Image starbuckscoffee = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/starbuckscoffee.jpeg")));
    Image classroom = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/classroom.jpeg")));
    Image confusedboy = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/confusedboy.jpeg")));
    Image girlannoyed = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/girlannoyed.jpeg")));
    Image angry = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/angry.jpeg")));
    Image eyeroll = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/eyeroll.gif")));
    Image rant = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/rant.jpeg")));
    Image yes = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/yes.jpeg")));
    Image no = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/no.jpeg")));
    Image sit = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/sit.png")));
    Image quiet = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/quiet.png")));
    Image yes2 = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/yes2.gif")));
    Image bye = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/bye.gif")));
    Image couple = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Stories/couple.jpeg")));
    

    
    CollegeLife(World world)
    {
        super(world);
        currentEvent = 1;
        hasCoffee = true;
    }
    
    void playEvent (String lastOption)
    {
        if (currentEvent == 1)
        {
            setImage(collegedorm);
            System.out.println ("Welcome to your story where you decide your life!");
            System.out.println ("You are a college girl that just wants to finsh school without distractions.");
            System.out.println ("Too bad you wake up late for your first day of senior year :(");
            System.out.println ("What are you going to do? Get COFFEE and be late to class or go straight to CLASS...");
            System.out.println ("What do you choose?");
            currentEvent = 2;
        }
        else if (currentEvent == 2)
        {
            if (lastOption.toUpperCase().equals("COFFEE"))
            {
                setImage(coffeeshop);
                //setImage(starbuckscoffee);
                System.out.println ("You go to Starbucks and get yourself a Venti Iced Americano with sugar.");
                System.out.println ("Now you are racing to class because you only have 2 minutes left until it starts!");
                System.out.println ("As you turn the corner you run into someone and spill your coffee! Oh No!");
                System.out.println ("You can either get MAD at the guy you ran into or run to CLASS...");
                System.out.println ("What are you going to do?");
                currentEvent = 3;
            }
            else if (lastOption.toUpperCase().equals("CLASS"))// go straight to class instead of get coffee
            {
                setImage(classroom);
                System.out.println ("You run to class across campus.");
                System.out.println ("When you get to class, the only seats left are in the back!");
                System.out.println ("You sit down and someone else late sits next to you with a cup of coffee");
                System.out.println ("You are very annoyed wth him because you haven't had your cup of coffee yet :(");
                System.out.println ("He can tell that you are irritated so he asks you what the problem is");
                System.out.println ("You can either IGNORE him or get ANGRY at him...");
                System.out.println ("What do you choose to do?");
                currentEvent = 4;
            }
                
        }
        else if (currentEvent == 3)
        {
            if (lastOption.toUpperCase().equals("MAD")) // gets mad because of coffee spill
            {
                setImage(confusedboy);
                System.out.println ("You go off on him");
                System.out.println ("You state 'You are thoughtless and clumsy and everything is your fault! All men suck!");
                System.out.println ("You run to class in a fit of fury"); //after the incident
                System.out.println ("You sit down in the back and someone else late sits next to you with a cup of coffee and a stain on his shirt");
                System.out.println ("You recognize him as the guy who spilled your $5 coffee and ruined your nice sweatshirt!");
                System.out.println ("He can feel the tension so he asks you what the problem is");
                System.out.println ("You can either IGNORE him or CONFRONT him after class...");
                System.out.println ("What do you choose to do?");
            }
            else if (lastOption.toUpperCase().equals("CLASS"))// go to class instead of get mad
            {
                setImage(classroom);
                System.out.println ("You decide that geting mad isn't worth it");
                System.out.println ("You clean yourself off and rush to class");
                System.out.println ("You sit down in the back and someone else late sits next to you with a cup of coffee and a stain on his shirt");
                System.out.println ("You recognize him as the guy who spilled your $5 coffee and ruined your nice sweatshirt!");
                System.out.println ("He can feel the tension so he asks you what the problem is");
                System.out.println ("You can either IGNORE him or CONFRONT him after class...");
                System.out.println ("What do you choose to do?");
            }
            currentEvent = 5;
        }
        else if (currentEvent == 4)
        {
            if (lastOption.toUpperCase().equals("IGNORE"))
            {
               setImage(girlannoyed);
               System.out.println ("He asks you what's wrong and you don't say anything at first");
               System.out.println ("He is still waiting for an answer");
               System.out.println ("You say 'Nothing is wrong' with an eye roll");
               System.out.println ("The class ends");
               System.out.println ("You part ways, never knowing each other");
               System.out.println ("THE END");
            }
            else if (lastOption.toUpperCase().equals("ANGRY")) //getting angry in class
            {
                setImage(angry);
                System.out.println ("He asks you what's wrong and you don't say anything at first");
                System.out.println ("He sips his coffee waiting for an answer");
                System.out.println ("You feel incredibly irritated as you feel his eyes on you and you hear each sip he takes");
                System.out.println ("You say to him 'Stop bothering me! I've already had a bad day and I can't deal wih your stupid sipping every 10 seconds!'");
                System.out.println ("He ignores you but knows the conversation isn't over...");
                System.out.println ("After class... he walks up to you and says 'I'm sorry about this morning and I'm sorry you have had a bad day.'");
                System.out.println ("You can either ROLL your eyes or RANT to him? What do you choose?");
                currentEvent = 6;
            }
        }
        else if (currentEvent == 5)
        {
            if (lastOption.toUpperCase().equals("IGNORE"))
            {
                setImage(girlannoyed);
                System.out.println ("After class you look at him, roll your eyes, and walk away");
                System.out.println ("The End.");
            }
            else if (lastOption.toUpperCase().equals("CONFRONT"))
            {
                setImage(angry);
                System.out.println ("After class you go up to this person and say 'You ruined my whole day! I was looking forward to my coffee!");
                System.out.println ("He says 'Can I offer you a new cup of coffee at 2pm today?...' with a smile...");
                System.out.println ("You can say a reluctant YES or NO and walk away? which do you choose?"); //if you choose no the story ends
                currentEvent = 7;
            }
        }
        else if (currentEvent == 6)
        {
            if (lastOption.toUpperCase().equals("ROLL"))
            {
                setImage(eyeroll);
                System.out.println ("You roll your eyes and you leave");
                System.out.println ("The End");
            }
            else if (lastOption.toUpperCase().equals("RANT"))
            {
                setImage(rant);
                System.out.println ("You say 'You have ruined my day! And now I'm going to fail class because I was late!' with a frustrated tone.");
                System.out.println ("'Well I could get you a cup of coffee to make it up to you. I did not mean to ruin your day' he says with a generous tone.");
                System.out.println ("You can choose to ACCEPT his offer for coffee or DENY and walk away? Which do you choose?");
                currentEvent = 8;
            }
        }
        else if (currentEvent == 7)
        {
            if (lastOption.toUpperCase().equals("YES"))
            {
                setImage(yes);
                System.out.println ("Both of you meet at the coffee shop at 2:00 pm");
                System.out.println ("He buys you a cup of your favorite coffee");
                System.out.println ("It is supes yummy");
                System.out.println ("Do you SIT and talk with him longer or until you are QUIET and have awkward silence?");
                currentEvent = 9;
            }
            else if (lastOption.toUpperCase().equals("NO"))
            {
                setImage(no);
                System.out.println ("You reject his offer and walks away");
                System.out.println ("You never make contact with each other again.");
                System.out.println ("The End.");
            }
        }
        else if (currentEvent == 8)
        {
            if (lastOption.toUpperCase().equals("ACCEPT"))
            {
                setImage(yes);
                System.out.println ("You accept his offer and head to the coffee shop");
                System.out.println ("You both meet there at 2:00 pm");
                System.out.println ("He buys you a cup of your favorite coffee and it was supes yummy");
                System.out.println ("Do you SIT and talk with him longer or until you are QUIET and have awkward silence?");
                currentEvent = 9;
            }
            else if (lastOption.toUpperCase().equals("DENY"))
            {
                setImage(no);
                System.out.println ("You deny his offer and leave.");
                System.out.println ("You never make contact with him again and think about what could have been. :(");
                System.out.println ("The End.");
            }
        }
        else if (currentEvent == 9)
        {
            if (lastOption.toUpperCase().equals("SIT"))
            {
                setImage(sit);
                System.out.println ("You decide to sit and talk with him for another hour before you have to leave.");
                System.out.println ("You both have a pretty good time.");
                System.out.println ("The date ends and he asks you out on another one");
                System.out.println ("Do you say YES to the offer or do you GHOST him forever?");
                currentEvent = 10;
            }
            else if (lastOption.toUpperCase().equals("QUIET")) 
            {
                setImage(quiet);
                System.out.println ("You sit there quietly while he talks.");
                System.out.println ("He is kind of annoying and it gets pretty awkward.");
                System.out.println ("The date comes to an end and he asks you out on another one!");
                System.out.println ("Too bad you don't like him and reject his offer.");
                System.out.println ("He is sad, but you guys both mutually part ways.");
                System.out.println ("You never see him again.");
                System.out.println ("The End.");
            }
        }
        else if (currentEvent == 10)
        {
            if (lastOption.toUpperCase().equals("YES")) 
            {
                setImage(yes2);
                System.out.println ("You excitedly say 'Yes' to his offer.");
                System.out.println ("You guys meet again the week after and you have a good time once again.");
                System.out.println ("Flashforward 10 years...");
                System.out.println ("You are living in your dream house in California.");
                System.out.println ("You have an amazing job and guess what...?");
                System.out.println ("You are about to celebrate your 9 year anniversary with your husband who spilled coffee on you!");
                System.out.println ("Enjoy your happy life - The End.");
                setImage(couple);
            }
            else if (lastOption.toUpperCase().equals("GHOST")) 
            {
                setImage(bye);
                System.out.println ("You decided to ghost him");
                System.out.println ("And you never talk to him again");
                System.out.println ("The End.");
            }
        }
    }
}

