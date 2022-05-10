package com.example.finallauncherrefactored.Projects.Snake;

import java.util.ArrayList;

class Snake
{
    enum Direction { N, S, E, W, NONE }

    SnakeChunk head;
    ArrayList<SnakeChunk> tail;
    Direction dir;
    boolean isAlive;

    Snake(int headX, int headY)
    {
        head = new SnakeChunk(headX, headY);
        tail = new ArrayList<SnakeChunk>();
        dir = Direction.NONE;
        isAlive = true;
    }

    void move()
    {
        //Move the snake's tail
        if (tail.size() > 0)
        {
            tail.remove(0);
            grow();
        }

        // Move the snake's head
        if (dir == Direction.N) head.y--;
        else if (dir == Direction.S) head.y++;
        else if (dir == Direction.E) head.x++;
        else if (dir == Direction.W) head.x--;

    }

    void goNorth() { dir = Direction.N;  }
    void goSouth() { dir = Direction.S;  }
    void goEast() { dir = Direction.E;  }
    void goWest() { dir = Direction.W;  }

    void grow()
    {
        int x = head.x;
        int y = head.y;

        tail.add(head);
        head = new SnakeChunk(x, y);
    }

    void checkIntersect()
    {
        for (int i = 0; i < tail.size() - 1; i++)
        {
            SnakeChunk sc = tail.get(i);
            if (head.x == sc.x && head.y == sc.y)
            {
                isAlive = false;
            }
        }
    }
}
