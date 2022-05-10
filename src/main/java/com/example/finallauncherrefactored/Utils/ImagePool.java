package com.example.finallauncherrefactored.Utils;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

public class ImagePool {

    private class Picture {
        String name;
        String filepath;
        Image image;

        Picture(String name, String filepath, Image image) {
            this.name = name;
            this.filepath = filepath;
            this.image = image;
        }
    }

    private ArrayList<Picture> launcherImages = new ArrayList<>();

    File assetsPath;

    private void gatherImages() {

        String[] pathnames = assetsPath.list();

        //This results with null?

        for (String s : pathnames) {
            String tempPath = assetsPath + "/" +  s;
            //System.out.println("Added " + tempPath);
            try {
                launcherImages.add(new Picture(s, tempPath, new Image(new FileInputStream(tempPath))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Image getImage(String imageName) {
        Main ref = new Main();
        File f = new File(Objects.requireNonNull(ref.getClass().getResource(imageName)).toString());
        if (f.exists()) {
            try {
                return new Image(new FileInputStream(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Image getUserImage(String name) {
        String eName = Main.encrypt(name);
        File uFile = new File(System.getProperty("user.dir") + "/UserImages/" + eName + ".png");
        if (uFile.exists()) {
            try {
                return new Image(new FileInputStream(uFile.getPath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!uFile.exists()) {
            try {
                return new Image(new FileInputStream(System.getProperty("user.dir") + "/LauncherImages/CurrentPicture.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public ImagePool() {}




}

