package com.example.finallauncherrefactored.Projects.MuderEscape;

class Game
{
    Survivor survivor;
    Murderer[] chasers;
    Map map;
    Key key;
    int clock;
    boolean gameOver;
    boolean youWin;

    Game()
    {
        map = new Map();
        survivor = new Survivor(4, 10, map);
        chasers = new Murderer[3];
        chasers[0] = new Murderer(46, 27, map, survivor);
        chasers[1] = new Murderer(10, 6, map, survivor);
        chasers[2] = new Murderer(9, 26, map, survivor);
        key = new Key(survivor);
        this.gameOver = false;
        this.youWin = false;
        clock = 0;
    }
    void checkGameOver()
    {
        for (Murderer m : chasers)
        {
            if ((m.x == survivor.x) && (m.y == survivor.y))
            {
                this.gameOver = true;
            }
        }
    }
    
    void checkWin()
    {
        if (survivor.x == 1)
        {
            this.youWin = true;
        }
    }

    void update()
    {
        clock++;
        if (clock == 20)
        {
            
            moveChasers();
            clock = 0;
        }
        key.checkPosession();
        if (key.inPosession == true)
        {
            openDoors();
        }
        checkWin();
        checkGameOver();
    
    }
    void openDoors()
    {
        for (Tile t : map.tiles)
        {
            if (t.getClass() == Door.class)
            {
                t.canPass = true;
            }
        }
    }

    void moveChasers()
    {
        for (Murderer chaser : chasers)
        {

            if ((Math.abs(chaser.x - survivor.x) > 15) || ((chaser.y - survivor.y) > Math.abs(15)))
            {
                chaser.randomMove();
            }
            else 
            {
                chaser.chase();
            }

    
        }

    }
    
    
}