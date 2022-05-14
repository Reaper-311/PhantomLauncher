package com.example.finallauncherrefactored.Projects.TetraShot;

abstract class  Enemy extends Collider
{
    double xPos;
    double yPos;
    boolean isAlive;//

    Enemy(double cx, double cy, double rx, double ry)
    {
        super(cx, cy, 15, 15);
        this.isAlive = true;

    }

}
