package com.example.finallauncherrefactored.Projects.SuperRacer2D;

import com.example.finallauncherrefactored.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.util.Objects;

public class SuperRacerApp
{
    World game;
    GraphicsContext gc;
    Animation animation;
    int level = 1;

    boolean[] actionInputs;

    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        game = new World();
        actionInputs = new boolean[4]; //up, down, left, right

        Canvas canvas = new Canvas(game.width, game.height);
        gc = canvas.getGraphicsContext2D();

        VBox vbox = new VBox();
        vbox.getChildren().add(canvas);

        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(this::handleKeyPress);
        scene.setOnKeyReleased(this::handleKeyRelease);

        stage.setScene(scene);
        stage.setTitle("Super Racer 2d");
        stage.setOnCloseRequest(a -> {
            try {
                Main main = new Main();
                Parent root = FXMLLoader.load(Objects.requireNonNull(main.getClass().getResource("MainMenuPG1.fxml")));
                Scene scene2 = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene2);
                window.setOnCloseRequest(e -> {
                    main.resetAppData();
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

    void handleKeyPress(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.P) // Press P to pause/unpause
        {
            animation.togglePause();
        }
        else if (key == KeyCode.UP || key == KeyCode.W)
        {
            actionInputs[0] = true;
        }
        else if (key == KeyCode.DOWN || key == KeyCode.S)
        {
            actionInputs[1] = true;
        }
        else if (key == KeyCode.LEFT || key == KeyCode.A)
        {
            actionInputs[2] = true;
        }
        else if (key == KeyCode.RIGHT || key == KeyCode.D)
        {
            actionInputs[3] = true;
        }
        else if (key == KeyCode.R || key == KeyCode.SPACE)
        {
            game = new World();
        }

    }

    void handleKeyRelease(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.UP || key == KeyCode.W)
        {
            actionInputs[0] = false;
        }
        else if (key == KeyCode.DOWN || key == KeyCode.S)
        {
            actionInputs[1] = false;
        }
        else if (key == KeyCode.LEFT || key == KeyCode.A)
        {
            actionInputs[2] = false;
        }
        else if (key == KeyCode.RIGHT || key == KeyCode.D)
        {
            actionInputs[3] = false;
        }

    }

    void updateCar()
    {
        if (actionInputs[0]) game.car.accelerate(0, -0.5); //up
        if (actionInputs[1]) game.car.accelerate(0, 0.5); //down
        if (actionInputs[2]) game.car.accelerate(-0.5, 0); //left
        if (actionInputs[3]) game.car.accelerate(0.5, 0); //right
    }

    void renderFinish()
    {
        gc.setFill(Color.GREEN);
        gc.fillRect(game.finish.x, game.finish.y, game.finish.width, game.finish.height);
    }

    void renderCar()
    {
        gc.setFill(Color.RED);
        gc.fillRect(game.car.x, game.car.y, game.car.width, game.car.height);
    }

    void renderOil(Oil o)
    {
        gc.setFill(Color.DARKGREY);
        gc.fillOval(o.x, o.y, o.width, o.height);
    }

    void renderRock(Rock r)
    {
        gc.setFill(Color.KHAKI);
        gc.fillOval(r.x, r.y, r.width, r.height);
    }

    void renderObstacles()
    {
        for (Obstacle obs : game.obstacles)
        {
            if (obs.getClass() == Rock.class)
            {
                renderRock((Rock) obs);
            }
            else if (obs.getClass() == Oil.class)
            {
                renderOil((Oil) obs);
            }
        }
    }

    void renderTime()
    {
        gc.setFill(Color.BLACK);

        gc.fillText("Level: " + level + "          Time: " + animation.elapsedTime, 10, 10);
    }

    void renderGame()
    {
        // Draw a white rectangle over everything.
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, game.width, game.height);

        renderCar();
        renderObstacles();
        renderFinish();
        renderTime();
    }

    class Animation extends AnimationTimer
    {
        private boolean isPaused = false;
        long startTime = 0;
        double elapsedTime = 0;

        /**
         * The main game loop.
         *
         * This method will be called 60 times per second while SuperRacer runs.
         */
        public void handle(long t)
        {
            if (startTime == 0)
            {
                startTime = t; // Remember the value of t when the game begins.
            }

            elapsedTime = (double)(t - startTime)/ 1_000_000_000;

            updateCar();
            game.car.move();
            game.checkCollision();
            game.checkOutOfBounds();
            renderGame();
            if (game.isCarOnFinish())
            {
                game = new World();
                level++;
            }
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
}
