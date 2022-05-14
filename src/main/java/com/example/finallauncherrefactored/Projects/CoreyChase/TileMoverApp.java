package com.example.finallauncherrefactored.Projects.CoreyChase;

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
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class TileMoverApp
{
    CGame game;
    GraphicsContext gc;
    Animation animation;
    Image gameOverImg = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("CoreyChase/gameover.png")));
    Image gameRenderMover = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("CoreyChase/coreyduck.png")));

    final double GRIDSIZE = 80;
    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        game = new CGame();

        Canvas canvas = new Canvas(game.width * GRIDSIZE, game.height * GRIDSIZE);
        gc = canvas.getGraphicsContext2D();

        VBox vbox = new VBox();
        vbox.getChildren().add(canvas);

        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(this::handleKey);

        stage.setScene(scene);
        stage.setTitle("Tile Mover");
        stage.setOnCloseRequest(a -> {
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
    }

    void handleKey(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.P) // Press P to pause/unpause
        {
            animation.togglePause();
        }

        else if (key == KeyCode.LEFT)
        {
            game.mover.move(-1, 0);
        }

        else if (key == KeyCode.RIGHT)
        {
            game.mover.move(1, 0);
        }
        else if (key == KeyCode.R)
        {
            game = new CGame();
        }
    }

    void renderMover()
    {
        double x = game.mover.x * GRIDSIZE;
        double y = game.mover.y * GRIDSIZE;
        double w = GRIDSIZE;
        double h = GRIDSIZE;
        gc.drawImage(gameRenderMover, x, y, w, h);
    }

    void renderGameOver()
    {
        double x = 0;
        double y = 0;
        double w = game.width * GRIDSIZE;
        double h = game.height * GRIDSIZE;

        gc.drawImage(gameOverImg, x, y, w, h);

    }

    void renderTile(Tile t)
    {
        gc.setFill(Color.DARKGRAY);
        double x = t.x * GRIDSIZE;
        double y = t.y * GRIDSIZE;
        double w = GRIDSIZE;
        double h = GRIDSIZE;
        gc.fillRect(x, y, w, h);
    }

    void renderTiles()
    {
        for (Tile t : game.tiles)
        {
            renderTile(t);
        }
    }

    void renderGame()
    {
        // Draw a white rectangle over everything.
        if(game.checkGameOver() == false)
        {
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);

            renderTiles();
            renderMover();
        }
        else
        {
            renderGameOver();

        }
    }


    class Animation extends AnimationTimer
    {
        private boolean isPaused = false;
        long lastUpdate = 0;

        /**
         * The main game loop.
         *
         * This method will be called 60 times per second while SnakeApp runs.
         */
        public void handle(long t)
        {
            game.update();
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
}
