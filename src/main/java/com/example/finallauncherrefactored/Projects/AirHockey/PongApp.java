package com.example.finallauncherrefactored.Projects.AirHockey;

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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PongApp
{
    Pong game;
    GraphicsContext gc;
    Animation animation;

    GameInputs leftInputs, rightInputs;

    double mouseX, mouseY;

    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        game = new Pong();

        leftInputs = new GameInputs();
        rightInputs = new GameInputs();

        Canvas canvas = new Canvas(game.width, game.height);
        gc = canvas.getGraphicsContext2D();

        VBox vbox = new VBox();
        vbox.getChildren().add(canvas);

        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnKeyReleased(this::handleKeyReleased);
        scene.setOnMouseMoved(this::handleMouse);

        stage.setScene(scene);
        stage.setTitle("Air Hockey");
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

    void updatePaddles()
    {
        if (leftInputs.holdingLeft) game.paddleLeft.moveLeft();
        if (leftInputs.holdingUp) game.paddleLeft.moveUp();
        if (leftInputs.holdingRight) game.paddleLeft.moveRight();
        if (leftInputs.holdingDown) game.paddleLeft.moveDown();
        
        if (rightInputs.holdingLeft) game.paddleRight.moveLeft();
        if (rightInputs.holdingUp) game.paddleRight.moveUp();
        if (rightInputs.holdingRight) game.paddleRight.moveRight();
        if (rightInputs.holdingDown) game.paddleRight.moveDown();
    }

    void handleMouse(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    void handleKeyPressed(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.P) // Press P to pause/unpause
        {
            animation.togglePause();
        }
        else if (key == KeyCode.R)
        {
            game = new Pong();
        }
        // player left -------------------------------------------------------
        else if (key == KeyCode.A)
        {
            leftInputs.holdingLeft = true;
        }
        else if (key == KeyCode.W)
        {
            leftInputs.holdingUp = true;
        }
        else if (key == KeyCode.D)
        {
            leftInputs.holdingRight = true;
        }
        else if (key == KeyCode.S)
        {
            leftInputs.holdingDown = true;
            game.paddleLeft.moveDown();
        }
        //player right---------------------------------------------------------
        else if (key == KeyCode.DOWN)
        {
            rightInputs.holdingDown = true;
            game.paddleRight.moveDown();
        }
        else if (key == KeyCode.UP)
        {
            rightInputs.holdingUp = true;
            game.paddleRight.moveUp();
        }
        else if (key == KeyCode.LEFT)
        {
            rightInputs.holdingLeft = true;
            game.paddleRight.moveLeft();
        }
        else if (key == KeyCode.RIGHT)
        {
            rightInputs.holdingRight = true;
            game.paddleRight.moveRight();
        }

    }

    void handleKeyReleased(KeyEvent e)
    {
        KeyCode key = e.getCode();

        // player left -------------------------------------------------------
        if (key == KeyCode.A)
        {
            leftInputs.holdingLeft = false;
        }
        else if (key == KeyCode.W)
        {
            leftInputs.holdingUp = false;
        }
        else if (key == KeyCode.D)
        {
            leftInputs.holdingRight = false;
        }
        else if (key == KeyCode.S)
        {
            leftInputs.holdingDown = false;
        }
        //player right---------------------------------------------------------
        else if (key == KeyCode.DOWN)
        {
            rightInputs.holdingDown = false;
        }
        else if (key == KeyCode.UP)
        {
            rightInputs.holdingUp = false;
        }
        else if (key == KeyCode.LEFT)
        {
            rightInputs.holdingLeft = false;
        }
        else if (key == KeyCode.RIGHT)
        {
            rightInputs.holdingRight = false;
        }
    }

    void renderPaddle()
    {
        gc.setFill(Color.SNOW);
        gc.fillRect(game.paddleLeft.x, game.paddleLeft.y, game.paddleLeft.width, game.paddleLeft.height);
        gc.fillRect(game.paddleRight.x, game.paddleRight.y, game.paddleRight.width, game.paddleRight.height);
        //gc.fillRect(game.paddle.x, game.paddle.y, game.paddle.width, 10);
    }

    void renderPuck()
    {
        gc.setFill(Color.BLACK);
        gc.fillOval(game.puck.x, game.puck.y, game.puck.width, game.puck.height); 
        //gc.fillRect(game.paddle.x, game.paddle.y, game.paddle.width, 5);
    }

    void renderGoal()
    {
        gc.setFill(Color.SNOW);
        gc.fillRect(game.goalLeft.x, game.goalLeft.y, game.goalLeft.width, game.goalLeft.height);
        gc.setFill(Color.SNOW);
        gc.fillRect(game.goalRight.x, game.goalRight.y, game.goalRight.width, game.goalRight.height);
    }

    void renderText()
    {
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Arial", 30));
        gc.fillText(Integer.toString(game.scoreLeft), 400, 50);
        gc.setFont(Font.font("Arial", 30));
        gc.fillText(Integer.toString(game.scoreRight), 530, 50);
    }

    void renderGame()
    {
        // Draw a pink rectangle over everything.
        gc.setFill(Color.LIGHTPINK);
        gc.fillRect(0, 0, game.width, game.height);
        gc.setFill(Color.WHITE);
        gc.fillRect(game.width/2, 0, 2, game.height);

        renderPaddle();
        renderPuck();
        renderGoal();
        renderText();
    }

    class Animation extends AnimationTimer
    {
        private boolean isPaused = false;

        /**
         * The main game loop.
         * 
         * This method will be called 60 times per second while SnakeApp runs.
         */
        public void handle(long t)
        {            
            game.update();
            updatePaddles();
            renderGame();
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
                isPaused = true;
            }
        }
    }

    class GameInputs
    {
        boolean holdingLeft;
        boolean holdingRight;
        boolean holdingUp;
        boolean holdingDown;

        void reset()
        {
            holdingLeft = false;
            holdingRight = false;
            holdingUp = false;
            holdingDown = false;
        }
    }
}