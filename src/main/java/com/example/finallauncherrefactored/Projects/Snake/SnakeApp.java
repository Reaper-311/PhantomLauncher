package com.example.finallauncherrefactored.Projects.Snake;

import com.example.finallauncherrefactored.Main;
import javafx.animation.AnimationTimer;
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

public class SnakeApp
{
    Window game;
    GraphicsContext gc;
    Animation animation;

    final double GRIDSIZE = 30;

    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        game = new Window(19, 19);

        Canvas canvas = new Canvas(game.width * GRIDSIZE, game.height * GRIDSIZE);
        gc = canvas.getGraphicsContext2D();

        VBox vbox = new VBox();
        vbox.getChildren().add(canvas);

        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(this::handleKey);

        stage.setScene(scene);
        stage.setTitle("Snake");
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

    void handleKey(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.P) // Press P to pause/unpause
        {
            animation.togglePause();
        }
        else if (key == KeyCode.W && game.snake.dir != Snake.Direction.S)
        {
            game.snake.goNorth();
        }
        else if (key == KeyCode.A  && game.snake.dir != Snake.Direction.E)
        {
            game.snake.goWest();
        }
        else if (key == KeyCode.S && game.snake.dir != Snake.Direction.N)
        {
            game.snake.goSouth();
        }
        else if (key == KeyCode.D  && game.snake.dir != Snake.Direction.W)
        {
            game.snake.goEast();
        }
        else if (key == KeyCode.R)
        {
            game = new Window(19, 19);
        }
    }

    void renderSnakeChunk(SnakeChunk chunk)
    {
        gc.setFill(Color.SEAGREEN);
        gc.fillRect(chunk.x * GRIDSIZE, chunk.y * GRIDSIZE, GRIDSIZE, GRIDSIZE);
    }

    void renderApple()
    {
        gc.setFill(Color.RED);
        gc.fillOval(game.apple.x * GRIDSIZE, game.apple.y * GRIDSIZE, GRIDSIZE, GRIDSIZE);
    }

    void renderGameOver()
    {
        gc.setFill(Color.PERU);
        double x = game.width * GRIDSIZE * 0.25;
        double y = game.height * GRIDSIZE * 0.25;
        double w = game.width * GRIDSIZE * 0.5;
        double h = game.height * GRIDSIZE * 0.5;

        gc.fillRect(x, y, w, h);

        gc.setFill(Color.WHITE);

        //gc.setFont((Font.font("", FontWeight.NORMAL, FontPosture.REGULAR, 50)));
        gc.setFont(new Font(Font.getFontNames().get(13), 50));
        gc.fillText("GAME OVER", x + 10, y + 100);
        gc.fillText("Score: " + game.snake.tail.size(), x + 10, y + 200);
    }

    void renderGame()
    {
        // Draw a white rectangle over everything.
        gc.setFill(Color.LIGHTGREY);
        gc.fillRect(0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);

        // Draw the snake's tail and head
        for (SnakeChunk chunk : game.snake.tail)
        {
            renderSnakeChunk(chunk);
        }
        renderSnakeChunk(game.snake.head);

        renderApple();
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
            if (t - lastUpdate > 150000000 - game.snake.tail.size() * 2500000)
            {
                if (!game.isGameOver())
                {
                    game.snake.move();
                    game.eatApple();
                    renderGame();
                }
                else
                {
                    renderGameOver();
                }

                lastUpdate = t;
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
