package com.example.finallauncherrefactored.Projects.BlackJack;

import java.util.ArrayList;

public class Dealer {
    static ArrayList<Card> hand = new ArrayList<Card>();
    Dealer() {}
    int getNumberOfAces(){
        int acesInHand = 0;
        for(Card c : hand){
            if(c.getValue() == 69) {
                acesInHand++;
            }
        }
        return acesInHand;
    }
    int getHandValue() {
        int handValue = 0;
        int acesInHand = getNumberOfAces();
        for (Card c : hand) {
            if(c.getValue() != 69){
                handValue = handValue + c.getValue();
            }
        }
        if(acesInHand > 0){
            if(acesInHand == 1){
                if(handValue + 11 < 17){
                    handValue = handValue + 11;
                }else if(handValue + 11 >= 17 && handValue + 11 <= 21){
                    handValue = handValue + 11;
                }else if(handValue + 11 > 21){
                    handValue = handValue + 1;
                } else{
                    handValue = handValue + 1;
                }
            }else if(acesInHand == 2){
                if(hand.size() == 2){
                    handValue = 12;
                }else if(handValue + 11 + 1 < 17){
                    handValue = handValue + 11 + 1;
                }else if(handValue + 11 + 1 >= 17 && handValue + 11 + 1 <= 21){
                    handValue = handValue + 11 + 1;
                }else{
                    handValue = handValue + 1 + 1;
                }
            }else if(acesInHand == 3){
                if(hand.size() == 3){
                    handValue = 13;
                }else if(handValue + 11 + 1 + 1 < 17){
                    handValue = handValue + 11 + 1 + 1;
                }else if(handValue + 11 + 1 + 1 >= 17 && handValue + 11 + 1 + 1 <= 21){
                    handValue = handValue + 11 + 1 + 1;
                }else{
                    handValue = handValue + 1 + 1 + 1;
                }
            }else if(acesInHand == 4){
                if(hand.size() == 4){
                    handValue = 14;
                }else if(handValue + 11 + 1 + 1 + 1 < 17){
                    handValue = handValue + 11 + 1 + 1 + 1;
                }else if(handValue + 11 + 1 + 1 + 1 >= 17 && handValue + 11 + 1 + 1 + 1 <= 21){
                    handValue = handValue + 11 + 1 + 1 + 1;
                }else{
                    handValue = handValue + 1 + 1 + 1 + 1;
                }
            }
        }
        return  handValue;
    }
    int getStartingHandValue(){
        if(hand.get(1).getValue() == 69){
            return 11;
        }else{
            return hand.get(1).getValue();
        }
    }
}
