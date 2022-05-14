package com.example.finallauncherrefactored.Projects.Lives;

class MovingTile {
    double x, y, width, height, centerX, centerY, deadX, deadY;
    boolean goRight, goLeft, onGround, isAlive;
    Game game;
    MovingTile (double x, double y, double width, double height, Game game) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        deadX=-1;
        deadY=-1;
        centerX = x+(width/2);
        centerY = y+(height/2);
        goRight=false;
        goLeft=false;
        onGround=false;
        isAlive=true;
        this.game=game;
    }
    void update () {
        applyGravity();
        if (goRight || goLeft) {
            x+=game.cat.dx;
        }
        if (game.state=="FLOORISWATER") {
            counterCollision();
        } else if (game.state=="CURIOSITY") {
            counterBCollision();
        }
        windowCollision();
        if (deadX==-1 && deadY==-1 && !isAlive) {
            deadX=x;
            deadY=y;
        }
        x=x;
        y=y;
        centerX = x+(width/2);
        centerY = y+(height/2);
    }
    void applyGravity () {
        if (!onGround) {
            y+=6;
            goRight=false;
            goLeft=false;
        } else {
            y=y;
        }
    }
    void counterCollision () {
        if (x<game.counter.x+game.counter.width && x+width>game.counter.x) {
            if (y+height>game.counter.y) {
                if (y+height>game.counter.y+10) {
                    if (x<game.counter.x+game.counter.width && 
                    x>game.counter.x+game.counter.width-10) {
                        x=x+3;
                    } else if (x+width>game.counter.x && 
                    x+width<game.counter.x+10) {
                        x=x-3;
                    } else {
                        
                    }
                }
                onGround=true;
            }
        } else {
            onGround=false;
            windowCollision();
            return;
        }
    }
    void counterBCollision () {
        if (x<game.counterB.x+game.counterB.width && x+width>game.counterB.x) {
            if (y+height>game.counterB.y) {
                if (y+height>game.counterB.y+10) {
                    if (x<game.counterB.x+game.counterB.width && 
                    x>game.counterB.x+game.counterB.width-10) {
                        x=x+3;
                    } else if (x+width>game.counterB.x && 
                    x+width<game.counterB.x+10) {
                        x=x-3;
                    } else {
                        
                    }
                }
                onGround=true;
            }
        } else {
            onGround=false;
            windowCollision();
            return;
        }
    }
    void windowCollision () {
        if (x+width>=game.width) {
            goRight=false;
            x=x-3;
        } else if (x<=0) {
            goLeft=false;
            x=x+3;
        }
        if (y+height>=game.height+(game.tileSize/2)) {
            isAlive=false;
        } else if (y<=0) {
            isAlive=true;
            y=y+3;
        } else {
            isAlive=true;
        }
    }
    
    
}