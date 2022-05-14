package com.example.finallauncherrefactored.Projects.MuderEscape;

class Survivor
{

    int x;
    int y;
    boolean isAlive;
    Map map;

    Survivor(int x, int y, Map map)
    {
        this.x = x;
        this.y = y;
        this.map = map;
    }

    void move(int dx, int dy)
    {
        this.x += dx;
        this.y += dy;
    }

    void goUp()
    {
        move(0, -1);  
        if (checkWallCollision() == true)
        {
            move(0, 1);
        }
    }

    void goDown()
    {  
        move(0, 1);
        if (checkWallCollision() == true)
        {
            move(0, -1);
        }
    }

    void goRight()
    {
        move(1,0); 
        if (checkWallCollision() == true)
        {
            move(-1, 0);
        }
    }

    void goLeft()
    {
        move(-1,0);
        if (checkWallCollision() == true)
        {
            move(1, 0);
        }
    }

    boolean checkWallCollision()
    {
        for (Tile t : map.tiles)
        {
            if (t.canPass == false && t.x == this.x && t.y == this.y)
            {
                return true;
            }
        }
        return false;
    }
}