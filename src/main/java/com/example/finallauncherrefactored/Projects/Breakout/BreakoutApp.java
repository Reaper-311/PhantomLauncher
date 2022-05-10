package com.example.finallauncherrefactored.Projects.Breakout;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Objects;

public class BreakoutApp
{
    Game game;
    GraphicsContext gc;
    Animation animation;

    double mouseX, mouseY;

    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        game = new Game();

        Canvas canvas = new Canvas(game.width, game.height);
        gc = canvas.getGraphicsContext2D();

        VBox vbox = new VBox();
        vbox.getChildren().add(canvas);

        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(this::handleKey);
        scene.setOnMouseMoved(this::handleMouse);

        stage.setScene(scene);
        stage.setTitle("Breakout");
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
        renderGame();
        renderPause();
        animation.togglePause(); // Show game instructions on start.
    }

    void updatePaddle()
    {
        game.paddle.cx = mouseX;
    }

    void handleMouse(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    void handleKey(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.P) // Press P to pause/unpause
        {
            animation.togglePause();
            renderPause();
        }
        else if (key == KeyCode.R)
        {
            game = new Game();
        }
        else if (key == KeyCode.A)
        {
            if (!animation.isPaused && game.lives > 0)
            {
                game.ball.dx -= 3;
                animation.timeLeftThrusterFired = animation.lastUpdate;
            }
        }
        else if (key == KeyCode.D)
        {
            if (!animation.isPaused && game.lives > 0)
            {
                game.ball.dx += 3;
                animation.timeRightThrusterFired = animation.lastUpdate;
            }
        }
    }

    void renderLeftThruster()
    {
        double thrusterSize = 25;
        double x = game.ball.cx;
        double y = game.ball.cy - game.ball.ry * 0.3;
        double w = thrusterSize;
        double h = game.ball.ry * 0.5;

        gc.setFill(Color.ORANGE);
        gc.fillOval(x, y, w, h);
    }

    void renderRightThruster()
    {
        double thrusterSize = 25;
        double x = game.ball.cx - thrusterSize;
        double y = game.ball.cy - game.ball.ry * 0.3;
        double w = thrusterSize;
        double h = game.ball.ry * 0.5;

        gc.setFill(Color.ORANGE);
        gc.fillOval(x, y, w, h);
    }

    void renderPaddle()
    {
        gc.setFill(Color.CADETBLUE);
        double x = game.paddle.cx - game.paddle.rx;
        double y = game.paddle.cy - game.paddle.ry;
        double w = game.paddle.rx * 2;
        double h = game.paddle.ry * 2;
        gc.fillRect(x, y, w, h);
    }

    void renderBlocks()
    {
        Color[] colors = {Color.DARKRED, Color.CRIMSON, Color.RED};
        for (Block block : game.blocks)
        {
            if (block.hp > 0)
            {
                double x = block.cx - block.rx;
                double y = block.cy - block.ry;
                double w = block.rx * 2;
                double h = block.ry * 2;

                gc.setFill(colors[block.hp - 1]);
                gc.fillRect(x, y, w, h);
            }
        }
    }

    void renderBall()
    {
        // Show thruster animation for a quarter of a second.
        if (animation.lastUpdate - animation.timeLeftThrusterFired < 250_000_000)
        {
            renderLeftThruster();
        }
        if (animation.lastUpdate - animation.timeRightThrusterFired < 250_000_000)
        {
            renderRightThruster();
        }

        double x = game.ball.cx - game.ball.rx;
        double y = game.ball.cy - game.ball.ry;
        double w = game.ball.rx * 2;

        gc.setFill(Color.MEDIUMPURPLE);
        gc.fillOval(x, y, w, w);
    }

    void renderText()
    {
        gc.setFill(Color.FIREBRICK);
        gc.setFont(new Font(24));
        gc.fillText("Lives: " + game.lives +
                        "   Level: " + game.level +
                        "\t\t\t\t" +
                        "   Best Combo: " + game.bestCombo +
                        "   Score: " + game.score
                , 20, 20);
    }

    void renderPause()
    {
        double x = game.width * 0.25;
        double y = game.height * 0.2;
        double w = game.width * 0.5;
        double h = game.height * 0.7;

        gc.setFill(Color.FIREBRICK);
        gc.fillRect(x, y, w, h);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font(45));
        gc.fillText("Breakout! ", x + 80, y + 80);
        gc.setFont(new Font(18));
        gc.fillText("Game paused. Press P to unpause.", x + 40, y + 120);
        gc.fillText("Press R to restart the game.", x + 40, y + 160);
        gc.fillText("Move the paddle with the mouse.", x + 40, y + 200);
        gc.fillText("Steer the ball left/right with A and D.", x + 40, y + 240);
        gc.fillText("Combos earn lots of points.", x + 40, y + 280);
    }

    void renderGameOver()
    {
        gc.setFill(Color.FIREBRICK);
        gc.fillRect(game.width * 0.25, game.height * 0.25, game.width * .5, game.height * .5);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(45));
        gc.fillText("Game Over", game.width * 0.35, game.height * 0.4);
        gc.setFont(new Font(24));
        gc.fillText("Press R to restart.", game.width * 0.375, game.height * 0.65);
    }

    void renderGame()
    {
        // Draw a white rectangle over everything.
        gc.setFill(Color.LIGHTGREY);
        gc.fillRect(0, 0, game.width, game.height);

        if (game.combo > 1)
        {
            gc.setFill(Color.DARKGREY);
            gc.setFont(new Font(80));
            gc.fillText("+" + game.combo, game.width * 0.44, game.height * 0.7);
        }

        renderPaddle();
        renderBlocks();
        renderText();
        if (game.lives > 0)
        {
            renderBall();
        }
        else
        {
            renderGameOver();
        }
    }

    void playSounds()
    {
        if (game.sfx.paddleBounce)
        {
        }
        if (game.sfx.wallBounce)
        {
        }
        if (game.sfx.blockBounce)
        {
        }
        if (game.sfx.loseLife)
        {
        }
        if (game.sfx.allClear)
        {
        }
    }

    class Animation extends AnimationTimer
    {
        private boolean isPaused = false;
        long lastUpdate;
        long timeLeftThrusterFired;
        long timeRightThrusterFired;

        /**
         * The main game loop.
         *
         * This method will be called 60 times per second while SnakeApp runs.
         */
        public void handle(long t)
        {
            lastUpdate = t;

            updatePaddle();
            game.update();
            playSounds();
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
