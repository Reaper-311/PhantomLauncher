package com.example.finallauncherrefactored.Projects.TetraShot;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameApp
{
    
    Game game; //Model of game
    GraphicsContext gc; //Drawing to Canvas
    Animation animation; //Timing for Animation
    GameInputs inputs; //Helper class to handle simultaneous key pressses
    GameSprites sprites;
    //Sets up game and javafx application
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


        stage.setTitle("TetraShot");

        stage.setTitle("TETRASHOT");


        stage.setTitle("TETRASHOT");

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
        
        sprites = new GameSprites();
        
        

        // Create the animation handler and tell it to begin working
        animation = new Animation();
        animation.start();
    }

    //Responds to keys being pressed on the keyboard
    void handleKeyPress(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.P) // Press P to pause/unpause
        {
            animation.togglePause();
        }
        if (key == KeyCode.A)
        {
            inputs.left = true;
        }
        if (key == KeyCode.D)
        {
            inputs.right = true;
        }
        if (key == KeyCode.W)
        {
            inputs.up = true;
        }
        if (key == KeyCode.S)
        {
            inputs.down = true;
        }
        if (key == KeyCode.RIGHT)
        {
            inputs.shootRight = true;
        }
        if (key == KeyCode.LEFT)
        {
            inputs.shootLeft = true;
        }
        if (key == KeyCode.UP)
        {
            inputs.shootUp = true;
        }
        if (key == KeyCode.DOWN)
        {
            inputs.shootDown = true;
        }
        if (key == KeyCode.R) // reset the game
        {
            game = new Game();
        }
    }

    //Responds to key being released
    void handleKeyRelease(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.A)
        {
            inputs.left = false;
        }
        if (key == KeyCode.D)
        {
            inputs.right = false;
        }
        if (key == KeyCode.W)
        {
            inputs.up = false;
        }
        if (key == KeyCode.S)
        {
            inputs.down = false;
        }
        if (key == KeyCode.RIGHT)
        {
            inputs.shootRight = false;
        }
        if (key == KeyCode.LEFT)
        {
            inputs.shootLeft = false;
        }
        if (key == KeyCode.UP)
        {
            inputs.shootUp = false;
        }
        if (key == KeyCode.DOWN)
        {
            inputs.shootDown = false;
        }
    }

    // Handles imputs to control the player
    void handleInputs()
    {
        if (inputs.left && !game.player.isLeftSideOutOfBounds(0)) 
        {
            game.player.moveLeft();
        }
        if (inputs.right && !game.player.isRightSideOutOfBounds(game.width))
        {
            game.player.moveRight();
        }
        if (inputs.up && !game.player.isTopSideOutOfBounds(0))
        {
            game.player.moveUp();
        }
        if (inputs.down && !game.player.isBottomSideOutOfBounds(game.height))
        {
            game.player.moveDown();
        }
        if (inputs.shootLeft) 
        {
            game.player.fireLaserLeft(-5, 0);
        }
        if (inputs.shootRight) 
        {
            game.player.fireLaserRight(5, 0);
        }
        if (inputs.shootUp) 
        {
            game.player.fireLaserUp(0, 5);
        }
        if (inputs.shootDown) 
        {
            game.player.fireLaserDown(0, -5);
        }
        inputs.shootLeft = false;// Lets only one laser be fired per tap of spacebar
        inputs.shootRight = false;
        inputs.shootUp = false;
        inputs.shootDown = false;
    }

    // Renders the ship
    void renderPlayer()
    {
        if (game.player.isAlive)
        {
            double x = game.player.cx - game.player.rx;
            double y = game.player.cy - game.player.ry;
            double w = game.player.rx * 2;
            double h = game.player.ry * 2;

            gc.drawImage(sprites.player, x, y, w, h);
        }
    }

    // Renders the Enemies
    void renderEnemies()
    {
        gc.setFill(Color.RED);

        for (Enemy a : game.enemies)
        {
            if (a.isAlive)
            {
                double x = a.cx - a.rx;
                double y = a.cy - a.ry;
                double w = a.rx * 2;
                double h = a.ry * 2;
                gc.drawImage(sprites.enemy, x, y , w, h);
            }
        }
    }

    //Renders the player's lasers
    void renderPlayerLaser(Laser laser)
    {
        if (laser.isFiredVertically)
        {
            double x = laser.cx + laser.rx;
            double y = laser.cy + laser.ry;
            double w = laser.rx * 2;
            double h = laser.ry * 2;

            // Flash red for 5 frames then orange for 5 frames
            if (laser.lifetime % 10 > 4)
            {
                gc.drawImage(sprites.whiteblueRay, x, y, w, h);
            }
            else
            {
                gc.drawImage(sprites.whiteblueRay, x, y, w, h);
            }
            
        }
        
    }
    void renderPlayerLaser2(Laser laser)
    { if (laser.isFiredHorizontally)
        {
            double x = laser.cx + laser.rx;
            double y = laser.cy + laser.ry;
            double w = laser.cx * 2;
            double h = laser.cy * 2;

            
            if (laser.lifetime % 10 > 4)
            {
                gc.drawImage(sprites.whiteblueRay, x, y, w, h);
            }
            else
            {
                gc.drawImage(sprites.whiteblueRay, x, y, w, h);
            }
            
        }
        
    }

    // Renders the laser
    void renderLasers()
    {
        for (Laser L : game.player.lasers)
        {
            renderPlayerLaser(L);
        }
    }
    void renderLasers2()
    {
        for(Laser L : game.player.lasers)
        {
            renderPlayerLaser2(L);
        }
    }

    // Renders the text
    void renderText()
    {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(28));
        gc.fillText("Level: " + game.level +
            "   Score: " + game.score +
            "   Time Bonus: " + game.getTimeBonus() , 10, 30);
    }

    // Renders the game over
    void renderGameOver()
    {
        double x = 0;
        double y = 0;
        double w = game.width;
        double h = game.height;

        gc.setFill(Color.WHITE);
        gc.fillRect(x, y, w, h);
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(50));
        gc.fillText("Game Over", x + 125, y + 100);
        gc.setFont(new Font(40));
        gc.fillText("Press R to restart.", x + 100, y + 400);
        gc.fillText("Score: " + game.score, x + 135, y + 250);
    }

    // Renders the game
    void renderGame()
    {
        // Draw a black rectangle over everything.
        gc.drawImage(sprites.background, 0, 0, game.width, game.height);

        if (game.noMoreEnemies() || (game.level > 1 && game.levelTime < 2))
        {
            gc.setFill(Color.DARKGREY);
            gc.setFont(new Font(50));
            gc.fillText("+" + animation.prevTimeBonus, game.width * 0.4, game.height * 0.6);
            if (game.level % 3 == 0 && game.levelTime < 2)
            {
                gc.fillText("+Ammo", game.width * 0.4, game.height * 0.7);
            }
        }
        else
        {
            animation.prevTimeBonus = game.getTimeBonus();
        }

        renderLasers();
        
        
        
        renderPlayer();
        renderEnemies();
        renderText();

        if (!game.player.isAlive)
        {
            renderGameOver();
        }
    }
    class GameSprites
    {
        Image player;
        Image enemy;
        Image whiteblueRay;
        Image background;

        GameSprites()
        {
            //String pathToFolder = this.getClass().getResource("images/").toExternalForm();

            // Image credits: Kenney Vleugels (www.kenney.nl)
            // https://opengameart.org/content/space-shooter-redux
            player = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("TetraShot/player.png")));
            enemy = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("TetraShot/enemy.png")));
            whiteblueRay = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("TetraShot/whiteblueRay.png")));
            
            
            
            
            // Background image credit: "Background Grid"
            //https://opengameart.org/content/grid-background
            background = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("TetraShot/background.png")));
            
        }
    }

    // Handles multiple keys being pressed at once
    class GameInputs
    {
        boolean left;
        boolean right;
        boolean up;
        boolean down;
        boolean shootRight;
        boolean shootLeft;
        boolean shootUp;
        boolean shootDown;

        GameInputs()
        {
            reset();
        }

        void reset()
        {
            left = false;
            right = false;
            up = false;
            down = false;
           
            shootRight = false;
            shootLeft = false;
            shootUp = false;
            shootDown = false;
        }
    }

    // Timing of game animation
    class Animation extends AnimationTimer
    {
        private boolean isPaused = false;
        long timeOfLastSecond = 0;
        int prevTimeBonus = 0;

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
            if (timeOfLastSecond == 0)
            {
                timeOfLastSecond = currentTime;
                return false;
            }

            // Determines if 1 second has elapsed
            if (currentTime - timeOfLastSecond >= 1_000_000_000)
            {
                timeOfLastSecond = currentTime; 
                return true;
            }
            return false;
        }

        // Allows game to be paused and unpaused
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
    
    
