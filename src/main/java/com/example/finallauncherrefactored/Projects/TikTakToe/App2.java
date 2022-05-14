package com.example.finallauncherrefactored.Projects.TikTakToe;

import com.example.finallauncherrefactored.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App2 //Ihavenoideahowthisworks
{
    GraphicsContext gc;
    double mouseX, mouseY;
    double scale = 88;
    boolean turn;

    TicUltimate game;

    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        game = new TicUltimate();

        Canvas canvas = new Canvas(792, 792);
        VBox container = new VBox();
        container.getChildren().addAll(canvas);
        gc = canvas.getGraphicsContext2D();

        canvas.setOnMouseMoved(this::handleMouseMove);
        canvas.setOnMouseClicked(this::handleMouseClick);
        Scene scene = new Scene(container);

        stage.setScene(scene);
        stage.setTitle("TIC-TAC-TOE ULTIMATE!!!!");
        stage.setOnCloseRequest(a -> {
            try {
                Main main = new Main();
                Parent root = FXMLLoader.load(Objects.requireNonNull(main.getClass().getResource("MainMenuPG5.fxml")));
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

        drawUltimate();
        drawNormal();
    }

    void handleMouseMove(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    void takeTurn()
    {
        if (turn == true)
        {
            turn = false;
        }
        else if (turn == false)
        {
            turn = true;
        }
    }

    void handleMouseClick(MouseEvent e)
    {
        takeTurn();

        int coordX = (int)(mouseX/scale);
        int coordY = (int)(mouseY/scale);

        int whichGame = 3 * (coordY / 3) + (coordX / 3);

        int whichSpot = 3 * (coordY % 3) + (coordX % 3);


        if (turn == false)
        {
            //drawO(coordX * 88, coordY * 88);
            game.input('o', whichGame, whichSpot);

            //drawO((double)(((int)mouseX)/88)*88,(double)(((int)mouseY)/88)*88);
        }
        if (turn == true)
        {
            //drawX(coordX * 88, coordY * 88);
            game.input('x', whichGame, whichSpot);

            //drawX((double)(((int)mouseX)/88)*88,(double)(((int)mouseY)/88)*88);
        }

        drawGame();
    }

    /*void checkPlace()
    {
    if (0 <= mouseX && mouseX <= 266 && 0 <= mouseY && mouseY <= 266)
    {
    = 0;
    }
    else if (266 <= mouseX && mouseX <= 532 && 0 <= mouseY && mouseY <= 266)
    {
    = 1;
    }
    }*/

    void drawUltimate()
    {
        gc.save();
        gc.translate(-3, -5);
        gc.setFill(Color.BLACK);
        gc.fillRect(264, 10, 6, 785);
        gc.setFill(Color.BLACK);
        gc.fillRect(528, 10, 6, 785);
        gc.setFill(Color.BLACK);
        gc.fillRect(10, 264, 788, 6);
        gc.setFill(Color.BLACK);
        gc.fillRect(10, 528, 788, 6);
        gc.restore();
    }

    void drawMarks()
    {
        for (int i = 0; i < 81; i++)
        {
            //TODO
        }
    }

    void drawX(double x, double y)
    {
        gc.save();
        gc.translate(x + 45, y + 45);
        gc.rotate(45);
        gc.setFill(Color.RED);
        gc.fillRect(-37.5, -10, 75, 20);
        gc.setFill(Color.RED);
        gc.fillRect(-10, -37.5, 20, 75);
        gc.restore();
    }

    void drawO(double x, double y)
    {
        gc.save();
        gc.translate(x + 45, y + 43);
        gc.rotate(45);
        gc.setFill(Color.BLUE);
        gc.fillOval(-37.5, -37.5, 75, 75);
        gc.setFill(Color.WHITE);
        gc.fillOval(-25, -25, 50, 50);
        gc.restore();
    }

    void drawNormal() // Draw the small tictactoe boards
    {
        gc.save();
        gc.translate(0, -5);
        if (game.grid[0] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[0] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[0] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(88, 5, 4, 250);
        if (game.grid[0] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[0] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[0] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(176, 5, 4, 250);
        if (game.grid[0] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[0] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[0] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 88, 250, 4);
        if (game.grid[0] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[0] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[0] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 176, 250, 4);
        gc.restore();

        gc.save();
        gc.translate(264, -5);
        if (game.grid[1] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[1] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[1] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(88, 5, 4, 250);
        if (game.grid[1] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[1] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[1] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(176, 5, 4, 250);
        if (game.grid[1] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[1] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[1] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 88, 250, 4);
        if (game.grid[1] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[1] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[1] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 176, 250, 4);
        gc.restore();

        gc.save();
        gc.translate(530, -5);
        if (game.grid[2] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[2] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[2] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(88, 5, 4, 250);
        if (game.grid[2] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[2] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[2] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(176, 5, 4, 250);
        if (game.grid[2] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[2] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[2] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 88, 250, 4);
        if (game.grid[2] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[2] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[2] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 176, 250, 4);
        gc.restore();
        //wheeeeeeee
        gc.save();
        gc.translate(0, 264);
        if (game.grid[3] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[3] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[3] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(88, 5, 4, 250);
        if (game.grid[3] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[3] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[3] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(176, 5, 4, 250);
        if (game.grid[3] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[3] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[3] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 88, 250, 4);
        if (game.grid[3] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[3] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[3] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 176, 250, 4);
        gc.restore();

        gc.save();
        gc.translate(264, 264);
        if (game.grid[4] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[4] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[4] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(88, 5, 4, 250);
        if (game.grid[4] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[4] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[4] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(176, 5, 4, 250);
        if (game.grid[4] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[4] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[4] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 88, 250, 4);
        if (game.grid[4] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[4] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[4] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 176, 250, 4);
        gc.restore();

        gc.save();
        gc.translate(530, 264);
        if (game.grid[5] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[5] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[5] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(88, 5, 4, 250);
        if (game.grid[5] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[5] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[5] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(176, 5, 4, 250);
        if (game.grid[5] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[5] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[5] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 88, 250, 4);
        if (game.grid[5] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[5] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[5] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 176, 250, 4);
        gc.restore();
        //wheeeeeeee
        gc.save();
        gc.translate(0, 528);
        if (game.grid[6] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[6] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[6] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(88, 5, 4, 250);
        if (game.grid[6] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[6] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[6] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(176, 5, 4, 250);
        if (game.grid[6] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[6] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[6] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 88, 250, 4);
        if (game.grid[6] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[6] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[6] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 176, 250, 4);
        gc.restore();

        gc.save();
        gc.translate(264, 528);
        if (game.grid[7] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[7] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[7] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(88, 5, 4, 250);
        if (game.grid[7] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[7] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[7] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(176, 5, 4, 250);
        if (game.grid[7] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[7] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[7] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 88, 250, 4);
        if (game.grid[7] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[7] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[7] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 176, 250, 4);
        gc.restore();

        gc.save();
        gc.translate(530, 528);
        if (game.grid[8] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[8] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[8] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(88, 5, 4, 250);
        if (game.grid[8] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[8] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[8] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(176, 5, 4, 250);
        if (game.grid[8] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[8] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[8] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 88, 250, 4);
        if (game.grid[8] == 'x')
        {
            gc.setFill(Color.RED);
        }
        else if (game.grid[8] == 'o')
        {
            gc.setFill(Color.BLUE);
        }
        else if (game.grid[8] == 'z')
        {
            gc.setFill(Color.PURPLE);
        }
        else
        {
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(5, 176, 250, 4);
        gc.restore();
    }

    void drawGame()
    {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 792, 792);

        game.placeMark();
        //game.checkWin('x');
        //game.checkWin('o');
        drawUltimate();
        drawNormal();

        for (int g = 0; g < 9; g++) // for each game
        {
            Tictactoe smallBoard = game.games[g];

            for (int z = 0; z < 9; z++) // for each spot
            {
                double x = (3 * scale) * (g % 3);
                double y = (3 * scale) * (g / 3);

                x += scale * (z % 3);
                y += scale * (z / 3);

                if (smallBoard.grid[z] == 'x')
                {
                    drawX(x, y);
                }
                else if (smallBoard.grid[z] == 'o')
                {
                    drawO(x, y);
                }
            }
        }
    }

}
