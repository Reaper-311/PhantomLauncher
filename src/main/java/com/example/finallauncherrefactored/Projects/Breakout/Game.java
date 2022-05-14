package com.example.finallauncherrefactored.Projects.Breakout;

/**
 * The data model of the breakout game.
 *
 * This class is responsible for setting up the initial conditions of
 * the game, keeping track of the state of the ball, the blocks, and
 * the paddle, and also managing changes to their state.
 *
 * This class also reports sound effects cues to the main application.
 */
class BGame
{
    /** Basic game data */
    int lives, score, level, combo, bestCombo;
    double width, height;

    /** The game objects. */
    Paddle paddle;
    Ball ball;
    Block[] blocks;

    /** Manages sound effects cues.  */
    SoundEvents sfx;

    /**
     * The game constructor.
     */
    BGame()
    {
        lives = 3;
        score = 0;
        level = 1;
        combo = 0;
        bestCombo = 0;
        width = 700;
        height = 500;

        paddle = new Paddle(width * 0.5, height - 20, 100);
        ball = new Ball(width * 0.5, height * 0.7, 2.5, -3);
        generateBlocks();

        sfx = new SoundEvents();
    }

    /**
     * Create three rows of blocks with random hp.
     */
    void generateBlocks()
    {
        blocks = new Block[26];

        for (int i = 0; i < 9; i++) //row 1
        {
            int randHP = (int) (Math.random() * 3) + 1;
            blocks[i] = new Block(70 + i * 70, 100, randHP);
        }

        for (int i = 9; i < 17; i++) //row 2
        {
            //Middle row has weak blocks for better combo chances.
            blocks[i] = new Block(105 + (i - 9) * 70, 170, 1);
        }

        for (int i = 17; i < 26; i++) //row 3
        {
            int randHP = (int) (Math.random() * 3) + 1;
            blocks[i] = new Block(70 + (i - 17) * 70, 230, randHP);

        }
    }

    /**
     * Updates the game to prepare a new frame of gameplay.
     *
     * This will be called during every step of the animation.
     */
    void update()
    {
        // reset all sound effect triggers
        sfx.reset();

        if (lives <= 0)
        {
            return;
        }

        // break up the movement into 10 smaller steps per frame for better collision detection
        int steps = 10;

        // dt represents a small increment of time.
        // dt is less than 1.0, which represents a single frame of the animation.
        double dt = 1.0 / steps;

        for (int step = 0; step < steps; step++)
        {
            //move horizontally and check if we need to bounce horizontally
            ball.stepHorizontal(dt);
            if (collisionDetected())
            {
                ball.stepHorizontal(-dt); //reverse
                ball.bounceHorizontal();
            }

            //move vertically and check if we need to bounce vertically
            ball.stepVertical(dt);
            if (collisionDetected())
            {
                ball.stepVertical(-dt); // reverse
                ball.bounceVertical();
            }

            if (allBlocksAreBroken() && ball.cy + 20 > paddle.cy) // ball is right above paddle
            {
                levelUp();
            }

            if (ballIsBelowFloor())
            {
                lives--;
                ball = new Ball(width * 0.5, height * 0.7, 2.5, -3);
                break;
            }
        }

    }

    void levelUp()
    {
        level++;
        generateBlocks();
        sfx.allClear = true;
    }

    boolean collisionDetected()
    {
        return collidesWithWall() || collidesWithPaddle() || collidesWithBlock();
    }

    boolean collidesWithBlock()
    {
        for (Block block : blocks)
        {
            if (block.hp > 0 && ball.intersects(block))
            {
                block.hp--;
                combo++;
                score += combo;

                ball.dx *= 1.02;
                ball.dy *= 1.02;

                sfx.blockBounce = true;
                return true;
            }
        }
        return false;
    }

    boolean collidesWithPaddle()
    {
        if (paddle.intersects(ball))
        {
            sfx.paddleBounce = true;
            resetCombo();
            return true;
        }
        return false;
    }

    boolean collidesWithWall()
    {
        if ((ball.cx - ball.rx < 0) //left wall
                || (ball.cx + ball.rx > this.width) //right wall
                || (ball.cy - ball.ry < 0)) //top wall
        {
            resetCombo();
            sfx.wallBounce = true;
            return true;
        }
        return false;
    }

    boolean ballIsBelowFloor()
    {
        if (ball.cy - ball.ry > this.width)
        {
            resetCombo();
            sfx.loseLife = true;
            return true;
        }
        return false;
    }

    boolean allBlocksAreBroken()
    {
        for (Block b : blocks)
        {
            if (b.hp > 0)
            {
                return false;
            }
        }
        return true;
    }

    void resetCombo()
    {
        if (combo > bestCombo)
        {
            bestCombo = combo;
        }
        combo = 0;
    }

    /**
     * Helper class to manage sound effect triggers that occur during gameplay.
     *
     * The "sfx" object made from this class is the means of communicating with
     * the actual media player in the main application.
     */
    class SoundEvents
    {
        boolean paddleBounce;
        boolean wallBounce;
        boolean blockBounce;
        boolean loseLife;
        boolean allClear;

        SoundEvents()
        {
            reset();
        }

        void reset()
        {
            paddleBounce = false;
            wallBounce = false;
            blockBounce = false;
            loseLife = false;
            allClear = false;
        }
    }
}
