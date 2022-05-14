package com.example.finallauncherrefactored.Projects.TikTakToe;

import java.util.Arrays;
class CopyOftictactoe
{
    char[] grid;
    
    // Constructor sets up the tic-tac-toe board
    CopyOftictactoe()
    {
        grid = new char[9];
        Arrays.fill(grid, '_');
    }
    
    void placeMark(int n, char c)
    {
        if (n >= 0 && n < 9)
        {
            grid[n] = c;
        }
        
        printBoard();
        checkWin('x');
        checkWin('o');
        if(checkWin('x') == true)
        {
        }
        if(checkWin('o') == true)
        {
        }
    }
    
    void printBoard()
    {
        // TODO
        // This method should print out the board based
        // on what characters are in the grid array.
        

        
        
        // OXO
        // XO_
    }
    
    // In this method, c is now just a variable, so the same
    // checking logic can be used for both 'X' and 'O'
    boolean checkWin(char c)
    {
        if(grid[0] == c && grid[1] == c && grid[2] == c) 
        {
            return true;
        }
        if(grid[3] == c && grid[4] == c && grid[5] == c) 
        {
            return true;
        }
        if(grid[6] == c && grid[7] == c && grid[8] == c) 
        {
            return true;
        }
        
        if(grid[0] == c && grid[3] == c && grid[6] == c) 
        {
            return true;
        }
        if(grid[1] == c && grid[4] == c && grid[7] == c) 
        {
            return true;
        }
        if(grid[2] == c && grid[5] == c && grid[8] == c) 
        {
            return true;
        }
        
        if(grid[0] == c && grid[4] == c && grid[8] == c) 
        {
            return true;
        }
        if(grid[2] == c && grid[4] == c && grid[6] == c) 
        {
            return true;
        }
        return false;
    }
    
}
