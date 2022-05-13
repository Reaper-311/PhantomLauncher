package com.example.finallauncherrefactored.Projects.VeryRealRPG;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;
import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;

class Game
{
    /**
     * Double to keep track of the size of the menu, mostly not used.
     */
    double width, height;
    /**
     *  Data used to construct each level, read from the level files.
     */
    char[][] levelData;
    /**
     * Each level that is stored in the game.
     */
    Level[] levels;
    /**
     * The list of strings that will display in each level, with the index of the levelDescript being the same as the level.
     */
    String[] levelDescript;
    /**
     * A list of strings for each type of interactable, based on its type.
     */
    String[] interactDescript;
    /**
     * Seed used for randomness, such as accuracy and enemy actions.
     */
    Random seed = new Random();
    /**
     * The list of each encounter in the game.
     */
    Encounter[] encounters;
    /**
     * The encounter most recently interacted with.
     */
    Encounter currentEncounter;
    /**
     * The description of the interactable most recently interacted with, used so that the game can continue to draw the text.
     */
    String lastInteract;
    /**
     * The player, which contains coordinates and stats.
     */
    Player player;
    /**
     * The side menu, contains the image used for the menu and the currently selected item.
     */
    SideMenu menu;
    /**
     * Whether the game is in combat, used to change what the game draws and what keys do.
     */
    boolean inCombat;
    /**
     * Images used for each enemy, depending on its id value.
     */
    ArrayList<Enemy> lastEnemies;
    /**
     * Keeps track of the last encounter, in order to revive the enemies if the ecounter is left.
     */
    Image[] sprites;
    /**
     * Images used for each equippable item.
     */
    Image[] itemSprites;
    /**
     * The images used for each interactable, which do not change based on the tileset.
     */
    Image[] interactSprites;
    /**
     * The image of the box used to store text in combat.
     */
    Image fightBox;
    /**
     * The pointer used to show what is being selected in menus.
     */
    Image pointer;
    /**
     * The bar used to show enemy HP.
     */
    Image bar;
    /**
     * Checks if the player is in the equip menu, used to see if the app should draw the pointer and change key effects.
     */
    boolean inMenu;
    /**
     * The enemy menu, keeps track of the enemies in the current encounter, and which ones are dead.
     */
    EnemyMenu enemyMenu;
    /**
     * The fight menu, which keeps track of the selected action, and which layer of actions is selected.
     */
    FightMenu fightMenu;
    /**
     * A boolean used to see if the player is allowed to act in combat.
     * If false, the enemy turn happens and toggles it back to true.
     */
    boolean isPlayerTurn;
    /**
     * Keeps track of if the player is selecting the target for an attack.
     */
    boolean targetSelecting;
    /**
     * A string that is printed, used to keep track of damage taken and dealt.
     */
    String battleText;
    /**
     * The game app, because I did not know when first making this that you could have it here.
     */
    GameApp gameApp;

