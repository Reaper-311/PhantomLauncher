package com.example.finallauncherrefactored.Projects.Lives;

class Game {
    double width, height, tileSize, spawnX, spawnY;
    double start, end, time;
    int score, highScore, rand;
    Cat cat;
    FloorTile counter;
    FloorTile counterB;
    WaterTile water;
    FloorTile fridge;
    MovingTile microwave;
    Food food;
    MovingTile pot;
    MovingTile vase;
    Laser laser;
    String state;
    Yarn yarn;
    Hoop hoop;
    Button begin;
    Button restart;
    Button skin;
    boolean gameOver;
    Game () {
        state = "MENU";
        //change back to tileSize=75
        tileSize=75;
        this.width = tileSize*15;
        this.height = tileSize*12;
        spawnX=width/2;
        spawnY=height/2;
        begin = new Button (width/2-tileSize, height/1.5, tileSize*4, tileSize*2, this);
        restart = new Button (width/2-tileSize, height/1.5, tileSize*4, tileSize*2, this);
        cat = new Cat(spawnX, spawnY, tileSize, this, 1.7);
        skin = new Button(cat.x, cat.y, cat.width, cat.height, this);
        generateFloors();
        update();
        highScore=5;
        score=0;
        gameOver=false;
        rand=-1;
        start=0;
        end=0;
        time=0;
    }
    
    void update () {
        if (cat.lives==0) {
            end=System.nanoTime();
            gameOver=true;
            if (score>=highScore) {
               highScore=score; 
            }
            score=0;
        } else {
            gameOver=false;
        }
        time=(int)(end-start);
        changeWin();
        changeSpawn();
        skin.x=cat.x;
        skin.y=cat.y;
    }
    
    void changeWin () {
        if (state=="FLOORISWATER") {
            food.x=fridge.x+food.width;
            food.y=fridge.y-food.height;
        } else if (state=="MENU") {
            food.x=width;
            food.y=0;
        } else if (state=="GAMEOVER") {
            food.x=width;
            food.y=0;
        } else if (state=="CURIOSITY") {
            if (!pot.isAlive) {
                food.x=width/2;
                food.y=height-(tileSize*3)-(tileSize/2);
            } else if (!vase.isAlive) {
                food.x=width;
                food.y=0;
                respawn();
            }
        } else if (state=="LASERCHASE") {
            if (laser.caught) {
                food.x=width-(tileSize*1.5);
                food.y=height-food.height;
            } else {
                food.x=width;
                food.y=0;
            }
        } else if (state=="YARN") {
            if (yarn.scored) {
                food.x=tileSize*8;
                food.y=height-food.height;
            } else {
                food.x=width;
                food.y=0;
            }
        }
    }
    
    void start () {
        cat.lives=10;
        nextGame();
    }
    
    void changeSpawn () {
        if (state=="GAMEOVER") {
            spawnX=cat.x;
            spawnY=cat.y;
        } else 
        if (state=="MENU") {
            spawnX=width/2;
            spawnY=height-(tileSize*3);
        } else
        if (state=="FLOORISWATER") {
            spawnX=tileSize/2;
            spawnY=tileSize*4;
        } else 
        if (state=="CURIOSITY") {
            spawnX=width/2;
            spawnY=tileSize*4;
        } else
        if (state=="LASERCHASE") {
            spawnX=tileSize;
            spawnY=tileSize*6;
        } else 
        if (state=="YARN") {
            spawnX=width/3;
            spawnY=height-(tileSize*2.5);
        }
    }
    
    void generateFloors () {
        counter = new FloorTile(0, height-(tileSize*3), tileSize*8, tileSize*3);
        counterB = new FloorTile(tileSize*4, height-(tileSize*3), tileSize*7, tileSize*3);
        microwave = new MovingTile(tileSize*3, tileSize*4, tileSize, tileSize, this);
        fridge = new FloorTile(width-(tileSize*2), tileSize*5, tileSize*2, height-(tileSize*5));
        water = new WaterTile(0, height-tileSize, width, tileSize); 
        food = new Food(width/2, height-(tileSize/2), tileSize, tileSize/2);
        pot = new MovingTile(tileSize*4 ,tileSize*3.5 ,tileSize*1.5, tileSize*2.5 ,this);
        vase = new MovingTile(tileSize*9.5 ,tileSize*3.5 ,tileSize*1.5, tileSize*2.5 ,this);
        laser = new Laser(width/2, height/2, tileSize*.2, this);
        yarn = new Yarn(tileSize, height-tileSize, tileSize*.5, this);
        hoop = new Hoop(width-tileSize, height/2.4, tileSize, this);
        //tileSize*7
    }
    
    void respawn () {
        changeSpawn();
        cat.x=spawnX;
        cat.y=spawnY;
        
        hoop.x=width-tileSize;
        hoop.y=height/2.4;
        
        yarn.x=tileSize;
        yarn.y=height-tileSize;
        yarn.scored=false;
        yarn.dx=0;
        yarn.dy=0;
        
        microwave.x=tileSize*3;
        microwave.y=tileSize*5;
        microwave.onGround=false;
        microwave.goRight=false;
        microwave.goLeft=false;
        
        pot.x=tileSize*4;
        pot.y=tileSize*3.5;
        pot.goLeft=false;
        pot.goRight=false;
        pot.onGround=false;
        
        vase.x=tileSize*9.5;
        vase.y=tileSize*3.5;
        vase.goLeft=false;
        vase.goRight=false;
        vase.onGround=false;
        
        laser.x=width/2;
        laser.y=height/2;
        laser.caught=false;
        
        cat.dx=0;
        cat.dy=0;
        cat.onGround=false;
        cat.lives--;
    }
    
    void nextGame () {
        rand=(int) (Math.random()*4);
        //0 is floor is water
        //1 is curiosity
        //2 is laser chase
        //3 is yarn game
        //
        if (state!="GAMEOVER") {
            if (rand==0) {
                state="CURIOSITY";
                s();
                food.x=width;
                food.y=0;
            } else if (rand==1) {
                state="FLOORISWATER";
                s();
                food.x=fridge.x+food.width;
                food.y=fridge.y-food.height;
            } else if (rand==2) {
                state="LASERCHASE";
                s();
                laser.active=false;
                food.x=width;
                food.y=height-food.height;
            }else if (rand==3) {
                state="YARN";
                s();
                yarn.scored=false;
                food.x=tileSize*8;
                food.y=height-food.height;
            }else {
                return;
            }
        } else if (state=="GAMEOVER") {
            state="MENU";
            cat.lives=10;
        }
        
    }
    void s () {
        score++;
        changeWin();
        cat.lives++; 
        respawn();
    }
    
}
    
