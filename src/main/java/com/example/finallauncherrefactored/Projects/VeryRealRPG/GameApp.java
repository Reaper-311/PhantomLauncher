package com.example.finallauncherrefactored.Projects.VeryRealRPG;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameApp
{
    Game game;
    GraphicsContext gc;
    GraphicsContext mc;
    double mouseX;
    double mouseY;
    double prevX;
    double prevY;
    int time;

    /**
     * Initializes the program, required for JavaFX.
     */

    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        game = new Game(this);

        Canvas canvas = new Canvas(1024, 1024);
        Canvas menu = new Canvas(416, 1024);
        gc = canvas.getGraphicsContext2D();
        mc = menu.getGraphicsContext2D();

        HBox hbox = new HBox();
        hbox.getChildren().addAll(canvas, menu);
        Scene scene = new Scene(hbox);

        //scene.setOnMouseClicked(this::handleClick);
        //scene.setOnMouseMoved(this::handleMove);
        scene.setOnKeyPressed(this::handleKey);
        //scene.setOnKeyReleased(this::handleRelease);

        stage.setScene(scene);
        stage.setTitle("Game");
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

        GameTimer timer = new GameTimer();
        timer.start();
        game.Initialize();
    }

    /**
     * Checks for key presses, and does something based on the current game conditions.
     */

    void handleKey(KeyEvent e)
    {
        KeyCode k = e.getCode();
        if (!game.inCombat)
        {
            if (game.inMenu)
            {
                if (k == KeyCode.W)
                {
                    game.menu.move(-1);
                }
                if (k == KeyCode.D)
                {
                    game.shiftMenu(1);
                }
                if (k == KeyCode.A)
                {
                    game.shiftMenu(-1);
                }
                if (k == KeyCode.S)
                {
                    game.menu.move(1);
                }
            }
            if (!game.inMenu)
            {
                if (k == KeyCode.SPACE)
                {
                    game.interact();
                }
                if (k == KeyCode.W)
                {
                    game.player.moveUp();
                }
                if (k == KeyCode.D)
                {
                    game.player.moveRight();
                }
                if (k == KeyCode.A)
                {
                    game.player.moveLeft();
                }
                if (k == KeyCode.S)
                {
                    game.player.moveDown();
                }
            }
            if (k == KeyCode.I)
            {
                game.inMenu = !game.inMenu;
            }
        }
        else
        {
            if (game.isPlayerTurn)
            {
                if (!game.targetSelecting)
                {
                    if (k == KeyCode.SPACE)
                    {
                        game.select();
                    }
                    if (k == KeyCode.W)
                    {
                        game.fightMenu.moveUp();
                    }
                    if (k == KeyCode.D)
                    {
                        game.fightMenu.advance();
                    }
                    if (k == KeyCode.A)
                    {
                        game.fightMenu.retract();
                    }
                    if (k == KeyCode.S)
                    {
                        game.fightMenu.moveDown();
                    }
                }
                else
                {
                    if (k == KeyCode.BACK_SPACE ^ k == KeyCode.S)
                    {
                        game.enemyMenu.type = 0;
                        game.targetSelecting = false;
                    }
                    if (k == KeyCode.D)
                    {
                        game.enemyMenu.moveRight();
                    }
                    if (k == KeyCode.A)
                    {
                        game.enemyMenu.moveLeft();
                    }
                    if (k == KeyCode.SPACE)
                    {
                        game.playerAction(game.fightMenu.layers[game.fightMenu.selected].options[game.fightMenu.layers[game.fightMenu.selected].selected]);
                    }
                }
            }
        }
    }

    /*
    void handleRelease(KeyEvent e)
    {
    KeyCode k = e.getCode();
    }
     */

    /**
     * Calls several methods to draw the game.
     * If the player is in combat, iit draws the enemies and the menus.
     * If the player is not in combat, it draws the tiles and the player.
     */
    public void drawGame()
    {
        if (!game.inCombat)
        {
            drawTiles();
            drawPlayer();
            drawDebug();
        }
        else
        {
            drawBackground();
            drawEnemies();
            drawFightMenu();
        }
        drawSideMenu();
    }


    /**
     * Draws a black screen to cover up the level tiles and pointers.
     */
    public void drawBackground()
    {
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,1024,1024);
    }

    /**
     * For each enemy in the current encounter, draws its sprite at its coordinates.
     */
    public void drawEnemies()
    {
        for (Enemy n : game.currentEncounter.enemies)
        {
            gc.drawImage(n.m.sprite, n.x, n.y);
            gc.drawImage(game.bar, n.x -2, n.y +200);
            gc.setFill(Color.RED);
            gc.fillRect(n.x, n.y + 201, n.HP/n.m.maxHP *196,16);
            gc.setFill(Color.WHITE);
            gc.fillText(n.m.name,n.x + 90 ,n.y + 235 );

        }
    }

    /**
     * Calls various methods to draw different parts of the fight menu.
     */
    public void drawFightMenu()
    {
        drawFightBoxes();
        drawFightText();
        drawFightPointer();
        drawEnemyPointer();
    }

    /**
     * If the player is selecting a target, shows the pointer.
     * If the target is 0, draws it on the selected enemy.
     * If the target is 2, draws it on all alive enemies.
     * If the target is 1, draws it next to the player health.
     */
    public void drawEnemyPointer()
    {
        if (game.targetSelecting)
        {
            if (game.enemyMenu.type == 0)
            {
                gc.drawImage(game.pointer, game.currentEncounter.enemies.get(game.enemyMenu.selected + game.enemyMenu.getFirstAlive()).x - 40,game.currentEncounter.enemies.get(game.enemyMenu.selected + game.enemyMenu.getFirstAlive()).y + 30);
            }
            if (game.enemyMenu.type == 2)
            {
                for (Enemy e : game.currentEncounter.enemies)
                {
                    if (e.isAlive)
                    {
                        gc.drawImage(game.pointer, e.x - 40, e.y + 30);
                    }
                }
            }
            if (game.enemyMenu.type == 1)
            {
                gc.drawImage(game.pointer, 990, 16);
            }
        }
    }
    /**
     * Draws the box that contains the 4 basic commands.
     * If the currently selected box is not the first one, draws an additional box.
     */
    public void drawFightBoxes()
    {
        gc.drawImage (game.fightBox, 440, 750);
        if (game.fightMenu.selected != 0)
        {
            gc.drawImage (game.fightBox, 600, 750);
        }
    }

    /**
     * Draws a pointer on the currently selected option of each active menu.
     */
    public void drawFightPointer()
    {
        gc.drawImage (game.pointer, 400, 760 + (30 * game.fightMenu.layers[0].selected));
        if (game.fightMenu.selected != 0)
        {

            gc.drawImage (game.pointer, 560, 760 + (20 * game.fightMenu.layers[game.fightMenu.selected].selected));
        }
    }

    /**
     * Draws the text of each action for each active layer.
     */
    public void drawFightText()
    {
        for (int i = 0 ; i < 4; i++)
        {
            gc.setFill(Color.WHITE);
            gc.fillText(game.fightMenu.layers[0].options[i].name, 450, 780 + 30 * i);
        }
        if (game.fightMenu.selected == 1)
        {
            for (int i = 0 ; i < 6; i++)
            {
                gc.setFill(Color.WHITE);
                gc.fillText(game.fightMenu.layers[1].options[i].name, 610, 780 + 20 * i);
                gc.fillText(Math.round(game.fightMenu.layers[1].options[i].cost) +"", 700, 780 + 20 * i);
            }
        }
        if (game.fightMenu.selected == 2)
        {
            for (int i = 0 ; i < 4; i++)
            {
                gc.setFill(Color.WHITE);
                gc.fillText(game.fightMenu.layers[2].options[i].name, 610, 780 + 20 * i);
                gc.fillText(Math.round(game.fightMenu.layers[2].options[i].cost) +"", 700, 780 + 20 * i);
            }
        }
    }

    /**
     * Draws each equipped item in the side menu.
     */
    public void drawEquips()
    {
        for (int i = 0; i < 4; i++)
        {
            mc.drawImage(game.itemSprites[game.player.equipped[i].id], 72, 523 + i *39);
        }

    }

    /**
     * Draws the image used in the side menu, the equips, the text, and the bars.
     * If the player is in the equips menu, draws the pointer for it.
     */
    public void drawSideMenu()
    {
        //draws the menu image and each equip
        mc.drawImage(game.menu.g, 0, 0);
        drawEquips();
        //writes text
        mc.setFill(Color.WHITE);
        mc.fillText("HP: " +Math.round(game.player.HP) + " / "+Math.round(game.player.maxHP), 8, 18);
        mc.fillText("MP: " +(int)Math.floor(game.player.MP) + " / "+(int)Math.floor(game.player.maxMP), 8, 60);
        mc.fillText("STAMINA: " +(int)Math.floor(game.player.stamina) + " / "+(int)Math.floor(game.player.maxStamina), 8, 102);
        mc.fillText(game.levelDescript[game.player.location.id], 12, 174);
        mc.fillText(game.lastInteract, 12, 252);
        mc.fillText("Weapon:\n"+game.player.equipped[0].name +"\n"+ "Armor:\n"+game.player.equipped[1].name +"\n" +"Headgear:\n"+game.player.equipped[2].name +"\n"+ "Pet:\n"+game.player.equipped[3].name, 136, 540);
        mc.fillText("STR: " + Math.round(game.player.STR) + "\nINT: " + Math.round(game.player.INT) + "\nAGI: " + Math.round(game.player.AGI) + "\nCON: " + Math.round(game.player.CON) + "\nMIND: " + Math.round(game.player.MIND) + "\nLUCK: " + Math.round(game.player.LUCK) +"\n\nLEVEL " + game.player.level, 230, 540);
        mc.fillText("Physical Attack: " + Math.round(game.player.att *10)+ "\nMagic Attack: " + Math.round(game.player.mag * 10) + "\nPhysical Defence: " + Math.round(game.player.def * 10) + "\nMagic Defence: " + Math.round(game.player.mdef * 10) + "\nAccuracy: " + Math.round(game.player.acc * 100)+ "%\nEvade: " + Math.round(game.player.evade *100)+"%\n\nExp: " + Math.round(game.player.exp)+"/100", 290, 540);
        mc.fillText(game.battleText, 12, 326);
        //draws bars
        mc.setFill(Color.RED);
        mc.fillRect(8,24, 400 *(game.player.HP / game.player.maxHP), 16);
        mc.setFill(Color.BLUE);
        mc.fillRect(8,66, 400 *(game.player.MP / game.player.maxMP), 16);
        mc.setFill(Color.LIME);
        mc.fillRect(8,108, 400 *(game.player.stamina / game.player.maxStamina), 16);
        if (game.inMenu && !game.inCombat)
        {
            drawEquipPointer();
        }

    }

    /**
     * Draws a pointer at the currently selected equip slot.
     */
    public void drawEquipPointer()
    {
        mc.drawImage(game.pointer,36, 526 + game.menu.selected *36);
    }

    /**
     * Draws debug text, showing the level and coordinates.
     */
    public void drawDebug()
    {
        //draws text containing variables useful for testing new level code
        gc.setFill(Color.WHITE);
        gc.fillText(game.player.x + ", "+game.player.y+", "+ " level "+game.player.location.id, 10,10);
    }

    /**
     * For each tile, draw the image in the current tileset corresponding to its sprite value.
     * If the tile has an interactable, draws the image in interact sprites corresponding to its sprite value.
     */
    public void drawTiles()
    {
        //for each x value, draw a tile for each y value from the location's tileset.
        for (Tile[] c : game.player.location.rows)
        {
            for (Tile t : c)
            {
                gc.drawImage(game.player.location.tileset[t.sprite], t.x * 64,(game.player.location.height - t.y) * 64 + -64);
                if (t.interact != null)
                {
                    gc.drawImage(game.interactSprites[t.interact.sprite], t.x * 64,(game.player.location.height - t.y) * 64 + -64);
                }
            }
        }
    }

    /**
     * Draws the player's current sprite, starting from the bottom left.
     */
    public void drawPlayer()
    {
        gc.drawImage(game.player.sprite,game.player.x*64,((game.player.location.height -game.player.y)*64)-64);
    }

    class GameTimer extends AnimationTimer
    {
        public void handle(long time)
        {
            game.update();
            drawGame();
        }
    }
}
