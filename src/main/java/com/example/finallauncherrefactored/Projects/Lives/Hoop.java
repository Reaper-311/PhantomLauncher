package com.example.finallauncherrefactored.Projects.Lives;

class Hoop {
    double x, y, width, height;
    Game game;
    Hoop (double x, double y, double width, Game game) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=width/2;
        this.game=game;
    }
}