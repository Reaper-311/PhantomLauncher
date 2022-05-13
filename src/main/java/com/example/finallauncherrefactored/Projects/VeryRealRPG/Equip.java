package com.example.finallauncherrefactored.Projects.VeryRealRPG;

public class Equip
{


    public final String name;
    public final double STR, INT, AGI, CON, MIND, LUCK;
    public final int id;
    public final Action ability;

    Equip(String name, double STR, double INT, double AGI, double CON, double MIND, double LUCK, int id, Action ability)
    {
        this.name = name;
        this.STR = STR;
        this.INT = INT;
        this.AGI = AGI;
        this.CON = CON;
        this.MIND = MIND;
        this.LUCK = LUCK;
        this.id = id;
        this.ability = ability;
    }

}
