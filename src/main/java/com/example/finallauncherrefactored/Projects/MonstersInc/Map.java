package com.example.finallauncherrefactored.Projects.MonstersInc;

class Map
{
    Arlo arlo;
    Ghouls[] ghouls;

    Key key;
    Health healthbar;
    Vent vent;
    Walls[] walls;
    
    int width;
    int height;

    Map()
    {
        this.width = 30;
        this.height = 20;

        arlo = new Arlo(width / 2, height / 2, this);

        ghouls = new Ghouls[10];
        key = new Key(28, 19);
        healthbar = new Health(1, 19);
        vent= new Vent(9, 9);
        walls = new Walls[5];
        
        generateWalls();
        generateGhouls();
    }

    void generateGhouls()
    {
        for(int i = 0; i < ghouls.length; i++)
        {

            double randX = (int) (Math.random() * width);
            double randY = (int) (Math.random() * height);

            Ghouls g = new Ghouls(randX, randY, this);

            ghouls[i] = g;
        }
    }
    
    void generateWalls()
    {
        for(int i = 0; i < walls.length; i++)
        {
           double randX = (int) (Math.random() * width);
           double randY = (int) (Math.random() * height);
           
           Walls w = new Walls(randX, randY);
           
           walls[i] = w;
        }
    }

    void attackArlo()
    {
        for(Ghouls g : ghouls)
        {
            if(g.canAttack(arlo) == true)
            {
                arlo.health--;
            }
        }
    }
    
    void bounceX()
    {
        arlo.dx *= -1;
    }
    
    void bounceY()
    {
        arlo.dy *= -1;
    }

    void keepInBound() // fix bottom and right bounds
    {
        if (arlo.x < 0)
        {
            arlo.x = 0;
        }
        if (arlo.x > width)  
        {
            arlo.x = width;
        }
        if (arlo.y < 0)
        {
            arlo.y = 0;
        }
        if (arlo.y > height)
        {
            arlo.y = height;
        }
    }
    
    void stayInBound() // for ghouls
    {
        for (Ghouls g : ghouls)
        {
            if (g.x < 0)
            {
                g.x = 0; 
            }
            if (g.x > width)
            {
                g.x = width;
            }
            if (g.y < 0)
            {
                g.y = 0; 
            }
            if (g.y > height)
            {
                g.y = height;
            }
        }
    }
    
    void update()
    {
        arlo.move();
        keepInBound();
        stayInBound();
        attackArlo();
        for(Ghouls g : ghouls)
        {
            if(g.isAlive == true)
            {
                g.move(); 
            }
        }
    }
    
    boolean isGameOver()
    {
        if(arlo.isAlive && arlo.health > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    void reset()
    {
        arlo.x = 2;
        arlo.y = 2;
        
        arlo.dx = 0;
        arlo.dy = 0;
    }
}