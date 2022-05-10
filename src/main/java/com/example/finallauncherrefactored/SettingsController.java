package com.example.finallauncherrefactored;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
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

    @FXML
    void btn_handleUploadPicture(ActionEvent event) {
        if (Main.getUserName().contains("GUEST")) {
            label_PFPStatus.setText("Error: Guest Account");
            return;
        }

        FileChooser fc = new FileChooser();
        File list = fc.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
        if (list != null) {
            String name = list.getName();
            String[] check = name.split("\\.");
            if(check[1].contains("png")) {
                System.out.println("Added: " + list.getName());


                File ufile = new File(Main.userPath + "/Accounts/" + Main.encrypt(Main.getUserName()) + ".txt");
                File destfile = new File(Main.userPath + "/Images/" + Main.encrypt(Main.getUserName()) + ".png");
                if (destfile.exists()) {
                    destfile.delete();
                    System.out.println("Debug: Removing existings PFP!");
                }
                try {
                    Files.copy(list.toPath(), destfile.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                label_PFPStatus.setText("Set Picture to " + list.getName() + "!");

                try {
                    ArrayList<String> uContents = new ArrayList<>();
                    Scanner uscan = new Scanner(ufile);
                    while (uscan.hasNextLine()) {
                        uContents.add(uscan.nextLine());
                    }
                    uscan.close();
                    for (int i = 0; i < uContents.size(); i++) {
                        String[] split = uContents.get(i).split(":");
                        if (split[0].contains(Objects.requireNonNull(Main.encrypt("PFP")))) {
                            split[1] = Main.encrypt(Main.getUserName()) + ".png";
                            String comb = split[0] + ":" + split[1];
                            uContents.set(i, comb);
                            break;
                        }
                    }

                    PrintStream ps = new PrintStream(ufile);
                    for (String s : uContents) {
                        ps.println(s);
                    }
                    ps.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                File currentphoto = new File(System.getProperty("user.dir") + "CurrentPicture.png");
                if (currentphoto.exists()) {
                    currentphoto.delete();
                    File newphoto = new File(System.getProperty("user.dir") + "CurrentPicture.png");
                    try {
                        Files.copy(currentphoto.toPath(), newphoto.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    File newphoto = new File(System.getProperty("user.dir") + "CurrentPicture.png");
                    try {
                        Files.copy(currentphoto.toPath(), newphoto.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

}

