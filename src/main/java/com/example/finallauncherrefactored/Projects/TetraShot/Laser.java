package com.example.finallauncherrefactored.Projects.TetraShot;

class Laser extends Collider
{
    double vx; //Horizontal Velocity
    double vy; //Vertical Velocity
    double vx2; //Second Horizontal Velocity
    double vy2; // Second Vertical Velocity
    boolean isFiredVertically; //Determines whether laser has been fired vertically
    boolean isFiredHorizontally; // Determines whether laser has been fired horizontally
    int lifetime; //How long the laster is active
    
    
    //Constructor for the Laser
    Laser()
    {   super(0, 0, 2, 7);
        vx = 0;
        vy = 0;
        vx2 = 0;
        vy2 = 0;
        isFiredVertically = false;
        isFiredHorizontally = false;
        lifetime = 0;
    }
    
    //Updates the laser's position based on it's x and y velocity
    void update()
    {
        if (isFiredVertically)
        {
            cx += vx;
            cy -= vy;
            
            lifetime++;
        }
        if(isFiredHorizontally)
        {
            cx -= vx2;
            cy += vy2;
            
            lifetime++;
        }
    }
    
    //Activates laser from the position of the Player, with an x and y velocity
    void fireFrom(double cx, double cy, double vx, double vy)
    {
        this.cx = cx;
        this.cy = cy;
        this.vx = vx;
        this.vy = vy;
        this.vx2 = vx2;
        this.vy2 = vy2;
        

        
        
      
        
        isFiredVertically = true;
        isFiredHorizontally = true;
    }
    
    //Resets the laser when it hits a border or a collider
    void reset()
    {
        isFiredVertically = false;
        isFiredHorizontally = false;
        cx = 0;
        cy = 0;
        vx = 0;
        vy = 0;
        vx2 = 0;
        vy2 = 0;
        
        lifetime = 0;
    }
    
}