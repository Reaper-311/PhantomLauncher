package com.example.finallauncherrefactored.Projects.BlockFight;

class Contestant {
    
    GameLogic game;
    AI ai;
    double defense, speed;
    String name;
    double health;
    double x, y, width, height, dy;
    boolean isJumping;
    boolean meleeRange;
    boolean magicRange;
    boolean rangeRange;
    boolean attack;
    Armory armory;
    
    Contestant(GameLogic game, double x, double y)
    {
        this.game = game;
        this.x = x;
        this.y = y;
        isJumping = false;
        dy = 0;
        
        this.armory = Armory.MELEE;
    }
    
    void startJumping()
    {
        if (isJumping == false)
        {
            isJumping = true;
            dy = -13;
        }
    }
    
    boolean meleeRange(Contestant c) {
        if(Math.abs(this.x - c.x) < 60) {
            return true;
        }
        return false;
    }
    
    boolean magicRange(Contestant c) {
        if(Math.abs(this.x - c.x) < 400) {
            return true;
        }
        return false;
    }
    
    boolean rangeRange(Contestant c) {
        if(Math.abs(this.x - c.x) > 50 && Math.abs(this.x - c.x) < 500) {
            return true;
        }
        return false;
    }
}