package com.example.finallauncherrefactored.Projects.SpaceInvaders;

class PlayerShip extends Ship
{
    /**
     * The constructor for the player's ship.
     *
     * The (cx, cy) coordinates of the center and the x-radius and y-radius of 20
     * are passed in to the superclass ship constructor.
     *
     * A player's ship has only 3 shots to fire. Make them count!
     *
     * This is our darkest hour. Earth depends on you.
     */
    PlayerShip(double cx, double cy)
    {
        super (cx, cy, 20, 20, 3);
    }

    /**
     * Move left.
     *
     * This method will be called directly from the App where it will
     * be linked to a game input control.
     */
    void moveLeft()
    {
        cx -= 5;
    }

    /**
     * Move right.
     *
     * This method will be called directly from the App where it will
     * be linked to a game input control.
     */
    void moveRight()
    {
        cx += 5;
    }

    /**
     * Make the ship capable of firing one extra shot.
     */
    void giveBonusLaser()
    {
        Laser[] expandedLasers = new Laser[lasers.length + 1];

        // copy the old lasers into the new array
        for (int i = 0; i < lasers.length; i++)
        {
            expandedLasers[i] = lasers[i];
        }

        // make a new laser in the last slot
        expandedLasers[lasers.length] = new Laser();

        // make the variable lasers refer to the bigger array
        lasers = expandedLasers;
    }
}
