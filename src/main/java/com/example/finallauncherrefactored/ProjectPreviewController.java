package com.example.finallauncherrefactored;

import com.example.finallauncherrefactored.Projects.SuperRacer2D.SuperRacerApp;
import com.example.finallauncherrefactored.Projects.VeryRealRPG.GameApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
    void btn_handleVeryRealRPGPlay(ActionEvent event) {
        GameApp app = new GameApp();
        app.start(event);
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