    Game(GameApp g)
    {
        this.gameApp = g;
        this.width = 1440;
        this.height = 1024;
        this.levels = new Level[15];
        try {
            levelData = LevelReader.main(LevelReader.levels);
        } catch (IOException i)
        {
            System.err.println("Caught IOException: " + i.getMessage());
        }
        initLevels();
        /*
        levels[0] = new Level();
        levels[1] = new Level("tile", 1);
        /*
        for (int i = 2; i < levels.length; i++)
        {
        levels[i] = new Level("sky", i);
        }
         */
        this.player = new Player();
        this.menu = new SideMenu();
        player.location = levels[0];
        this.levelDescript = new String[15];
        levelDescript[0] = "This is the room you spawn in!";
        levelDescript[1] = "You enter the old mansion, and notice the smell of rotting flesh.";
        levelDescript[10] = "You continue along the river, and find out that you are in what is\n currently the final room! Congratulations!";
        levelDescript[11] = "You find a bridge across a small river. What could be on the other side?";
        levelDescript[5] = "You enter into the forest, and start to realize that the wildlife will probably\n continue to impede you.";
        levelDescript[6] = "You see a walled off garden, and a mansion to the south.";
        for (int i = 0; i < levelDescript.length; i++)
        {
            if (levelDescript[i] == null)
            {
                levelDescript[i] = "test";
            }
        }
        this.interactDescript = new String[4];
        this.pointer = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/pointer.png")));
        this.fightBox = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/fightbox.png")));
        this.bar = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/bar.png")));
        this.itemSprites = new Image[20];
        interactDescript[0] = "Use WASD to move yourself and cursors. Use space to interact with things,\nand make selections in combat. Use i to toggle changing your equipment.";
        interactDescript[1] = "You have found a pickaxe!";
        interactDescript[2] = "You have encountered some enemies!";
        interactDescript[3] = "You smash the rock with your pickaxe.";
        this.lastInteract = interactDescript[0];
        this.inCombat = false;
        this.isPlayerTurn = true;
        this.encounters = new Encounter[1];
        encounters[0] = new Encounter();
        this.targetSelecting = false;
        this.currentEncounter = new Encounter();
        //this.sprites = new Image[3];
        this.itemSprites = new Image[20];
        this.interactSprites = new Image[3];
        this.inMenu = false;
        this.battleText = "";
        this.fightMenu = new FightMenu();
        initMenu();
    }

    /**
     * Constructs a new level for each level in the game, based on the level data for it.
     */
    public void initLevels()
    {
        for (int i = 0; i < 15; i++)
        {
            levels[i] = new Level(levelData[i], i);
        }
    }

    /**
     * Sets up the images that are used, and sets the player location to level 0.
     */
    public void Initialize()
    {
        startSprites();

        for (Level l : levels)
        {
            l.Initialize();
        }

        player.location = levels[0];

        for (Encounter e : encounters)
        {
            for (Enemy m : e.enemies)
            {
                m.sprite = m.m.sprite;
            }
        }
    }

    /**
     * Starts the image arrays for enemies, equips, and interactables.
     */
    public void startSprites()
    {
        /*
        for (int i = 0 ; i < sprites.length; i++)
        {
        this.sprites[i] = new Image("/Images/sprite"+i+".png", 196, 196, true, true);
        }
         */
        for (int i = 0 ; i < itemSprites.length; i++)
        {
            this.itemSprites[i] = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/equip"+i+".png")));
        }
        for (int i = 0 ; i < interactSprites.length; i++)
        {
            this.interactSprites[i] = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/interact"+i+".png")), 64, 64, true, true);
        }
    }

    /**
     * Sees if the player interactable and depending on its type, does something.
     * 0 displays the text contained in the interactable.
     * 1 gives the player a pickaxe, and clears itself.
     * 2 starts an encounter based on the encounter value of the interactable.
     * 3 clears the tile if the player has a pickaxe.
     */
    public void interact()
    {
        if (player.checkTile().interact != null )
        {
            int type = player.checkTile().interact.type;
            this.lastInteract = interactDescript[player.checkTile().interact.type];
            if (type == 0)
            {
                lastInteract = player.checkTile().interact.text;
            }
            if (type == 2)
            {
                startEncounter(player.checkTile().interact);
                initMenu();
            }
            if (type == 1)
            {
                player.hasPickaxe = true;
                clearTile();
            }
            if (type == 3)
            {
                if (player.hasPickaxe)
                {
                    clearTile();
                }
                else
                {
                    lastInteract = "You need a pickaxe to break this rock.";
                }
            }
        }
    }

    /**
     * Sets actions used for each option in the fight menu.
     */
    public void initMenu()
    {
        for (Layer l : fightMenu.layers)
        {
            if (l.depth == 1)
            {
                l.options[0] = Action.FIRE;
                l.options[1] = Action.ICE;
                l.options[2] = Action.THUNDER;
                l.options[3] = Action.SHINE;
                l.options[4] = Action.DEATH;
                l.options[5] = Action.HEAL;
            }
            if (l.depth == 0)
            {
                l.options[0] = Action.ATTACK;
                l.options[1] = Action.MAGIC;
                l.options[2] = Action.SPECIAL;
                l.options[3] = Action.FLEE;
            }
            if (l.depth == 2)
            {
                l.options[0] = player.equipped[0].ability;
                l.options[1] = player.equipped[1].ability;
                l.options[2] = player.equipped[2].ability;
                l.options[3] = player.equipped[3].ability;
            }
        }
    }

