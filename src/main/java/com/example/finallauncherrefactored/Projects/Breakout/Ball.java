package com.example.finallauncherrefactored.Projects.Breakout;

class Ball extends Collider
{
    double dx, dy;

    Ball(double cx, double cy, double dx, double dy)
    {
        super(cx, cy, 10, 10);

        this.dx = dx;
        this.dy = dy;
    }

    void bounceHorizontal()
    {
        dx *= -1;
    }

    void bounceVertical()
    {
        dy *= -1;
    }

    void stepHorizontal(double dt)
    {
        cx += dx * dt;
    }

    void stepVertical(double dt)
    {
        cy += dy * dt;
    }
}
