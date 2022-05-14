package com.example.finallauncherrefactored.Projects.TikTakToe;

import java.util.Arrays;

class Tictactoe
{
    char[] grid;

    
    
    // Constructor sets up the tic-tac-toe board
    Tictactoe()
    {
        grid = new char[9];
        Arrays.fill(grid, '_');
        //this.x = x;
        //this.y = y;
        //this.width = 282;
        //this.height = 282;
    }
    
    void placeMark(int n, char c)//a
    {
        if (n >= 0 && n < 9)
        {
            grid[n] = c;
        }
        /*
        printBoard();
        checkWin('x');
        checkWin('o');
        if(checkWin('o') == true && checkWin('x') == true)
        {
            System.out.println("HAAAXXXXXXXX!!!!");
        }
        if(checkWin('x') == true)
        {
            System.out.println("X Wins!");
        }
        if(checkWin('o') == true)
        {
            System.out.println("O Wins!");
        }*/
    }
    
    void printBoard()
    {
        System.out.println(""+ grid[0]+ grid[1]+ grid[2]);
        System.out.println(""+ grid[3]+ grid[4]+ grid[5]);
        System.out.println(""+ grid[6]+ grid[7]+ grid[8]);
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
    
    /*void play()
    {
        Scanner s = new Scanner(System.in);
        
        boolean hasWinner = false;
        while (!hasWinner)
        {
            System.out.println("Player X, it's your turn. Pick a number 0-8");
            printBoard();
            int xChoice = s.nextInt();
            placeMark(xChoice, 'x');
            
            System.out.println("Player O, it's your turn. Pick a number 0-8");
            printBoard();
            int oChoice = s.nextInt();
            placeMark(oChoice, 'o');
            // TODO: finish this game loop
            
            // O needs a turn. 
            // Check that the requested moves are valid.
            // Update the "hasWinner" variable or else this will be an infinite loop
        }
        
        s.close();
    }*/
}
