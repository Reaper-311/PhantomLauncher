package com.example.finallauncherrefactored.Projects.CoreyChase;

public class Mover
{
    int x, y;

    Mover(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    void move(int dx, int dy)
    {
        this.x += dx;
        this.y += dy;
    }
}
