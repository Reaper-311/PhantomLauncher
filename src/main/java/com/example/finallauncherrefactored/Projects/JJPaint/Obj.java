package com.example.finallauncherrefactored.Projects.JJPaint;

class Obj
{   double centerx;
    double centery;
    double radiusx;
    double radiusy;
  
    App appp;
    Obj(double centerx, double centery, double radiusx, double radiusy)
    {
        this.centerx = centerx;
        this.centery = centery;
        this.radiusx = radiusx;
        this.radiusy = radiusy;
    }
    /*
    boolean isOutOfBounds(double xMin, double yMin, double xMax, double yMax)
    {
        if(isLeftSideOutOfBounds(xMin) || isTopSideOutOfBounds(yMin)|| isRightSideOutOfBounds(xMax)|| isBottomSideOutOfBounds(yMax))
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    boolean isLeftSideOutOfBounds(double xMin)
    {
        return centerx - radiusx < xMin;
    }

    boolean isRightSideOutOfBounds(double xMax)
    {
        return centerx + radiusx > xMax;
    }

    
    boolean isTopSideOutOfBounds(double yMin)
    {
        return centery - radiusy < yMin;
    }

    
     * Returns true if the bottom side of this collider is below the bottom boundary.

    boolean isBottomSideOutOfBounds(double yMax)
    {
        return centery + radiusy > yMax;
    }
    */
}