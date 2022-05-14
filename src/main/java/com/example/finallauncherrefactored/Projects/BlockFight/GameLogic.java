package com.example.finallauncherrefactored.Projects.BlockFight;

class GameLogic {
    BlockyBlockerson player;
    CirCircleson cir;
    Triminion tri;
    AI ai;
    double height, width;

    GameLogic(){
        height = 540;
        width = 960;

        player = new BlockyBlockerson(this, width / 2, height - 20);
        cir = new CirCircleson(this, width / 2, height - 20);
        tri = new Triminion(this, width / 2, height - 20);
    }

    void registerHits()
    {
        if (cir.meleeRange(player) == true && player.attack == true && player.armory == Armory.MELEE)
        {
            cir.health -= 25; 
            //tri.health -= 25;
        }
        
        if(cir.magicRange(player) == true && player.attack == true && player.armory == Armory.MAGIC) {
            cir.health -= 15;
            //tri.health -= 15;
        }
        
        if(cir.rangeRange(player) == true && player.attack == true && player.armory == Armory.RANGE) {
            cir.health -= 10;
            //tri.health -= 10;
        }

        if (Math.random() < 0.01)
        {
            cir.attack();
        }

        player.attack = false;
    }

    void update(){
        applyGravity(player);
        applyGravity(cir);
        registerHits();
        Boundaries();
    }

    void applyGravity(Contestant c){

        c.dy += 0.5;
        c.y += c.dy;

        if (c.y + 20 > height)
        {
            c.y = height - 20; //Set him on the floor exactly
            c.dy = 0;
            c.isJumping = false;
        }

    }

    void Boundaries() {
        if(player.x + player.width >= width) {
            player.x = width - player.width;
        }

        if(player.x <= 0){
            player.x = 0;
        }
    }
}