    /**
     * Used when a fight option is selected.
     * If it is 1 or 2 in layer 0, opens layer 1 or 2.
     * If it is 3 in layer 0, ends and refreshes the combat.
     * Otherwise, starts target selection for the selected action.
     */
    public void select()
    {
        if (fightMenu.selected == 0 && (fightMenu.layers[0].selected == 1 ^ fightMenu.layers[0].selected == 2))
        {
            fightMenu.advance();
        }
        else if (fightMenu.layers[fightMenu.selected].options[fightMenu.layers[fightMenu.selected].selected].id == 3)
        {
            this.inCombat = false;
            fightMenu.layers[0].selected = 0;
            currentEncounter.enemies = lastEnemies;
            player.restore();
            battleText = "You escaped!";
        }
        else
        {
            selectTarget(fightMenu.layers[fightMenu.selected].options[fightMenu.layers[fightMenu.selected].selected]);
        }
    }

    /**
     * Starts target selection, with the type of the enemy menu changed to the target type of the selected action.
     */
    public void selectTarget(Action a)
    {
        this.targetSelecting = true;
        enemyMenu.type = a.target;
    }

    /**
     * Moves the selected slot of the item menu based on the direction, looping if it would change the type, updates the players stats, and restores them.
     */
    public void shiftMenu(int d)
    {
        if ((player.equipped[menu.selected].id % 5) == 0 && d == -1)
        {
            player.equipped[menu.selected] = player.equips[player.equipped[menu.selected].id+4];
        }
        else if ((player.equipped[menu.selected].id+ 1) % 5 == 0 && d == 1)
        {
            player.equipped[menu.selected] = player.equips[player.equipped[menu.selected].id-4];
        }
        else
        {
            player.equipped[menu.selected] = player.equips[player.equipped[menu.selected].id + d];
        }
        player.updateStats();
        player.restore();
    }

    /**
     * Depending on the selected target and action, does the action.
     * If the player does not have enough MP or stamina, does not do damage.
     * Otherwise, it sets the player turn to false and deducts the cost.
     */
    public void playerAction(Action a)
    {
        if ((a.isMagic && a.cost <= player.MP) ^(!a.isMagic && a.cost <= player.stamina))
        {
            battleText = "";
            if (a.target == 0)
            {
                Enemy t = enemyMenu.encounter.enemies.get(enemyMenu.selected + enemyMenu.getFirstAlive());
                if  (checkAccuracy(player.acc, t.m.evade, a))
                {
                    damageEnemy(t, a);

                }
                else
                {
                    battleText += t.m.name +" avoided the attack!\n";
                }
                player.deductCost(a);
            }
            if (a.target == 1)
            {
                Enemy t = new Enemy();
                t.m.mag = (player.mag * player.mdef);
                t.m.att = (player.def*player.mag);
                damagePlayer(t, a);
                player.deductCost(a);
            }
            if (a.target == 2)
            {
                for (Enemy t : currentEncounter.enemies)
                {
                    if  (checkAccuracy(player.acc, t.m.evade, a))
                    {
                        damageEnemy(t, a);
                    }
                    else
                    {
                        battleText += t.m.name +" avoided the attack!\n";
                    }
                }
                player.deductCost(a);
            }
            isPlayerTurn = false;
        }
        else
        {
            battleText = "Not enough MP/Stamina!";
            this.targetSelecting = false;
        }
        for (int i = 0; i < currentEncounter.enemies.size(); i++)
        {
            currentEncounter.enemies.get(i).checkForDead();
            if (!currentEncounter.enemies.get(i).isAlive)
            {
                currentEncounter.enemies.remove(i);
                while (enemyMenu.selected >= currentEncounter.enemies.size())
                {
                    enemyMenu.selected--;
                }
            }
        }

    }

