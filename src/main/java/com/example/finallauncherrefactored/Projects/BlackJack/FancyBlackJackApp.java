package com.example.finallauncherrefactored.Projects.BlackJack;

import com.example.finallauncherrefactored.Main;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FancyBlackJackApp {
    Group root = new Group();
    BlackJackGame game = new BlackJackGame(new User(Main.getBlackJackCurrency()));
    BlackJackSoundEffects sound = new BlackJackSoundEffects();

    ArrayList<ImageView> userCardNodes = new ArrayList<ImageView>();
    ArrayList<ImageView> dealerCardNodes = new ArrayList<ImageView>();

    Text userScore = null;
    Text dealerScore = null;
    double mouseX;
    double mouseY;
    public FancyBlackJackApp() throws FileNotFoundException {
    }

    public void start(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        //VBox root = new VBox();
        stage.setTitle("Black Jack");
        //.root.getChildren().add(canvas);
        Scene scene = new Scene(root, 1200, 800);
        scene.setOnMouseMoved(this::handleOnMouseMove);
        scene.setOnMouseClicked(mouseEvent -> {
            try {
                handleOnMouseClick(mouseEvent);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        stage.setOnCloseRequest(a -> {
            if(!Main.getUserName().equals("GUEST")){
                Main.setBlackJackCurrency(game.user.money);
            }
            sound.dealCard.stop();
            sound.flipCard.stop();
            sound.bridgeCards.stop();
            try {
                Main main = new Main();
                Parent root = FXMLLoader.load(Objects.requireNonNull(main.getClass().getResource("MainMenuPG2.fxml")));
                Scene scene2 = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene2);
                window.setOnCloseRequest(e -> {
                    Main.resetAppData();
                    window.close();
                });
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        stage.setScene(scene);
        stage.show();
        startGame();
    }
    void startGame() throws FileNotFoundException{
        removeObjectNodes();
        game.restartGame();
        game.dealCards();
        SequentialTransition animationInitialDeal = animationInitialDeal();
        createDeckNode();
        SequentialTransition start = new SequentialTransition(animationInitialDeal);
        start.setCycleCount(1);
        start.play();
        start.setOnFinished(actionEvent -> beginPlayableGame());
    }
    void beginPlayableGame(){
        removeObjectNodes();
        flipCards();
        addTextNodes();
    }
    void removeObjectNodes(){
        root.getChildren().clear();
        userCardNodes.clear();
        dealerCardNodes.clear();
        addEssentialNodes();
    }
    void addEssentialNodes(){
        Rectangle backgroundRect = new Rectangle(1200, 800, Color.DARKGREEN);
        root.getChildren().add(backgroundRect);
        //user's money
        Text userMoney = new Text(1050,750,"User Money: $"+game.user.money);
        userMoney.setFont((Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 13)));
        userMoney.setFill(Color.WHITE);
        root.getChildren().add(userMoney);
        createDeckNode();
    }
    void addTextNodes(){
        //hit and stand 'buttons'
        Text hitButton = new Text(200,700, "HIT");
        hitButton.setFont((Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 50)));
        hitButton.setFill(Color.WHITE);
        Text standButton = new Text(400, 700, "STAND");
        standButton.setFont((Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 50)));
        standButton.setFill(Color.WHITE);

        //user and dealer scores
        userScore = new Text(310,600,"User's Score:" + game.currentUserHandValue);
        userScore.setFont((Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 13)));
        userScore.setFill(Color.WHITE);
        dealerScore = new Text(305, 300,"Dealer's Score: "+ game.currentDealerHandValue);
        dealerScore.setFont((Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 13)));
        dealerScore.setFill(Color.WHITE);
        root.getChildren().addAll(hitButton, standButton, userScore,dealerScore);
    }
    void createDeckNode(){
        ImageView deckNode = new ImageView(game.deck.cardBackImage);
        deckNode.setFitWidth(150);
        deckNode.setFitHeight(210);
        deckNode.setX(25);
        deckNode.setY(250);
        root.getChildren().add(deckNode);
    }
    void flipCards(){
        removeObjectNodes();
        addTextNodes();
        flipUserCards();
        flipDealerCards();
        sound.flipCard.play();
        try {
            updateScores();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(game.isOver){
            gameOver();
        }
    }
    void flipUserCards(){
        int xPos = 200;
        int additionInt = 100;
        for(Card c : game.user.hand){
            ImageView tempIV = new ImageView(c.image);
            userCardNodes.add(tempIV);
            userCardNodes.get(userCardNodes.size() - 1).setFitWidth(150);
            userCardNodes.get(userCardNodes.size() - 1).setFitHeight(210);
            userCardNodes.get(userCardNodes.size() - 1).setX(xPos);
            userCardNodes.get(userCardNodes.size() - 1).setY(455 - 105);
            root.getChildren().add(userCardNodes.get(userCardNodes.size() - 1));
            xPos+=additionInt;
        }
    }
    void flipDealerCards(){
        int xPos = 200;
        int additionInt = 100;
        if(!game.dealerHandShown){
            ImageView tempCardBack = new ImageView(game.deck.cardBackImage);
            tempCardBack.setFitWidth(150);
            tempCardBack.setFitHeight(210);
            tempCardBack.setX(xPos);
            tempCardBack.setY(175-105);
            root.getChildren().add(tempCardBack);
            ImageView tempIV = new ImageView(game.dealer.hand.get(1).image);
            tempIV.setFitWidth(150);
            tempIV.setFitHeight(210);
            tempIV.setX(xPos + additionInt);
            tempIV.setY(175-105);
            dealerCardNodes.add(tempIV);
            root.getChildren().add(dealerCardNodes.get(dealerCardNodes.size()-1));
        }else{
            for(Card c : game.dealer.hand){
                ImageView tempIV = new ImageView(c.image);
                dealerCardNodes.add(tempIV);
                dealerCardNodes.get(dealerCardNodes.size() - 1).setFitWidth(150);
                dealerCardNodes.get(dealerCardNodes.size() - 1).setFitHeight(210);
                dealerCardNodes.get(dealerCardNodes.size() - 1).setX(xPos);
                dealerCardNodes.get(dealerCardNodes.size() - 1).setY(175 - 105);
                root.getChildren().add(dealerCardNodes.get(dealerCardNodes.size() - 1));
                xPos+=additionInt;
            }
        }
    }
    void updateScores(){
        if(userScore != null){
            userScore.setText("User's Score:" + game.currentUserHandValue);
        }
        if(dealerScore != null){
            dealerScore.setText("Dealer's Score: "+ game.currentDealerHandValue);
        }
    }
    SequentialTransition animationInitialDeal(){
        ArrayList<ImageView> userCardBackNodes = new ArrayList<ImageView>();
        ArrayList<ImageView> dealerCardBackNodes = new ArrayList<ImageView>();
        for(int i = 0; i < game.user.hand.size();i++) {
            userCardBackNodes.add(new ImageView(game.deck.cardBackImage));
            root.getChildren().add(userCardBackNodes.get(i));
            userCardBackNodes.get(i).setFitWidth(150);
            userCardBackNodes.get(i).setFitHeight(210);
            userCardBackNodes.get(i).setX(25);
            userCardBackNodes.get(i).setY(250);
        }
        for(int i = 0; i < game.dealer.hand.size();i++) {
            dealerCardBackNodes.add(new ImageView(game.deck.cardBackImage));
            root.getChildren().add(dealerCardBackNodes.get(i));
            dealerCardBackNodes.get(i).setFitWidth(150);
            dealerCardBackNodes.get(i).setFitHeight(210);
            dealerCardBackNodes.get(i).setX(25);
            dealerCardBackNodes.get(i).setY(250);
        }
        int xPos = 200;
        int i = 0;
        //userPath
        Line uLine = new Line(userCardBackNodes.get(i).getX() + 75,userCardBackNodes.get(i).getY() + 105,xPos + 75, 455);
        PathTransition upt1 = new PathTransition();
        upt1.setCycleCount(1);
        upt1.setDuration(Duration.seconds(1));
        upt1.setPath(uLine);
        upt1.setNode(userCardBackNodes.get(i));
        upt1.setOnFinished(actionEvent -> sound.dealCard.play());

        //dealerPath
        Line dLine = new Line(dealerCardBackNodes.get(i).getX() + 75,dealerCardBackNodes.get(i).getY() + 105,xPos + 75, 200 - 25);
        PathTransition dpt1 = new PathTransition();
        dpt1.setCycleCount(1);
        dpt1.setDuration(Duration.seconds(1));
        dpt1.setPath(dLine);
        dpt1.setNode(dealerCardBackNodes.get(i));
        dpt1.setOnFinished(actionEvent -> sound.dealCard.play());

        i++;
        xPos+=100;

        uLine = new Line(userCardBackNodes.get(i).getX() + 75,userCardBackNodes.get(i).getY() + 105,xPos + 75, 455);
        PathTransition upt2 = new PathTransition();
        upt2.setCycleCount(1);
        upt2.setDuration(Duration.seconds(1));
        upt2.setPath(uLine);
        upt2.setNode(userCardBackNodes.get(i));
        upt2.setOnFinished(actionEvent -> sound.dealCard.play());

        dLine = new Line(dealerCardBackNodes.get(i).getX() + 75,dealerCardBackNodes.get(i).getY() + 105,xPos + 75, 200 - 25);
        PathTransition dpt2 = new PathTransition();
        dpt2.setCycleCount(1);
        dpt2.setDuration(Duration.seconds(1));
        dpt2.setPath(dLine);
        dpt2.setNode(dealerCardBackNodes.get(i));
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2.483));
        pauseTransition.setOnFinished(actionEvent -> sound.dealCard.play());
        SequentialTransition sqt = new SequentialTransition(pauseTransition, upt1, dpt1,upt2,dpt2);
        sqt.setCycleCount(1);
        sound.bridgeCards.play();
        return sqt;
    }
    PathTransition dealUserCard(){
        ImageView downCard = new ImageView(game.deck.cardBackImage);
        downCard.setFitWidth(150);
        downCard.setFitHeight(210);
        downCard.setX(25);
        downCard.setY(250);
        root.getChildren().add(downCard);
        int xPos =200 +( 100 *game.user.hand.size() - 1);
        Line line = new Line(downCard.getX() + 75,downCard.getY() + 105,xPos - 25, 455);
        PathTransition pt = new PathTransition();
        pt.setNode(downCard);
        pt.setDuration(Duration.seconds(.682));
        pt.setPath(line);
        return pt;
    }
    PathTransition dealDealerCard(){
        ImageView downCard = new ImageView(game.deck.cardBackImage);
        downCard.setFitWidth(150);
        downCard.setFitHeight(210);
        downCard.setX(25);
        downCard.setY(250);
        root.getChildren().add(downCard);
        int xPos =200 +( 100 *game.dealer.hand.size() - 1);
        Line line = new Line(downCard.getX() + 75,downCard.getY() + 105,xPos - 25, 175);
        PathTransition pt = new PathTransition();
        pt.setNode(downCard);
        pt.setDuration(Duration.seconds(.682));
        pt.setPath(line);
        return pt;
    }
    void hit(){
        if(!game.dealerHandShown){
            game.updateDealerHand();
            flipCards();
        }
        int temp = game.dealer.hand.size();
        SequentialTransition hits;
        try{
            game.userHit();
            PathTransition userHit = dealUserCard();
            userHit.setCycleCount(1);
            if(temp != game.dealer.hand.size()){
                PathTransition dealerHit = dealDealerCard();
                hits = new SequentialTransition(userHit, dealerHit);
                userHit.setOnFinished(actionEvent -> sound.dealCard.play());
                hits.setCycleCount(1);
                sound.dealCard.play();
            }else{
                hits = new SequentialTransition(userHit);
            }
            hits.play();
            hits.setOnFinished(actionEvent -> flipCards());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    void stand(){
        if(!game.dealerHandShown){
            game.updateDealerHand();
            flipCards();
        }
        int temp = game.dealer.hand.size();
        if(!game.dealerHandShown){
            flipDealerCards();
        }
        try{
            game.userStand();
            if(temp != game.dealer.hand.size()){
                PathTransition dealerHit = dealDealerCard();
                dealerHit.setCycleCount(1);
                sound.dealCard.play();
                dealerHit.play();
                dealerHit.setOnFinished(actionEvent -> flipCards());
                if(game.isOver){
                    gameOver();
                }else{
                    stand();
                }
            }else{
                if(game.isOver){
                    gameOver();
                }else{
                    stand();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    void gameOver() {
        Rectangle gameOverRectangle = new Rectangle(100, 100, Color.YELLOW);
        gameOverRectangle.setX(500);
        gameOverRectangle.setY(300);
        root.getChildren().add(gameOverRectangle);
        Text gameOverText = new Text();
        gameOverText.setText("Game Over");
        gameOverText.setFont((Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 13)));
        gameOverText.setX(510);
        gameOverText.setY(320);
        gameOverText.setFill(Color.BLACK);
        root.getChildren().add(gameOverText);
        Text winnerText = new Text();
        winnerText.setFont((Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 13)));
        if (game.winner == BlackJackGame.Winner.TIE) {
            winnerText.setText(game.winner.toString());
            winnerText.setX(540);
            winnerText.setY(340);
        }else if(game.winner == BlackJackGame.Winner.USER){
            winnerText.setText(game.winner + " WINS!");
            winnerText.setX(506);
            winnerText.setY(350);
        }else if(game.winner == BlackJackGame.Winner.DEALER){
            winnerText.setText(game.winner + " WINS!");
            winnerText.setX(501);
            winnerText.setY(350);
        }
        root.getChildren().add(winnerText);
        Text winConditionText= new Text();
        winConditionText.setFont((Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 13)));
        winConditionText.setFill(Color.BLACK);
        winConditionText.setY(370);
        if(game.winCondition == -10){
            winConditionText.setText("- $10");
            winConditionText.setX(525);
        }else if(game.winCondition == 0){
            winConditionText.setText("");
            winConditionText.setX(520);
        }else if(game.winCondition == 10){
            winConditionText.setText("+ $10");
            winConditionText.setX(525);
        }else if(game.winCondition == 15){
            winConditionText.setText("+ $15");
            winConditionText.setX(525);
        }
        root.getChildren().add(winConditionText);
    }
    SequentialTransition gameOverAnimation(){
        removeObjectNodes();
        int xPos = 200;
        int additionInt = 100;
        ImageView[] userCardBackNodes = new ImageView[game.user.hand.size()];
        for(int i = 0; i < game.user.hand.size();i++){
            ImageView tempIV = new ImageView(game.deck.cardBackImage);
            tempIV.setFitWidth(150);
            tempIV.setFitHeight(210);
            tempIV.setX(xPos);
            tempIV.setY(455 - 105);
            userCardBackNodes[i] = tempIV;
            root.getChildren().add(userCardBackNodes[i]);
            xPos+=additionInt;
        }
        xPos = 200;
        ImageView[] dealerCardBackNodes = new ImageView[game.dealer.hand.size()];
        for(int i = 0; i < game.dealer.hand.size();i++){
            ImageView tempIV = new ImageView(game.deck.cardBackImage);
            tempIV.setFitWidth(150);
            tempIV.setFitHeight(210);
            tempIV.setX(xPos);
            tempIV.setY(175 - 105);
            dealerCardBackNodes[i] = tempIV;
            root.getChildren().add(dealerCardBackNodes[i]);
            xPos+=additionInt;
        }
        PathTransition[] userPathTransitions = new PathTransition[userCardBackNodes.length];
        PathTransition[] dealerPathTransitions = new PathTransition[dealerCardBackNodes.length];
        for(int i = 0; i < userPathTransitions.length;i++){
            Line tempLine1 = new Line(userCardBackNodes[i].getX() + 75, userCardBackNodes[i].getY() + 105,25 + 75,250 + 105);
            PathTransition tempTransition = new PathTransition();
            tempTransition.setCycleCount(1);
            tempTransition.setNode(userCardBackNodes[i]);
            tempTransition.setPath(tempLine1);
            tempTransition.setDuration(Duration.seconds(.682));
            tempTransition.setOnFinished(actionEvent -> sound.dealCard.play());
            userPathTransitions[i] = tempTransition;
        }
        for(int i =0;i< dealerPathTransitions.length;i++){
            Line tempLine2 = new Line(dealerCardBackNodes[i].getX() +75, dealerCardBackNodes[i].getY() +105,25 + 75,250 + 105);
            PathTransition tempTransition = new PathTransition();
            tempTransition.setCycleCount(1);
            tempTransition.setNode(dealerCardBackNodes[i]);
            tempTransition.setPath(tempLine2);
            tempTransition.setDuration(Duration.seconds(.682));
            if(i != dealerPathTransitions.length - 1){
                tempTransition.setOnFinished(actionEvent -> sound.dealCard.play());
            }
            dealerPathTransitions[i] = tempTransition;
        }
        SequentialTransition seqUserTransitions = new SequentialTransition(userPathTransitions);
        SequentialTransition seqDealerTransitions = new SequentialTransition(dealerPathTransitions);
        PauseTransition pause = new PauseTransition(Duration.seconds(.682));
        pause.setOnFinished(actionEvent -> sound.dealCard.play());
        SequentialTransition seq = new SequentialTransition(pause,seqUserTransitions,seqDealerTransitions);
        return seq;
    }
    void handleOnMouseClick(MouseEvent mouseEvent) throws FileNotFoundException {
        if(game.isOver){
            sound.flipCard.play();
            SequentialTransition gameOverAnimation = gameOverAnimation();
            gameOverAnimation.setOnFinished(actionEvent -> {
                try {
                    startGame();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            gameOverAnimation.play();
        }else {
            if (mouseX > 200 && mouseX < 300 &&
                    mouseY > 650 && mouseX < 710) {
                hit();
            } else if (mouseX > 400 && mouseX < 580 &&
                    mouseY > 650 && mouseX < 710) {
                stand();
            }
        }
    }
    void handleOnMouseMove(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();
    }
    class BlackJackSoundEffects{
        // Sound Effects Credit: f4ngy"
        //Bridging Cards - https://freesound.org/people/f4ngy/sounds/240778/
        //Dealing Card - https://freesound.org/people/f4ngy/sounds/240777/
        //Card Flip - https://freesound.org/people/f4ngy/sounds/240776/
        AudioClip dealCard;
        AudioClip flipCard;
        AudioClip bridgeCards;
        BlackJackSoundEffects(){
            try{
                dealCard = new AudioClip(Objects.requireNonNull(Main.class.getResource("Sounds/dealCard.wav")).toExternalForm());
                flipCard = new AudioClip(Objects.requireNonNull(Main.class.getResource("Sounds/flipCard.wav")).toExternalForm());
                bridgeCards = new AudioClip(Objects.requireNonNull(Main.class.getResource("Sounds/bridgeCards.wav")).toExternalForm());
                dealCard.setVolume(.5);
                flipCard.setVolume(1.0);
                bridgeCards.setVolume(.5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
