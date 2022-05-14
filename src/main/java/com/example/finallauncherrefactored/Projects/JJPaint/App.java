package com.example.finallauncherrefactored.Projects.JJPaint;

class App
{   
    
    double width;
    double height;

    
    Brush brush;

   

    /**
     * The game constructor.
     */
    App()
    {

        width = 1920 ;
        height = 1080;

        brush = new Brush(width * 0.5, height * .5, 10);

    }

    /**
     * Updates the game to prepare a new frame of gameplay.
     * 
     * This will be called during every step of the animation.
     */
    void update()
    {   

        // break up the movement into 10 smaller steps per frame for better collision detection
        int steps = 1000000;

        // dt represents a small increment of time.
        // dt is less than 1.0, which represents a single frame of the animation.
        double dt = 1.0 / steps;

    }
  
}

   