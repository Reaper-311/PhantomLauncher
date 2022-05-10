package com.example.finallauncherrefactored.Projects.SpaceInvaders;

class Laser extends Collider
{
    /** The horizontal velocity of the laser. */
    double vx;

    /** The vertical velocity of the laser. */
    double vy;

    /** Tracks whether the laser is currently active or inactive.  */
    boolean isFired;

    /** The number of frames that the laser has been active */
    int lifetime;

    /**
     * Constructor for an inactive laser.
     *
     * Used only at the beginning of the game to provide each ship with a potential
     * laser beam to fire.
     */
    Laser()
    {
        // Newly created lasers have no meaningful position or velocity.
        super(0, 0, 2, 7);

        vx = 0;
        vy = 0;
        isFired = false;
        lifetime = 0;
    }

    /**
     * Called each frame of the running game to move the laser according to
     * its vx and vy velocity.
     */
    void update()
    {
        if (isFired)
        {
            // move the laser
            cx += vx;
            cy += vy;
            lifetime++;
        }
    }

    /**
     * Activate the laser from a given position (cx, cy) and with a given x and y velocity.
     */
    void fireFrom(double cx, double cy, double vx, double vy)
    {
        this.cx = cx;
        this.cy = cy;
        this.vx = vx;
        this.vy = vy;
        isFired = true;
    }

    /**
     * Reset the laser and deactivate it.
     *
     * This method will be called when the laser hits the edge of the screen
     * or a collider.
     */
    void reset()
    {
        isFired = false;
        cx = 0;
        cy = 0;
        vx = 0;
        vx = 0;
        lifetime = 0;
    }
}
