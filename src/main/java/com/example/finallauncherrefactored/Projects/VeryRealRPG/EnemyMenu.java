package com.example.finallauncherrefactored.Projects.VeryRealRPG;

class EnemyMenu
{
    int type;
    int selected;
    Encounter encounter;

    EnemyMenu(Encounter e)
    {
        this.type = 0;
        this.selected = 0;
        this.encounter = e;
    }

    public void moveRight()
    {
        if (this.selected+1 >= this.getAliveCount())
        {
            this.selected = 0;
        }
        else
        {
            this.selected++;
        }
    }

    public void moveLeft()
    {
        if (this.selected == 0 && this.getAliveCount() > 0)
        {
            this.selected = this.getAliveCount() - 1;
        }
        else
        {
            this.selected--;
        }
    }

    public int getFirstAlive()
    {
        /*
        for (int i = 0; i < encounter.enemies.size(); i++)
        {
            if (encounter.enemies.get(i).isAlive)
            {
                return i;
            }

        }
        */
        return 0;
    }

    public int getAliveCount()
    {

        return encounter.enemies.size();
    }
    public int getDeadCount()
    {
        int c = 0;
        for (int i = 0; i < encounter.enemies.size(); i++)
        {
            if (!encounter.enemies.get(i).isAlive)
            {
                c++;
            }

        }
        return c;
    }
}
