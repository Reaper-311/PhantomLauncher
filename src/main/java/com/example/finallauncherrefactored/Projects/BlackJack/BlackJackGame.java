package com.example.finallauncherrefactored.Projects.BlackJack;
import java.io.FileNotFoundException;
public class BlackJackGame  {
    Dealer dealer;
    User user;
    Deck deck;
    boolean isOver;
    boolean dealerHandShown;
    Winner winner;
    int currentUserHandValue;
    int currentDealerHandValue;
    // 0 = tie, 10 = win, -10 = loss, 15 = win off immediate blackJack
    int winCondition;
    enum Winner{USER, DEALER, TIE}

    BlackJackGame() throws FileNotFoundException{
        dealer = new Dealer();
        user = new User();
        deck = new Deck();
        this.isOver = false;
        this.dealerHandShown = false;
        currentUserHandValue = 0;
        currentDealerHandValue = 0;
    }
    BlackJackGame(User user) throws FileNotFoundException{
        dealer = new Dealer();
        this.user = user;
        deck = new Deck();
        this.isOver = false;
        this.dealerHandShown = false;
        currentUserHandValue = 0;
        currentDealerHandValue = 0;
    }
    //Graphical Game Methods
    void dealCards() throws FileNotFoundException{
        user.hand.add(deck.pullRandomCard());
        dealer.hand.add(deck.pullRandomCard());
        user.hand.add(deck.pullRandomCard());
        dealer.hand.add(deck.pullRandomCard());
        currentUserHandValue = user.getHandValue();
        currentDealerHandValue = dealer.getHandValue();
        checkInitialDealWinConditions();
        currentDealerHandValue = dealer.getStartingHandValue();
    }
    void checkInitialDealWinConditions(){
        if(currentUserHandValue == 21 && currentDealerHandValue == 21){
            winner = Winner.TIE;
            winCondition = 0;
            dealerHandShown = true;
            isOver = true;
        }else if(currentUserHandValue == 21){
            winner = Winner.USER;
            winCondition = 15;
            user.money = user.money + 15;
            dealerHandShown = true;
            isOver = true;
        }
    }
    void userHit() throws FileNotFoundException {
        user.hand.add(deck.pullRandomCard());
        currentUserHandValue = user.getHandValue();
        update();
    }
    void userStand() throws FileNotFoundException{
        standCheck();
        update();
    }
    void checkGameOver() {
        if (currentDealerHandValue == 21 && currentUserHandValue == 21) {
            winner = Winner.TIE;
            winCondition = 0;
            isOver = true;
        } else if (currentUserHandValue == 21 && currentDealerHandValue != 21) {
            winner = Winner.USER;
            winCondition = 10;
            user.money = user.money + 10;
            isOver = true;
        } else if (currentDealerHandValue == 21 && currentUserHandValue != 21) {
            winner = Winner.DEALER;
            winCondition = -10;
            user.money = user.money - 10;
            dealerHandShown = true;
            isOver = true;
        } else if (currentUserHandValue > 21 && currentDealerHandValue < currentUserHandValue) {
            winner = Winner.DEALER;
            winCondition = -10;
            user.money = user.money - 10;
            isOver = true;
        } else if (currentDealerHandValue > 21 && currentUserHandValue < currentDealerHandValue) {
            winner = Winner.USER;
            winCondition = 10;
            user.money = user.money + 10;
            isOver = true;
        }
    }
    void standCheck() {
        if(!dealerHandShown){
            updateDealerHand();
        }
        if (currentDealerHandValue >= 17 && currentDealerHandValue < 22) {
            if (currentDealerHandValue == currentUserHandValue) {
                winner = Winner.TIE;
                winCondition = 0;
                this.isOver = true;
            } else if (currentUserHandValue > currentDealerHandValue && currentUserHandValue < 22) {
                winner = Winner.USER;
                winCondition = 10;
                user.money = user.money + 10;
                this.isOver = true;
            }else if(currentDealerHandValue > currentUserHandValue){
                winner = Winner.DEALER;
                winCondition = -10;
                user.money = user.money - 10;
                this.isOver = true;
            }
        }
    }
    void update() throws FileNotFoundException {
        if(!dealerHandShown){
            updateDealerHand();
        }
        if (currentDealerHandValue < 17) {
            dealer.hand.add(deck.pullRandomCard());
            currentDealerHandValue = dealer.getHandValue();
        }
        checkGameOver();
    }
    void restartGame() throws FileNotFoundException {
        isOver = false;
        dealerHandShown = false;
        deck = new Deck();
        user.hand.clear();
        dealer.hand.clear();
    }
    void updateDealerHand(){
        dealerHandShown = true;
        currentDealerHandValue = dealer.getHandValue();
    }
}
