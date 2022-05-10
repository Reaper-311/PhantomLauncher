package com.example.finallauncherrefactored.Projects.SuperRacer2D;

class World
{
    Car car;
    Obstacle[] obstacles;
    Finish finish;

    double width, height;

    World()
    {
        this.width = 600;
        this.height = 600;

        car = new Car(30, 30);

        finish = new Finish(510, 560);

        obstacles = new Obstacle[9];

        for (int i = 0; i < obstacles.length; i++)
        {
            if (Math.random() < .5)
            {
                obstacles[i] = new Rock(Math.random() * width, Math.random() * height);
            }
            else
            {
                obstacles[i] = new Oil(Math.random() * width, Math.random() * height);
            }
        }
    }

    boolean isCarOnFinish()
    {
        Finish o = finish;

        if (car.x > o.x && car.x < o.x + o.width)
        {
            if (car.y > o.y && car.y < o.y + o.height)
            {
                return true;
            }
        }
        else if (car.x + car.width > o.x && car.x + car.width < o.x + o.width)
        {
            if (car.y + car.height > o.y && car.y + car.height < o.y + o.height)
            {
                return true;
            }
        }

        return false;

    }

    void checkCollision()
    {
        for (Obstacle o : obstacles)
        {
            if (car.x > o.x && car.x < o.x + o.width)
            {
                if (car.y > o.y && car.y < o.y + o.height)
                {
                    reset();
                }
            }
            else if (car.x + car.width > o.x && car.x + car.width < o.x + o.width)
            {
                if (car.y + car.height > o.y && car.y + car.height < o.y + o.height)
                {
                    reset();
                }
            }
        }
    }

    void checkOutOfBounds()
    {
        if (car.x < 0) // past the left edge
        {
            car.bounceX();
            car.x = 0;
        }
        else if (car.x + car.width > width) //past the right edge
        {
            car.bounceX();
            car.x = width - car.width;
        }
        else if (car.y < 0) //past the top edge
        {
            car.bounceY();
            car.y = 0;
        }
        else if (car.y + car.height > height) //past the bottom edge
        {
            car.bounceY();
            car.y = height - car.height;
        }
    }

    void reset()
    {
        car.x = 30;
        car.y = 30;

        car.dx = 0;
        car.dy = 0;
    }
}
