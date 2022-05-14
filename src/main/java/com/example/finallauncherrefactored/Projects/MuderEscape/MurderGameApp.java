package com.example.finallauncherrefactored.Projects.MuderEscape;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MurderGameApp
{
    Game game;
    GraphicsContext gc;

    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        game = new Game();

        Canvas canvas = new Canvas(game.map.width * game.map.gridsize, game.map.height * game.map.gridsize);
        gc = canvas.getGraphicsContext2D();

        VBox vbox = new VBox();
        vbox.getChildren().add(canvas);

        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(this::handleKey);

        stage.setScene(scene);
        stage.setTitle("Mansion");
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

        GameAnimation animation = new GameAnimation();
        animation.start();

    }

    void handleKey(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.UP)
        {
            game.survivor.goUp();
        }
        else if (key == KeyCode.LEFT)
        {
            game.survivor.goLeft();
        }
        else if (key == KeyCode.DOWN)
        {
            game.survivor.goDown();
        }
        else if (key == KeyCode.RIGHT)
        {
            game.survivor.goRight();
        }
        else if (key == KeyCode.R)
        {
            game = new Game();
        }
    }

    void drawTiles()
    {
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0, game.map.width * game.map.gridsize, game.map.height * game.map.gridsize);

        
        for (Tile t : game.map.tiles)
        {
            if (t.getClass() == Door.class)
            {
                gc.setFill(Color.MOCCASIN);
            }
            else
            {
                gc.setFill(Color.BLACK);
            }
            
            double x = t.x * game.map.gridsize;
            double y = t.y * game.map.gridsize;
            double w = game.map.gridsize;
            double h = game.map.gridsize;
            gc.fillRect(x, y, w, h);
        }
    }

    void drawSurvivor()
    {
        gc.setFill(Color.GREEN);
        double x = game.survivor.x * game.map.gridsize;
        double y = game.survivor.y * game.map.gridsize;
        double w = game.map.gridsize;
        double h = game.map.gridsize;
        gc.fillOval(x, y, w, h);
    }

    void drawMurderer()
    {
        gc.setFill(Color.BROWN);
        for (Murderer chaser : game.chasers)
        {
            double x = chaser.x * game.map.gridsize;
            double y = chaser.y * game.map.gridsize;
            double w = game.map.gridsize;
            double h = game.map.gridsize;
            gc.fillOval(x, y, w, h);
        }
    }

    void drawKey()
    {
        gc.setFill(Color.GOLD);
        if (game.key.inPosession == false)
        {
            double w = game.map.gridsize;
            double h = game.map.gridsize;

            double x = game.key.x * game.map.gridsize;
            double y = game.key.y * game.map.gridsize;
            gc.fillOval(x, y, w, h);
        }
    }

    void drawGame()
    {
        drawTiles();
        drawSurvivor();
        drawMurderer();
        drawKey();
    }

    void renderGameOver()
    {
        gc.setFill(Color.PERU);
        double x = game.map.width * game.map.gridsize * 0.25;
        double y = game.map.height * game.map.gridsize * 0.25;
        double w = game.map.width * game.map.gridsize * 0.5;
        double h = game.map.height * game.map.gridsize * 0.5;

        gc.fillRect(x, y, w, h);

        gc.setFill(Color.WHITE);

        
        gc.setFont(new Font(Font.getFontNames().get(13), 50));
        gc.fillText("GAME OVER", x + 10, y + 100);
    }
    
    void gameWin()
    {
        gc.setFill(Color.PERU);
        double x = game.map.width * game.map.gridsize * 0.25;
        double y = game.map.height * game.map.gridsize * 0.25;
        double w = game.map.width * game.map.gridsize * 0.5;
        double h = game.map.height * game.map.gridsize * 0.5;

        gc.fillRect(x, y, w, h);

        gc.setFill(Color.GREEN);

        
        gc.setFont(new Font(Font.getFontNames().get(13), 50));
        gc.fillText("YOU ESCAPED!", x + 10, y + 100);
    }
    class GameAnimation extends AnimationTimer
    {
        public void handle(long t)
        {
            if (game.gameOver == true)
            {
                renderGameOver();
                return;

            }
            
            if (game.youWin == true)
            {
                gameWin();
                return;
            }
            game.update();
            drawGame();

        }
    }
}