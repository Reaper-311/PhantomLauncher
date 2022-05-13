package com.example.finallauncherrefactored.Projects.VeryRealRPG;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class SideMenu
{
    Image g;
    int selected;
    SideMenu()
    {
        this.g = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/menu.png")));
        this.selected = 0;
    }

    public void move(int dir)
    {
        if ((selected + dir) <= 3 && (selected + dir) >= 0)
        {
            this.selected += dir;
        }
    }
}
