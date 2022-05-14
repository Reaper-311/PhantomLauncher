package com.example.finallauncherrefactored.Projects.MonstersInc;

class Walls
{
    double x;
    double y;
    double width;
    double height;
    
    Walls(double x, double y)
    {
        this.x = x;
        this.y = y;
        
        double randWidth = 20 + Math.random()* 10;
        double randHeight = 5 + Math.random()* 5;
        
        width = randWidth;
        height = randHeight;
    }
}