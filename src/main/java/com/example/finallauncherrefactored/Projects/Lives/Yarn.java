package com.example.finallauncherrefactored.Projects.Lives;

class Yarn {
    double x, y, dx, dy, width, height, centerX, centerY, friction, g, speed;
    boolean scored, onGround;
    Game game;
    Yarn ( double x, double y, double width, Game game) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=width;
        this.game=game;
        dx=0;
        dy=0;
        friction=2;
        speed=0.5;
        centerX = x+(width/2);
        centerY = y+(height/2);
        scored=false;
        onGround=false;
        g=.2;
    }
    void launch () {
        x=game.tileSize;
        y=game.height-game.tileSize;
        dy=-speed*5;
        dx=speed;
    }
    void move () {
        scoreCheck();
        applyGravity();
        windowCollision();
        x+=dx;
        y+=dy;
        
    }
    void windowCollision () {
        if (x+width+speed>=game.width) {
            dx=-dx;
        }
        if (x-speed<=0) {
            dx=-dx;
        }
        if (y+height+g>=game.height) {
            dy=0;
            onGround=true;
        } else if (y-speed<=0) {
            onGround=false;
            dy+=speed;
        } else {
            onGround=false;
        }
    }
    void scoreCheck () {
        if (dy>1 && x+width>game.hoop.x && y>game.hoop.y+(game.hoop.height/2) && y<game.hoop.y+game.hoop.height) {
            scored=true;
        }
    }
    void applyGravity () {
        if (!onGround) {
            if (dy>10) {
                dy=10;
            } else if (dy<-10) {
                dy=-10;
            }
            dy+=g;
        } else {
            dx=0;
        }
    }
    
}