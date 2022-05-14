package com.example.finallauncherrefactored.Projects.TikTakToe;

public  class   TikBoard
{
    Tictactoe game;
    Objects[] object;

    double  x,y,width,height;

    TikBoard(double x,  double  y  )
    {
        this.x  =   x;
        this.y=y;
        this.width=282;
        this.height=282;

        
        
        //hui

        for(int i=0;    i   <9;i++)
        {
            if(i%3==0&&i>0)
            {
                y+=94;
                x-=94*3;
            }
            else    
            {
                x    +=  94; 
            }
            
            object[i]   =   new Space(x,y);
        }
    }

    void    placeMark(int n, char c)
    {

        if(n>=0&&n<9)
        { 
            
        }

    }

    
    
    
    //test
    /*boolean equals()
    {
        
        if(object.length    ==  search1.length)
        {
            return  true;
        }
        else
        {
            return  false;
        }
        
       
    }*/
}