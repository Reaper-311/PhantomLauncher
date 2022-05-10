package com.example.finallauncherrefactored.Projects.Breakout;

class Block extends Collider
{
    int hp;

    Block(double cx, double cy, int hp)
    {
        super(cx, cy, 20, 20);

        this.hp = hp;
    }
}