    /**
     * Damages the player, based on the stats of the enemy that does the action, the stats of the player, and the power of the action.
     */
    public void damagePlayer(Enemy a, Action m)
    {
        if (checkAccuracy(a.m.acc, player.evade, m))
        {
            double var = 1+ (seed.nextDouble()/5);
            if (m.isMagic)
            {

                player.HP -=(a.m.mag*m.power*var / player.mdef);
                if (a.m.mag*m.power / player.mdef > 0)
                {
                    battleText += "You took "+Math.round(a.m.mag*m.power*var /player.mdef)+" damage from " + a.m.name + "'s "+ m.name+"!\n";
                }
                else
                {
                    battleText += "You recovered "+ Math.round(Math.abs((a.m.mag*var*m.power /player.mdef)))+" HP!\n";
                }
            }
            else
            {
                player.HP -=(a.m.att*m.power / player.def);
                if (a.m.att*m.power / player.def > 0)
                {
                    battleText += "You took "+Math.round(a.m.att*var*m.power /player.def)+" damage from " + a.m.name + "'s "+ m.name+"!\n";
                }
                else
                {
                    battleText += "You recovered "+ Math.round(Math.abs((a.m.att*var*m.power /player.def)))+" HP!\n";
                }
            }
            player.updateStats();
        }
        else
        {
            battleText += a.name +" missed its attack!\n";
        }
    }

    /**
     * Damages an enemy based on its stats, the stats of the player, and the power and element of the used action.
     */
    public void damageEnemy(Enemy a, Action m)
    {
        double var = 1+ (seed.nextDouble()/5);
        if (m.isMagic)
        {
            a.HP -=(player.mag*m.power*var /a.m.mdef * (1- a.m.resistances[m.element]));
            if (player.mag*m.power /a.m.mdef * (1- a.m.resistances[m.element]) > 0)
            {
                battleText += a.m.name + " took "+Math.round((player.mag*m.power*var /a.m.mdef) * (1 - a.m.resistances[m.element]))+" damage!\n";
            }
            else
            {
                battleText += a.m.name + " recovered "+ Math.round(Math.abs((player.mag*m.power*var /a.m.mdef) * (1 -a.m.resistances[m.element])))+" HP!\n";
            }
        }
        else
        {
            a.HP -=(player.att*m.power *var/a.m.def * (1 -a.m.resistances[m.element]));
            if (player.att*m.power*var /a.m.def * (1 -a.m.resistances[m.element]) > 0)
            {
                battleText += a.m.name + " took "+Math.round((player.att*m.power*var /a.m.def) * (1 - a.m.resistances[m.element]))+" damage!\n";
            }
            else
            {
                battleText += a.m.name + " recovered "+Math.round(Math.abs((player.att*m.power*var /a.m.def * (1 - a.m.resistances[m.element]))))+" HP!\n";
            }
        }
        if (a.HP <= 0)
        {
            battleText += a.m.name + " has been slain!\n";
        }

    }

    /**
     * Stops combat, removes the encounter from the tile, and gives exp.
     */
    public void winEncounter()
    {
        this.inCombat = false;
        fightMenu.layers[0].selected = 0;
        currentEncounter.enemies = lastEnemies;
        player.restore();
        battleText += "You won!\nYou gained "+Math.round(currentEncounter.exp)+ " EXP!";
        if (player.exp + currentEncounter.exp >= 100)
        {
            battleText += "\nYou leveled up!";
        }
        player.addExp(currentEncounter.exp);
        clearTile();
    }

    /**
     * Sets the interactable on the tile the player is looking at to null.
     */
    public void clearTile()
    {
        Tile look = player.checkTile();
        player.location.rows[look.x][look.y].interact = null;
    }

