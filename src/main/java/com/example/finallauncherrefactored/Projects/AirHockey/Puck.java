package com.example.finallauncherrefactored.Projects.AirHockey;

class Puck
{
    double x, y, dx, dy;
    double width;
    double height;
    Pong game;
    Puck (double x, double y, double width, double height, Pong game)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        dx = -4;
        dy = 3.5;
        this.game = game;
    }
    
    void move()
    {
        x += dx;
        y += dy;
        collide();
    }
    
    void collide()
    {
        if(x < 0)
        {
            dx *= -1;
            
        }
        else if (x + width > game.width)
        {
            dx *= -1;
            
        }
        else if(y < 0)
        {
            dy *= -1;
            
        }
        else if (y + height > game.height)
        {
            dy *= -1;
        }
    }
    
    boolean hasScoredLeft()
    {
        double centerX = this.x + this.width/2;
        double centerY = this.y + this.height/2;
        
        if (centerX > game.goalLeft.x &&
        centerX < game.goalLeft.x + game.goalLeft.width &&
        centerY > game.goalLeft.y &&
        centerY < game.goalLeft.y + game.goalLeft.height)
        {
            return true;
        }
        return false;
    }
    boolean hasScoredRight()
    {
        double centerX = this.x + this.width/2;
        double centerY = this.y + this.height/2;
        
        if (centerX > game.goalRight.x &&
        centerX < game.goalRight.x + game.goalRight.width &&
        centerY > game.goalRight.y &&
        centerY < game.goalRight.y + game.goalRight.height)
        {
            return true;
        }
        return false;
    }
}