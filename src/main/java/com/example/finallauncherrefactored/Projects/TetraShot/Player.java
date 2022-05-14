package com.example.finallauncherrefactored.Projects.TetraShot;

class Player extends Collider
{
        
    
    
    double vx; //The horizontal velocity of the player
    double vy; //The vertical velocity of the player
    double vx2;
    double vy2;

    boolean isAlive; //Determines whether the player is aliv
    Laser[] lasers; //Gives an array of lasers to the player
    //Game game; //Calls the game class to player

    //Constructor for player
    Player(double cx, double cy)
    {
        super(cx, cy, 20.0, 20.0);
    }
    Player(double cx, double cy, double rx, double ry, int shots)
    {
        super(cx, cy, 20.0, 20.0);
        isAlive = true;
        
        lasers = new Laser[shots];
        for (int i = 0; i < lasers.length; i++)
        {
            lasers[i] = new Laser();
        }
    }
    
    
    //Gives Player ability to move left
    void moveLeft()
    {

        cx -= 3;

    }

    //Gives player ability to move right
    void moveRight()
    {

        cx += 3;

    }

    //Gives Player ability to move up
    void moveUp()
    
    {
        cy -= 3;
    }
    
    //Gives Player ability to move down
    void moveDown()
    {
        cy += 3;
    }
    
    void fireLaserUp(double vx, double vy)
    {
        if (!isAlive)
        {
            return;
        }
        
        for (Laser laser : lasers)
        {
            if (!laser.isFiredVertically)
            {
                laser.fireFrom(cx, cy, vx, vy);
                laser.rx = 2;
                laser.ry = 7;
                
                return; //prevent firing more than one laser at a single moment in time
            }
        }
    }
    void fireLaserDown(double vx, double vy)
    {
        if (!isAlive)
        {
            return;
        }
        
        for (Laser laser : lasers)
        {
            if (!laser.isFiredVertically)
            {
                laser.fireFrom(cx, cy, vx, vy);
                laser.rx = 2;
                laser.ry = 7;
                return; //prevent firing more than one laser at a single moment in time
            }
        }
    }
    void fireLaserRight(double vx2, double vy2)
    {if (!isAlive)
        {
            return;
        }
        
        for (Laser laser : lasers)
        {
            if (!laser.isFiredHorizontally)
            {
                laser.fireFrom(cx, cy, vx2, vy2);
                laser.rx = 7;
                laser.ry = 2;
                return; //prevent firing more than one laser at a single moment in time
            }
        }
    }
    void fireLaserLeft(double vx2, double vy2)
    {if (!isAlive)
        {
            return;
        }
        
        for (Laser laser : lasers)
        {
            if (!laser.isFiredHorizontally)
            {
                laser.fireFrom(cx, cy, vx2, vy2);
                laser.rx = 7;
                laser.ry = 2;
                
                return; //prevent firing more than one laser at a single moment in time
            }
        }
    }
    void fireLaser()
    {
        for (int i = 0; i < lasers.length; i++)
        {
            if (lasers[i] == null)
            {
                lasers[i] = new Laser();
                return; // Stop the loop from generating more lasers.
            }
        }
    }
    
    
}