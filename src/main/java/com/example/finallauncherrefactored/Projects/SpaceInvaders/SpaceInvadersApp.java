package com.example.finallauncherrefactored.Projects.SpaceInvaders;

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
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Objects;

public class SpaceInvadersApp
{
    /** The data model of the game. */
    Game game;

    /** Handles drawing to the canvas.*/
    GraphicsContext gc;

    /** Handles the timing of the animation*/
    Animation animation;

    /** Helper class to handle simultaneous key presses */
    GameInputs inputs;

    /**
     * Sets up the game and starts the JavaFX Application.
     *
     * This method is required for any class inheriting from Application. It
     * must accept a "stage" object representing a window for the running program.
     *
     * The stage is loaded with a "scene" object that represents the contents of
     * the window.
     *
     * The scene is loaded with some kind of container that manages the layout of
     * all of the smaller components (buttons, canvas, text fields, etc.) inside
     * the window.
     */
    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        // Set up the data model of our game
        game = new Game();
        inputs = new GameInputs();

        // Set up the canvas and drawing controller
        Canvas canvas = new Canvas(game.width, game.height);
        gc = canvas.getGraphicsContext2D();

        // Create a vertical layout manager to hold our canvas
        VBox vbox = new VBox();
        vbox.getChildren().add(canvas);

        // Create the scene and tell it which methods to use for keyboard events
        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(this::handleKeyPress);
        scene.setOnKeyReleased(this::handleKeyRelease);

        // Display the program window for our game
        stage.setScene(scene);
        stage.setTitle("Space Invaders");
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

