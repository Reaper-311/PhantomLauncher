package com.example.finallauncherrefactored.Projects.ArsonDiner;

public class FoodSpawn extends Entity{
    
    FoodSpawn() {
        this.x = 0;
        this.y = 0;
        this.height = 60;
        this.width = 60;
    }
    
    FoodSpawn(double xCoord, double yCoord) {
        this.x = xCoord;
        this.y = yCoord;
        this.height = 60;
        this.width = 60;
    }
    
    FoodSpawn(double xCoord, double yCoord, double height, double width) {
        this.x = xCoord;
        this.y = yCoord;
        this.height = height;
        this.width = width;
    }
    
}