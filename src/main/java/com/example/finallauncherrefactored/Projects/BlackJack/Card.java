package com.example.finallauncherrefactored.Projects.BlackJack;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Card {
    static String[] suits = {"♢" , "♡", "♣", "♠"};
    static String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    String suit;
    String value;
    Image image;

    Card(String suit, String value){
        this.suit = suit;
        this.value = value;
        image = null;
    }
    Card(String suit, String value, boolean inHand){
        this.suit = suit;
        this.value = value;
        if(inHand) {
            image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Images/" + suit + "/" + suit + value + ".png")));
        }
    }
    void printCard(Card inputCard) {
        System.out.println(inputCard.suit + inputCard.value);
    }
    int getValue() {
        if (value.equals("A")) {
            return 69;
        } else if (value.equals("2")) {
            return 2;
        } else if (value.equals("3")) {
            return 3;
        } else if (value.equals("4")) {
            return 4;
        } else if (value.equals("5")) {
            return 5;
        } else if (value.equals("6")) {
            return 6;
        } else if (value.equals("7")) {
            return 7;
        } else if (value.equals("8")) {
            return 8;
        } else if (value.equals("9")) {
            return 9;
        } else if (value.equals("10")) {
            return 10;
        } else if (value.equals("J")) {
            return 10;
        } else if (value.equals("K")) {
            return 10;
        } else if (value.equals("Q")) {
            return 10;
        } else {
            throw new IllegalArgumentException("Error Occurred in Card Value check method in Card class. Card inputted resulted in invalid parameters.");
        }
    }
}
