package com.example.finallauncherrefactored.Projects.VeryRealRPG;

import com.example.finallauncherrefactored.Main;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;

import java.util.Objects;

class Player
{
    double x, y, xVel, yVel;
    Level location;
    int place, placeTo, direction;
    double exp;
    Equip[] equips;
    Equip[] equipped;
    int level;
    boolean hasPickaxe;
    Image sprite, up, down, left, right;
    double HP, maxHP, MP, maxMP, stamina, maxStamina;
    double STR, INT, AGI, MIND, CON, LUCK;
    double att, mag, def, mdef, acc, evade;

    Player()
    {
        this.x = 2;
        this.y = 2;
        this.xVel = 0;
        this.yVel = 0;
        this.direction = 0;
        this.place = 0;
        this.placeTo = 0;
        this.exp = 0;
        this.level = 1;
        this.maxHP = 100;
        this.HP = 100;
        this.maxMP = 100;
        this.MP = 100;
        this.maxStamina = 100;
        this.stamina = 100;

        this.equips = new Equip[]{new Equip("Sword", 1.2, 1.1, 1.3, 1.1, 1, 1.2,0, Action.SLASH), new Equip("Spear", 1.3, 1.1, 1.1, 1.4, 1.1, 1, 1, Action.SKEWER), new Equip("Knife", 1.1, 1.3, 1.5, 0.8, 1, 1.1,2, Action.BACKSTAB), new Equip("Hammer", 1.5, 1, 0.6, 1.2, 1.1, 1,3, Action.SMASH), new Equip("Staff", 0.8, 1.5, 1.1, 1, 1.2, 1,4, Action.FIREBALL),
                new Equip("Leather Armor", 1.1, 1, 1.3, 1.2, 1.1, 1,5, Action.RESPITE), new Equip("Plate Mail", 1.45, 0.8, 0.8, 1.5, 1, 1,6, Action.TACKLE), new Equip("Cloak", 1, 1.1, 1.6, 1.1, 1.2, 1,7, Action.STEALTH), new Equip("Tunic", 1, 1.2, 1.1, 1, 1.5, 1.1,8, Action.HEALOTHERS), new Equip("Robe", 0.8, 1.4, 1.1, 0.8, 1.25, 1,9, Action.ICESHARD),
                new Equip("No Hat", 1.1, 1.1, 1.2, 1.1, 1.1, 1,10, Action.PUNCH), new Equip("Helm", 1.3, 0.8, 0.9, 1.45, 1, 1,11, Action.HEADBUTT), new Equip("Hood", 1.1, 1.15, 1.45, 1, 1.15, 1.1,12, Action.SWIFTSTRIKES), new Equip("Mitre", 1, 1.2, 1, 1, 1.4, 1.2, 13, Action.RADIANCE), new Equip("Wizard's Hat", 0.8, 1.35, 1.1, 0.9, 1.2, 1,14, Action.THUNDERSTRIKE),
                new Equip("Bird", 1.0, 1.1, 1.1, 1.1, 1, 1,15, Action.WHIRLWIND), new Equip("Frog", 1, 1, 1.1, 1.2, 1.2, 1,16, Action.ICICLE), new Equip("Rat", 1, 1, 1.2, 1.1, 1, 1.2,17, Action.SEAR), new Equip("Snail", 1.0, 1.0, 0.4, 1.4, 1.4, 1.4,18, Action.ROCKFALL),new Equip("Cat", 1, 1.2, 1.2, 1, 1, 1.1,19, Action.GLIMMER)};

        this.equipped = new Equip[4];
        equipped[0] = equips[0];
        equipped[1] = equips[5];
        equipped[2] = equips[10];
        equipped[3] = equips[15];

        this.sprite = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/player2.png")));
        this.up = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/player0.png")));
        this.down = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/player2.png")));
        this.left = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/player3.png")));
        this.right = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/player1.png")));
        updateStats();
        restore();
    }

