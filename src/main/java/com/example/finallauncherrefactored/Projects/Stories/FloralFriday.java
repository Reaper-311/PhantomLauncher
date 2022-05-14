package com.example.finallauncherrefactored.Projects.Stories;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class FloralFriday extends Event
{
    Image flowershop = new Image (Objects.requireNonNull(Main.class.getResourceAsStream("Stories/flowershop.png")));
    Image bedroom = new Image (Objects.requireNonNull(Main.class.getResourceAsStream("Stories/bedroom.jpg")));
    Image funeral = new Image (Objects.requireNonNull(Main.class.getResourceAsStream("Stories/funeral.jpg")));
    Image cafe = new Image (Objects.requireNonNull(Main.class.getResourceAsStream("Stories/cafe.jpg")));
    Image entryway = new Image (Objects.requireNonNull(Main.class.getResourceAsStream("Stories/entryway.jpg")));
    Image dinner = new Image (Objects.requireNonNull(Main.class.getResourceAsStream("Stories/dinner.jpg")));
    Image wine = new Image (Objects.requireNonNull(Main.class.getResourceAsStream("Stories/wine.jpg")));
    Image patio = new Image (Objects.requireNonNull(Main.class.getResourceAsStream("Stories/patio.jpg")));
    Image fireplace = new Image (Objects.requireNonNull(Main.class.getResourceAsStream("Stories/fireplace.jpg")));

    
    FloralFriday(World world)
    {
        super(world);
        currentEvent = 1;
    }

    void playEvent (String lastOption)
    {
        if (currentEvent == 1)
        {
            System.out.println("Welcome to your story where you decide your life!");
            System.out.println("You are a florist getting ready for work.");
            System.out.println("When you arrive at work, you decide what you want to do.");
            System.out.println("Would you like to arrange FLOWERS in the back or work at the front DESK...");
            System.out.println("What do you choose?");
            currentEvent = 2;
        }
        else  if (currentEvent == 2)
        {
            if (lastOption.toUpperCase().equals("FLOWERS"))// go to the back and arrange flowers
            {
                setImage(flowershop);
                System.out.println("You get to work and decide to work in the back arranging flowers.");
                System.out.println("The first order you have to work on is for a funeral.");
                System.out.println("You start to think about an old friend who just passed away whose funeral you are attendingafter work.");
                System.out.println("After finishing the order, you bring it out to the front desk and catch a quick glimpse of the man picking them up.");
                System.out.println("Type CLOCK OUT to finish the work day.");
                currentEvent = 3;
            }
            else if (lastOption.toUpperCase().equals("DESK"))
            {
                setImage(flowershop);
                System.out.println("You get to work and start giving out orders in the front.");
                System.out.println("One of your customers is a fine gentleman picking up flowers for a funeral.");
                System.out.println("Thinking about your old friend that had just passed away, you tell him 'sorry for your loss' and hand him the flowers.");
                System.out.println("Type CLOCK OUT to finish the work day.");
                currentEvent = 3;
            }
        }      
        else if (currentEvent == 3)
        {
            if (lastOption.toUpperCase().equals("CLOCK OUT"))
            {
                setImage(bedroom);
                System.out.println("You finish work and head home.");
                System.out.println("When you get home, you call your husband and start to get ready for the funeral of your old friend.");
                System.out.println("You pick out two differnt outfits.");
                System.out.println("There is a black pantsuit or a black dress you could wear.");
                System.out.println("Which do you choose? PANTSUIT or DRESS?");
                currentEvent = 4;
            }
        }
        else if (currentEvent == 4)
        {
            if (lastOption.toUpperCase().equals("PANTSUIT"))
            {
                setImage(funeral);
                System.out.println("You put the pantsuit on and finish getting ready.");
                System.out.println("You look at yourself in the mirror and say 'SLAY!'");
                System.out.println("Arriving at the funeral, people start say their condolences.");
                System.out.println("You walk around saying hello to familiar faces, and when you turn your head...");
                System.out.println("It's the guy from the flower shop.");
                System.out.println("You want to go up and say hello...");
                System.out.println("Do you go up BEFORE or AFTER the service?");
                currentEvent = 5;
            }
            else if (lastOption.toUpperCase().equals("DRESS"))
            {
                setImage(funeral);
                System.out.println("You put the dress on and finish getting ready.");
                System.out.println("You look at yourself in the mirror and say 'POP OFF'");
                System.out.println("Arriving at the funeral, people start saying their condolences.");
                System.out.println("You walk around saying hello to familiar faces, and when you turn your head...");
                System.out.println("It's the guy from the flower shop.");
                System.out.println("You want to go up and say hello...");
                System.out.println("Do you go up BEFORE or AFTER the service?");
                currentEvent = 5;
            }
        }
        else if (currentEvent == 5)
        {
            if(lastOption.toUpperCase().equals("BEFORE"))
            {
                setImage(funeral);
                System.out.println("You decide to go up to the guy before the service starts.");
                System.out.println("You tell him that you recognized him from the floral shop and he remebers you.");
                System.out.println("He asks if you know [old friend's name].");
                System.out.println("You say yes and how you went to elementry school together.");
                System.out.println("You start talking and he asked to go to a nearby cafe to talk more.");
                System.out.println("Do you say YES and talk more or NO and go home?");
                currentEvent = 6;
            }
            else if (lastOption.toUpperCase().equals("AFTER"))
            {
                setImage(funeral);
                System.out.println("You decide to wait until after the service to go up to him.");
                System.out.println("When the service is over, you go up to him and notice him crying.");
                System.out.println("You approach him to comfort him and he recognizes you from the shop.");
                System.out.println("You mention that it was your old friend that had passed away.");
                System.out.println("He asks you to go to a nearby cafe to talk more.");
                System.out.println("Do you say YES and talk more or NO and go home?");
                currentEvent = 6;
            }
        }
        else if (currentEvent == 6)
        {
            if(lastOption.toUpperCase().equals("NO"))
            {
                setImage(bedroom);
                System.out.println("You decide not to go to the cafe with him, and go home.");
                System.out.println("You head home and get ready for bed.");
                System.out.println("After getting ready for bed, your husband calls.");
                System.out.println("He asks how you are doing after the funeral.");
                System.out.println("You tell him the service was good and you caught up with an old friend.");
                System.out.println("He tells you he's sorry about your friend and that he will be home soon.");
                System.out.println("You say good night to him and go to bed.");
                System.out.println("The End.");
            }
            else if (lastOption.toUpperCase().equals("YES"))
            {
                setImage(cafe);
                System.out.println("You meet him at the cafe after the service and have a cup of coffee.");
                System.out.println("You two talk about how you both knew your friend.");
                System.out.println("Then you realize you both went to the same school for a few years.");
                System.out.println("He asks what you did after highschool.");
                System.out.println("Now is your chance...");
                System.out.println("Do you mention your husband? YES or NO?");
                currentEvent = 7;
            }
        }
        else if (currentEvent == 7)
        {
            if(lastOption.toUpperCase().equals("YES"))
            {
                System.out.println("You tell him that after highschool you married your highschool sweetheart.");
                System.out.println("He congratulates you. You tell him thank you and that you're really happy with your husband.");
                System.out.println("After a few hours you wrap up the conversations and call it a night.");
                System.out.println("You say your goodbyes and that it was nice to catch up with him.");
                System.out.println("You go home and call your husband and tell him all about your day.");
                System.out.println("The End.");
            }
            else if (lastOption.toUpperCase().equals("NO"))
            {
                setImage(cafe);
                System.out.println("You decide not to mention your husband and suddenly he asks you out on a date.");
                System.out.println("You hesitate to answer...");
                System.out.println("Do you say YES or NO?");
                currentEvent = 8;
            }
        }
        else if (currentEvent == 8)
        {
            if (lastOption.toUpperCase().equals("NO"))
            {
                System.out.println("You say no to going on a date with him.");
                System.out.println("You let him know that you have a husband that you love very much.");
                System.out.println("He appologizes and that he hopes it isn't weird between you two.");
                System.out.println("You tell him it is okay and that you should have told him you had a husband.");
                System.out.println("Now things are a bit awkward, so you tell him you are going to go home.");
                System.out.println("You leave the cafe and head home.");
                System.out.println("When you get home, you call your husband and share each others' days.");
                System.out.println("The End.");
            }
            else if (lastOption.toUpperCase().equals("YES"))
            {
                System.out.println("You say yes to going on a date with him and set a time and place.");
                System.out.println("You two say goodbye for the day and head home.");
                System.out.println("It's the next day and you get ready for work.");
                System.out.println("After work, you get home to get ready for your date.");
                System.out.println("You have two different outfit options...");
                System.out.println("Do you wear a RED dress or a BLUE dress?");
                currentEvent = 9;
            }
        }
        else if(currentEvent == 9)
        {
            if (lastOption.toUpperCase().equals("RED"))
            {
                setImage(entryway);
                System.out.println("The red dress was calling your name.");
                System.out.println("You finish getting ready and look in the mirror.");
                System.out.println("While looking at yourself in the mirror you say 'POP OFF!'");
                System.out.println("Suddenly your doorbell rings and he's here!");
                System.out.println("Type NEXT to see what happens...");
                currentEvent = 10;
            }
            else if (lastOption.toUpperCase().equals("BLUE"))
            {
                setImage(entryway);
                System.out.println("The blue dress was calling your name.");
                System.out.println("You finish getting ready and look in the mirror.");
                System.out.println("While looking in the mirror you say 'SLAY!'");
                System.out.println("Suddenly your doorbell rings and he's here!");
                System.out.println("Type NEXT to see what happends...");
                currentEvent = 10;
            }
        }
        else if (currentEvent == 10)
        {
            if (lastOption.toUpperCase().equals("NEXT"))
            {
                setImage(dinner);
                System.out.println("He picks you up and says 'You look very beautiful'");
                System.out.println("You say thank you and head to the resturant.");
                System.out.println("At the resturant you order a drink or two and have a few appetizers.");
                System.out.println("You guys talk for a few ours and you invite him over for a glass of wine.");
                System.out.println("Type WINE to see what happens next...");
                currentEvent = 11;
            }
        }
        else if (currentEvent == 11)
        {
            if (lastOption.toUpperCase().equals("WINE"))
            {   
                setImage(patio);
                System.out.println("When you get to your house, you open up a bottle of wine and pour two glasses.");
                System.out.println("You guys sit outside on the patio and talk even more.");
                System.out.println("After you both finish your glasses of wine you think about offering another...");
                System.out.println("Do you offer another GLASS of wine or call it a NIGHT?");
                currentEvent = 12;
            }
        }
        else if (currentEvent == 12)
        {
            if (lastOption.toUpperCase().equals("NIGHT"))
            {
                System.out.println("After your glass of wine you decide you are ready for bed.");
                System.out.println("You tell him you are tired and he lets you be.");
                System.out.println("He says goodnight and leaves.");
                System.out.println("You clean up and get ready for bed.");
                System.out.println("The End");
            }
            else if (lastOption.toUpperCase().equals("GLASS"))
            {
                setImage(fireplace);
                System.out.println("You offer him another glass of wine and he gets up to pour another.");
                System.out.println("You two move back inside because it is starting to get cold.");
                System.out.println("He offers to start your fire place because he notices you are cold.");
                System.out.println("With the fire buring in the back, you start to talk more and more...");
                System.out.println("Another hour or two pass by and you hear the doorknob rattling...");
                System.out.println("You both get up. The door opens and it's your husband!");
                System.out.println("He sees you and the other guy and yells at him to get out!");
                System.out.println("The guy runs away out of fear and your husband looks at you confused.");
                System.out.println("You tell him it's not what it looks like and begin to explain that you were old friends and you saw him at the funeral.");
                System.out.println("He's still upset because there is an empty bottle of wine...");
                System.out.println("Type NEXT to see what happens...");
                currentEvent = 13;
            }
        }
        else if (currentEvent == 13)
        {
            if (lastOption.toUpperCase().equals("NEXT"))
            {
                setImage(bedroom);
                System.out.println("You reassure your husband that he was just an old friend and you two were just catching up.");
                System.out.println("You never tell him that you went out on an actual date.");
                System.out.println("After a while, he cools off and forgives you without any further questions.");
                System.out.println("Then you and your husband live Happily Ever After.");
                System.out.println("The End.");
            }
        }
    }
} 
