package com.example.finallauncherrefactored.Projects.ArmWrestling;

import com.example.finallauncherrefactored.Main;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;


public class ArmWrestlingApp
{
    Window game;
    MainMenu mainMenu = new MainMenu(50,25);
    TitleScreen title;
    GraphicsContext gc;
    Animation animation;
    long animationRate = 400_000_000;
    final double GRIDSIZE = 30;
    private int currentImageCount = 1;
    private Image currentImage;
    Image start, playerSelect, armOne, armTwo, armThree, armFour, armOneSelected, armTwoSelected, armThreeSelected,armFourSelected;
    Image frameOne, frameTwo, frameThree, frameFour, frameFive;
    Image oneWins, twoWins;
    Image armOneVarmTwo, armOneVarmThree, armOneVarmFour;
    Image armTwoVarmOne, armTwoVarmThree, armTwoVarmFour;
    Image armThreeVarmOne, armThreeVarmTwo, armThreeVarmFour;
    Image armFourVarmOne, armFourVarmTwo, armFourVarmThree;
    Image p1char1Winning, p1char2Winning, p1char3Winning, p1char4Winning;
    Image p2char1Winning, p2char2Winning, p2char3Winning, p2char4Winning;
    GameSounds sounds = new GameSounds();
    
    public void start(ActionEvent event) throws Exception
    {
        Stage stage = new Stage();
        game = new Window(25, 25);
        title = new TitleScreen(25, 25);
        
        
        
        frameOne = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/frameone.jpg")));
        frameTwo = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/frametwo.jpg")));
        frameThree = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/framethree.jpg")));
        frameFour = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/framefour.jpg")));
        frameFive = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/framefive.jpg")));
        start = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/art-title.png")));
        playerSelect = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/pixil-frame-0-6.png")));
        armOne = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/regular1.jpg")));
        armOneSelected = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/highlight1.jpg")));
        armTwo = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/regular2.jpg")));
        armTwoSelected = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/highlight2.jpg")));
        armThree = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/Messages Image(440553862).jpg")));
        armThreeSelected = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/Messages Image(1920036670).jpg")));
        armFour = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/armfour.jpg")));
        armFourSelected = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/Messages Image(3599452062).jpg")));
        oneWins = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/playerOneWins.png")));
        twoWins = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/playerTwoWins.png")));
        
        //arm one on left
        armOneVarmTwo = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/char1vschar2.png")));
        armOneVarmThree = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/char1vschar3.png")));
        armOneVarmFour = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/char1vschar4.png")));
        
        //arm two on left
        armTwoVarmOne = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/char2vschar1.png")));
        armTwoVarmThree = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/char2vschar3.png")));
        armTwoVarmFour = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/char2vschar4.png")));
        
        //arm three on left
        armThreeVarmOne = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/char3vschar1.png")));
        armThreeVarmTwo = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/char3vschar2.jpg")));
        armThreeVarmFour = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/char3vschar4.png")));
        
        //arm four on left
        armFourVarmOne = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/char4vschar1.png")));
        armFourVarmTwo = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/char4vschar2.png")));
        armFourVarmThree = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/char4vschar3.png")));
        
        //left arm winning
        p2char1Winning = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/p2char1Winning.png")));
        p2char2Winning = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/p2char2Winning.png")));
        p2char3Winning = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/p2char3Winning.png")));
        p2char4Winning = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/p2char4Winning.png")));
        
        //right arm winning
        p1char1Winning = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/p1char1Winning.png")));
        p1char2Winning = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/p1char2Winning.png")));
        p1char3Winning = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/p1char3Winning.png")));
        p1char4Winning = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArmWrestling/Images/p1char4Winning.png")));
        
        Canvas canvas = new Canvas(game.width * GRIDSIZE, game.height * GRIDSIZE);
        gc = canvas.getGraphicsContext2D();
        
        VBox vbox = new VBox();
        vbox.getChildren().add(canvas);
        
        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(this::handleKey);
        
        stage.setScene(scene);
        stage.setTitle("Arm Wrestling");
        stage.setOnCloseRequest(a -> {
            sounds.playerOne.stop();
            sounds.enterToPlay.stop();
            sounds.playerTwo.stop();
            sounds.playerThree.stop();
            sounds.playerFour.stop();
            sounds.gruntOne.stop();
            sounds.gruntTwo.stop();
            sounds.gruntThree.stop();
            sounds.gruntFour.stop();
            sounds.gruntFive.stop();
            sounds.gruntSix.stop();
            sounds.gruntSeven.stop();
            sounds.gruntEight.stop();
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
        stage.show();
        
        animation = new Animation();
        animation.start();
        
        sounds.enterToPlay.play();
       
    }
    
    void handleKey(KeyEvent e)
    {
        KeyCode key = e.getCode();
        
        if (key == KeyCode.ENTER)
        {
            title.noTitle = true;
            
            if (title.noTitle == true && mainMenu.playerOneSelect == true && mainMenu.playerTwoSelect == true)
            {
                mainMenu.noMenu = true;
            }
        }
        
        
        
        //player one character select
        if (key == KeyCode.F && mainMenu.cOneTwo == false)
        {
            mainMenu.cOne = true;
            mainMenu.playerOneSelect = true;
            if (title.noTitle || mainMenu.noMenu)
            {
                sounds.playerOne.play();
            }
            
            
            if (mainMenu.characterTwo == true)
            {
                mainMenu.characterTwo = false;
            }
            if (mainMenu.characterThree == true)
            {
                mainMenu.characterThree = false;
            }
            if (mainMenu.characterFour == true)
            {
                mainMenu.characterFour = false;
            }
        }
        if (key == KeyCode.G && mainMenu.cTwoTwo == false)
        {
            mainMenu.characterTwo = true;
            mainMenu.playerOneSelect = true;
            
            if (title.noTitle || mainMenu.noMenu)
            {
                sounds.playerTwo.play();
            }
            
            if (mainMenu.cOne == true)
            {
                mainMenu.cOne = false;
            }
            if (mainMenu.characterThree == true)
            {
                mainMenu.characterThree = false;
            }
            if (mainMenu.characterFour == true)
            {
                mainMenu.characterFour = false;
            }
        }
        if (key == KeyCode.H && mainMenu.cThreeTwo == false)
        {
            mainMenu.characterThree = true;
            mainMenu.playerOneSelect = true;
            if (title.noTitle || mainMenu.noMenu)
            {
                sounds.playerThree.play();
            }
            if (mainMenu.characterTwo == true)
            {
                mainMenu.characterTwo = false;
            }
            if (mainMenu.cOne == true)
            {
                mainMenu.cOne = false;
            }
            if (mainMenu.characterFour == true)
            {
                mainMenu.characterFour = false;
            }
        }
        if (key == KeyCode.J && mainMenu.cFourTwo == false)
        {
            mainMenu.characterFour = true;
            mainMenu.playerOneSelect = true;
            if (title.noTitle || mainMenu.noMenu)
            {
                sounds.playerFour.play();
            }
            if (mainMenu.characterTwo == true)
            {
                mainMenu.characterTwo = false;
            }
            if (mainMenu.characterThree == true)
            {
                mainMenu.characterThree = false;
            }
            if (mainMenu.cOne == true)
            {
                mainMenu.cOne = false;
            }
        }
        
        //player two character select
        if (key == KeyCode.C && mainMenu.cOne == false)
        {
            mainMenu.cOneTwo = true;
            mainMenu.playerTwoSelect = true;
            if (title.noTitle || mainMenu.noMenu)
            {
                sounds.playerOne.play();
            }
            if (mainMenu.cTwoTwo == true)
            {
                mainMenu.cTwoTwo = false;
            }
            if (mainMenu.cThreeTwo == true)
            {
                mainMenu.cThreeTwo = false;
            }
            if (mainMenu.cFourTwo == true)
            {
                mainMenu.cFourTwo = false;
            }
        }
        if (key == KeyCode.V && mainMenu.characterTwo == false)
        {
            mainMenu.cTwoTwo = true;
            mainMenu.playerTwoSelect = true;
            if (title.noTitle || mainMenu.noMenu)
            {
                sounds.playerTwo.play();
            }
            if (mainMenu.cOneTwo == true)
            {
                mainMenu.cOneTwo = false;
            }
            if (mainMenu.cThreeTwo == true)
            {
                mainMenu.cThreeTwo = false;
            }
            if (mainMenu.cFourTwo == true)
            {
                mainMenu.cFourTwo = false;
            }
        }
        if (key == KeyCode.B && mainMenu.characterThree == false)
        {
            mainMenu.cThreeTwo = true;
            mainMenu.playerTwoSelect = true;
            if (title.noTitle || mainMenu.noMenu)
            {
                sounds.playerThree.play();
            }
            if (mainMenu.cTwoTwo == true)
            {
                mainMenu.cTwoTwo = false;
            }
            if (mainMenu.cOneTwo == true)
            {
                mainMenu.cOneTwo = false;
            }
            if (mainMenu.cFourTwo == true)
            {
                mainMenu.cFourTwo = false;
            }
        }
        if (key == KeyCode.N && mainMenu.characterFour == false)
        {
            mainMenu.cFourTwo = true;
            mainMenu.playerTwoSelect = true;
            if (title.noTitle || mainMenu.noMenu)
            {
                sounds.playerFour.play();
            }
            if (mainMenu.cTwoTwo == true)
            {
                mainMenu.cTwoTwo = false;
            }
            if (mainMenu.cThreeTwo == true)
            {
                mainMenu.cThreeTwo = false;
            }
            if (mainMenu.cOneTwo == true)
            {
                mainMenu.cOneTwo = false;
            }
        }
        
        
        if (key == KeyCode.O && mainMenu.noMenu == true)
        {
            
            if (game.playerOne.counter == 0 || game.playerOne.counter % 2 == 0)
            {
                game.playerOne.increasePoints();
            }
            game.playerOne.addToComboO();
            game.playerOne.realComboCheck();
        }
        if (key == KeyCode.P && mainMenu.noMenu == true)
        {
            
            if (game.playerOne.counter == 0 || game.playerOne.counter % 2 != 0)
            {
                game.playerOne.increasePoints();
            }
            game.playerOne.addToComboP();
            game.playerOne.realComboCheck();
        }
        
        if (key == KeyCode.Q && mainMenu.noMenu == true)
        {
            
            if (game.playerTwo.counter == 0 || game.playerTwo.counter % 2 == 0)
            {
                game.playerTwo.increasePoints();
            }
            game.playerTwo.addToComboQ();
            game.playerTwo.realComboCheck();
        }
        if (key == KeyCode.W && mainMenu.noMenu == true)
        {
            
            if (game.playerTwo.counter == 0 || game.playerTwo.counter % 2 != 0)
            {
                game.playerTwo.increasePoints();
            }
            game.playerTwo.addToComboW();
            game.playerTwo.realComboCheck();

        }
        if (key == KeyCode.SPACE)
        {
            game = new Window(25, 25);
        }
        }
    
    void renderGame()
    {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
        
        if (mainMenu.cOne == true && mainMenu.cTwoTwo == true)
        {
            gc.drawImage(armTwoVarmOne, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            if (game.playerOne.counter > game.playerTwo.counter + 20)
            {
                gc.drawImage(p1char1Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
            if (game.playerTwo.counter > game.playerOne.counter + 20)
            {
                gc.drawImage(p2char2Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
        }
        if (mainMenu.cOne == true && mainMenu.cThreeTwo == true)
        {
            gc.drawImage(armThreeVarmOne, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            if (game.playerOne.counter > game.playerTwo.counter + 20)
            {
                gc.drawImage(p1char1Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
            if (game.playerTwo.counter > game.playerOne.counter + 20)
            {
                gc.drawImage(p2char3Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
        }
        if (mainMenu.cOne == true && mainMenu.cFourTwo == true)
        {
            gc.drawImage(armFourVarmOne, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            if (game.playerOne.counter > game.playerTwo.counter + 20)
            {
                gc.drawImage(p1char1Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
            if (game.playerTwo.counter > game.playerOne.counter + 20)
            {
                gc.drawImage(p2char4Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
        }
        if (mainMenu.characterTwo == true && mainMenu.cOneTwo == true)
        {
            gc.drawImage(armOneVarmTwo, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            if (game.playerOne.counter > game.playerTwo.counter + 20)
            {
                gc.drawImage(p1char2Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
            if (game.playerTwo.counter > game.playerOne.counter + 20)
            {
                gc.drawImage(p2char1Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
        }
        if (mainMenu.characterTwo == true && mainMenu.cThreeTwo == true)
        {
            gc.drawImage(armThreeVarmTwo, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            if (game.playerOne.counter > game.playerTwo.counter + 20)
            {
                gc.drawImage(p1char2Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
            if (game.playerTwo.counter > game.playerOne.counter + 20)
            {
                gc.drawImage(p2char3Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
        }
        if (mainMenu.characterTwo == true && mainMenu.cFourTwo == true)
        {
            gc.drawImage(armFourVarmTwo, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            if (game.playerOne.counter > game.playerTwo.counter + 20)
            {
                gc.drawImage(p1char2Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
            if (game.playerTwo.counter > game.playerOne.counter + 20)
            {
                gc.drawImage(p2char4Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
        }
        if (mainMenu.characterThree == true && mainMenu.cOneTwo == true)
        {
            gc.drawImage(armOneVarmThree, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            if (game.playerOne.counter > game.playerTwo.counter + 20)
            {
                gc.drawImage(p1char3Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
            if (game.playerTwo.counter > game.playerOne.counter + 20)
            {
                gc.drawImage(p2char1Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
        }
        if (mainMenu.characterThree == true && mainMenu.cTwoTwo == true)
        {
            gc.drawImage(armTwoVarmThree, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            if (game.playerOne.counter > game.playerTwo.counter + 20)
            {
                gc.drawImage(p1char3Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
            if (game.playerTwo.counter > game.playerOne.counter + 20)
            {
                gc.drawImage(p2char2Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
        }
        if (mainMenu.characterThree == true && mainMenu.cFourTwo == true)
        {
            gc.drawImage(armFourVarmThree, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            if (game.playerOne.counter > game.playerTwo.counter + 20)
            {
                gc.drawImage(p1char3Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
            if (game.playerTwo.counter > game.playerOne.counter + 20)
            {
                gc.drawImage(p2char4Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
        }
        if (mainMenu.characterFour == true && mainMenu.cOneTwo == true)
        {
            gc.drawImage(armOneVarmFour, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            if (game.playerOne.counter > game.playerTwo.counter + 20)
            {
                gc.drawImage(p1char4Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
            if (game.playerTwo.counter > game.playerOne.counter + 20)
            {
                gc.drawImage(p2char1Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
        }
        if (mainMenu.characterFour == true && mainMenu.cTwoTwo == true)
        {
            gc.drawImage(armTwoVarmFour, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            if (game.playerOne.counter > game.playerTwo.counter + 20)
            {
                gc.drawImage(p1char4Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
            if (game.playerTwo.counter > game.playerOne.counter + 20)
            {
                gc.drawImage(p2char2Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
        }
        if (mainMenu.characterFour == true && mainMenu.cThreeTwo == true)
        {
            gc.drawImage(armThreeVarmFour, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            if (game.playerOne.counter > game.playerTwo.counter + 20)
            {
                gc.drawImage(p1char4Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
            if (game.playerTwo.counter > game.playerOne.counter + 20)
            {
                gc.drawImage(p2char3Winning, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
            }
        }
        
        gc.setFill(Color.BLACK);
        gc.setFont((Font.font("", FontWeight.NORMAL, FontPosture.REGULAR, 30)));
        gc.fillText("Player One Score: " + game.playerOne.counter, game.width + 430, game.height + 80);
        gc.fillText("Player Two Score: " + game.playerTwo.counter, game.width + 40, game.height + 80);
    }
    
    void renderGameOver()
    {
        if (game.isGameOver() == true && game.playerOne.counter > game.playerTwo.counter)
        {
            gc.drawImage(oneWins, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
        }
        if (game.isGameOver() == true && game.playerTwo.counter > game.playerOne.counter)
        {
            gc.drawImage(twoWins, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
        }
    }
    
    void renderTitleMenu()
    {
        //this is where the title image will go
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
        
        gc.drawImage(currentImage, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
        gc.drawImage(start, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
        
        gc.setFill(Color.RED);
        gc.fillRect(200, 555, 400, 55);
        gc.setFill(Color.WHITE);
        gc.setFont((Font.font("", FontWeight.NORMAL, FontPosture.REGULAR, 40)));
        gc.fillText("Press ENTER to play!", game.width + 200, game.height + 570);
    }
    
    boolean grunting() 
    {
        Random rand = new Random();
        int guess = rand.nextInt(300);
        
        final int GRUNT_0 = 3;
        final int GRUNT_1 = 25;
        final int GRUNT_2 = 69;
        final int GRUNT_3 = 7;
        final int GRUNT_4 = 84;
        final int GRUNT_5 = 46;
        final int GRUNT_6 = 12;
        final int GRUNT_7 = 99;
        
        if (guess == GRUNT_0) 
        {
            sounds.gruntOne.play();
            return true;
        } else if (guess == GRUNT_1) {
            sounds.gruntTwo.play();
            return true;
        }else if (guess == GRUNT_2) {
            sounds.gruntThree.play();
            return true;
        }else if (guess == GRUNT_3) {
            sounds.gruntFour.play();
            return true;
        }else if (guess == GRUNT_4) {
            sounds.gruntFive.play();
            return true;
        }else if (guess == GRUNT_5) {
            sounds.gruntSix.play();
            return true;
        }else if (guess == GRUNT_6) {
            sounds.gruntSeven.play();
            return true;
        }else if (guess == GRUNT_7) {
            sounds.gruntEight.play();
            return true;
        }
        
        return false;
    }
    
    void renderMainMenu()
    {
        //this is where the players can pick their arms, main menu should be its own class
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
        
        gc.drawImage(playerSelect, 0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);
        
        gc.drawImage(armOne, 110, 210, 150, 150);
        gc.drawImage(armTwo, 450, 210, 150, 150);
        gc.drawImage(armThree, 110, 465, 150, 150);
        gc.drawImage(armFour, 450, 465, 150, 150);
        
        gc.setFill(Color.BLACK);
        gc.fillRect(300, 210, 120, 410);
        
        gc.setFill(Color.WHITE);
        gc.setFont((Font.font("", FontWeight.NORMAL, FontPosture.REGULAR, 35)));
        gc.fillText("1: F / C", 310, 250);
        gc.fillText("2: G / V", 310, 350);
        gc.fillText("3: H / B", 310, 500);
        gc.fillText("4: J / N", 310, 580);
        
        if (mainMenu.cOne == true  || mainMenu.cOneTwo == true)
        {
            gc.drawImage(armOneSelected, 110, 210, 150, 150);
            
        }
        
        if (mainMenu.characterTwo == true || mainMenu.cTwoTwo == true)
        {
            gc.drawImage(armTwoSelected, 450, 210, 150, 150);
            
        }
        
        if (mainMenu.characterThree == true || mainMenu.cThreeTwo == true)
        {
            gc.drawImage(armThreeSelected, 110, 465, 150, 150);
            
        }
        
        if (mainMenu.characterFour == true || mainMenu.cFourTwo == true)
        {
            gc.drawImage(armFourSelected, 450, 465, 150, 150);
            
        }
        
        if (mainMenu.playerOneSelect == true && mainMenu.playerTwoSelect == true)
        {
            gc.setFill(Color.RED);
            gc.fillRect(180, 680, 400, 55);
            gc.setFill(Color.WHITE);
            gc.setFont((Font.font("", FontWeight.NORMAL, FontPosture.REGULAR, 40)));
            gc.fillText("Press ENTER to play!", game.width + 185, game.height + 695);
        }
        
    }
    
    class GameSounds
    {
        AudioClip playerOneWins;
        AudioClip gruntOne;
        AudioClip gruntTwo;
        AudioClip gruntThree;
        AudioClip gruntFour;
        AudioClip gruntFive;
        AudioClip gruntSix;
        AudioClip gruntSeven;
        AudioClip gruntEight;
        AudioClip playerOne;
        AudioClip playerTwo;
        AudioClip playerThree;
        AudioClip playerFour;
        AudioClip enterToPlay;
        
        GameSounds()
        {
            try 
            {
                playerOneWins = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/playerOneWins.wav").toExternalForm());
                gruntOne = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/gruntingOne.wav").toExternalForm());
                gruntTwo = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/gruntingTwo.wav").toExternalForm());
                gruntThree = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/gruntingThree.wav").toExternalForm());
                gruntFour = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/gruntingFour.wav").toExternalForm());
                gruntFive = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/gruntingFive.wav").toExternalForm());
                gruntSix = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/gruntingSix.wav").toExternalForm());
                gruntSeven = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/gruntingSeven.wav").toExternalForm());
                gruntEight = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/gruntingEight.wav").toExternalForm());
                playerOne = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/playerOne.wav").toExternalForm());
                playerTwo = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/playerTwo.wav").toExternalForm());
                playerThree = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/playerThree.wav").toExternalForm());
                playerFour = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/playerFour.wav").toExternalForm());
                enterToPlay = new AudioClip(Main.class.getResource("ArmWrestling/Sounds/enterToPlay.wav").toExternalForm());
                
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }
    
    class Animation extends AnimationTimer
    {
        private long lastUpdate = 0;
        private final long SECOND = 1_000_000_000;
        private long comboStart = 0;
        private long lastGrunt = 0;
        boolean hasPlayedPlayerOneWinsSound = false;
        public void handle(long now)
        {
            renderTitleMenu();
            
            
            if (now - lastUpdate > SECOND)
            {
                if (currentImageCount == 1)
                {
                    currentImage = frameOne;
                    currentImageCount++;
                    lastUpdate = now;
                }
                if (currentImageCount == 2)
                {
                    currentImage = frameTwo;
                    currentImageCount++;
                    lastUpdate = now;
                }
                if (currentImageCount == 3)
                {
                    currentImage = frameThree;
                    currentImageCount++;
                    lastUpdate = now;
                }
                if (currentImageCount == 4)
                {
                    currentImage = frameFour;
                    currentImageCount++;
                    lastUpdate = now;
                }
                if (currentImageCount == 5)
                {
                    currentImage = frameFive;
                    currentImageCount = 1;
                    lastUpdate = now;
                }
            }
            
            if (title.noTitle)
            {
                renderMainMenu();
            }
            
            if (mainMenu.noMenu)
            {
                renderGame();
                
                if ((now - lastGrunt > SECOND || lastGrunt == 0) && game.isGameOver() == false) {
                    if (grunting()) {
                    lastGrunt = now;
                }
                }
        
            }
            
            
            
            if (mainMenu.noMenu) {
               if (now - game.playerOne.alternationStart < SECOND * 2) {
                if (game.playerOne.isAlternating)
                    {
                        
                        gc.setFill(Color.BLACK);
                        gc.setFont((Font.font("", FontWeight.NORMAL, FontPosture.REGULAR, 30)));
                        gc.fillText("COMBO!!", game.width + 50, game.height + 300);
                    }
            }
            if (now - game.playerOne.alternationStart < SECOND * 2) {
                if (game.playerTwo.isAlternating)
                    {
                        
                        gc.setFill(Color.BLACK);
                        gc.setFont((Font.font("", FontWeight.NORMAL, FontPosture.REGULAR, 30)));
                        gc.fillText("COMBO!!", game.width + 50, game.height + 400);
                    }
            }
            }
            
            
            
            if (game.isGameOver() == true)
            {
                renderGameOver();
            }
            if (game.isGameOver() == true && !hasPlayedPlayerOneWinsSound)
            {
                sounds.playerOneWins.play();
                hasPlayedPlayerOneWinsSound = true;
            }
            
            
        }
    }
}