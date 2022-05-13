package com.example.finallauncherrefactored;

import com.example.finallauncherrefactored.Projects.Breakout.BreakoutApp;
import com.example.finallauncherrefactored.Projects.Snake.SnakeApp;
import com.example.finallauncherrefactored.Projects.SpaceInvaders.SpaceInvadersApp;
import com.example.finallauncherrefactored.Utils.ImagePool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.Objects;

public class MainMenuController {

    @FXML
    private Button btn_Close;

    @FXML
    private Button btn_Breakout;

    @FXML
    private Button btn_Settings;

    @FXML
    private Button btn_SignOut;

    @FXML
    private Button btn_Snake;

    @FXML
    private Button btn_SpaceInvaders;

    @FXML
    private Button btn_SuperRacer2D;

    @FXML
    private Button btn_nextPage;

    @FXML
    private ImageView imgview_Breakout;

    @FXML
    private ImageView imgview_Snake;

    @FXML
    private ImageView imgview_SpaceInvaders;

    @FXML
    private ImageView imgview_SuperRacer2D;

    @FXML
    private ImageView imgview_PFP;

    @FXML
    void btn_handleClose(ActionEvent event) {
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Main.resetAppData();
        window.close();
    }

    @FXML
    void btn_handleBreakout(ActionEvent event) {
        BreakoutApp b = new BreakoutApp();
        b.start(event);
    }

    @FXML
    void btn_handleNextPage(ActionEvent event) {
        Main.updatePFP();
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenuPG2.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("phantomlauncher.png")).toExternalForm()));
        window.setOnCloseRequest(e -> {
            Main.resetAppData();
            window.close();
        });
        window.show();
    }

    @FXML
    void btn_handleSettings(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SettingsView.fxml")));
            Scene scene = new Scene(root);
            Stage window= new Stage();
            window.initModality(Modality.WINDOW_MODAL);
            window.initOwner(((Node)event.getSource()).getScene().getWindow());
            window.setScene(scene);
            window.setTitle("Phantom: Settings");
            window.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("phantomlauncher.png")).toExternalForm()));
            window.setOnCloseRequest(e -> {
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleSignOut(ActionEvent event) {
        Main main = new Main();
        main.resetPFP();
        Main.resetAppData();

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignInView.fxml")));
            Scene scene = new Scene(root);

            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("phantomlauncher.png")).toExternalForm()));
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleSnake(ActionEvent event) {
        SnakeApp snakeApp = new SnakeApp();
        snakeApp.start(event);
    }

    @FXML
    void btn_handleSpaceInvaders(ActionEvent event) {
        SpaceInvadersApp space = new SpaceInvadersApp();
        space.start(event);
    }

    @FXML
    void btn_handleSuperRacer2D(ActionEvent event) {
        try {
            Main main = new Main();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SuperRacerPreview.fxml")));
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

}
