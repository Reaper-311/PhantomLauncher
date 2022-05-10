package com.example.finallauncherrefactored.Projects.SpaceInvaders;

class Game
{
    /** The dimensions of the game. */
    double width, height;

    /** The level. */
    int level;

    /** The elapsed time in the level */
    int levelTime;

    /** The score. */
    int score;

    /** The direction the aliens are moving: right or left. */
    boolean aliensAreMovingRight;

    /** The player's ship. */
    PlayerShip ship;

    /** The horde of aliens. */
    Alien[] aliens;

    /**
     * Sets up the initial conditions of a new game.
     */
    Game()
    {
        width = 500;
        height = 600;
        level = 1;
        levelTime = 0;
        score = 0;

        aliensAreMovingRight = true;

        // Make the ship and position it at the middle of the bottom edge.
        ship = new PlayerShip(width / 2, height - 50);

        spawnAliens();
    }

    void spawnAliens()
    {
        aliens = new Alien[7 + level];

        for (int i = 0; i < aliens.length; i++)
        {
            double x = 30 + 50 * (i%8);
            double y = 80 + 80 * (i/8);
            aliens[i] = new Alien(x, y);
        }
    }

    void update()
    {
        moveAliens();
        alienShoot();
        updateLasers();
        checkHit();

        if (noMoreAliens() && noMoreAlienLasers())
        {
            levelUp();
        }
    }

    boolean noMoreAliens()
    {
        for (Alien a : aliens)
        {
            if (a.isAlive)
            {
                return false;
            }
        }
        return true;
    }

    boolean noMoreAlienLasers()
    {
        for (Alien a : aliens)
        {
            for (Laser laser : a.lasers)
            {
                if (laser.isFired)
                {
                    return false;
                }
            }
        }
        return true;
    }

    void moveAliens()
    {
        if (aliensAreMovingRight)
        {
            moveAliensRight();
        }
        else
        {
            moveAliensLeft();
        }
    }

    void moveAliensRight()
    {
        if (noMoreAliens())
        {
            return;
        }

        // Move all aliens over by 2 pixels
        for (Alien a : aliens)
        {
            a.cx += 2;
        }

        // Figure out which alien is alive furthest to the right
        Alien rightmostAlien = aliens[0];
        for (Alien a : aliens)
        {
            if (a.isAlive && a.cx > rightmostAlien.cx)
            {
                rightmostAlien = a;
            }
        }

        // Change directions if necessary
        if (rightmostAlien.isRightSideOutOfBounds(width))
        {
            aliensAreMovingRight = false;
        }
    }

    void moveAliensLeft()
    {
        if (noMoreAliens())
        {
            return;
        }

        // Move all aliens over by 2 pixels
        for (Alien a : aliens)
        {
            a.cx -= 2;
        }

        // Figure out which alien is alive furthest to the left
        Alien leftmostAlien = aliens[7];;
        for (Alien a : aliens)
        {
            if (a.isAlive && a.cx < leftmostAlien.cx)
            {
                leftmostAlien = a;
            }
        }

        // Change directions if necessary
        if (leftmostAlien.isLeftSideOutOfBounds(0))
        {
            aliensAreMovingRight = true;
        }
    }

    void alienShoot()
    {
        for (Alien a : aliens)
        {
            if (a.isAlive && Math.random() < 0.01) // 1% chance to fire every frame
            {
                double randomvx = (Math.random() * 3) - 1.5; // add a bit of chaos
                a.fireLaser(randomvx, 5);
            }
        }
    }

    void checkHit()
    {
        // Check if an alien needs to die
        for (Alien a : aliens)
        {
            for (Laser laser : ship.lasers)
            {
                if (a.isAlive && laser.isFired && laser.intersects(a))
                {
                    a.isAlive = false;
                    laser.reset();
                    score++;
                }
            }
        }

        // Check if the ship got hit
        for (Alien a : aliens)
        {
            for (Laser laser : a.lasers)
            {
                if (laser.isFired && laser.intersects(ship))
                {
                    ship.isAlive = false;
                    laser.reset();
                }
            }
        }
    }

    void updateLasers()
    {
        // Ship's lasers
        for (Laser laser : ship.lasers)
        {
            if (laser.isFired)
            {
                laser.update();

                if (laser.isOutOfBounds(0, 0, width, height))  //Off the screen
                {
                    laser.reset();
                }
            }
        }

        // Alien's lasers
        for (Alien a : aliens)
        {
            for (Laser laser : a.lasers)
            {
                if (laser.isFired)
                {
                    laser.update();

                    if (laser.isOutOfBounds(0, 0, width, height))  //Off the screen
                    {
                        laser.reset();
                    }
                }
            }
        }
    }

    void levelUp()
    {
        // Add a bonus score based on how many seconds remain on the timer
        score += getTimeBonus();

        level++;

        // award a new laser every three levels
        if (level % 3 == 0)
        {
            ship.giveBonusLaser();
        }

        // reset the level
        levelTime = 0;
        spawnAliens();
    }

    int getTimeBonus()
    {
        return 30 - levelTime;
    }
}