    /**
     * Each alive enemy does an attack, and if the player is reduced below 0 HP, combat is ended.
     */
    public void enemyTurn()
    {
        targetSelecting = false;
        if (currentEncounter.checkAliveEnemies() == 0)
        {
            fightMenu.layers[0].selected = 0;
            fightMenu.selected = 0;
            isPlayerTurn = true;
            winEncounter();
            return;
        }
        else
        {
            for (Enemy e : currentEncounter.enemies)
            {
                e.checkForDead();
                if (e.isAlive)
                {
                    enemyAct(e , e.m.ai);
                }
            }
            if (currentEncounter.incoming != null)
            {
                currentEncounter.enemies.add(currentEncounter.incoming);
                currentEncounter.incoming = null;
            }

            fightMenu.layers[0].selected = 0;
            fightMenu.selected = 0;
            isPlayerTurn = true;
            if (currentEncounter.checkAliveEnemies() == 0)
            {
                winEncounter();
            }

            if (!currentEncounter.enemies.get(enemyMenu.selected + enemyMenu.getFirstAlive()).isAlive)
            {
                enemyMenu.selected = 0;
            }

            if (player.HP <= 0)
            {
                this.inCombat = false;
                fightMenu.layers[0].selected = 0;
                currentEncounter.enemies = lastEnemies;
                player.restore();
                battleText += "You were gravely wounded, but managed to get away.";
            }
        }
    }

    /**
     * If the first number multiplied by the accuracy of the action minus the second number is greater than a random number, returns true.
     */
    public boolean checkAccuracy(double a, double b, Action m)
    {
        if (seed.nextDouble() <= (a * m.acc)- b)
        {
            return true;
        }
        return false;
    }

    /**
     * Sets up the current encounter and enemy menu.
     */
    public void startEncounter(Interactable i)
    {
        inCombat = true;
        currentEncounter = i.encounter;
        lastEnemies = currentEncounter.getEnemies();
        battleText = currentEncounter.desc;
        enemyMenu = new EnemyMenu(currentEncounter);
    }

    public void update()
    {
        player.update();
        if (player.placeTo != 0)
        {
            screenScroll();
        }
        if (!isPlayerTurn)
        {
            enemyTurn();
        }
    }

    public void screenScroll()
    {
        if (player.location.id + player.placeTo >= 0 && player.location.id + player.placeTo < levels.length)
        {

            if (player.placeTo == 5)
            {

                player.location = levels[player.location.id + player.placeTo];
                player.y = 0;
            }
            if (player.placeTo == 1  && ((player.location.id + 1) % 5) != 0)
            {
                player.location = levels[player.location.id + player.placeTo];
                player.x = 0;
            }
            if (player.placeTo == -5)
            {
                player.location = levels[player.location.id + player.placeTo];
                player.y = 15;
            }
            if (player.placeTo == -1 && ((player.location.id) % 5) != 0)
            {
                player.location = levels[player.location.id + player.placeTo];
                player.x = 15;
            }
        }
        player.placeTo = 0;
    }

    /**
     * Depending on the ai value of an enemy, does an action.
     * 0 mostly uses a basic attack, with a small chance to use an alternate attack.
     * 1 uses an alternate attack more often.
     * 2 is used for the ghoul, and summons a skeleton when there are no other skeletons.
     */
    public void enemyAct(Enemy e, int ai)
    {
        if (ai == 0)
        {
            if (Math.random() > 0.05)
            {
                damagePlayer(e, Action.ATTACK);
            }
            else
            {
                damagePlayer(e, e.m.action);
            }

        }

        if (ai == 1)
        {
            if (Math.random() < 0.6 + e.state * 0.15)
            {
                damagePlayer(e, Action.ATTACK);
                e.state--;
            }
            else
            {
                damagePlayer(e, e.m.action);
                e.state++;
            }
        }

        if (ai == 2)
        {
            if (e.state < 3)
            {

                damagePlayer(e, Action.ATTACK);

                e.state++;

            }
            else
            {
                int size = currentEncounter.enemies.size();
                if  (size < 2)
                {
                    currentEncounter.incoming = new Enemy(Monster.SKELETON, 100, 100);
                    battleText += "Another skeleton arrives!\n";
                    e.state = 0;
                }
                else
                {

                    damagePlayer(e, e.m.action);

                }
            }

        }

    }

}

