package com.example.finallauncherrefactored.Projects.VeryRealRPG;

import javafx.scene.image.Image;
class Enemy
{
    Monster m;
    int x ,y, id;
    double HP;
    Image sprite;
    boolean isAlive;
    String name;
    int state;

    Enemy(Monster m, int x, int y)
    {
        this.m = m;
        this.x = x;
        this.y = y;
        this.id = m.id;
        this.HP = m.maxHP;
        this.isAlive = true;
        this.name = m.name;
        this.state = 0;
    }
    Enemy(int x, int y)
    {
        this.m = Monster.TOAD;
        this.x = x;
        this.y = y;
        this.id = m.id;
        this.HP = m.maxHP;
        this.isAlive = true;
        this.name = m.name;
        this.state = 0;
    }
    Enemy()
    {
        this.m = Monster.PLAYER;
        this.x = 100;
        this.y = 100;
        this.isAlive = true;
        this.HP = m.maxHP;
        this.id = m.id;
        this.name = "Player";
        this.state = 0;
    }

    public void checkForDead()
    {
        if (this.HP <= 0)
        {
            this.isAlive = false;
        }
        if (this.HP > m.maxHP)
        {
            HP = m.maxHP;
        }
    }
}

