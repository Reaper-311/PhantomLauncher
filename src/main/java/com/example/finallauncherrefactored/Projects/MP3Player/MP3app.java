package com.example.finallauncherrefactored.Projects.MP3Player;

import com.example.finallauncherrefactored.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MP3app
{
    Player player;

    Button playPauseBtn, backBtn, skipBtn, shuffleBtn;
    Label songLbl, artistLbl, timeLbl;
    

    TextArea trackList;

    ImageView view;

    public void start(ActionEvent event)
    {
        Stage stage = new Stage();
        player = new Player();

        playPauseBtn = new Button("Play");
        backBtn = new Button("Back");
        skipBtn = new Button("Skip");
        shuffleBtn = new Button("Shuffle"); 

        ButtonBar bb = new ButtonBar();
        bb.getButtons().addAll(backBtn, playPauseBtn, shuffleBtn, skipBtn);

        songLbl = new Label("Song Name");
        artistLbl = new Label("Artist Name");
        timeLbl = new Label("00:00");

        songLbl.setFont(Font.font(20));
        artistLbl.setFont(Font.font(20));
        timeLbl.setFont(Font.font(14));

        HBox infoPane = new HBox(50, songLbl, artistLbl);

        view = new ImageView();
        view.setImage(player.getCurrentSong().cover);
        
        view.setFitHeight(300);
        view.setPreserveRatio(true);

        HBox albumPane = new HBox(view);
        albumPane.setAlignment(Pos.CENTER);

        VBox leftPane = new VBox(5, albumPane, infoPane, timeLbl, bb);

        trackList = new TextArea();
        trackList.setEditable(false);

        HBox mp3Pane = new HBox(leftPane, trackList);
        Scene scene = new Scene(mp3Pane);
        playPauseBtn.setOnAction(this::playPauseClicked);
        backBtn.setOnAction(this::backClicked);
        skipBtn.setOnAction(this::skipClicked);
        shuffleBtn.setOnAction(this::shuffleClicked);

        stage.setScene(scene);
        stage.setTitle("MP3 Player");
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
                
        player.mediaPlayer.setCycleCount(1);
        player.mediaPlayer.play();

        
        
        update();
    }

    void playPauseClicked(ActionEvent e)
    {
        // Code to run when button is clicked...
        if (playPauseBtn.getText().equals("Play"))
        {
            playPauseBtn.setText("Pause");
            player.Play();
        } 
        else
        {
            playPauseBtn.setText("Play");
            player.Play();
        }

        update();
    }

    void backClicked(ActionEvent e)
    {
        // Code to run when button is clicked...
        player.skipBack();
        update();
    }

    void skipClicked(ActionEvent e)
    {
        // Code to run when button is clicked...
        player.skipForeward();
        update();

    }
    void shuffleClicked(ActionEvent e)
    {
        // Code to run when button is clicked...
        player.Shuffle();
        update();
    }

    void update()
    {
        //Reset song name
        songLbl.setText(player.getCurrentSong().name);
        
        artistLbl.setText(player.getCurrentSong().artist);
        
        timeLbl.setText(player.getCurrentSong().time);
        
        // Add all tracks to the sidebar.
        trackList.clear();
        for (Song s : player.tracklist.playlist)
        {
            trackList.appendText(s.name + "\n");
        }
        
        view.setImage(player.getCurrentSong().cover);
    
    }
}