package com.example.finallauncherrefactored.Projects.Lives;

class Button {
    double x, y, width, height;
    Game game;
    Button (double x, double y, double width, double height, Game game) {
        this.x=x;
        this.y=y;
        this.game=game;
        this.width = width;
        this.height = height;
    }
}