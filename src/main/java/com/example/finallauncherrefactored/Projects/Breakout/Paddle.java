package com.example.finallauncherrefactored.Projects.Breakout;

class Paddle extends Collider
{
    double width;

    Paddle(double cx, double cy, double width)
    {
        super(cx, cy, width/2, 6);
    }
}
