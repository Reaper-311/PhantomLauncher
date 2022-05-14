package com.example.finallauncherrefactored.Projects.JJPaint;

import com.example.finallauncherrefactored.Main;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MsPaint
{
    App app;
    GraphicsContext gc;
    Animation animation;
    int red;
    int blue;
    int green;
    double x1, x2, y1, y2, xb1, xb2, yb1, yb2;
    double mouseX, mouseY;
    boolean linestart = false;
    boolean isDrawing = true; 
    double lw = 5;
    boolean drawstart = false;
    Canvas canvas;
    WritableImage wim;
    File file;
    boolean seed = true;
    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        app = new App();
        file = new File("CanvasImage.png");
        canvas = new Canvas(app.width, app.height);
        gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        wim = new WritableImage((int)app.width, (int)app.height);
        root.getChildren().add(canvas);
        VBox vbox = new VBox();
        vbox.getChildren().add(canvas);
        canvas.snapshot(null, wim);
        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(this::handleKey);
        scene.setOnMouseDragged(this::handleMouse);
        scene.setOnMouseMoved(this::handleMove);
        scene.setOnMouseClicked(this::handleClick);
        scene.setOnMouseReleased(this::handleRelease);
        //scene.setOnMouseReleased(this::handleRelease);
        stage.setScene(scene);
        stage.setTitle("Paint");
        stage.setOnCloseRequest(a -> {
            try {
                Main main = new Main();
                Parent load = FXMLLoader.load(Objects.requireNonNull(main.getClass().getResource("MainMenuPG3.fxml")));
                Scene scene2 = new Scene(load);
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

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, app.width, app.height);

        renderApp();
        renderPause();
        animation.togglePause(); // Show game instructions on start.
    }

    void updateBrush()
    {
        app.brush.centery = mouseY;
        app.brush.centerx = mouseX;
    }

    void handleClick(MouseEvent e)
    {

        gc.fillRect(e.getX()-lw/2, e.getY()-lw/2, lw, lw);
    }

    void handleRelease(MouseEvent e)
    {
        isDrawing = false;
        xb2 = xb1;
        yb2 = yb1;
        drawstart = false;
    }

    /* void handleRelease(MouseEvent e)
    {
    isDrawing = false;
    }
     */
    void handleMouse(MouseEvent e)
    {
        isDrawing = true;
        mouseX = e.getX();
        mouseY = e.getY();
        renderBrush();
    }

    void handleMove(MouseEvent e)
    {

        mouseX = e.getX();
        mouseY = e.getY();
        xb1 = mouseX;
        yb1 = mouseY;
    }

    void handleKey(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.ADD) // Press P to pause/unpause
        {
            animation.togglePause();
            renderPause();
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, app.width, app.height);
        }
        else if (key == KeyCode.R)
        {
            //Make animation to wipe
            app = new App();
        }
        else if (key == KeyCode.U)
        {if(red <255)
            {
                red+= 5;
            }
            gc.setFill(Color.rgb(red, green, blue));
            gc.setStroke(Color.rgb(red, green, blue ));

            gc.fillRect(0, 0, 20, lw);
        }
        else if (key == KeyCode.I)
        {if(green < 255)
            {
                green+=5;
            }
            gc.setFill(Color.rgb(red, green, blue ));
            gc.setStroke(Color.rgb(red, green, blue ));

            gc.fillRect(0, 0, 20, lw);
        }
        else if (key == KeyCode.O)
        { if (blue < 255)
            {
                blue+= 5;
            }
            gc.setFill(Color.rgb(red, green, blue));
            gc.setStroke(Color.rgb(red, green, blue ));

            gc.fillRect(0, 0, 20, lw);
        }
        else if (key == KeyCode.J)
        {if(red > 0)
            {
                red-= 5;
            }
            gc.setFill(Color.rgb(red, green, blue));
            gc.setStroke(Color.rgb(red, green, blue ));

            gc.fillRect(0, 0, 20, lw);
        }
        else if (key == KeyCode.K)
        {if(green > 0)
            {
                green-=5;
            }
            gc.setFill(Color.rgb(red, green, blue ));
            gc.setStroke(Color.rgb(red, green, blue ));
            gc.fillRect(0, 0, 20, lw);
        }
        else if (key == KeyCode.L)
        { if (blue > 0)
            {
                blue-= 5;
            }
            gc.setFill(Color.rgb(red, green, blue));
            gc.fillRect(0, 0, 20, lw);
        }
        if (key == KeyCode.Z) 
        {  
            if(linestart == false)
            {
                x1 = mouseX;
                y1 = mouseY;
                toggleLine();

            }
            else if(linestart == true)
            {
                x2 = mouseX;
                y2 = mouseY;
                gc.strokeLine(x1, y1, x2, y2);
                toggleLine();

            }

        }
        if (key == KeyCode.T) 
        {
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 20, lw);
            lw += 5;
            gc.setFill(Color.rgb(red, green, blue));
            gc.fillRect(0, 0, 20, lw);

            gc.setLineWidth(lw); 
        }
        if (key == KeyCode.Y) 
        {
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 20, lw);
            lw -= 5;
            gc.setFill(Color.rgb(red, green, blue));
            gc.fillRect(0, 0, 20, lw);
            gc.setLineWidth(lw);  
        }
        if (key == KeyCode.S) 
        {
            canvas.snapshot(null, wim);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
            } catch (Exception s) {
            }

        }
    }

    void renderBrush()
    {   
        if(isDrawing)
        {
            if(drawstart == false)
            {
                toggledraw();
            }
            else if(drawstart == true)
            {
                xb2 = mouseX;
                yb2 = mouseY;
                gc.strokeLine(xb1, yb1, xb2, yb2);
                drawstart = false;
                xb1 = xb2;
                yb1 = yb2;
            }

        }

    }

    void renderPause()
    {
        double x = app.width * 0.25;
        double y = app.height * 0.2;
        double w = app.width * 0.5;
        double h = app.height * 0.7;

        gc.setFill(Color.BLACK);
        gc.setFont(new Font(45));

        gc.setFont(new Font(18));
        gc.fillText("Press Plus (Numpad) to start.", x + 40, y + 120);
        gc.fillText("Press T to increase width, Y to decrease ", x + 20, y + 160);
        gc.fillText("Move the Brush by Dragging", x + 40, y + 200);
        gc.fillText("Use \"U\" \"I\" \"O\" and \"J\" \"K\" \"L\" to change color", x + 40, y + 240);
        gc.fillText("Press  S to save when done", x + 40, y + 280);
    }

    void renderApp()
    {

    }

    public void toggleLine()
    {
        if(linestart)
        {
            linestart = false;
        }
        else if(!linestart)
        {
            linestart = true; 
        }
    }

    public void toggledraw()
    {
        if(drawstart)
        {
            drawstart = false;
        }
        else if(!linestart)
        {
            drawstart = true; 
        }
    }

    class Animation extends AnimationTimer
    {
        private boolean isPaused = false;
        long lastUpdate;
        long timeLeftThrusterFired;
        long timeRightThrusterFired;

        public void handle(long t)
        {  

            updateBrush();

            renderApp();
        }

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

