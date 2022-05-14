package com.example.finallauncherrefactored.Projects.TetraShot;

class Collider
{
    double cx;
    double cy;
    double rx;
    double ry;
    
    Collider(double cx,double cy,double rx,double ry)
    {
        this.cx = cx;
        this.cy = cy;
        this.rx = rx;
        this.ry = ry;
    }
    
    boolean intersects(Collider other)
    {
        return isHorizontallyAlignedWith(other) 
        && isVerticallyAlignedWith(other);
    }
    
     boolean isOutOfBounds(double xMin, double yMin, double xMax, double yMax)
    {
        return isLeftSideOutOfBounds(xMin)
        || isTopSideOutOfBounds(yMin)
        || isRightSideOutOfBounds(xMax)
        || isBottomSideOutOfBounds(yMax);
    }
    
    boolean isHorizontallyAlignedWith(Collider other)
    {
        return verticalDistanceTo(other) <= this.ry + other.ry;
    }
    boolean isVerticallyAlignedWith(Collider other)
    {
        return horizontalDistanceTo(other) <= this.rx + other.rx;
    }
    double horizontalDistanceTo(Collider other)
    {
        return Math.abs(other.cx - this.cx);
    }
    double verticalDistanceTo(Collider other)
    {
        return Math.abs(other.cy - this.cy);
    }
     boolean isLeftSideOutOfBounds(double xMin)
    {
        return cx - rx < xMin;
    }
    boolean isRightSideOutOfBounds(double xMax)
    {
        return cx + rx > xMax;
    }
    boolean isTopSideOutOfBounds(double yMin)
    {
        return cy - ry < yMin;
    }
    boolean isBottomSideOutOfBounds(double yMax)
    {
        return cy + ry > yMax;
    }

}