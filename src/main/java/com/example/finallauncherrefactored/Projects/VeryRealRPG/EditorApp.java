package com.example.finallauncherrefactored.Projects.VeryRealRPG;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditorApp extends Application
{
    TextField[] text;

    /**
     * Starts the level editing application.
     */

    public void start(Stage stage)
    {
        HBox[] rows = new HBox[16];
        VBox vb = new VBox();
        this.text = new TextField[256];
        for (int b = 0; b < 16; b++)
        {
            rows[b] = new HBox();
            for (int i = 0; i < 16; i++)
            {
                text[b *16 + i] = new TextField("");
                text[b * 16 + i].setMaxWidth(50);
                rows[b].getChildren().add(text[b * 16 + i]);
            }
            rows[b].setMinSize(800, 50);
            vb.getChildren().add(rows[b]);
        }

        Scene scene = new Scene(vb);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Replaces all commas with a blank string, to more easily replace blank space.
     */

    void clearCommas()
    {
        for (TextField t : text)
        {
            if (t.getText().equals(","))
            {
                t.setText("");
            }
        }
    }

    /**
     * Takes a string of 256 characters and sets each text box to the corresponding character.
     */

    void setLevel(String level)
    {
        int count = 0;
        for (int i = 15; i >= 0; i--)
        {
            for (int c = 0; c < 16; c++)
            {
                this.text[i*16 + c].setText(level.charAt(count) +"");
                count++;
            }
        }
        clearCommas();
    }

    /**
     * Prints the current state of the text boxes as a string that can be used to create a level.
     */
    void getLevel()
    {
        String level = "";
        for (int i = 15; i >= 0; i--)
        {
            for (int c = 0; c < 16; c++)
            {
                if (!this.text[i*16 + c].getText().equals(""))
                {
                    level += this.text[i*16 + c].getText();
                }
                else {
                    level += ",";
                }
            }
        }
        System.out.println(level);
    }
}

