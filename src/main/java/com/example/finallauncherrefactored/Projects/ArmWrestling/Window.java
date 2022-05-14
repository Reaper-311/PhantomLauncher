package com.example.finallauncherrefactored.Projects.ArmWrestling;

class Window
{
    int height;
    int width;
    
    PlayerOne playerOne;
    PlayerTwo playerTwo;
    
    Window(int height, int width)
    {
        this.height = height;
        this.width = width;
        playerOne = new PlayerOne();
        playerTwo = new PlayerTwo();
    }
    
    boolean isGameOver()
    {
        if (playerOne.counter > playerTwo.counter + 50)
        {
            return true;
        }
        
        if (playerTwo.counter > playerOne.counter + 50)
        {
            return true;
        }
        
        return false;
    }
}