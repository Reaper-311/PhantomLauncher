package com.example.finallauncherrefactored.Projects.TikTakToe;

import java.util.Arrays;

class TicUltimate
{
    char[] grid;
    Tictactoe[] games;
    double  x,y,width,height;
    Object[] object;
    
    //a
    // Constructor sets up the tic-tac-toe board
    TicUltimate()
    {
        grid = new char[9];
        games = new Tictactoe[9];
        Arrays.fill(grid, '_');
        
        for (int i = 0; i < games.length; i++)
        {
            games[i] = new Tictactoe();
        }
    }

    void placeMark()
    {
        for (int i = 0; i < grid.length; i++)
        {
            if (games[i].checkWin('o') == true && games[i].checkWin('x') == true)
            {
                grid[i] = 'z';
            }
            else if (games[i].checkWin('x') == true)
            {
                grid[i] = 'x';
            }
            else if (games[i].checkWin('o') == true)
            {
                grid[i] = 'o';
            }
            
        }
    }
    
    void input(char c, int whichGame, int whichSpot)
    {
        if (games[whichGame].grid[whichSpot] == '_')
        {
            games[whichGame].grid[whichSpot] = c;
        }
    }
    
    
    void boardRelocator()
    {
        this.x  =   x;
        this.y=y;
        this.width=282;
        this.height=282;
        object = new Objects[9];
        
        
        
        
        
        for(int i=0;    i   <9;i++)
        {
            if(i%3==0&&i>0)
            {
                y+=94;
                x-=94*3;
            }
            else    
            {
                x    +=  94*3; 
            }
            
            games[i]   =   new Tictactoe();
        }
        
        
    }
    
    // In this method, c is now just a variable, so the same
    // checking logic can be used for both 'X' and 'O'
    boolean checkWin(char c)
    {
        for (int i = 0; i < grid.length; i++) //look here
        {
            if (grid[i] == 'z')
            {
                grid[i] = c;
            }
        }

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

    void run()
    {
        boolean hasWinner = false;
        while (!hasWinner)
        {
            placeMark();
            checkWin('x');
            placeMark();
            checkWin('o');
        }
        if (checkWin('x') == true)
        {
            hasWinner = true;
        }

        if (checkWin('o') == true)
        {
            hasWinner = true;
        }
    }
    /*void play()
    {
    Scanner s = new Scanner(System.in);

    boolean hasWinner = false;
    while (!hasWinner)
    {

    //int xChoice = s.nextInt();
    //placeMark(xChoice, 'x');

    //int oChoice = s.nextInt(); look here for error
    //placeMark(oChoice, 'o');
    // TODO: finish this game loop

    // O needs a turn. 
    // Check that the requested moves are valid.
    // Update the "hasWinner" variable or else this will be an infinite loop
    }

    s.close();
    }*/
}
