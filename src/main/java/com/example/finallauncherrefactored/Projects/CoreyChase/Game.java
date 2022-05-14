package com.example.finallauncherrefactored.Projects.CoreyChase;

public class Game
{
    Mover mover;
    Tile[] tiles;
    int width, height, timer;

    Game()
    {
        mover = new Mover(5, 5);

        this.width = 12;
        this.height = 10;
        this.timer= 0;
        generateTiles();
    }

    void generateTiles()
    {
        tiles = new Tile[7];

        for (int i = 0; i < tiles.length; i++)
        {
            int randX = (int) (Math.random() * this.width);
            int randY = (int) (Math.random() * this.height);

            tiles[i] = new Tile(randX, randY);
        }
    }

    void update()
    {
        if(checkGameOver() == false)
        {
            //Add the code that the game requires to update every cycle
            timer++;

            if(timer == 10)
            {
                moveTiles();
                if(checkCollision() == true)
                {
                    mover.y++;
                    mover.x = 5;
                    generateTiles();
                }
                timer = 0;
            }

        }
    }

    void moveTiles()
    {
        for(Tile t : tiles)
        {
            t.y++;

            if(t.y > 10)
            {
                t.x = (int) (Math.random() * this.width);
                t.y = 0;
            }
        }

    }

    boolean checkCollision()
    {
        for(Tile t : tiles)
        {
            if(mover.x == t.x && mover.y == t.y)
            {
                return true;
            }
        }
        return false;
    }

    boolean checkGameOver()
    {
        if(mover.y > 9)
        {
            return true;
        }
        return false;
    }
}

