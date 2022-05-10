package com.example.finallauncherrefactored.Projects.SpaceInvaders;

class Alien extends Ship
{
    /**
     * The constructor for an alien.
     *
     * The (cx, cy) coordinates of the center of the alien are passed to the
     * ship constructor along with a horizontal and vertical radius of 20 pixels.
     *
     * Each alien has 4 lasers to fire. Be careful, their aim is unpredictable!
     */
    Alien(double cx, double cy)
    {
        super(cx, cy, 20, 20, 4);
        isAlive = true;
    }
}
