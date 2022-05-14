package com.example.finallauncherrefactored.Projects.JJPaint;

class Brush extends Obj
{   
    double width;
    
    Brush(double centerx, double centery, double width)
    {
        super(centerx, centery, width/2, 6);
    }
}