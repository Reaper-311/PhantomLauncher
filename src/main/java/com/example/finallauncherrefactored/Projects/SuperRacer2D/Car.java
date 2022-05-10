package com.example.finallauncherrefactored.Projects.SuperRacer2D;

class Car
{
    double x;
    double y;
    double dx;
    double dy;
    double width, height;

    Car(double x, double y)
    {
        this.x = x;
        this.y = y;
        dx = 0;
        dy = 0;
        width = 20;
        height = 20;
    }

    void move()
    {
        x += dx;
        y += dy;

        // friction
        dx *= 0.95;
        dy *= 0.95;

        // come to a stop
        if (Math.abs(dx) < 0.001)
        {
            dx = 0;
        }

        if (Math.abs(dy) < 0.001)
        {
            dy = 0;
        }
    }

    void accelerate(double ddx,double ddy)
    {
        dx += ddx;
        dy += ddy;
    }

    void bounceX()
    {
        dx *= -1.8;
    }

    void bounceY()
    {
        dy *= -1.8;
    }
}
