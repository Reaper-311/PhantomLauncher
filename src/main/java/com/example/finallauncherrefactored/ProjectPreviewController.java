package com.example.finallauncherrefactored;

import com.example.finallauncherrefactored.Projects.AirHockey.PongApp;
import com.example.finallauncherrefactored.Projects.ArmWrestling.ArmWrestlingApp;
import com.example.finallauncherrefactored.Projects.BlackJack.BlackJackGame;
import com.example.finallauncherrefactored.Projects.BlackJack.FancyBlackJackApp;
import com.example.finallauncherrefactored.Projects.BlockFight.FIGHT;
import com.example.finallauncherrefactored.Projects.JJPaint.MsPaint;
import com.example.finallauncherrefactored.Projects.MP3Player.MP3app;
import com.example.finallauncherrefactored.Projects.MonstersInc.MontersIncApp;
import com.example.finallauncherrefactored.Projects.MuderEscape.MurderGameApp;
import com.example.finallauncherrefactored.Projects.Stories.StoriesApp;
import com.example.finallauncherrefactored.Projects.SuperRacer2D.SuperRacerApp;
import com.example.finallauncherrefactored.Projects.TikTakToe.App2;
import com.example.finallauncherrefactored.Projects.VeryRealRPG.GameApp;
import com.example.finallauncherrefactored.Projects.Wordle.WordleApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class ProjectPreviewController {

    @FXML
    private Button btn_SuperRacerGoBack;

    @FXML
    private Button btn_SuperRacerPlay;
    @FXML
    private Button btn_VeryRealRPGPlay;

    @FXML
    private Button btn_BlackJack;

    @FXML
    private Button btn_ArmWrestling;

    @FXML
    private Button btn_Wordle;


    @FXML
    private Button btn_MonstersInc;


    @FXML
    private Button btn_JJPaint;

    @FXML
    private Button btn_AirHockey;

    @FXML
    private Button btn_BlockFight;

    @FXML
    private Button btn_TetraShot;

    @FXML
    private Button btn_MurderEscape;

    @FXML
    private Button btn_MP3Player;

    @FXML
    private Button btn_Stories;

    @FXML
    private Button btn_TikTakToe;

    @FXML
    void btn_handleTikTakToe(ActionEvent event) {
        App2 app = new App2();
        app.start(event);
    }

    @FXML
    void btn_handleStories(ActionEvent event) {
        StoriesApp app = new StoriesApp();
        app.start(event);
    }

    @FXML
    void btn_handleMP3Player(ActionEvent event) {
        MP3app app = new MP3app();
        app.start(event);
    }

    @FXML
    void btn_handleMurderEscape(ActionEvent event) {
        MurderGameApp app = new MurderGameApp();
        app.start(event);
    }

    @FXML
    void btn_handleTetraShot(ActionEvent event) {
        com.example.finallauncherrefactored.Projects.TetraShot.GameApp app = new com.example.finallauncherrefactored.Projects.TetraShot.GameApp();
        app.start(event);
    }

    @FXML
    void btn_handleBlockFight(ActionEvent event) {
        FIGHT app = new FIGHT();
        app.start(event);
    }

    @FXML
    void btn_handleAirHockey(ActionEvent event) {
        PongApp app = new PongApp();
        app.start(event);
    }


    @FXML
    void btn_handleJJPaint(ActionEvent event) {
        MsPaint app = new MsPaint();
        app.start(event);
    }

    @FXML
    void btn_handleMonstersInc(ActionEvent event) {
        MontersIncApp app = new MontersIncApp();
        app.start(event);
    }

    @FXML
    void btn_handleWordle(ActionEvent event) {
        WordleApp app = new WordleApp();
        app.start(event);
    }

    @FXML
    void btn_handleArmWrestling(ActionEvent event) {
        ArmWrestlingApp app = new ArmWrestlingApp();
        try {
            app.start(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleVeryRealRPGPlay(ActionEvent event) {
        GameApp app = new GameApp();
        app.start(event);
    }

    @FXML
    void btn_handleBlackJack(ActionEvent event) {
        try {
            FancyBlackJackApp b = new FancyBlackJackApp();
            try {
                b.start(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_HandleGoBack(ActionEvent event) {
        try {
            Main main = new Main();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenuPG1.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_SuperRacerhandlePlay(ActionEvent event) {
        SuperRacerApp racer = new SuperRacerApp();
        racer.start(event);
    }

}