        // Create the animation handler and tell it to begin working
        animation = new Animation();
        animation.start();
    }

    /**
     * Responds to a key being pressed on the keyboard.
     *
     * This method accepts a KeyEvent object that is created automatically by JavaFX
     * every time the user presses down a key. The KeyEvent contains, among other
     * things, information about which key was just pressed.
     *
     * Keys that might be held down at the same time (such as spacebar + direction
     * to move while shooting) will give choppy inputs unless we "remember" these
     * inputs. We use a helper class called GameInputs (defined at the bottom of the
     * file) to remember which keys are being held down together.
     */
    void handleKeyPress(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.P) // Press P to pause/unpause
        {
            animation.togglePause();
        }
        if (key == KeyCode.LEFT || key == KeyCode.A)
        {
            inputs.left = true;
        }
        if (key == KeyCode.RIGHT || key == KeyCode.D)
        {
            inputs.right = true;
        }
        if (key == KeyCode.SPACE)
        {
            inputs.space = true;
        }
        if (key == KeyCode.R) // reset the game
        {
            game = new Game();
        }
    }

    /**
     * Responds to a key being released on the keyboard.
     */
    void handleKeyRelease(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.LEFT || key == KeyCode.A)
        {
            inputs.left = false;
        }
        if (key == KeyCode.RIGHT || key == KeyCode.D)
        {
            inputs.right = false;
        }
        if (key == KeyCode.SPACE)
        {
            inputs.space = false;
        }
    }

    /**
     * Handle multiple game inputs to control the ship.
     */
    void handleInputs()
    {
        if (inputs.left && !game.ship.isLeftSideOutOfBounds(0))
        {
            game.ship.moveLeft();
        }
        if (inputs.right && !game.ship.isRightSideOutOfBounds(game.width))
        {
            game.ship.moveRight();
        }
        if (inputs.space)
        {
            game.ship.fireLaser(0, -5);
        }

        inputs.space = false; // Let only one laser be fired per tap of the spacebar
    }

    void renderShip()
    {
        if (game.ship.isAlive)
        {
            double x = game.ship.cx - game.ship.rx;
            double y = game.ship.cy - game.ship.ry;
            double w = game.ship.rx * 2;
            double h = game.ship.ry * 2;

            gc.setFill(Color.CADETBLUE);
            gc.fillRect(x, y, w, h);
        }
    }

    void renderAliens()
    {
        gc.setFill(Color.GREENYELLOW);

        for (Alien a : game.aliens)
        {
            if (a.isAlive)
            {
                double x = a.cx - a.rx;
                double y = a.cy - a.ry;
                double w = a.rx * 2;
                double h = a.ry * 2;
                gc.fillOval(x, y, w, h);
            }
        }
    }

    void renderPlayerLaser(Laser laser)
    {
        if (laser.isFired)
        {
            double x = laser.cx - laser.rx;
            double y = laser.cy - laser.ry;
            double w = laser.rx * 2;
            double h = laser.ry * 2;

            // Flash red for 5 frames then orange for 5 frames
            if (laser.lifetime % 10 > 4)
            {
                gc.setFill(Color.RED);
            }
            else
            {
                gc.setFill(Color.DARKORANGE);
            }
            gc.fillRect(x, y, w, h);
        }
    }

    void renderAlienLaser(Laser laser)
    {
        if (laser.isFired)
        {
            double x = laser.cx - laser.rx;
            double y = laser.cy - laser.ry;
            double w = laser.rx * 2;
            double h = laser.ry * 2;

            // Flash green for 5 frames then greenyellow for 5 frames
            if (laser.lifetime % 10 > 4)
            {
                gc.setFill(Color.GREEN);
            }
            else
            {
                gc.setFill(Color.GREENYELLOW);
            }
            gc.fillRect(x, y, w, h);
        }
    }

    void renderLasers()
    {
        for (Laser L : game.ship.lasers)
        {
            renderPlayerLaser(L);
        }

        for (Alien a : game.aliens)
        {
            for (Laser L : a.lasers)
            {
                renderAlienLaser(L);
            }
        }
    }

    void renderText()
    {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(28));
        gc.fillText("Level: " + game.level +
                "   Score: " + game.score +
                "   Time Bonus: " + game.getTimeBonus() , 10, 30);
    }

    void renderGameOver()
    {
        double x = game.width * 0.25;
        double y = game.height * 0.25;
        double w = game.width * 0.5;
        double h = game.height * 0.5;

        gc.setFill(Color.FIREBRICK);
        gc.fillRect(x, y, w, h);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(45));
        gc.fillText("Game Over!", x + 10, y + 50);
        gc.setFont(new Font(24));
        gc.fillText("Press R to restart.", x + 35, y + 150);
        gc.fillText("Score: " + game.score, x + 35, y + 250);
    }

    void renderGame()
    {
        // Draw a black rectangle over everything.
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, game.width, game.height);

        if (game.noMoreAliens() || (game.level > 1 && game.levelTime < 2))
        {
            gc.setFill(Color.DARKGREY);
            gc.setFont(new Font(50));
            gc.fillText("+" + animation.prevTimeBonus, game.width * 0.4, game.height * 0.6);
            if (game.level % 3 == 0 && game.levelTime < 2)
            {
                gc.fillText("+laser", game.width * 0.4, game.height * 0.7);
            }
        }
        else
        {
            animation.prevTimeBonus = game.getTimeBonus();
        }

        renderLasers();
        renderShip();
        renderAliens();
        renderText();

        if (!game.ship.isAlive)
        {
            renderGameOver();
        }
    }

    /**
     * Helper class to handle multiple keys being pressed simultaneously.
     *
     * For more explanation, see the above comment for handleKeyPress.
     */
    class GameInputs
    {
        boolean left;
        boolean right;
        boolean space;

        GameInputs()
        {
            reset();
        }

        void reset()
        {
            left = false;
            right = false;
            space = false;
        }
    }

    /**
     * Handles the timing of the game's animation.
     */
    class Animation extends AnimationTimer
    {
        private boolean isPaused = false;
        long timeOfLastSecond = 0;
        int prevTimeBonus = 0;

        /**
         * The main game loop.
         *
         * This method will be called ~60 times per second while SpaceInvadersApp runs.
         *
         * When we inherit from AnimationTimer, we are required to write a method named
         * "handle". It must accept a long integer input that represents the timestamp
         * of the current frame in nanoseconds.
         *
         * We can use this input to measure elapsed time and trigger certain effects
         * at a particular time.
         */
        public void handle(long t)
        {
            if (oneSecondHasPassed(t))
            {
                // Add a second to the game's internal clock
                game.levelTime++;
            }

            handleInputs();
            game.update();
            renderGame();
        }

        boolean oneSecondHasPassed(long currentTime)
        {
            // This only happens the very first time the game is started.
            if (timeOfLastSecond == 0)
            {
                timeOfLastSecond = currentTime;
                return false;
            }

            // Determine if 1 second (1 billion nanoseconds) have elapsed
            if (currentTime - timeOfLastSecond >= 1_000_000_000)
            {
                timeOfLastSecond = currentTime; //reset
                return true;
            }
            return false;
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
