package com.example.finallauncherrefactored.Projects.Snake;

class Window
{
    int width;
    int height;

    Snake snake;
    Apple apple;

    Window(int width, int height)
    {
        this.width = width;
        this.height = height;

        snake = new Snake(width / 2, height / 2);
        generateApple();
    }

    void generateApple()
    {
        int randX = (int) (Math.random() * width);
        int randY = (int) (Math.random() * height);

        apple = new Apple(randX, randY);
    }

    void eatApple()
    {
        if (snake.head.x == apple.x && snake.head.y == apple.y)
        {
            snake.grow();
            generateApple();
        }
    }

    void checkOutOfBounds()
    {
        if (snake.head.x > width - 1 || snake.head.y > height - 1 ||
                snake.head.x < 0 || snake.head.y < 0)
        {
            snake.isAlive = false;
        }
    }

    boolean isGameOver()
    {
        snake.checkIntersect();
        checkOutOfBounds();

        if (snake.isAlive)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
