package com.example.finallauncherrefactored;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class SettingsController {

    @FXML
    private Button btn_Confirm;

    @FXML
    private Button btn_UploadPicture;

    @FXML
    private Button btn_about;

    @FXML
    private Button btn_goBack;


    @FXML
    private Button btn_SignInDisable;

    @FXML
    private Button btn_SignInEnable;

    @FXML
    private Label label_PFPStatus;

    @FXML
    private Label label_SignedInStatus;


    @FXML
    void btn_handleSignInDisable(ActionEvent event) {
        String username = Main.getUserName();
        assert username != null;

        if (username.contains("GUEST")) {
            label_SignedInStatus.setText("Error: Guest");
            return;
        }

        File f = new File(Main.appDataPath);
        ArrayList<String> ar = new ArrayList<>();
        try {
            Scanner fscan = new Scanner(f);
            while (fscan.hasNextLine()) {
                ar.add(fscan.nextLine());
            }
            fscan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ar.size(); i++) {
            if (ar.get(i).contains("Stay")) {
                String[] split = ar.get(i).split(":");
                split[1] = "false";
                String comb = split[0] + ":" + split[1];
                ar.set(i, comb);
                ar.removeIf(e -> e.contains("persistence"));
                break;
            }
        }

        try {
            PrintStream ps = new PrintStream(f);
            for (String s : ar) {
                ps.println(s);
            }
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        label_SignedInStatus.setText("Status: Disabled");
    }

    @FXML
    void btn_handleSignInEnable(ActionEvent event) {
        String username = Main.getUserName();
        assert username != null;

        if (username.contains("GUEST")) {
            label_SignedInStatus.setText("Error: Guest");
            return;
        }

        File f = new File(Main.appDataPath);
        ArrayList<String> ar = new ArrayList<>();
        try {
            Scanner fscan = new Scanner(f);
            while (fscan.hasNextLine()) {
                ar.add(fscan.nextLine());
            }
            fscan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ar.size(); i++) {
            if (ar.get(i).contains("Stay")) {
                String[] split = ar.get(i).split(":");
                split[1] = "true";
                String comb = split[0] + ":" + split[1];
                ar.set(i, comb);
                ar.removeIf(e -> e.contains("persistence"));
                ar.add("persistence:" + username);
                break;
            }
        }

        try {
            PrintStream ps = new PrintStream(f);
            for (String s : ar) {
                ps.println(s);
            }
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        label_SignedInStatus.setText("Status: Enabled");
    }

    @FXML
    void btn_handleAbout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SettingsAbout.fxml")));
            Scene scene = new Scene(root);
            Stage window= new Stage();
            window.initModality(Modality.WINDOW_MODAL);
            window.initOwner(((Node)event.getSource()).getScene().getWindow());
            window.setScene(scene);
            window.setTitle("Phantom: About");
            window.setOnCloseRequest(e -> {
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleConfirm(ActionEvent event) {
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    void btn_handleGoBack(ActionEvent event) {

    }



}

