package com.example.finallauncherrefactored.Projects.MuderEscape;

class Tile
{
    
    int x;
    int y;
    boolean canPass;
    Tile(int x, int y, boolean canPass)
    {
        this.x = x;
        this.y = y;
        this.canPass = canPass;
    }
}