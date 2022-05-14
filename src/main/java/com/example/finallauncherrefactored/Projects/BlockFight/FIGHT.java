package com.example.finallauncherrefactored.Projects.BlockFight;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FIGHT
{
    //JavaFX
    //main hub
    //draw
    GameLogic game;
    GraphicsContext gc;
    Animation animation;
    AI ai;
    Contestant contestant;
    Armory armory;
    Image blockyTexture = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("BlockFight/blocky.png")));
    Image circlesonTexture = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("BlockFight/circleson.png")));
    Image floorTexture = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("BlockFight/floor.png")));
    Image triminionTexture = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("BlockFight/triminionTexture.png")));

    boolean movingLeft;
    boolean movingRight;
    boolean holdingShift;
    //boolean jump;

    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        game = new GameLogic();

        Canvas canvas = new Canvas(game.width, game.height);
        gc = canvas.getGraphicsContext2D();

        VBox vbox = new VBox();
        vbox.getChildren().add(canvas);

        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(this::handleKey);
        scene.setOnKeyReleased(this::handleRelease);
        scene.setOnMouseClicked(this::handleMouse);

        stage.setScene(scene);
        stage.setTitle("Block Fight");
        stage.setOnCloseRequest(a -> {
            try {
                Main main = new Main();
                Parent root = FXMLLoader.load(Objects.requireNonNull(main.getClass().getResource("MainMenuPG4.fxml")));
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
    }

    void handleMouse(MouseEvent e) {
        game.player.clickAttack();
    }

    void handleRelease(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if(key == KeyCode.A) {
            game.player.movingLeft = false;
        }
        else if(key == KeyCode.D) {
            game.player.movingRight = false;
        }
        else if(key == KeyCode.SHIFT) {
            game.player.holdingShift = false;
        }
        else if(key == KeyCode.W) {
            //game.player.isJumping = false;
        }
    }

    void handleKey(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.P) // Press P to pause/unpause
        {
            animation.togglePause();
        }
        else if (key == KeyCode.R) {
            game = new GameLogic();
        }
        else if(key == KeyCode.A) {
            game.player.movingLeft = true;
        }
        else if(key == KeyCode.D) {
            game.player.movingRight = true;
        }
        else if(key == KeyCode.SHIFT) {
            game.player.holdingShift = true;
        }
        else if (key == KeyCode.W) {
            game.player.startJumping();
        }
        else if (key == KeyCode.DIGIT1) {
            game.player.armory = Armory.MELEE;            //armory.EqMagic = false;
                //armory.EqRange = false;
        }
        else if(key == KeyCode.DIGIT2) {
            game.player.armory = Armory.MAGIC;
            //armory.EqMelee = false;
            //armory.EqRange = false;
        }
        else if(key == KeyCode.DIGIT3) {
            game.player.armory = Armory.RANGE;
            //armory.EqMelee = false;
            //armory.EqMagic = false;
        }
    }

    void renderBlockyBlockerson()
    {
        gc.drawImage(blockyTexture, game.player.x, game.player.y - 50, game.player.width, 60);
    }

    void renderCirCircleson() {
        gc.drawImage(circlesonTexture, game.cir.x, game.cir.y - 50, game.player.width, 60);
    }
    
    void renderTriminion()  {
        //gc.drawImage(triminionTexture, game.tri.x, game.tri.y - 50, game.player.width, 60);
    }

    void renderFloor() {
        //gc.drawImage(floorTexture, 0, game.height - 10, 1080, game.height);
        gc.setFill(Color.GRAY);
        gc.fillRect(0, game.height - 10, 1080, game.height);
    }

    void renderGame()
    {
        // Draw a white rectangle over everything.
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, game.width, game.height);

        renderBlockyBlockerson();
    }

    void pauseScreen(){
        gc.setFill(Color.BLUE);
        gc.fillRect(350, 50, 270, 270);
        gc.setFill(Color.WHITE);
        gc.fillText("Game is paused, press p to unpause", 400, 200);
    }

    void drawHealth() {
        gc.setFill(Color.BLACK);
        gc.fillText("Health: " + game.player.health, 1, 10);
        gc.fillText("Cir Health: " + game.cir.health, 1, 20);
        //gc.fillText("Tri Health: " + game.tri.health, 1, 30);
        gc.fillText("Press 1 for melee, 2 for magic, and 3 for ranged attacks", 350, 10);
    }

    void gameOver() {
        if(game.player.health <= 0) {
            animation.stop();
            pauseScreen();
            gc.setFill(Color.RED);
            gc.fillRect(350, 50, 270, 270);
            gc.setFill(Color.WHITE);
            gc.fillText("Game over, you lose", 400, 200);
        }
        else if(game.cir.health <= 0) {
            animation.stop();
            pauseScreen();
            gc.setFill(Color.GREEN);
            gc.fillRect(350, 50, 270, 270);
            gc.setFill(Color.WHITE);
            gc.fillText("Game over, you won!", 400, 200);
        }
    }
    class Animation extends AnimationTimer
    {
        private boolean isPaused = false;

        /**
         * The main game loop.
         * 
         * This method will be called 60 times per second while FIGHT runs.
         */
        public void handle(long t)
        {   
            game.player.updateBlockyBlockerson();
            game.cir.move();
            game.update();
            renderGame();
            renderCirCircleson();
            renderTriminion();
            renderFloor();
            drawHealth();
            gameOver();
        }

        /**
         * Toggles between pausing and unpausing.
         */
        public void togglePause()
        {
            if (isPaused)
            {
                start();
                isPaused = false;
            }
            else
            {
                stop();
                pauseScreen();
                isPaused = true;
            }
        }
    }
}