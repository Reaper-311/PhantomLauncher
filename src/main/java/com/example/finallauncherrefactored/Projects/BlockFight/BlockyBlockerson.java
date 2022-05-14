package com.example.finallauncherrefactored.Projects.BlockFight;

class BlockyBlockerson extends Contestant
{
    //array for weapons equiped
    //health
    //money
    //skill points
    //  damage
    //  defence
    //  magic?
    //  speed
    //makes terrible puns and cliche jokes at random
    GameLogic game;
    FIGHT fight;

    boolean movingLeft;
    boolean movingRight;
    boolean holdingShift;
    double startJump;
    double maxJump;

    BlockyBlockerson(GameLogic game, double x, double y) {
        super(game, x, y);
        this.startJump = y - 250;
        width = 60;
        maxJump = y + 100;
        this.health = 100;
    }

    void updateBlockyBlockerson() {
        if (movingLeft == true)
        {
            x -= 5;
        }
        else if (movingRight == true)
        {
            x += 5;
        }

        if(holdingShift == true && movingLeft == true){
            x -= 10;
        }
        else if(holdingShift == true && movingRight == true) {
            x += 10;
        }
        
        /*
        if(meleeRange == true && attack == true)
        {
            game.cir.health -= 25;
        }
        else if (magicRange == true && attack == true) {
            game.cir.health -= 15;
        }
        else if (rangeRange == true && attack == true) {
            game.cir.health -= 10;
        }
        */
        // game.player.x = keyA - game.player.width / 2;
    }
    
    void clickAttack() {
        attack = true;
    }
}