package com.example.finallauncherrefactored.Projects.BlockFight;

class AI extends Contestant
{
    double distanceX;
    double distanceY;
    //BlockyBlockerson Block;
    //CirCircleson Sir;
    //double movementDesision = (Math.random()*100);
    //double distance = game.player.x - Sir.x;
    //double attackDesision = (Math.random()*100);

    AI(GameLogic game, double x, double y)
    {
        super(game, x, y);
    }

    public void move()
    {
        //movement code
        //if on oposite sides of map

        double distance = game.player.x - this.x;

        if(this.x > game.player.x && Math.abs(distance) > 60)
        {
            this.x -=3;
        }
        else if(this.x < game.player.x && Math.abs(distance) > 60)
        {
            this.x +=3;
        }

        if (Math.random() < 0.01)
        {
            startJumping();
        }
    }
    
    void attack()
    {
        if(meleeRange(game.player) == true)
        {
            game.player.health -= 15;
        }
    }
}
//move left
//move right
//jump
//melee attack - 60 pixels
//magic attack - 120 pixels, or 30 pixels for high damage
//ranged attack - 300 pixels MAX
//math.random movement
//
//math.random attack times within a given interval length away from blocky
//  if statement starting with math.random attack time that changes constantly
//  if statements inside to deside when it will attack and what type
//  closer to blocky more likely to attack
//
//
//math.random attack type