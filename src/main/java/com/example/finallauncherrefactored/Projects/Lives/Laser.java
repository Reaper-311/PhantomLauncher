package com.example.finallauncherrefactored.Projects.Lives;

import java.util.Random;
class Laser {
    double x, y, dx, dy, width, height, speed, centerX, centerY, maxY, maxX, minY, minX;
    Game game;
    boolean goLeft, goRight, goUp, goDown, caught, active;
    Laser (double x, double y, double width, Game game) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=width;
        this.game=game;
        goRight=false;
        goLeft=false;
        goUp=false;
        goDown=false;
        caught=false;
        active=false;
        dx=0;
        dy=0;
        speed=10;
        centerX = x+(width/2);
        centerY = y+(height/2);
        maxY=game.height-game.tileSize;
        maxX=game.width-game.tileSize;
        minY=game.tileSize*3;
        minX=game.tileSize;
    }
    void move () {
        Random r = new Random();
        int dir = r.nextInt(5);
        int w = 1;
        if (dir==0) {
            //up
            if (y>maxY-game.tileSize) {
                w=2;
            } else {
                w=1;
            }
            dy=-speed*w;
        }
        if (dir==1) {
            //down
            if (y<minY+game.tileSize) {
                w=2;
            } else {
                w=1;
            }
            dy=+speed*w;
        }
        if (dir==2) {
            //left
            if (x>maxX-game.tileSize) {
                w=2;
            } else {
                w=1;
            }
            dx=-speed*w;
        }
        if (dir==3) {
            //right
            if (x<minX+game.tileSize) {
                w=2;
            } else {
                w=1;
            }
            dx=+speed*w;
        }
        
        if (!(x+dx>maxX || x+dx<minX)) {
            x+=dx;
        } else {
            dx=-dx*dx;
        }
        if (!(y+dy>maxY || y+dy<minY)) {
            y+=dy;
        } else {
            dy=-dy*dy;
        }

    }
}
 