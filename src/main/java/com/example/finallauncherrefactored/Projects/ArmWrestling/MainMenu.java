package com.example.finallauncherrefactored.Projects.ArmWrestling;

class MainMenu
{
    //this is where the players can pick their arms, maybe an enum class?
    int height;
    int width;
    boolean cOne;
    boolean characterTwo;
    boolean characterThree;
    boolean characterFour;
    
    boolean playerOneSelect;
    boolean playerTwoSelect;
    
    boolean cOneTwo;
    boolean cTwoTwo;
    boolean cThreeTwo;
    boolean cFourTwo;
    
    boolean noMenu;
    
    MainMenu(int height, int width)
    {
        this.height = height;
        this.width = width;
        this.cOne = false;
        this.characterTwo = false;
        this.characterThree = false;
        this.characterFour = false;
        
        this.playerOneSelect = false;
        this.playerTwoSelect = false;
        
        this.cOneTwo = false;
        this.cTwoTwo = false;
        this.cThreeTwo = false;
        this.cFourTwo = false;
        
        this.noMenu = false;
    }
}