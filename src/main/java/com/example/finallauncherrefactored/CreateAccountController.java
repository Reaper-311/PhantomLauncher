package com.example.finallauncherrefactored;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CreateAccountController {

    Main main = new Main();

    @FXML
    private Button btn_Confirm;

    @FXML
    private Button btn_GoBack;

    @FXML
    private Label label_Error;

    @FXML
    private PasswordField pField_Password;

    @FXML
    private TextField tField_Username;

    @FXML
    void pField_handleKeyPressed(KeyEvent event) {
        if (event.getCode() != KeyCode.ENTER) {
            return;
        }
        String enteredUser = tField_Username.getText();
        String enteredPass = pField_Password.getText();

        if (main.checkAccountExistence(enteredUser)) {
            label_Error.setText("Account '" + enteredUser + "' already exists!");
            return;
        }

        if (!main.checkAccountExistence(enteredUser)) {
            main.createAccount(enteredUser, enteredPass);
        }

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

    /**
     * Confirmation button handler.
     *
     * Checks to see if credentials already exist.
     * If so, duplication is prevented.
     * If not, account is created and user is prompted to sign in with newly created account.
     *
     * Method automatically called by button interaction via CreateAccount.fxml
     * @param event
     */
    @FXML
    void btn_OnConfirm(ActionEvent event) {
        String enteredUser = tField_Username.getText();
        String enteredPass = pField_Password.getText();

        if (main.checkAccountExistence(enteredUser)) {
            label_Error.setText("Account '" + enteredUser + "' already exists!");
            return;
        }

        if (!main.checkAccountExistence(enteredUser)) {
            main.createAccount(enteredUser, enteredPass);
        }

        returnToSignIn(event);
    }

    /**
     * Simply returns the user back to the SignInView page.
     *
     * Method automatically called by button interaction via CreateAccount.fxml
     * @param event
     */
    @FXML
    void btn_OnGoBack(ActionEvent event) {
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

    /**
     * Private method to return user to SignInView page.
     *
     * Intakes event from node.
     * @param event
     */
    private void returnToSignIn(ActionEvent event) {
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
