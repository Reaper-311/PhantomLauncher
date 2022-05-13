package com.example.finallauncherrefactored.Projects.VeryRealRPG;


public enum Action
{
    //0 is no element, 1 is fire, 2 is ice, 3 is thunder, 4 is darkness, 5 is light
    ATTACK(0, 1,0, false, true, 0, 1,0, "Attack"),

    MAGIC(1, 0, 1, false, false, 0, 100,0, "Magic"),

    SPECIAL(2, 0,1, false, false, 0, 100,0, "Special"),

    FLEE(3, 0,1, false, false, 0, 100,0, "Flee"),

    FIRE(4,1.2, 0, true,true, 1, 1.2,5, "Fire"),

    ICE(5,1.5, 0, true,true, 2, 0.8,12, "Ice"),

    THUNDER(6, 1, 2,true,true, 3, 0.8,20, "Thunder"),

    HEAL(7,-7, 1, true, true, 5, 100,7, "Heal"),

    SHINE(8, 0.8, 0, true, true, 5, 2,7, "Shine"),

    DEATH(30, 7, 0, true, true, 4, 0.35, 30, "Death"),

    SLASH(9, 0.75, 2, false, true, 0 ,0.9, 4, "Wide Slash"),

    SKEWER(10, 1.5, 0, false, true, 0 ,1.1,8, "Skewer"),

    BACKSTAB(11, 5, 0, false, true, 0 , 0.5, 30, "Lethal Stab"),

    SMASH(12, 1.5, 0, false, true, 0 ,1.3, 12, "Smash"),

    FIREBALL(13, 0.8, 2, true, true, 1 ,1.3, 10, "Fireball"),

    RESPITE(14, -6, 1, false, true, 0 ,100, 12, "Respite"),

    TACKLE(15, 1.8, 0, false, true, 0 ,0.8, 4, "Tackle"),

    STEALTH(16, 3, 0, false, true, 0 ,1.8, 16, "Sneak Attack"),

    HEALOTHERS(17, -15, 2, true, true, 4 ,100, 15, "Heal Others"),

    ICESHARD(18, 1.3, 2, true, true, 2 ,1.3, 12, "Iceshard"),

    PUNCH(19, 1.1, 0, false, true, 0 ,1.1, 2, "Punch"),

    HEADBUTT(20, 1.3, 0, false, true, 0 ,0.8, 6, "Headbutt"),

    SWIFTSTRIKES(21, 1.1, 2, false, true, 0 ,1.4, 12, "Swift Strikes"),

    RADIANCE(22, 1, 2, true, true, 5 ,1.5, 18, "Radiance"),

    THUNDERSTRIKE(23, 2, 0, true, true, 3 ,1.3,20, "Thunderstrike"),

    WHIRLWIND(24, 1.2, 0, false, true, 3 ,1.2,8, "Whirlwind"),

    ICICLE(25, 1.4, 0, false, true, 2 ,0.8,8, "Icicle"),

    SEAR(26, 1.1, 0, false, true, 1 ,1.3,6, "Sear"),

    ROCKFALL(27, 1.2, 0, true, true, 0 ,1.2, 8, "Rockfall"),

    GLIMMER(28, 1.2, 0, false, true, 5 ,1.3,10, "Glimmer"),

    CRITICAL(34, 3, 0, false, true, 5 ,10,10, "critical hit"),

    DARKNESS(31, 1, 0, true, true, 4 ,0.9,10, "Darkness"),

    SCRATCH(32, 1.2, 0, false, true, 0 ,1.3,10, "Scratch"),

    WAVE(33, 1.8, 0, false, true, 2 ,1.3,10, "splashing around"),

    GUST(29, 1, 0, true, true, 3 ,1.0, 10, "Gust");


    public final int id;
    public final double power;
    public final int target;
    public final boolean isMagic;
    public final boolean usesTurn;
    public final int element;
    public final double acc;
    public final double cost;
    public final String name;
    Action(int id, double power, int target, boolean isMagic, boolean usesTurn, int element, double acc, double cost, String name)
    {
        this.id = id;
        this.power = power;
        this.target = target;
        this.isMagic = isMagic;
        this.usesTurn = usesTurn;
        this.element = element;
        this.acc = acc;
        this.cost = cost;
        this.name = name;
    }
}

