package com.example.finallauncherrefactored.Projects.Lives;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LivesApp
{
    Game game;
    GraphicsContext gc;
    Animation animation;
    Image catImage;
    Image laserImage;
    Image fridgeImage;
    Image foodImage;
    Image microwaveImage;
    Image counterImage;
    Image waterImage;
    Image background;
    Image potImage;
    Image vaseImage;
    Image brokenPotImage;
    Image yarnImage;
    Image hoopImage;
    Image startImage;
    Image restartImage;
    Image gameoverImage;
    Image logoImage;
    Skin skin;

    double mouseX, mouseY;
    enum Skin {
        DEFAULT, COWBOY, DRAGON, SPACE, DEBUG, WIDE, FIELD
    }

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
        scene.setOnKeyReleased(this::handleKey2);
        scene.setOnMouseMoved(this::handleMouse);
        scene.setOnMouseClicked(this::handleMouse2);
        skin = Skin.DEFAULT;
        stage.setScene(scene);
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
        stage.setTitle("9 Lives");
        stage.show();

        loadImages();

        animation = new Animation();
        animation.start();
    }

    void handleMouse2 (MouseEvent e) {
        checkButtons();
    }

    void handleMouse(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();        
    }

    void handleKey2(KeyEvent e) {
        KeyCode key = e.getCode();
        if (key == KeyCode.D) {
            game.cat.goRight=false;
        } else if (key == KeyCode.A) {
            game.cat.goLeft=false;
        }
    }

    void checkButtons () {
        if (game.state=="MENU" && mouseX>game.begin.x && mouseX<game.begin.x+game.begin.width
        && mouseY>game.begin.y && mouseY<game.begin.y+game.begin.height) {
            game.start();
        }else
        if (game.state=="GAMEOVER" && mouseX>game.restart.x && mouseX<game.restart.x+game.restart.width
        && mouseY>game.restart.y && mouseY<game.restart.y+game.restart.height) {
            game.cat.lives=-1;
            game.state="MENU";
        }else
        if (mouseX>game.skin.x && mouseX<game.skin.x+game.skin.width
        && mouseY>game.skin.y && mouseY<game.skin.y+game.skin.height) {
            changeSkin();
        }
    }

    void changeSkin(){
        if(skin==Skin.DEFAULT){
            skin=Skin.DEBUG;
        }else
        if(skin==Skin.DEBUG){
            skin=Skin.WIDE;
        }else
        if(skin==Skin.WIDE){
            skin=Skin.COWBOY;
        }else
        if(skin==Skin.COWBOY){
            skin=Skin.DRAGON;
        }else
        if(skin==Skin.DRAGON){
            skin=Skin.SPACE;
        }else
        if(skin==Skin.SPACE){
            skin=Skin.FIELD;
        }else
        if(skin==Skin.FIELD){
            skin=Skin.DEFAULT;
        }
    }

    void handleKey(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.P) // Press P to pause/unpause
        {
            animation.togglePause();
        } else if (key == KeyCode.R) {
            game = new Game();
        } else if (key == KeyCode.D) {
            game.cat.goRight=true;
            game.cat.goLeft=false;
        } else if (key == KeyCode.A) {
            game.cat.goRight=false;
            game.cat.goLeft=true;
        }
        if (key == KeyCode.W) {
            game.cat.jumping=true;
        }
        if (key == KeyCode.N) {
            if (game.state!="GAMEOVER" && game.state!="MENU") {
                game.nextGame();
            }
        }
        if (key == KeyCode.L) {
            if (game.state=="MENU") {
                //game.start=System.nanoTime();
                game.start();
            } else if (game.state=="GAMEOVER") {
                game.cat.lives=-1;
                game.state="MENU";
            }
        }
        if (key == KeyCode.I) {
            game.yarn.launch();
        }
    }

    void renderButtons () {
        
        if (game.state=="MENU") {
            gc.drawImage(logoImage, game.tileSize*3, game.tileSize*2, game.tileSize*5, game.tileSize*3);
            /*
            gc.setFill(Color.LIGHTBLUE);
            gc.fillRect(game.begin.x, game.begin.y, game.begin.width, game.begin.height);
            gc.setFill(Color.BLACK);
            gc.fillText("START", game.begin.x+(game.begin.width/2), game.begin.y+(game.begin.height/2));
             */
            gc.drawImage(startImage, game.begin.x, game.begin.y, game.begin.width, game.begin.height);
        } else {

        }
        if (game.state=="GAMEOVER") {
            gc.drawImage(gameoverImage, game.tileSize*3, game.tileSize*2, game.tileSize*5, game.tileSize*3);
            gc.drawImage(restartImage, game.restart.x, game.restart.y, game.restart.width, game.restart.height);
        } else {

        }
    }

    void loadImages () {
        //more images please
        catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/CatRight.png")));
        foodImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Food.png")));
        laserImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Laser.png")));
        fridgeImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Fridge.png")));
        microwaveImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Microwave.png")));
        counterImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Coutner2.png")));
        waterImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Water.png")));
        background = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Background.png")));
        potImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Pot.png")));
        vaseImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Vase.png")));
        brokenPotImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/BrokenPot.png")));
        yarnImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Yarn.png")));
        hoopImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Hoop.png")));
        startImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Start.png")));
        restartImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Restart.png")));
        gameoverImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/GameOver.png")));
        logoImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/Logo.png")));
    }

    void renderYarn () {
        game.yarn.move();
        /*
        gc.setFill(Color.PINK);
        gc.fillOval(game.yarn.x, game.yarn.y, game.yarn.width, game.yarn.height);

        gc.setFill(Color.BLACK);
        gc.fillRect(game.hoop.x, game.hoop.y, game.hoop.width, game.hoop.height);
         */
        gc.drawImage(yarnImage, game.yarn.x, game.yarn.y, game.yarn.width, game.yarn.height);
        gc.drawImage(hoopImage, game.hoop.x, game.hoop.y, game.hoop.width, game.hoop.height);
    }

    void renderCat () {
        game.cat.width=game.tileSize;
        if(skin==Skin.DEFAULT){
            if (game.cat.goRight) {
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/CatRight.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*4), game.cat.y-((game.cat.height/8)*2.5),
                    game.cat.width*1.47, game.cat.height*1.4);
            }else
            if (game.cat.goLeft) {
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/CatLeft.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*-.1), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }else{
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/CatFront.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*.1), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }
        }
        if(skin==Skin.DEBUG){

            gc.setFill(Color.SANDYBROWN);
            gc.fillRect(game.cat.x, game.cat.y, game.cat.width, game.cat.height);

            gc.setFill(Color.WHITE);
            if (game.cat.onGround) {
                gc.fillText("onGround", game.cat.x, game.cat.y-3);
            } else {
                gc.fillText("not onGround", game.cat.x, game.cat.y-3);
            }

            gc.setFill(Color.WHITE);
            gc.fillText("Cat", game.cat.centerX-(game.cat.width/3), game.cat.centerY);

        }
        if(skin==Skin.WIDE){
            game.cat.width=game.tileSize*2.4;
            game.skin.width=game.cat.width;
            if (game.cat.goRight) {
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/CatRight.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*4), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }else
            if (game.cat.goLeft) {
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/CatLeft.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*-.1), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }else{
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/CatFront.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*.1), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }
        }
        if(skin==Skin.COWBOY){
            if (game.cat.goRight) {
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/CowboyRight.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*4), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }else
            if (game.cat.goLeft) {
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/CowboyLeft.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*-.1), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }else{
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/CowboyFront.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*.1), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }
        }
        if(skin==Skin.SPACE){
            if (game.cat.goRight) {
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/SpaceRight.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*4), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }else
            if (game.cat.goLeft) {
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/SpaceLeft.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*-.1), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }else{
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/SpaceFront.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*.1), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }
        }
        if(skin==Skin.FIELD){
            if (game.cat.goRight) {
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/FieldRight.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*4), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }else
            if (game.cat.goLeft) {
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/FieldLeft.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*-.1), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }else{
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/FieldFront.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*.1), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }
        }
        if(skin==Skin.DRAGON){
            if (game.cat.goRight) {
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/DinoRight.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*4), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }else
            if (game.cat.goLeft) {
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/DinoLeft.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*-.1), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }else{
                catImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("9Lives/Images/DinoFront.png")));
                gc.drawImage(catImage, game.cat.x-((game.cat.width/8)*.1), game.cat.y-((game.cat.height/8)*2.5), 
                    game.cat.width*1.47, game.cat.height*1.4);
            }
        }

    }

    void updateCat () {
        game.cat.move();
        game.microwave.update();
        game.pot.update();
        game.vase.update();
    }

    void updateGame () {
        game.update();
        if (game.gameOver) {
            game.state="GAMEOVER";
        }
    }

    void renderWin () {
        /*
        gc.setFill(Color.RED);
        gc.fillRect(game.food.x, game.food.y, game.food.width, game.food.height);
         */
        gc.drawImage(foodImage,game.food.x, game.food.y, game.food.width, game.food.height);
    }

    void renderLaser () {
        if (game.state=="LASERCHASE") {
            Laser l = game.laser;
            if (l.active==false) {
                l.x=game.width/2;
                l.y=game.height/2;
                l.active=true;
            }
            /*
            gc.setFill(Color.SILVER);
            gc.fillRect(l.minX, l.minY, l.maxX-l.minX, l.maxY-l.minY);
             */
            gc.setFill(Color.LIMEGREEN);
            gc.fillOval(l.x, l.y, l.width, l.height);

        } else {
            game.laser.x=game.width;
            game.laser.y=game.height;

        }
    }

    void updateLaser () {
        if (!game.laser.caught) {
            game.laser.move();
        }
    }

    void renderFloor () {

        if (game.state=="FLOORISWATER") {
            FloorTile f = game.counter;
            FloorTile k = game.fridge;
            MovingTile o = game.microwave;
            WaterTile w = game.water;

            /*
            gc.setFill(Color.GREY);
            gc.fillRect(o.x, o.y, o.width, o.height);
            gc.setFill(Color.BLACK);
            gc.fillText("Microwave", o.centerX, o.centerY);
             */
            gc.drawImage(microwaveImage,o.x, o.y, o.width, o.height);
            /*
            gc.setFill(Color.WHITE);
            gc.fillRect(f.x, f.y, f.width, f.height);
            gc.setFill(Color.BLACK);
            gc.fillText("Counter", f.centerX, f.centerY);
             */
            gc.drawImage(counterImage,f.x, f.y, f.width, f.height);
            /*
            gc.setFill(Color.WHITE);
            gc.fillRect(k.x, k.y, k.width, k.height);
            gc.setFill(Color.BLACK);
            gc.fillText("Fridge", k.centerX, k.centerY);
             */
            gc.drawImage(fridgeImage,k.x, k.y, k.width, k.height);
            /*
            gc.setFill(Color.BLUE);
            gc.fillRect(w.x, w.y, w.width, w.height);
            gc.setFill(Color.WHITE);
            gc.fillText("Water", w.centerX, w.centerY);
             */
            gc.drawImage(waterImage,w.x, w.y, w.width, w.height);
        } else if (game.state=="GAMEOVER") {
            gc.setFill(Color.WHITE);
            //gc.fillText("GAME OVER", game.width/2, game.height/2);
        } else if (game.state=="MENU") {
            gc.setFill(Color.WHITE);
            //gc.fillText("MENU", game.width/2, game.height/2);
        } else if (game.state=="CURIOSITY") {
            MovingTile o = game.pot;
            MovingTile k = game.vase;
            FloorTile p = game.counterB;

            if (!game.pot.isAlive) {
                /*
                gc.setFill(Color.BROWN);
                gc.fillRect(o.deadX, o.deadY, o.width, o.height);
                 */
                gc.drawImage(brokenPotImage,o.deadX-o.width, o.deadY-(o.width/3), o.width, o.height);
            } else {
                /*
                gc.setFill(Color.BURLYWOOD);
                gc.fillRect(o.x, o.y, o.width, o.height);
                gc.setFill(Color.BLACK);
                gc.fillText("Pot", o.centerX, o.centerY);
                 */
                gc.drawImage(potImage,o.x, o.y, o.width, o.height);
            }

            if (!game.vase.isAlive) {
                gc.setFill(Color.CORAL);
                gc.fillRect(k.deadX, k.deadY, k.width-10, k.height);
            } else {
                /*
                gc.setFill(Color.LIGHTCORAL);
                gc.fillRect(k.x, k.y, k.width, k.height);
                gc.setFill(Color.BLACK);
                gc.fillText("Vase", k.centerX, k.centerY);
                 */
                gc.drawImage(vaseImage,k.x, k.y, k.width, k.height);
            }

            /*
            gc.setFill(Color.WHITE);
            gc.fillRect(p.x, p.y, p.width, p.height);
            gc.setFill(Color.BLACK);
            gc.fillText("CounterB", p.centerX, p.centerY);
             */
            gc.drawImage(counterImage,p.x, p.y, p.width, p.height);
        } else if (game.state=="YARN") {
            game.yarn.move();
            renderYarn();
        }

    }

    void renderScore () {
        gc.setFill(Color.WHITE);
        gc.fillText("Score: "+ game.score, game.tileSize/2, game.tileSize/2);
        gc.fillText("Highscore: "+ game.highScore, game.tileSize/2, game.tileSize/1);

        //gc.fillText("Time: "+ game.time, game.tileSize*7, game.tileSize );
    }

    void renderControls () {
        gc.setFill(Color.WHITE);
        gc.fillText("P = Pause", game.width/2, game.height/4-20);
        gc.fillText("R = Restart", game.width/2, game.height/4-10);
        if (game.state=="MENU") {
            gc.fillText("L = Start", game.width/2, game.height/4);
            gc.fillText("N = nothing", game.width/2, game.height/4+10);
            gc.fillText("time does not work and niether do the images, please fix this max!", game.width/2, game.height/4+50);
        } else if (game.state=="GAMEOVER") {
            gc.fillText("L = Menu", game.width/2, game.height/4);
            gc.fillText("N = nothing", game.width/2, game.height/4+10);
        } else {
            gc.fillText("L = nothing", game.width/2, game.height/4);
            gc.fillText("N = Next Minigame", game.width/2, game.height/4+10);
        }
    }

    void renderLives () {
        gc.setFill(Color.WHITE);
        gc.fillText(""+game.cat.lives, game.tileSize/2, game.tileSize/4);
    }

    void renderGame()
    {
        /*
        gc.setFill(Color.DARKGREY);
        gc.fillRect(0, 0, game.width, game.height);
         */
        gc.drawImage(background,0, 0, game.width, game.height);
        gc.setFill(Color.DARKBLUE);
        gc.fillRect(0, 0, 120, 100);
        renderButtons();
        renderLaser();
        renderCat();
        renderWin();
        renderFloor();
        renderLives();
        //renderControls();
        renderScore();

    }
    class Animation extends AnimationTimer
    {
        private boolean isPaused = false;

        /**
         * The main game loop.
         * 
         * This method will be called 60 times per second while game runs.
         */
        public void handle(long t)
        {   

            updateCat();
            updateLaser();
            updateGame();
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