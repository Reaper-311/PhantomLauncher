package com.example.finallauncherrefactored.Projects.AirHockey;

class Paddle
{
    double x, y;
    double width;
    double height; 
    Paddle (double x, double y, double height, double width)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    void moveDown()
    {
        y += 5;
    }

    void moveUp()
    {
        if (y > 0)
        {
            y += -5;
        }
    }

    void moveLeft()
    {
        if (x > 0)
        {
            x += -5;
        }
    }

    void moveRight()
    {
        x += 5;
    }
}
