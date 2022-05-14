package com.example.finallauncherrefactored.Projects.MuderEscape;

class Murderer
{
    int x;
    int y;
    Map map;
    Survivor survivor;

    Murderer(int x, int y, Map map, Survivor survivor)
    {
        this.x = x;
        this.y = y;
        this.map = map;
        this.survivor = survivor;
    }

    
    void move(int dx , int dy)
    {
        this.x += dx; 
        this.y += dy; 
    }

    void goUp()
    {
        move(0, 1);  
        if (checkWallCollision() == true)
        {
            move(0, -1);
        }
    }

    void goDown()
    {  
        move(0, -1);
        if (checkWallCollision() == true)
        {
            move(0, 1);
        }
    }

    void goRight()
    {
        move(1, 0); 
        if (checkWallCollision() == true)
        {
            move(-1, 0);
        }
    }

    void goLeft()
    {
        move(-1, 0);
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

    void randomMove()
    {
        int r = (int)(Math.random() * 4);

        if (r == 0) { goUp();}
        else if (r == 1) { goDown();}
        else if (r == 2) { goLeft();}
        else { goRight();}
    }

    void chase()
    {
        
        if (this.x > survivor.x)
        {
            goLeft();
        }
        else if (this.x < survivor.x)
        {
            goRight();
        }
        if (this.y > survivor.y)
        {
            goDown();
        }
        else if (this.y < survivor.y)
        {
            goUp();
        }
    }
    
    
    
    
}

    
