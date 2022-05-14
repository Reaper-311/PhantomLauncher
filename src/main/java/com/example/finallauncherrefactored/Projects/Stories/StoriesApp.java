package com.example.finallauncherrefactored.Projects.Stories;

import com.example.finallauncherrefactored.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;


public class StoriesApp
{
    ImageView view;
    TextArea output;
    TextField input;
    String option = "ChooseEvent";
    
    World world;
    
    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        world = new World();
        
        //display window
        output = new TextArea();
        output.setWrapText(true);
        output.setEditable(false);
        output.appendText("Story Time!\n");
        
        //prints text display window
        Console console = new Console(output);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        System.setErr(ps);
        
        //text box
        input = new TextField();
        
        //Image view
        view = new ImageView();
        view.setImage(world.currentEvent.currentImage);


        //everytime they press enter
        input.setOnAction(this::textEntered);
        
        //idk what this does
        VBox pane = new VBox();
        pane.getChildren().addAll(view, output, input);

        Scene scene = new Scene(pane, 600,800);
        stage.setTitle("STORY TIME");
        stage.setScene(scene);
        
        // Show the Stage (window)
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
    
        world.currentEvent.playEvent("");

    }
    
    private void textEntered (ActionEvent event)
    {
        option = input.getText();
        System.out.println("\nYou said: " + option + "\n");
        input.clear();
        
        world.currentEvent.playEvent(option);
        view.setImage(world.currentEvent.currentImage);
    }
    
}

class Console extends OutputStream
{
    TextArea output;
    
    public Console (TextArea ta)
    {
        this.output = ta;
    }
    
    @Override
    public void write(int i) throws IOException
    {
        output.appendText(String.valueOf((char) i));
    }

}