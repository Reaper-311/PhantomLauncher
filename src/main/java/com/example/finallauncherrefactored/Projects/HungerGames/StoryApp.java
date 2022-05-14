package com.example.finallauncherrefactored.Projects.HungerGames;

import com.example.finallauncherrefactored.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class StoryApp
{
    ImageView view;
    TextArea text;

    Story story;

    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        //Set up the game data
        story = new Story();

        //Set up an image container
        view = new ImageView();
        view.setFitHeight(300);
        view.setPreserveRatio(true);

        refreshImage();
        
        //Set up a text display window
        text = new TextArea();
        text.setWrapText(true);
        text.setEditable(false);

        //Redirect "System.out.println()" to print to the text display window
        Console console = new Console(text);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        System.setErr(ps);
        System.out.println("Press SPACE to turn the page. Press B to go back.\n");

        // Create a new window manager and put all components inside
        SplitPane storySpread = new SplitPane(view, text);

        Scene scene = new Scene(storySpread, 900,400);
        stage.setTitle("Story Time");
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
        stage.setScene(scene);

        // Tell the app to listen for keypresses
        text.setOnKeyPressed(this::handleKey);
        
        // Show the Stage (window)
        stage.show();
        
        story.readCurrentPage();
    }

    void refreshImage()
    {
        view.setImage(story.getCurrentPage().image);
    }
    
    void handleKey(KeyEvent k)
    {
        KeyCode key = k.getCode();
        
        if (key == KeyCode.SPACE)
        {
            text.clear();
            story.turnPage();
            refreshImage();
            story.readCurrentPage();
        }
        else if (key == KeyCode.B)
        {
            text.clear();
            story.turnBackPage();
            refreshImage();
            story.readCurrentPage();
        }
    }
}

class Console extends OutputStream
{
    TextArea output;

    public Console(TextArea ta)
    {
        this.output = ta;
    }

    @Override
    public void write(int i) throws IOException
    {
        output.appendText(String.valueOf((char) i));
    }

}
