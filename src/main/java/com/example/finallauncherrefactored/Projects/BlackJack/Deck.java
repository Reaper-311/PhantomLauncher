package com.example.finallauncherrefactored.Projects.BlackJack;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Deck {
    ArrayList<Card> cards = new ArrayList<Card>();

    Image cardBackImage;
    Deck() throws FileNotFoundException { this.cards = createDeck(); }
    ArrayList<Card> createDeck(){
        cardBackImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Images/CardBack.png")));
        for(String suit : Card.suits)
        {
            for(String value : Card.values)
            {
                cards.add(new Card(suit, value));
            }
        }
        return cards;
    }

    Card pullRandomCard(){
        Random r = new Random();
        int cardNumber = r.nextInt(cards.size());
        Card pulledCard = new Card(cards.get(cardNumber).suit, cards.get(cardNumber).value, true);
        cards.remove(cardNumber);
        return pulledCard;
    }
}
