package com.example.finallauncherrefactored.Projects.ArmWrestling;

class PlayerTwo
{
    int counter;
    int[] combo = {};
    boolean isAlternating;
    long alternationStart = 0;
    
    PlayerTwo()
    {
        counter = 0;
        combo = new int[10];
    }
    
    void increasePoints()
    {
        counter++;
    }
    
    void addToComboQ()
    {
        for (int i = 0; i < combo.length; i++)
        {
            if (combo[i] == 0)
            {
                combo[i] = 1;
                break;
            }
        }
        
    }
    
    void addToComboW()
    {
        for (int i = 0; i < combo.length; i++)
        {
            if (combo[i] == 0)
            {
                combo[i] = 2;
                break;
            }
        }
        
    }
    
    void realComboCheck()
    {
        if (combo.length == 10)
        {
            for (int i = 0; i < combo[9]; i+=2)
            {
                if ((combo[i] == 1 && combo[i + 1] == 2) || (combo[i] == 2 && combo[i + 1] == 1))
                {
                    isAlternating = true;
                    alternationStart = System.nanoTime();
                }
                else
                {
                    isAlternating = false;
                    if (isAlternating == false)
                    {
                        combo = new int[10];
                    }
                }
                
            }
        }
        if (isAlternating == true)
        {
            counter = counter + 5;
            combo = new int[10];
            isAlternating = false;
        }
    }
}