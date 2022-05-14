package com.example.finallauncherrefactored.Projects.MuderEscape;

class Key
{
    int x;
    int y;
    boolean inPosession;
    Survivor survivor;
    Key(Survivor survivor)
    {
        this.survivor = survivor;
        int i = (int)(Math.random() * 4);
        if (i < 1)
        {
            this.x = 46;
            this.y = 4;
        }
        if (i == 1)
        {
            this.x = 25;
            this.y = 27;
        }
        if (i == 2)
        {
            this.x = 46;
            this.y = 27;
        }
        if (i == 3)
        {
            this.x = 5;
            this.y = 27;
        }
        
        this.inPosession = false;
        
        
    }
    
    void checkPosession()
    {
        if ((survivor.x == this.x) && (survivor.y == this.y))
        {
            inPosession = true;
        }
    }
}