package com.example.finallauncherrefactored.Projects.TikTakToe;

import java.util.Arrays;

class TicUl
{
    char[] grid;
    Tictactoe[] games;
    Object[] object;

    double  x,y,width,height;
    //ha
    // Constructor sets up the tic-tac-toe board
    TicUl()
    {
        grid = new char[9];
        games = new Tictactoe[9];
        Arrays.fill(grid, '_');
    }

    void placeMark()
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
                y+=94*3;
                x-=94*3*3;
            }
            else    
            {
                x    +=  94*3; 
            }

            object[i]   =   new Space(x,y);
        }

        
        for (int i = 0; i < 9; i++)
        {
            if (games[i].checkWin('x') == true)
            {
                grid[i] = 'x';
                if(grid[i] == 'x')
                {
                    object[i] = new X(x*3,y*3);
                }
            }
            else{
                grid[i] = '_';
                if(grid[i] == '_')
                {
                    object[i] = new Space(x*3,y*3);
                }
            }
            if (games[i].checkWin('o') == true)
            {
                grid[i] = 'o';
                if(grid[i] == 'o')
                {
                    object[i] = new O(x*3,y*3);
                }
            }
            else{
                grid[i] = '_';
                if(grid[i] == '_')
                {
                    object[i] = new Space(x*3,y*3);
                }
            }
            if (games[i].checkWin('o') == true && games[i].checkWin('o') == true)
            {
                grid[i] = 'z';
            }
        }
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
    }
     */
}