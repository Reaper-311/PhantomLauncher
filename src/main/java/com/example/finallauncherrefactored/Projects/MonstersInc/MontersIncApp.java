package com.example.finallauncherrefactored.Projects.MonstersInc;

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
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MontersIncApp
{
    Map game;
    Button play, pause;
    GraphicsContext gc;
    Animation animation;

    double tileSize = 30;

    public void start(ActionEvent event)
    {
        Stage stage = new Stage();

        game = new Map();

        Canvas canvas = new Canvas(game.width * tileSize, game.height * tileSize);
        gc = canvas.getGraphicsContext2D();

        VBox vbox = new VBox();
        vbox.getChildren().add(canvas);

        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(this::handleKey);

        stage.setScene(scene);
        stage.setTitle("Monsters Inc");
        stage.setOnCloseRequest(a -> {
            try {
                Main main = new Main();
                Parent root = FXMLLoader.load(Objects.requireNonNull(main.getClass().getResource("MainMenuPG3.fxml")));
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

        play = new Button("Play");
        pause = new Button("Pause");

        animation = new Animation();

        animation.start();

    }

    void handleKey(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.P) 
        {
            animation.togglePause();
        }
        else if (key == KeyCode.W)
        {
            game.arlo.goNorth();
        }

        else if (key == KeyCode.A)
        {
            game.arlo.goWest();
        }
        else if (key == KeyCode.S)
        {
            game.arlo.goSouth();
        }
        else if (key == KeyCode.D)
        {
            game.arlo.goEast();
        }
        else if (key == KeyCode.N)
        {
            game = new Map();
        }
    }

    void renderArlo()
    {
        gc.setFill(Color.ANTIQUEWHITE);

        double x = game.arlo.x *tileSize;
        double y = game.arlo.y *tileSize;

        gc.fillRect(x, y, tileSize, tileSize);
    }

    void renderGhouls()
    {
        gc.setFill(Color.DARKSEAGREEN);

        for (Ghouls g : game.ghouls)
        {
            gc.fillOval(g.x *tileSize, g.y *tileSize, tileSize, tileSize);
        }
    }

    void renderKey()
    {
        gc.setFill(Color.GOLD);

        double x = game.key.x *tileSize;
        double y = game.key.y *tileSize;

        gc.fillOval(x, y, tileSize, tileSize);
    }

    void renderHealthBar()
    {
        gc.setFill(Color.RED);

        double x = game.healthbar.x * tileSize;
        double y = game.healthbar.y * tileSize;

        gc.fillRect(x, y, tileSize, tileSize);
    }

    void renderVent()
    {
        gc.setFill(Color.GRAY);

        double x = game.vent.x * tileSize;
        double y = game.vent.y * tileSize;

        gc.fillRect(x, y, tileSize, tileSize);
    }

    void renderWalls()
    {
        gc.setFill(Color.BLACK);

        for (Walls w : game.walls)
        {
            gc.fillOval(w.x * tileSize, w.y * tileSize, tileSize, tileSize);
        }
    }

    void renderGame()
    {
        gc.setFill(Color.DARKBLUE);
        gc.fillRect(0, 0, game.width *tileSize, game.height *tileSize);

        renderArlo();
        renderGhouls();
        renderKey();
        renderVent();
        renderWalls();
        renderHealthBar();
    }

    class Animation extends AnimationTimer
    {
        private boolean isPaused = false;
        long lastUpdate = 0;

        public void handle(long t)
        {            
            if(t - lastUpdate > 100000000)
            {
                if(!game.isGameOver())
                {
                    game.update();
                    renderGame();
                }
                lastUpdate = t;
            }
        }

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