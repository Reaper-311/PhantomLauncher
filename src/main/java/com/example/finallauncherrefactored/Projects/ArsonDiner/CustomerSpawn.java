package com.example.finallauncherrefactored.Projects.ArsonDiner;

public class CustomerSpawn extends Entity{
    
    CustomerSpawn() {
        this.x = 0;
        this.y = 0;
        this.height = 100;
        this.width = 100;
    }
    
    CustomerSpawn(double xCoord, double yCoord) {
        this.x = xCoord;
        this.y = yCoord;
        this.height = 100;
        this.width = 100;
    }
    
    CustomerSpawn(double xCoord, double yCoord, double height, double width) {
        this.x = xCoord;
        this.y = yCoord;
        this.height = height;
        this.width = width;
    }
    
}