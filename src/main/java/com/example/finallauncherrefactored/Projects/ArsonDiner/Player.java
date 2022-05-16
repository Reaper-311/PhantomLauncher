package com.example.finallauncherrefactored.Projects.ArsonDiner;

class Player extends Entity
{
    double dx;
    double dy;
    boolean isCarrying;
    
    Player(double x, double y)
    {
        this.x = x;
        this.y = y;
        dx = 8;
        dy = 8;
        width = 150;
        height = 180;
        isCarrying = false;
    }
    
    void move(String key)
    {
        if (key.equals("W")) {
            this.y -= dy;
        }
        if (key.equals("S")) {
            this.y += dy;
        }if (key.equals("A")) {
            this.x -= dx;
        }if (key.equals("D")) {
            this.x += dx;
        }
    }
    
    void bounceX(){
        dx*= -1;
    }
    
    void bounceY(){
        dy *= -1;
    }
}