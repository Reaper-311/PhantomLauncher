package com.example.finallauncherrefactored.Projects.AirHockey;

class Goal
{
    int score;
    double x, y, width, height;
    
    Goal(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        this.score = 0;
    }
}