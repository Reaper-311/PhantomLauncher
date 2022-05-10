package com.example.finallauncherrefactored.Projects.SpaceInvaders;

class Ship extends Collider
{
    /** The array of laser shots that a ship can fire at once. */
    Laser[] lasers;

    /** The ship's alive/dead status. */
    boolean isAlive;

    /**
     * The constructor for a ship.
     *
     * The center coordinates of the ship (cx, cy) and the ship's horizontal
     * and vertical radii (rx and ry) need to be supplied to the Collider
     * superclass.
     *
     * In addition to that, a ship has a number of "shots" that determines the
     * size of the lasers array.
     */
    Ship(double cx, double cy, double rx, double ry, int shots)
    {
        super(cx, cy, rx, ry);
        isAlive = true;

        lasers = new Laser[shots];
        for (int i = 0; i < lasers.length; i++)
        {
            lasers[i] = new Laser();
        }
    }

    /**
     * If possible, activate an inactive laser at the ship's coordinates with the
     * given x,y velocity.
     */
    void fireLaser(double vx, double vy)
    {
        if (!isAlive)
        {
            return;
        }

        for (Laser laser : lasers)
        {
            if (!laser.isFired)
            {
                laser.fireFrom(cx, cy, vx, vy);

                return; //prevent firing more than one laser at a single moment in time
            }
        }
    }
}
