package com.example.finallauncherrefactored;

import com.example.finallauncherrefactored.Projects.BlackJack.FancyBlackJackApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class MainMenuPG2Controller {

    @FXML
    private Button btn_BlackJack;

    @FXML
    private Button btn_Close;

    @FXML
    private Button btn_Settings;

    @FXML
    private Button btn_SignOut;

    @FXML
    private Button btn_backPage;

    @FXML
    private Button btn_nextPage;

    @FXML
    private ImageView imgview_BlackJack;

    @FXML
    private ImageView imgview_PFP;

    @FXML
    void btn_handleBackPage(ActionEvent event) {
        Main.updatePFP();
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenuPG1.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setOnCloseRequest(e -> {
            Main.resetAppData();
            window.close();
        });
        window.show();
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
    void btn_handleClose(ActionEvent event) {
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Main.resetAppData();
        window.close();
    }

    @FXML
    void btn_handleNextPage(ActionEvent event) {

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
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

