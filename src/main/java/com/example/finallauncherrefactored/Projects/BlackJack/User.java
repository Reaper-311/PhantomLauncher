package com.example.finallauncherrefactored.Projects.BlackJack;

import java.util.ArrayList;

public class User {
    public static ArrayList<Card> hand = new ArrayList<Card>();
    int money;
    User() {
        this.money = 0;
    }
    User(int money){
        this.money = money;
    }
    int getNumberOfAces(){
        int acesInHand = 0;
        for(Card c : hand){
            if(c.getValue() == 69) {
                acesInHand++;
            }
        }
        return acesInHand;
    }
    int getHandValue(){
        int handValue = 0;
        int acesInHand = getNumberOfAces();
        for(Card c : hand) {
            if(c.getValue() != 69){
                handValue = handValue + c.getValue();
            }
        }
        if(acesInHand > 0){
            if(acesInHand == 1){
                if (handValue + 11 == 21) {
                    handValue = 21;
                } else if (handValue + 11 > 21) {
                    handValue = handValue + 1;
                } else {
                    handValue = handValue + 11;
                }
            }else if(acesInHand == 2){
                if(hand.size() == 2)
                {
                    handValue = 12;
                }else if(handValue + 11 + 1 == 21){
                    handValue = 21;
                } else if(handValue + 11 + 1 > 21){
                    handValue = handValue + 1 + 1;
                }
            }else if(acesInHand == 3){
                if(hand.size() == 3)
                {
                    handValue = 13;
                }else if(handValue + 11 + 1 + 1 == 21){
                    handValue = 21;
                }else if(handValue + 11 + 1 + 1 > 21){
                    handValue = handValue + 1 + 1 + 1;
                }
            }else if(acesInHand == 4){
                if(hand.size() == 4)
                {
                    handValue = 14;
                }else if(handValue + 11 + 1 + 1 + 1 == 21){
                    handValue = 21;
                }else if(handValue + 11 + 1 + 1 + 1  > 21){
                    handValue = handValue + 1 + 1 + 1 + 1;
                }
            }
        }
        return handValue;
    }
}
