package com.example.finallauncherrefactored.Projects.TetraShot;

class Game
{ 
    double width, height;

    int level;
    int levelTime;
    int score;
    Player player;
    Enemy[] enemies;
    //Laser[] lasers;

    Game()
    {
        width = 500;
        height = 600;
        level = 1;
        levelTime = 0;
        score = 0;

        player = new Player(width / 2, height / 2, 10, 10, 3);
        spawnEnemies();

        
    }
    void spawnEnemies()
    {

        enemies = new Enemy[2 + level];

        for(int i = 0; i < enemies.length; i++)
        {
            double x = Math.random() * 500;
            double y = Math.random() * 600;

            while (x < 75 || x > 425)
            {
                x = Math.random() * 500;
                
            }

            while (y < 75 || y > 525)
            {   
                y = Math.random() * 600;
            }

            while(x > 200 && x < 300 && y > 250 && y < 350)
            {
                x = Math.random() * 500;
                y = Math.random() * 600;
            }
            

            if (i < 6 - level)
            {
                enemies[i] = new Stationary(x, y);
            }
            else
            {
                enemies[i] = new Chase(x, y);
            }

        }

    }

    boolean noMoreEnemies()
    {
        for(Enemy a : enemies)
        {
            if(a.isAlive)
            {
                return false;
            }
        }
        return true;
    }

    
    void checkHit()
    {
        //check if an enemy needs to die
        for(Enemy a : enemies)
        {
            for(Laser laser : player.lasers)
            { if(a.isAlive && laser.isFiredVertically && laser.isFiredHorizontally && laser.intersects(a))
                {
                    a.isAlive = false;
                    laser.reset();
                    score++;
                }

            }
        }

        //check if the player got hit by enemy
        for(Enemy a : enemies)
        {

            if(a.intersects(player) && a.isAlive)
                
            {
                player.isAlive = false;

            }

        }
    }

    void updateLasers()
    {// player's lasers
        for(Laser laser : player.lasers)
        {
            if(laser.isFiredVertically || laser.isFiredHorizontally)
            {
                laser.update();
                if(laser.isOutOfBounds(0, 0, width, height))
                {
                    laser.reset();
                }
            }
        }

        

    }

    void moveEnemies()
    {

        for(Enemy a: enemies)
        {
            if (a.getClass() == Stationary.class)
            {
                continue;
            }
            if((player.cx != width/2) || (player.cy != height/2))
            {
                
            
            if(player.cx<=a.cx)
            {
                a.cx-=1;
            }
            if(player.cy<=a.cy)
            {
                a.cy-=1;
            }
            if(player.cx>=a.cx)
            {
                a.cx+=1;
            }
            if(player.cy>=a.cy)
            {
                a.cy+=1;
            }
        }
        }  

    }

    void levelUp()
    {
        //we need to adjust based on the concept of our game
        score += getTimeBonus();
        level++;
        levelTime = 0;
        spawnEnemies();
        player = new Player(width / 2, height / 2, 10, 10, 3);
    }

    int getTimeBonus()
    {
        return 30 - levelTime;
    }

    void update()
    {
        updateLasers();
        checkHit();

        moveEnemies();
        if(noMoreEnemies())
        {
            levelUp();

        }

        

    }
}

