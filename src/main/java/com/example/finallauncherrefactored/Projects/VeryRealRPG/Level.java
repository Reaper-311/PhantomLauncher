package com.example.finallauncherrefactored.Projects.VeryRealRPG;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

class Level
{
    Tile[] columns;
    Tile[][] rows;
    int width, height;
    int id;
    String name;
    Image[] tileset;

    Level()
    {
        this.columns = new Tile[16];
        this.rows = new Tile[16][];
        this.width = 16;
        this.height = 16;
        this.id = 0;
        this.tileset = new Image[9];
        this.name = "sky";
    }

    Level(String name, int id)
    {
        this.columns = new Tile[16];
        this.rows = new Tile[16][];
        this.width = 16;
        this.height = 16;
        this.name = name;
        this.id = id;
        this.tileset = new Image[9];
    }

    Level(char[] tiles, int id)
    {
        this.columns = new Tile[16];
        this.rows = new Tile[16][];
        this.width = 16;
        this.height = 16;
        this.name = "tile";
        if (tiles[256] == 't')
        {
            this.name = "tile";
        }
        else if (tiles[256] == 's')
        {
            this.name = "sky";
        }
        this.id = id;

        this.tileset = new Image[10];

        for (int i = 0; i < 16; i++)
        {
            rows[i] = new Tile[16];
            for (int h = 0; h < 16; h++)
            {
                rows[i][h] = new Tile(i, h, tiles[(h * 16) + i]);
            }
        }
    }

    void Initialize()
    {
        /*
        for (int w = 0; w < rows.length; w++)
        {
        rows[w] = new Tile[16];
        for (int h = 0; h < this.height; h++)
        {
        if (h == 8 && w != 1)
        {
        rows[w][h] = new Tile(w,h,2);
        }
        else if (h == 6 && w == 6)
        {
        rows[w][h] = new Tile(w,h,0, new Interactable(2));
        }
        else
        {
        rows[w][h] = new Tile(w,h,3);
        }
        }
        }
         */
        for (int i = 0; i < tileset.length; i++)
        {
            tileset[i] = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/"+name+i+".png")),64.0,64.0,true, true);
        }
    }
}
