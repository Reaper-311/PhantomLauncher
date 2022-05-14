package com.example.finallauncherrefactored.Projects.Lives;

class FloorTile {
    double x, y, width, height, centerX, centerY;
    FloorTile ( double x, double y, double width, double height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        centerX = x+(width/2);
        centerY = y+(height/2);
    }
    
}