    public void updateStats()
    {
        //checks stats from equips and level and recalculates them
        STR = (level * 2 + 8) * equipped[0].STR * equipped[1].STR * equipped[2].STR * equipped[3].STR;
        INT = (level * 2 + 8) * equipped[0].INT * equipped[1].INT * equipped[2].INT * equipped[3].INT;
        CON = (level * 2 + 8) * equipped[0].CON * equipped[1].CON * equipped[2].CON * equipped[3].CON;
        AGI = (level * 2 + 8) * equipped[0].AGI * equipped[1].AGI * equipped[2].AGI * equipped[3].AGI;
        MIND = (level * 2 + 8) * equipped[0].MIND * equipped[1].MIND * equipped[2].MIND * equipped[3].MIND;
        LUCK = (level * 4 + 6) * equipped[0].LUCK * equipped[1].LUCK * equipped[2].LUCK * equipped[3].LUCK;
        //gets used statistics from base stats
        maxHP = (level * 15) + (30 * (1 + CON/15) * (1 + STR/35));
        maxMP = (10 * (1 + MIND/7) * (1 + INT/12));
        maxStamina = (10 * (1 + CON/8) * (1 + AGI/14));
        att = (level/5 + 0.8) * (6 * (1 + (STR-10)/10) * (1 + CON/50));
        mag = (level/5 + 0.8) * (6 * (1 + (INT-10)/10) * (1 + MIND/50));
        def = (level/5 + 0.8) * (1.5 * (1 + (CON-10)/20));
        mdef = (level/5 + 0.8) *(1.5 * (1 + (MIND-10)/20));
        acc = level/20 + (1 * (1 + (AGI-10)/35) * (1 + LUCK/100));
        evade = level/25 +(0.24 * (1 + (AGI-12)/35) * (1 + LUCK/80));
        if (HP > maxHP)
        {
            HP = maxHP;
        }
        if (MP > maxMP)
        {
            MP = maxMP;
        }
        if (stamina > maxStamina)
        {
            stamina = maxStamina;
        }

    }

    public void addExp(double amount)
    {
        exp += amount;
        while (exp >= 100)
        {
            levelUp();
        }
    }

    public void levelUp()
    {
        level++;
        exp -= 100;
        updateStats();
        restore();
    }

    public void changeEquip(int n, int d)
    {
        //changes the equipped item in slot n
        if ( !(n % 5 == 0 && d == -1) && !((n+1) % 5 == 0 && d == 1 ))
        {
            equipped[n] = equips[equipped[n].id +d];
        }
        updateStats();
        restore();
    }

    public void moveLeft()
    {
        if (yVel == 0)
        {
            direction = 3;
            if (checkTile().type == 1)
            {
                startScroll();
            }
            else if (checkTile().type > 2 && checkTile().interact == null)
            {
                xVel = -0.1;
            }
        }
    }

    public void moveDown()
    {
        if (xVel == 0)
        {
            direction = 2;
            if (checkTile().type == 1)
            {
                startScroll();
            }
            else if (checkTile().type > 2 && checkTile().interact == null)
            {
                yVel = - 0.1;
            }
        }
    }

    public void moveUp()
    {
        if (xVel ==0)
        {
            direction = 0;
            if (checkTile().type == 1)
            {
                startScroll();
            }
            else if (checkTile().type > 2 && checkTile().interact == null)
            {
                yVel = 0.1;
            }
        }
    }

    public void startScroll()
    {
        if (direction == 0)
        {
            this.placeTo = 5;
        }
        if (direction == 1)
        {
            this.placeTo = 1;
        }
        if (direction == 2)
        {
            this.placeTo = -5;
        }
        if (direction == 3)
        {
            this.placeTo = -1;
        }
    }

    public void moveRight()
    {
        if (yVel == 0)
        {
            direction = 1;
            if (checkTile().type == 1)
            {
                startScroll();
            }
            else if (checkTile().type > 2 && checkTile().interact == null)
            {
                xVel = 0.1;
            }
        }
    }

    public void deductCost(Action a)
    {
        if(a.isMagic)
        {
            MP -= a.cost;
        }
        else
        {
            stamina -= a.cost;
        }
    }

    public Tile checkTile()
    {
        Tile t;
        if (direction == 0 && y < 15)
        {
            return (location.rows[(int) Math.round(x)][(int) Math.round(y+1)]);
        }
        if (direction == 1 && x < 15)
        {
            return (location.rows[(int) Math.round(x+1)][(int) Math.round(y)]);
        }
        if (direction == 2 && y > 0)
        {
            return (location.rows[(int) Math.round(x)][(int) Math.round(y-1)]);
        }
        if (direction == 3 && x > 0)
        {
            return (location.rows[(int) Math.round(x-1)][(int) Math.round(y)]);
        }
        else
        {
            return new Tile(0,0,1);
        }
    }

    public void alignTile()
    {
        if (x % 1 <= 0.0001 ^ x % 1 >= 0.9999)
        {
            xVel = 0;
            x = Math.round(x);
        }
        if (y % 1 <= 0.0001 ^ y % 1 >= 0.9999)
        {
            yVel = 0;
            y = Math.round(y);
        }
    }

    public void applyVelocity()
    {
        x += xVel;
        y += yVel;
    }

    public void alignPlayer()
    {
        xVel = 0;
        x = Math.round(x);
        yVel = 0;
        y = Math.round(y);
    }

    public void showDirection()
    {
        if (direction == 0)
        {
            sprite = up;
        }
        if (direction == 1)
        {
            sprite = right;
        }
        if (direction == 3)
        {
            sprite = left;
        }
        if (direction == 2)
        {
            sprite = down;
        }
    }

    public void restore()
    {
        this.HP = maxHP;
        this.MP = maxMP;
        this.stamina = maxStamina;
    }

    public void update()
    {
        applyVelocity();
        alignTile();
        showDirection();

    }
}
