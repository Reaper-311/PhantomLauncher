package com.example.finallauncherrefactored.Projects.MonstersInc;

import java.util.Random;

class Ghouls
{
    double x, y;
    double width, height;
    boolean isAlive;
    int anger;
    Map map;
    
    Ghouls(double x, double y, Map map)
    {
        isAlive = true;
        this.x = x;
        this.y = y;
        this.map = map;
        this.anger = anger;
    }
    
    void move()
    {
        Random r = new Random();
        int randomChangeInX = r.nextInt(3) - 1;
        int randomChangeInY = r.nextInt(3) - 1;
        
        this.x += randomChangeInX;
        this.y += randomChangeInY;
        if(this.x > 0 || this.x > map.width)
        {
           randomChangeInX--; 
        }
        
        if(this.y > 0 || this.y > map.height)
        {
            randomChangeInY--;
        }
    }
    
    double getDistanceToArlo(Arlo player)
    {
        double deltaX = player.x - this.x;
        double deltaY = player.y - this.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    
    boolean sameLocationArlo(Arlo player)
    {
        if(this.getDistanceToArlo(player) == 0)
        {
            return true;
        }
        return false;
    }
    
    boolean canAttack(Arlo player)
    {
        if(this.isAlive == true && player.isAlive == true && 
           this.sameLocationArlo(player))
        {
            return true;
        }
        return false;
    }
}