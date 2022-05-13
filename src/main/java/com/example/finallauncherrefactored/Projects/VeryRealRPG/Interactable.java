package com.example.finallauncherrefactored.Projects.VeryRealRPG;

class Interactable
{
    int type;
    int id;
    Encounter encounter;
    int sprite;
    String text;
    Interactable(int type)
    {
        this.type = type;
        this.id = 1;
        this.encounter = null;
        this.sprite = 0;
    }
    Interactable(String text)
    {
        this.type = 0;
        this.id = 1;
        this.encounter = null;
        this.sprite = 0;
        this.text = text;
    }
    Interactable(int type, int sprite)
    {
        this.type = type;
        this.id = 1;
        this.encounter = null;
        this.sprite = sprite;
    }
    Interactable(int type, int sprite, Encounter encounter)
    {
        this.type = type;
        this.id = 1;
        this.encounter = encounter;
        this.sprite = sprite;
    }

}
