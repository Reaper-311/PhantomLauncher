package com.example.finallauncherrefactored;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main extends Application {
    public static final String userPath = System.getProperty("user.dir") + "/Users";
    public static final String appDataPath = System.getProperty("user.dir") + "/AppData.txt";
    public String currentUser = "";

    @Override
    public void start(Stage stage) throws Exception {
        File appdata = new File(System.getProperty("user.dir") + "/AppData.txt");
        if (!appdata.exists()) {
            appdata.createNewFile();
            System.out.println("Created new AppData File!");
            try {
                PrintStream ps = new PrintStream(appdata);
                ps.println("StaySignedIn:false");
                ps.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        resetPFP();

        if (checkPersistence()) {
            currentUser = getPersistenceValue();
            System.out.println("Debug: Persistence value found");
            loginWithPersistence();
            FXMLLoader load = new FXMLLoader(getClass().getResource("MainMenuPG1.fxml"));
            Parent root = load.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return;
        }



        FXMLLoader load = new FXMLLoader(getClass().getResource("SignInView.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        stage.setTitle("Phantom");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Public static method that updates the MainMenu's PFP to the currently signing in user.
     */
    public static void updatePFP() {

    }

    /**
     * Method for the Main class that resets the "CurrentPicture.png" image to the "DefaultPFP.png"
     * Resetting this is necessary for how FXML files are loaded - the FXML node is set to a specific path, so modifying
     * the "CurrentPicture.png" is how to show custom Profile Pictures.
     * Returns nothing.
     */
    public void resetPFP() {
        File currentpicture = new File(getClass().getResource("CurrentPicture.png").getPath());
        File defaultpicture = new File(getClass().getResource("DefaultPFP.png").getPath());
        if (currentpicture.exists()) {
            currentpicture.delete();
        }
        try {
            Files.copy(defaultpicture.toPath(), currentpicture.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private method called on startup.
     * Method updates the AppData.txt file when user launches application.
     */
    private void loginWithPersistence() {
        System.out.println("Attempted Persistent Login");
        File f = new File(appDataPath);
        ArrayList<String> ar = new ArrayList<>();
        try {
            Scanner fscan = new Scanner(f);
            while (fscan.hasNextLine()) {
                ar.add(fscan.nextLine());
            }
            fscan.close();
            ar.add("name:" + currentUser);
            PrintStream ps = new PrintStream(f);
            for (String s : ar) {
                ps.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private method called on startup.
     * Method returns a String containing the persistence stored in AppData.txt
     * Returns null if nothing is found.
     * @return
     */
    private String getPersistenceValue() {
        File f = new File(appDataPath);
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
        for (String s : ar) {
            String[] split = s.split(":");
            if (split[0].contains("persistence")) {
                return split[1];
            }
        }
        return null;
    }

    /**
     * Private method called on startup.
     * Method returns true if there is a persistent user.
     * Method returns false if there is no persistent user.
     * @return
     */
    private boolean checkPersistence() {
        File f = new File(appDataPath);
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

        for (String s : ar) {
            if (s.contains("persistence")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that grabs the currently signed in user's name in the AppData.txt file.
     *
     * Returns name as a plain String if found.
     * Returns null if no name is found.
     * @return
     */
    public static String getUserName() {
        File f = new File(appDataPath);
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
        for (String s : ar) {
            if (s.contains("name")) {
                String[] split = s.split(":");
                return split[1];
            }
        }
        return null;
    }

    /**
     * Updates the AppData.txt file to include the currently signed-in user.
     *
     * Intakes the name of the currently logged-in user.
     * @param name
     */
    public void updateAppDataName(String name) {
        try {
            File appdata = new File(System.getProperty("user.dir") + "/AppData.txt");
            ArrayList<String> ar = new ArrayList<>();
            Scanner fscan = new Scanner(appdata);
            while (fscan.hasNextLine()) {
                ar.add(fscan.nextLine());
            }
            fscan.close();
            ar.add("name:" + name);
            PrintStream ps = new PrintStream(appdata);
            for (String s : ar) {
                ps.println(s);
            }
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that intakes nothing. It is called on shutdown
     * to clean up the AppData.txt file for next launch.
     */
    public static void resetAppData() {
        try {
            File appdata = new File(System.getProperty("user.dir") + "/AppData.txt");
            ArrayList<String> ar = new ArrayList<>();
            Scanner fscan = new Scanner(appdata);
            while (fscan.hasNextLine()) {
                ar.add(fscan.nextLine());
            }
            fscan.close();
            ar.removeIf(e -> e.contains("name"));
            PrintStream ps = new PrintStream(appdata);
            for (String s : ar) {
                ps.println(s);
            }
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that intakes nothing. It is called on sign out
     * to clean up the AppData.txt file for all persistence
     * data to ensure a clean reset.
     *
     * Performs all actions of resetAppData(), just includes the
     * persistence wipe.
     */
    public static void resetAppDataPersistence() {
        try {
            File appdata = new File(appDataPath);
            ArrayList<String> ar = new ArrayList<>();
            Scanner fscan = new Scanner(appdata);
            while (fscan.hasNextLine()) {
                ar.add(fscan.nextLine());
            }
            fscan.close();
            ar.removeIf(e -> e.contains("name"));
            ar.removeIf(e -> e.contains("persistence"));
            for (int i = 0; i < ar.size(); i++) {
                String[] split = ar.get(i).split(":");
                if (split[0].contains("Stay")) {
                    split[1] = "false";
                    String comb = split[0] + ":" + split[1];
                    ar.set(i, comb);
                }
            }
            PrintStream ps = new PrintStream(appdata);
            for (String s : ar) {
                ps.println(s);
            }
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks to see if account username matches account password.
     *
     * Returns true if successful, false if unsuccessful.
     * @param username
     * @param password
     * @return
     */
    public boolean credentialHandshake(String username, String password) {
        File newFile = new File(userPath + "/Accounts/" + encrypt(username) + ".txt");

        if (!newFile.exists()) {
            return false;
        }


        ArrayList<String> ar = new ArrayList<>();
        try {
            Scanner fileScan = new Scanner(newFile);
            while (fileScan.hasNextLine()) {
                ar.add(fileScan.nextLine());
            }
            fileScan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String s : ar) {
            if (s.contains(Objects.requireNonNull(encrypt(password)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Commits the creation of an account to disk.
     * File is created in Users/Accounts
     * File name is the username encrypted. Contents are encrypted.
     * @param username
     * @param password
     */
    public void createAccount(String username, String password) {
        File newFile = new File(userPath + "/Accounts/" + encrypt(username) + ".txt");

        if (newFile.exists()) {
            System.err.println("Error: Attempted creation of already existing account.\n Check to see if creation is checked beforehand. (Main.createAccount())");
        }

        try {
            if (newFile.createNewFile()) {
                System.out.println("Debug: Successfully created new User File!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintStream ps = new PrintStream(newFile);
            ps.println(encrypt(password));
            ps.println(encrypt("PFP:" + encrypt("null")));
            //Add other required to be saved data to user file

            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if an account exists based on a given Username.
     * Returns true if account exists.
     * Returns false if account does not exist.
     * @param CheckedUserName
     * @return
     */
    public boolean checkAccountExistence(String CheckedUserName) {
        File newFile = new File(userPath + "/Accounts/" + encrypt(CheckedUserName) + ".txt");
        return newFile.exists();
    }

    /**
     * Encryption method that returns a given string encrypted using the SHA-512 algorithm.
     * @param inputString
     * @return
     */
    public static String encrypt(String inputString) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(inputString.getBytes());
            BigInteger digestedInt = new BigInteger(1, messageDigest);
            StringBuilder hashedText = new StringBuilder(digestedInt.toString(16));
            while (hashedText.length() < 32) {
                hashedText.insert(0, "0");
            }
            return hashedText.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
