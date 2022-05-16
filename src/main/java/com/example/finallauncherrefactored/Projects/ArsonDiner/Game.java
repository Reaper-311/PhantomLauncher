package com.example.finallauncherrefactored.Projects.ArsonDiner;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Game
{
    World game;
    GraphicsContext gc;
    Animation animation;
    Random random = new Random();
    int level = 1;
    //Level Images
    Image background = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArsonDiner/pics/diner.png")));
    Image counter = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArsonDiner/pics/counter.png")));
    Image endGame = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArsonDiner/pics/L.png")));
    //Food Images
    Image milk = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArsonDiner/pics/melk.png")));
    Image burger = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArsonDiner/pics/burg.png")));
    Image muffin = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArsonDiner/pics/mufn.png")));
    //Player Image
    Image image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArsonDiner/pics/waiter.png")));
    //Customer Images
    Image customerBird = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArsonDiner/pics/smellican.png")));
    Image customerBlob = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArsonDiner/pics/amongusamongus.png")));
    Image customerMachine = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("ArsonDiner/pics/lkeafujgesdlfkjegh.png")));
    //Audio Files
    String musicFile = Objects.requireNonNull(Main.class.getResource("ArsonDiner/pics/arson diner_1.m4a")).toExternalForm();
    Media sound = new Media(new File(Objects.requireNonNull(Main.class.getResource("ArsonDiner/pics/arson diner_1.m4a")).toExternalForm()).getPath());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    
    String musicFile2 = Objects.requireNonNull(Main.class.getResource("ArsonDiner/pics/arson diner L.m4a")).toExternalForm();
    Media sound2 = new Media(new File(Objects.requireNonNull(Main.class.getResource("ArsonDiner/pics/arson diner L.m4a")).toExternalForm()).getPath());
    MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
    
    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        game = new World();

        Canvas canvas = new Canvas(game.width, game.height);
        gc = canvas.getGraphicsContext2D();

        VBox vbox = new VBox();
        vbox.getChildren().add(canvas);

        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(this::handleKey);

        stage.setScene(scene);
        stage.setTitle("Arson Diner");
        stage.setOnCloseRequest(a -> {
            mediaPlayer.stop();
            mediaPlayer2.stop();
            try {
                mediaPlayer.stop();
                mediaPlayer2.stop();
                Main main = new Main();
                Parent root = FXMLLoader.load(Objects.requireNonNull(main.getClass().getResource("MainMenuPG6.fxml")));
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
        mediaPlayer.play();
    }

    void handleKey(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.P) // Press P to pause/unpause
        {
            animation.togglePause();
        }

        else if (key == KeyCode.W){
            game.player.move("W");
        }
        else if (key == KeyCode.S){
            game.player.move("S");
        }
        else if (key == KeyCode.A){
            game.player.move("A");
        }
        else if (key == KeyCode.D){
            game.player.move("D");
        }
        else if (key == KeyCode.R){
            game = new World();
        } 
        else if (key == KeyCode.Q) {
            resetCarriedFood();
        }
    }

    //Collision Methods
    boolean isColliding(Entity One, Entity Two) { //Entity One should be smaller or same size as Entity Two for this method to function properly
        if (One.x >= Two.x && One.x <= Two.x + Two.width) { //Checks Player Left Boundary
            if (One.y >= Two.y && One.y <= Two.y + Two.height) { //Check Player Top Boundary
                return true;
            } else if (One.y + One.height >= Two.y && One.y + One.height <= Two.y + Two.height) { //Checks Player Bottom Boundary
                return true;
            }
        } else if (One.x + One.width >= Two.x && One.x + One.width <= Two.x + Two.width) { //Checks Player Right Boundary
            if (One.y >= Two.y && One.y <= Two.y + Two.height) { //Check Player Top Boundary
                return true;
            } else if (One.y + One.height >= Two.y && One.y + One.height <= Two.y + Two.height) { //Checks Player Bottom Boundary
                return true;
            }
        }
        return false;
    }

    void playerOutOfBounds(Entity One) {
        int leftBound = 150;
        int rightBound = 700;
        int topBound = 200;
        int bottomBound = 625;

        if (One.x < leftBound) {
            One.x = leftBound;
        } 
        if (One.x + One.width > rightBound) {
            One.x = rightBound - One.width;
        }
        if (One.y < topBound) {
            One.y = topBound;
        }
        if (One.y + One.height > bottomBound) {
            One.y = bottomBound - One.height;
        }
    }

    //Customer Methods
    void renewCustomers() {
        boolean emptySeat;
        int seatNumber = checkForOpenSeat();

        if (seatNumber > 0) {
            emptySeat = true;
            while (emptySeat == true) {
                int placeHolder = random.nextInt(game.customers.length);
                System.out.println(placeHolder);
                if (game.customers[placeHolder].seat == 0) {
                    game.customers[placeHolder].seat = seatNumber;
                    emptySeat = false;
                }

            }
        }

    }

    int checkForOpenSeat() {
        boolean[] seats = new boolean[3];

        for(Customer c : game.customers) {
            if(c.seat != 0) {
                seats[c.seat - 1] = true;
            }
        }

        for(int i = 0; i < seats.length; i++) {
            if (seats[i] == false) {
                return i + 1;
            }
        }

        return 0;
    }

    void moveCustomers() {
        int step = 2;
        for(Customer customer : game.customers) {
            if (customer.seated == false) {
                if (customer.seat == 0 && customer.y >= game.customerSpawn.y) { //Return to spawn
                    customer.x += step;
                } else if (customer.seat == 0 && customer.y < game.customerSpawn.y) { //Return to spawn
                    customer.y += step;
                } else if (customer.seat == 1 && customer.x > 450) { //Moves left to seat #1
                    customer.x -= step;
                } else if (customer.seat == 2 && customer.x > 250) { //Moves left to seat #2
                    customer.x -= step;
                } else if (customer.seat == 3 && customer.x > 30 && customer.y >= game.customerSpawn.y) { //Moves left to seat #3
                    customer.x -= step;
                } else if (customer.seat == 3 && customer.x <= 30 && customer.y < game.customerSpawn.y) { //Moves up to seat #3
                    customer.y -= step;
                }   
            }
        }
    }

    void alignCustomers() {
        for(Customer customer : game.customers) {
            if (customer.seat == 0 && customer.x > 800) {
                customer.x = game.customerSpawn.x;
                customer.y = game.customerSpawn.y;
            } else if (customer.seat == 1 && customer.x < 460) {
                customer.x = 450;
                customer.y = game.customerSpawn.y - 50;
            } else if (customer.seat == 2 && customer.x < 260) {
                customer.x = 250;
                customer.y = game.customerSpawn.y - 50;
            } else if (customer.seat == 3 && customer.x <= 40 && customer.y >= game.customerSpawn.y) { //Moves customer up a few pixels at the corner
                customer.x = 30;
                customer.y = game.customerSpawn.y - 10;
            } else if (customer.seat == 3 && customer.x  < 40 && customer.y < 310) {
                customer.x = 30;
                customer.y = 300;
            }
        }
    }

    boolean shake = false;
    void angryShake() {
        for (Customer customer : game.customers) {
            if (customer.satisfaction < 5 && customer.satisfaction > 3&&customer.seated == true) {
                if (shake) {
                    customer.y += 3;
                } else {
                    customer.y -= 3;
                }
            } else if (customer.satisfaction <= 3 && customer.seated == true) {
                if (shake) {
                    customer.y += 5;
                } else {
                    customer.y -= 5;
                }
            } else if (customer.satisfaction <= 1 && customer.seated == true) {
                if (shake) {
                    customer.y += 8;
                } else {
                    customer.y -= 8;
                }
            } 

        }
        shake = !shake;
    }

    void updateSeatedStatus() {
        for (Customer customer : game.customers) {
            if (customer.x == 450 && customer.y == game.customerSpawn.y - 50) { //Seat #1
                customer.seated = true;
            } else if (customer.x == 250 && customer.y == game.customerSpawn.y - 50) { //Seat #2
                customer.seated = true;
            } else if (customer.x == 30 && customer.y == 300) { //Seat #3
                customer.seated = true;
            }
        }
    }

    void decreaseSatisfaction() {
        for (Customer customer : game.customers) {
            if (customer.seated == true) {
                customer.satisfaction--;
            }
        }
    }

    void resetSatisfaction() {
        for (Customer customer : game.customers) {
            if (customer.seated == false) {
                customer.satisfaction = 10;
            }
        }
    }
    
    boolean isGameOver() {
        for (Customer customer : game.customers) {
            if (customer.satisfaction <= 0) {
                return true;
            }
        }
        return false;
    }

    //Render Methods
    void renderPlayer()
    {
        gc.drawImage(image, game.player.x, game.player.y);
    }

    void renderFoodSpawn() {
        //gc.setFill(Color.GREEN);
        //gc.fillRect(game.foodSpawn.x, game.foodSpawn.y, game.foodSpawn.width, game.foodSpawn.height);
    }

    void renderCustomerSpawn() {
        gc.setFill(Color.PINK);
        gc.fillRect(game.customerSpawn.x, game.customerSpawn.y, game.customerSpawn.width, game.customerSpawn.height);
    }

    void renderCustomers() {
        //gc.setFill(Color.BLUE);
        for (Customer customer : game.customers) {
            if (customer.order == Order.BURGER) {
                gc.drawImage(customerBlob, customer.x, customer.y);
            } else if (customer.order == Order.MILK) {
                gc.drawImage(customerMachine, customer.x, customer.y);
            } else if (customer.order == Order.MUFFIN) {
                gc.drawImage(customerBird, customer.x, customer.y);
            }
        }
    }

    void renderCounter() {
        gc.drawImage(counter, 167, 514);
    }

    void renderFoods() {
        for(Food f : game.foods) {
            if (f.order == Order.BURGER) {
                gc.drawImage(burger, f.x, f.y);
            } else if (f.order == Order.MILK) {
                gc.drawImage(milk, f.x, f.y - 20);
            } else {
                gc.drawImage(muffin, f.x, f.y - 8);
            }
        }
    }

    void renderGame()
    {
        // Draw a white rectangle over everything.
        gc.drawImage(background, 0, 0);

        // Draw game elements
        renderPlayer();
        renderCounter();
        renderCustomers();
        renderFoodSpawn();
        renderFoods();
    }

    //Check Methods

    void checkFoodPickup() {
        for (Food f : game.foods) {
            if(isColliding(f, game.player) && !game.player.isCarrying) {
                f.isCarried = true;
                game.player.isCarrying = true;
            }
        }
    }

    void checkFoodDropoff() {
        for (Food f : game.foods) {
            for (Customer customer : game.customers) {
                if(isColliding(f, customer) && f.order == customer.order) {
                    f.isCarried = false;
                    f.isDelivered = true;
                    f.x = game.foodSpawn.x;
                    if (f.order == Order.MILK) {
                        f.x += 100;
                    } else if (f.order == Order.MUFFIN) {
                        f.x += 200;
                    }
                    f.y = game.foodSpawn.y;
                    game.player.isCarrying = false; 
                    customer.seat = 0;
                    customer.seated = false;
                }
            }
        }
    }

    void updateFoodLocation() {
        for (Food f : game.foods) {
            if (f.isCarried) {
                f.x = game.player.x;
                f.y = game.player.y;
            }
        }
    }

    void resetCarriedFood() {
        for (Food f : game.foods) {
            if (f.isCarried) {
                f.x = game.foodSpawn.x;
                if (f.order == Order.MILK) {
                    f.x += 100;
                } else if (f.order == Order.MUFFIN) {
                    f.x += 200;
                }
                f.y = game.foodSpawn.y;
                f.isCarried = false;
                game.player.isCarrying = false;
            }
        }
    }

    class Animation extends AnimationTimer
    {
        private boolean isPaused = false;

        /**
         * The main game loop.
         * 
         * This method will be called 60 times per second while SnakeApp runs.
         */

        int counter = 0;

        public void handle(long t)
        {   
            //Boundary Check
            playerOutOfBounds(game.player);
            //Customer Movement
            moveCustomers();
            alignCustomers();
            updateSeatedStatus();
            angryShake();
            //Customer Conditionals
            if (counter % 240 == 0) {
                renewCustomers();
            }
            if (counter % 120 == 0) {
                decreaseSatisfaction();
            }
            resetSatisfaction();
            //Food Conditionals
            updateFoodLocation();
            checkFoodPickup();
            checkFoodDropoff();
            //Render
            renderGame();
            //Loop Music
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
            //Game Over Condition
            if (isGameOver()) {
                mediaPlayer.stop();
                gc.drawImage(endGame, 0, 0);
                mediaPlayer2.play();
            }
            
            counter++;
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