package com.example.finallauncherrefactored.Projects.SuperRacer2D;

class Oil extends Obstacle
{
    Oil(double x, double y)
    {
        super(x, y);

        double randWidth = 40 + Math.random() * 100;
        double randHeight = 30 + Math.random() * 30;

        width = randWidth;
        height = randHeight;
    }


}
