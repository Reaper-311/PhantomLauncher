package com.example.finallauncherrefactored.Projects.Lives;

class Cat {
    double x, y, dx, dy, width, height, speed, g, centerX, centerY, jumpR;
    Game game;
    int lives;
    boolean goLeft, goRight, onGround, jumping;
    Cat (double x, double y, double width, Game game, double jumpR) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=width*2;
        dx=0;
        dy=0;
        lives=9;
        this.game=game;
        goRight=false;
        this.jumpR=jumpR;
        goLeft=false;
        onGround=false;
        jumping=false;
        speed=0.5;
        g=0.5;
        centerX = x+(width/2);
        centerY = y+(height/2);
    }

    void move () {
        if (lives>9) {
            lives=9;
        }
        winCondition();
        applyGravity();
        //testCollision();

        if (goRight) {
            dx+=speed;
        } else if (goLeft) {
            dx-=speed;
        } else {
            if (dx>0) {
                dx--;
            } else if (dx<0) {
                dx++;
            }
        }

        if (dx>8) {
            dx=8;
        } else if (dx<-8) {
            dx=-8;
        }

        windowCollision();
        if (game.state=="FLOORISWATER") {
            counterCollision();
            fridgeCollision();
            waterCollision();
        } else if (game.state=="CURIOSITY") {
            counterBCollision();
            vaseCollision();
            potCollision();
        } else if (game.state=="LASERCHASE") {
            laserCollision();
        } else if (game.state=="YARN") {
            yarnCollision();
        }
        checkJump();

        x+=dx;
        y+=dy;

        centerX = x+(width/2);
        centerY = y+(height/2);

    }

    void yarnCollision () {
        if (x+width+7>game.yarn.x && x-7<game.yarn.x+game.yarn.width) {
            //x positions aligned
            if (y+height>game.yarn.y && y<game.yarn.y+game.yarn.height) {
                //y position aligned
                if (game.yarn.y+game.yarn.height>y-height) {
                    //push up
                    game.yarn.y=y-game.yarn.height;
                    game.yarn.dy=-(game.yarn.dy*3);
                    if (goRight) {
                        game.yarn.dx+=game.yarn.speed;
                    } else if (goLeft) {
                        game.yarn.dx-=game.yarn.speed;
                    }
                }
                
                //can't push down
            }
        }
    }

    void checkJump () {
        if (!onGround && jumping) {
            jumping=false;
        }

        if (game.state=="CURIOSITY") {
            jumpR=0.4;
        } else {
            jumpR=1.7 ;
        }

        if (onGround && jumping) {
            dy=-g*(game.tileSize/jumpR);
            onGround=false;
            jumping=false;
        }
    }

    void testCollision () {
        if (x>game.tileSize*7) {
            goLeft=true;
            goRight=false;
        } else if (x<game.tileSize*8) {
            goRight=true;
            goLeft=false;
        }
    }

    void counterCollision () {
        if (x<game.counter.x+game.counter.width && x+width>game.counter.x) {
            if (y+height+speed>game.counter.y && y<game.counter.y+game.counter.height) {
                if (y+height>game.counter.y+10) {
                    if (x-speed<game.counter.x+game.counter.width && 
                    x>game.counter.x+game.counter.width-10) {
                        dx=+speed*4;
                    } else if (x+width+speed>game.counter.x && 
                    x+width<game.counter.x+10) {
                        dx=-speed*4;
                    } else {
                        y=game.counter.y-height;
                    }
                }
                dy=0;
                onGround=true;
            }
        } else {
            onGround=false;
            windowCollision();
            microwaveCollision();
            return;
        }
    }

    void counterBCollision () {
        if (x<game.counterB.x+game.counterB.width && x+width>game.counterB.x) {
            if (y+height+speed>game.counterB.y && y<game.counterB.y+game.counterB.height) {
                if (y+height>game.counterB.y+10) {
                    if (x-speed<game.counterB.x+game.counterB.width && 
                    x>game.counterB.x+game.counterB.width-10) {
                        dx=+speed*4;
                    } else if (x+width+speed>game.counterB.x && 
                    x+width<game.counterB.x+10) {
                        dx=-speed*4;
                    } else {
                        y=game.counterB.y-height;
                    }
                }
                dy=0;
                onGround=true;
            }
        } else {
            onGround=false;
            windowCollision();
            //potCollision();
            //vaseCollision();
            return;
        }
    }

    void fridgeCollision () {
        if (x<game.fridge.x+game.fridge.width && x+width>game.fridge.x) {
            if (y+height>game.fridge.y && y<game.fridge.y+game.fridge.height) {
                if (y+height>game.fridge.y+10) {
                    if (x-speed<game.fridge.x+game.fridge.width && 
                    x>game.fridge.x+game.fridge.width-10) {
                        dx=+speed*4;
                    } else if (x+width+speed>game.fridge.x && 
                    x+width<game.fridge.x+10) {
                        dx=-speed*4;
                    } else {
                        y=game.fridge.y-height;
                    }
                }
                dy=0;
                onGround=true;
            }
        } else {
            onGround=false;
            windowCollision();
            microwaveCollision();
            counterCollision();
            return;
        }
    }

    void microwaveCollision () {
        if (game.microwave.isAlive) {
            if (x<game.microwave.x+game.microwave.width && x+width>game.microwave.x) {
                if (y+height+3>game.microwave.y && y<game.microwave.y+game.microwave.height) {
                    if (y+height>game.microwave.y-1) {
                        if (x+width>game.microwave.x && x+width<game.microwave.x+10
                        && dx>0) {
                            game.microwave.goRight=true;
                            game.microwave.goLeft=false;
                        } else if (x<game.microwave.x+game.microwave.width && x>game.microwave.x+game.microwave.width-10
                        && dx<0) {
                            game.microwave.goLeft=true;
                            game.microwave.goRight=false;
                        } else {
                            game.microwave.goRight=false;
                            game.microwave.goLeft=false;
                        }
                        if (x+width>game.microwave.x && x+width<game.microwave.x+10
                        && dx==0) {
                            dx-=1;
                        } else if (x<game.microwave.x+game.microwave.width && x>game.microwave.x+game.microwave.width-10
                        && dx==0) {
                            dx+=1;
                        }
                    }
                    dy=0;
                    onGround=true;
                } else {
                    onGround=false;
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }

    void potCollision () {
        if (game.pot.isAlive) {
            if (x<game.pot.x+game.pot.width && x+width>game.pot.x) {
                if (y+height+3>game.pot.y && y<game.pot.y+game.pot.height) {
                    if (y+height>game.pot.y-1) {
                        if (x+width>game.pot.x && x+width<game.pot.x+10
                        && dx>0) {
                            game.pot.goRight=true;
                            game.pot.goLeft=false;
                        } else if (x<game.pot.x+game.pot.width && x>game.pot.x+game.pot.width-10
                        && dx<0) {
                            game.pot.goLeft=true;
                            game.pot.goRight=false;
                        } else {
                            game.pot.goRight=false;
                            game.pot.goLeft=false;
                        }
                        if (x+width>game.pot.x && x+width<game.pot.x+10
                        && dx==0) {
                            dx-=1;
                        } else if (x<game.pot.x+game.pot.width && x>game.pot.x+game.pot.width-10
                        && dx==0) {
                            dx+=1;
                        }
                    }
                    //
                    dy=0;
                    onGround=true;
                    //
                } else {
                    //
                    onGround=false;
                    //
                }
            } else {
                if (!(x<game.vase.x+game.vase.width && x+width>game.vase.x)) {
                    onGround=false;
                    counterBCollision();
                }
                return;
            }
        } else {
            return;
        }
    }

    void vaseCollision () {
        if (game.vase.isAlive) {
            if (x<game.vase.x+game.vase.width && x+width>game.vase.x) {
                if (y+height+3>game.vase.y && y<game.vase.y+game.vase.height) {
                    if (y+height>game.vase.y-1) {
                        if (x+width>game.vase.x && x+width<game.vase.x+10
                        && dx>0) {
                            game.vase.goRight=true;
                            game.vase.goLeft=false;
                        } else if (x<game.vase.x+game.vase.width && x>game.vase.x+game.vase.width-10
                        && dx<0) {
                            game.vase.goLeft=true;
                            game.vase.goRight=false;
                        } else {
                            game.vase.goRight=false;
                            game.vase.goLeft=false;
                        }
                        if (x+width>game.vase.x && x+width<game.vase.x+10
                        && dx==0) {
                            dx-=1;
                        } else if (x<game.vase.x+game.vase.width && x>game.vase.x+game.vase.width-10
                        && dx==0) {
                            dx+=1;
                        }
                    }
                    //
                    dy=0;
                    onGround=true;
                    //
                } else {
                    //
                    onGround=false;
                    //
                }
            } else {
                if (!(x<game.pot.x+game.pot.width && x+width>game.pot.x)) {
                    onGround=false;
                    counterBCollision();
                }
                return;
            }
        } else {
            return;
        }
    }

    void waterCollision () {
        if (y+height>game.water.y+(game.tileSize/3)) {
            game.respawn();
        }
    }

    void laserCollision () {
        if (!game.laser.caught) {
            if (x<game.laser.x+game.laser.width && x+width>game.laser.x) {
                if (y<game.laser.y+game.laser.height && y+height>game.laser.y) {
                    game.laser.caught=true;
                }
            }
        }
    }

    void windowCollision () {
        if (x+width+speed>=game.width) {
            dx=-speed;
        } else if (x-speed<=0) {
            dx=+speed;
        }
        if (y+height+g>=game.height) {
            dy=0;
            onGround=true;
        } else if (y-speed<=0) {
            dy+=0.2;
        }
    }

    void applyGravity () {
        if (!onGround) {
            if (dy>13) {
                dy=13;
            } else if (dy<-15) {
                dy=-15;
            }
            dy+=g; 
        }
    }

    void winCondition () {
        if (x+width>game.food.x+9 && x<game.food.x+game.food.width-9 && y+height>game.food.y-9 && y<game.food.y+game.food.height+9) {
            game.nextGame();
        }
    }
}