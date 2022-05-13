package com.example.finallauncherrefactored.Projects.VeryRealRPG;

import javafx.scene.image.Image;
import java.util.ArrayList;
class Encounter
{
    Enemy incoming;
    ArrayList<Enemy> enemies;
    int id;
    String desc;
    double exp;
/*
    Encounter[] fights = new Encounter[]{
            (new Encounter()),



        };
        */

    Encounter()
    {
        this.enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(Monster.TOAD ,220, 100));
        enemies.add(new Enemy(Monster.TOAD, 620, 100));
        this.id = 0;
        this.exp = 15;
        this.desc = "You come across two bloodthirsty toads, which you think might make good target practice.";

    }

    Encounter(ArrayList<Enemy> enemies, double exp, String description)
    {
        this.enemies = enemies;
        this.id = 0;
        this.exp = exp;
        this.desc = description;
    }

    public void refresh(Encounter e)
    {

    }

    public ArrayList<Enemy> getEnemies()
    {
        ArrayList<Enemy> list = new ArrayList<Enemy>();
        for (Enemy e: this.enemies)
        {
            list.add(new Enemy(e.m, e.x, e.y));
        }



        return list;
    }

    public int checkAliveEnemies()
    {
        int c = 0;
        for (Enemy e : enemies)
        {
            if (e.isAlive)
            {
                c++;
            }
        }
        return c;
    }
}
