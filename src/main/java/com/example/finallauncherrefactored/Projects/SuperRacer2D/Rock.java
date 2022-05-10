package com.example.finallauncherrefactored.Projects.SuperRacer2D;

class Rock extends Obstacle
{
    Rock(double x, double y)
    {
        super(x, y);

        double randWidth = 40 + Math.random() * 40;
        double randHeight = 50 +  Math.random() * 50;

        width = randWidth;
        height = randHeight;
    }


}
