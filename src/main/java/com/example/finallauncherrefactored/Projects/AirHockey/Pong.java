package com.example.finallauncherrefactored.Projects.AirHockey;

class Pong
{
    double width, height;
    Paddle paddleLeft;
    Paddle paddleRight;
    Goal goalLeft;
    Goal goalRight;
    Puck puck;
    int scoreLeft;
    int scoreRight;
    Pong()
    {
        this.width = 1000;
        this.height = 500;

        goalLeft = new Goal(0, 150, 50, 200);
        goalRight = new Goal(950, 150, 50, 200);
        paddleLeft = new Paddle(60, 225, 70, 35); 
        paddleRight = new Paddle(905, 225, 70, 35);
        puck = new Puck(400, 200, 50, 50, this);
        scoreLeft = 0;
        scoreRight = 0;
    }

    boolean willBounceOffPaddle()
    {
        if (puck.x < paddleLeft.x + paddleLeft.width
        && puck.x + puck.width > paddleLeft.x
        && puck.y < paddleLeft.y + paddleLeft.height
        && puck.y + puck.height > paddleLeft.y)
        {
            return true;
        }
        if (puck.x < paddleRight.x + paddleRight.width && puck.x + puck.width > paddleRight.x
        && puck.y < paddleRight.y + paddleRight.height
        && puck.y + puck.height > paddleRight.y)
        {
            return true;
        }
        return false;
    }

    void resetPuck()
    {
        //TODO
        puck.x = 400;
        puck.y = 200;
        puck.dx = 9.5;
        puck.dy = 5.5;

        if (Math.random() < 0.01)
        {   
            puck.dx *= -1;
        }
    }

    void keepPaddlesInBounds()
    {
        if(paddleLeft.x < 0 )
        {
            paddleLeft.x = 0;
            
        }
        
        if(paddleLeft.x > this.width)
            
            {
                paddleLeft.x = this.width - this.paddleLeft.width;
                
            }
            if(paddleLeft.y > this.height)
            {
                
                paddleLeft.y = this.height - this.paddleLeft.height;
            }
            if(paddleRight.x < 0 )
        {
            paddleRight.x = 0;
            
        }
        
        if(paddleRight.x > this.width)
            
            {
                paddleRight.x = this.width - this.paddleRight.width;
                
            }
            if(paddleRight.y > this.height)
            {
                paddleRight.y = this.height - this.paddleRight.height;
                
            }
        }
        
    

    void update()
    {
        //puck.move();

        // Horizontal move
        puck.x += puck.dx;
        if (willBounceOffPaddle())
        {
            puck.x -= puck.dx;
            puck.dx *= -1;
        }

        //Vertical move
        puck.y += puck.dy;
        if (willBounceOffPaddle())
        {
            puck.y -= puck.dy;
            puck.dy *= -1;
        }

        puck.collide();

        keepPaddlesInBounds();
        if (puck.hasScoredLeft())
        {
          scoreRight++;
          resetPuck();
        }
        if(puck.hasScoredRight())
        {
            scoreLeft++;
            resetPuck();
            
            
        }
        
    }
}