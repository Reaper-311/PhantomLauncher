package com.example.finallauncherrefactored;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.Objects;

public class SignInController {

    Main main = new Main();

    @FXML
    private Button btn_Confirm;

    @FXML
    private Button btn_CreateAccount;

    @FXML
    private PasswordField pField_Password;

    @FXML
    private TextField tField_Username;

    @FXML
    private Label label_Error;

    @FXML
    private Button btn_Guest;

    @FXML
    void btn_OnGuest(ActionEvent event) {
        try {
            main.updateAppDataName("GUEST");
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenuPG1.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("phantomlauncher.png")).toExternalForm()));
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
    void btn_OnCreateAccount(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateAccount.fxml")));
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
    void onBtnConfirm(ActionEvent event) {
        if (main.credentialHandshake(tField_Username.getText(), pField_Password.getText())) {
            try {
                Main.updatePFP();
                main.updateAppDataName(tField_Username.getText());
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenuPG1.fxml")));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("phantomlauncher.png")).toExternalForm()));
                window.setOnCloseRequest(e -> {
                    main.resetAppData();
                    window.close();
                });
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!main.credentialHandshake(tField_Username.getText(), pField_Password.getText())) {
            label_Error.setText("Account not found!");
            return;
        }
    }


}
