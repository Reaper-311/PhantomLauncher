package com.example.finallauncherrefactored.Projects.VeryRealRPG;

import com.example.finallauncherrefactored.Main;
import javafx.scene.image.Image;

import java.util.Objects;

public enum Monster
{
    TOAD("Toad", 50, 15, 1.5, 1, 1, 0.9, 0.15,new double[]{0,0,0.3,-1,0,0}, 0, 0, Action.TACKLE , new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/sprite0.png")), 196, 196, true, true)),

    BIRD("Bird", 35, 9, 7, 0.7, 0.8, 0.8, 0.5, new double[]{-0.3,0,-0.5,-0.5,0,0}, 0, 1, Action.GUST , new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/sprite1.png")), 196, 196, true, true)),

    CAT("Cat", 50, 7, 7, 0.9, 0.9, 1, 0.4, new double[]{0,-0.3,0.3,0,0,0}, 0, 2, Action.FIRE, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/sprite6.png")), 196, 196, true, true)),

    RAT("Rat", 70, 9, 5, 1.1, 0.9, 0.9, 0.3, new double[]{0,-0.3,0.2,0,0,0}, 0, 3, Action.STEALTH, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/sprite7.png")), 196, 196, true, true)),

    BAT("Bat", 35, 5, 9, 0.9, 1.1, 1.1, 0.5, new double[]{0,0,-0.3,-0.5,0.3,-0.3},  1, 4,Action.GUST, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/sprite8.png")), 196, 196, true, true)),

    SPIDER("Spider", 50, 8, 12, 0.8, 0.9, 1.2, 0.2,new double[]{0,-0.3,0,0,0.3,-0.5}, 1, 0, Action.DARKNESS, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/sprite11.png")), 196, 196, true, true)),

    BEE("Bee", 45, 7, 12, 0.8, 1, 1.3, 0.25,new double[]{-0.3,-0.3,0.3,-1,0,0}, 1, 0,Action.SKEWER, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/sprite9.png")), 196, 196, true, true)),

    BEAR("Bear", 300, 35, 7, 1.2, 1.1, 1.2, 0.1,new double[]{0,-0.5,0.3,0.3,0,0},  0, 0,Action.TACKLE, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/sprite10.png")), 196, 196, true, true)),

    OWL("Owl", 65, 10, 18, 1, 1.2, 1.3, 0.45,new double[]{-0.3,0,-0.3,-0.3,0.5,0.5}, 1, 0,Action.SHINE, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/sprite12.png")), 196, 196, true, true)),

    MANATEE("Manatee", 4000, 15, 15, 0.1, 0.7, 1.4, 0.05,new double[]{0.9, 0.5, 0.5, -1, 0.2, 0.2}, 1, 0,Action.WAVE, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/sprite13.png")))),

    GHOUL("Ghoul", 500, 20, 1, 0.8, 0.6, 1.5, 0.6, new double[]{0,0.5,0.5,0,1.25,-1.5}, 2, 5,Action.SMASH, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/sprite5.png")), 196, 196, true, true)),

    SKELETON("Skeleton", 150, 9, 6, 0.75, 0.5, 1.2, 0.6, new double[]{0,0.3,0.3,0,1.25,-2}, 0, 6,Action.DEATH, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/sprite4.png")), 196, 196, true, true)),

    PLAYER("You", 1, 1, 1, 1, 1, 1, 0, new double[]{0,0,0,0,0,0}, 0, 0,Action.TACKLE, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("VeryRealRPG/Images/sprite4.png")), 196, 196, true, true));

    public final String name;
    public final double maxHP;
    public double att;
    public double mag;
    public final double def;
    public final double mdef;
    public final double acc;
    public final double evade;
    public final double[] resistances;
    public final int ai;
    public final int id;
    public final Action action;
    public final Image sprite;

    Monster(String name, double HP, double att, double mag, double def, double mdef, double acc, double evade, double[] resistances, int ai, int id, Action a, Image image)
    {
        this.name = name;
        this.maxHP = HP;
        this.att = att;
        this.mag = mag;
        this.def = def;
        this.mdef = mdef;
        this.acc = acc;
        this.evade = evade;
        this.resistances = resistances;
        this.ai = ai;
        this.id = id;
        this.action = a;
        this.sprite = image;
    }


}

