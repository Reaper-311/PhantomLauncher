package com.example.finallauncherrefactored.Projects.MonstersInc;

class Arlo
{
    enum Direction {N, S, E, W, NONE}

    double x, y;
    boolean isAlive;
    double dx;
    double dy;
    double width, height;
    Map map; //declare map

    boolean hasKey;
    
    Direction dir;
    int health = 10;

    Arlo (double x, double y, Map map)
    {
        isAlive = true;
        this.x = 2;
        this.y = 2;
        dx = 0;
        dy = 0;
        width = 20;
        height = 20;
        this.health = 10;
        dir = Direction.NONE;
        this.map = map;
    }
    
    void move()
    {
        x += dx;
        y += dy;

        //friction :)
        dx *= 0.98;
        dy *= 0.98;

        //stop
        if (Math.abs(dx) < 0.001)
        {
            dx = 0;
        }

        if (Math.abs(dy) < 0.001)
        {
            dy = 0;
        }

        if (dir == Direction.N) y--;
        else if (dir == Direction.S) y++;
        else if (dir == Direction.E) x++;
        else if (dir == Direction.W) x--;
    }

    void goNorth() { dir = Direction.N;  }

    void goSouth() { dir = Direction.S;  }

    void goEast() { dir = Direction.E;  }

    void goWest() { dir = Direction.W;  }

    void accelerate(double ddx,double ddy)
    {
        dx =+ ddx;
        dy += ddy;
    }
    
    //Gety keyy
    
    double getDistanceToKey(Key object)
    {
        double deltaX = object.x - this.x;
        double deltaY = object.y - this.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    
    boolean sameLocationKey(Key object)
    {
        if(this.getDistanceToKey(object) == 0)
        {
            return true;
        }
        return false;
    }
    
    //Gety In dee vent
    
    double getDistanceToVent(Vent object)
    {
        double deltaX = object.x - this.x;
        double deltaY = object.y - this.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    
    boolean sameLocationVent(Vent object)
    {
        if(this.getDistanceToVent(object) == 0)
        {
            return true;
        }
        return false;
    }
    
    boolean canPickUpKey(Key object)
    {
        if (sameLocationKey(object) == true)
        {
            return true; 
        }
        return false;
    }
    
    boolean hasPickedUpKey()
    {
        return true;
    }
    
    boolean openVent(Vent v)
    {
        if (this.getDistanceToVent(v) == 0 && hasPickedUpKey() == true)
        {
            return true;
        }
        return false;
    }
}