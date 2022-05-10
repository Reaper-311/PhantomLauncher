package com.example.finallauncherrefactored.Projects.Breakout;

/**
 * Collider represents any rectangular game object that needs to detect collisions
 */
class Collider
{
    /**
     * The x coordinate of the center.
     */
    double cx;

    /**
     * The y coordinate of the center.
     */
    double cy;

    /**
     * The horizontal distance from the center to the edge (the x radius).
     */
    double rx;

    /**
     * The vertical distance from the center to the edge (the y radius).
     */
    double ry;

    /**
     * The constructor for a collider.
     */
    Collider(double cx, double cy, double rx, double ry)
    {
        this.cx = cx;
        this.cy = cy;
        this.rx = rx;
        this.ry = ry;
    }

    /**
     * Returns true if this collider intersects the other given collider.
     *
     * This is the main method used for collision detection between colliders.
     */
    boolean intersects(Collider other)
    {
        return isHorizontallyAlignedWith(other)
                && isVerticallyAlignedWith(other);
    }

    /**
     * Returns true if any edge of the collider is beyond the given boundaries.
     */
    boolean isOutOfBounds(double xMin, double yMin, double xMax, double yMax)
    {
        return isLeftSideOutOfBounds(xMin)
                || isTopSideOutOfBounds(yMin)
                || isRightSideOutOfBounds(xMax)
                || isBottomSideOutOfBounds(yMax);
    }

    //----- Helper methods ------------------------

    /**
     * Returns true if a horizontal line could pass through this and the other given collider.
     *
     * One collider will "shadow" another from the side if the vertical distance between
     * their centers is less than the sum of their vertical radii.
     */
    boolean isHorizontallyAlignedWith(Collider other)
    {
        return verticalDistanceTo(other) <= this.ry + other.ry;
    }

    /**
     * Returns true if a vertical line could pass through this and the other given collider.
     *
     * One collider will "shadow" another from above if the horizontal distance between
     * their centers is less than the sum of their horizontal radii.
     */
    boolean isVerticallyAlignedWith(Collider other)
    {
        return horizontalDistanceTo(other) <= this.rx + other.rx;
    }

    /**
     * Returns the horizontal displacement between this and the other given collider.
     */
    double horizontalDistanceTo(Collider other)
    {
        return Math.abs(other.cx - this.cx);
    }

    /**
     * Returns the vertical displacement between this and the other given collider.
     */
    double verticalDistanceTo(Collider other)
    {
        return Math.abs(other.cy - this.cy);
    }

    /**
     * Returns true if the left side of this collider is to the left of the left boundary.
     */
    boolean isLeftSideOutOfBounds(double xMin)
    {
        return cx - rx < xMin;
    }

    /**
     * Returns true if the right side of this collider is to the right of the right boundary.
     */
    boolean isRightSideOutOfBounds(double xMax)
    {
        return cx + rx > xMax;
    }

    /**
     * Returns true if the top side of this collider is above the top boundary.
     */
    boolean isTopSideOutOfBounds(double yMin)
    {
        return cy - ry < yMin;
    }

    /**
     * Returns true if the bottom side of this collider is below the bottom boundary.
     */
    boolean isBottomSideOutOfBounds(double yMax)
    {
        return cy + ry > yMax;
    }
}
