package com.example.finallauncherrefactored.Projects.MuderEscape;

import java.util.ArrayList;

class Map
{
    int width;
    int height;
    int gridsize;
    ArrayList<Tile> tiles;
    Map()
    {
        this.width = 50;
        this.height = 30;
        this.gridsize = 20;
        tiles = new ArrayList<Tile>();
        
        tiles.add(new Door(2, 15));
        tiles.add(new Door(2, 14));
        tiles.add(new Door(2, 16));
        tiles.add(new Wall(41, 28));
        tiles.add(new Wall(41, 27));
        tiles.add(new Wall(41, 26));
        tiles.add(new Wall(41, 25));
        tiles.add(new Wall(41, 24));
        tiles.add(new Wall(42, 24));
        tiles.add(new Wall(43, 24));
        tiles.add(new Wall(45, 24));
        tiles.add(new Wall(46, 24));
        tiles.add(new Wall(38, 24));
        tiles.add(new Wall(39, 24));
        tiles.add(new Wall(40, 24));
        tiles.add(new Wall(38, 23));
        tiles.add(new Wall(38, 22));
        tiles.add(new Wall(38, 21));
        tiles.add(new Wall(47, 24));
        tiles.add(new Wall(27, 3));
        tiles.add(new Wall(27, 4));
        tiles.add(new Wall(27, 6));
        tiles.add(new Wall(27, 7));
        for (int i = 2; i < 49; i++)
        {
            tiles.add(new Wall(i, 2)); 
        }
        
        for (int i = 48; i > 2; i--)
        {
            tiles.add(new Wall(i, 29)); 
            tiles.add(new Wall(48, i)); 
        }
        
        for (int i = 2; i < 14; i++)
        {
            tiles.add(new Wall(2, i));
        }
        
        for (int i = 17; i < 30; i++)
        {
            tiles.add(new Wall(2, i));
        }
        
        for (int i = 2; i < 10; i++)
        {
            tiles.add(new Wall(34, i));
        }
        
        for (int i = 12; i < 15; i++)
        {
            tiles.add(new Wall(34, i));
        }
        
        for (int i = 34; i < 49; i++)
        {
            tiles.add(new Wall(i, 15));
        }
        
        for (int i = 2; i < 6; i++)
        {
            tiles.add(new Wall(i, 20));
        }
        
        for (int i = 7; i < 12; i++)
        {
            tiles.add(new Wall(i, 20));
        }
        
        for (int i = 14; i < 20; i++)
        {
            tiles.add(new Wall(i, 20));
        }
        
        for (int i = 29; i < 34; i++)
        {
            tiles.add(new Wall(i, 20));
        }
        
        for (int i = 35; i < 39; i++)
        {
            tiles.add(new Wall(i, 20));
        }
        
        for (int i = 21; i < 27; i++)
        {
            tiles.add(new Wall(i, 20));
        }
        
        for (int i = 20; i < 24; i++)
        {
            tiles.add(new Wall(11, i));
        }
        
        for (int i = 20; i < 25; i++)
        {
            tiles.add(new Wall(14, i));
        }
        
        for (int i = 20; i < 24; i++)
        {
            tiles.add(new Wall(26, i));
        }
        
        for (int i = 20; i < 25; i++)
        {
            tiles.add(new Wall(29, i));
        }
        
        for (int i = 25; i < 30; i++)
        {
            tiles.add(new Wall(11, i));
        }
        
        for (int i = 26; i < 30; i++)
        {
            tiles.add(new Wall(14, i));
        }
        
        for (int i = 25; i < 30; i++)
        {
            tiles.add(new Wall(26, i));
        }
        
        for (int i = 26; i < 30; i++)
        {
            tiles.add(new Wall(29, i));
        }
        
        for (int i = 32; i < 35; i++)
        {
            tiles.add(new Wall(i, 7));
        }
        
        for (int i = 21; i < 31; i++)
        {
            tiles.add(new Wall(i, 7));
        }
        
        for (int i = 17; i < 20; i++)
        {
            tiles.add(new Wall(i, 10));
        }
        
        for (int i = 12; i < 15; i++)
        {
            tiles.add(new Wall(i, 10));
        }
        
        for (int i = 7; i < 11; i++)
        {
            tiles.add(new Wall(20, i));
        }
        
        for (int i = 2; i < 11; i++)
        {
            tiles.add(new Wall(12, i));
        }
    }
}