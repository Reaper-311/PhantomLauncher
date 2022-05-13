package com.example.finallauncherrefactored.Projects.VeryRealRPG;

class FightMenu
{
    Layer[] layers;
    int selected;

    FightMenu()
    {
        this.selected = 0;
        this.layers = new Layer[3];
        layers[0] = new Layer(4,0);
        layers[1] = new Layer(6,1);
        layers[2] = new Layer(4,2);
    }

    public void advance()
    {
        if (this.layers[selected].selected == 1 && this.selected == 0)
        {
            this.selected = 1;
            this.layers[selected].selected = 0;
        }
        else if (this.layers[selected].selected == 2 && this.selected == 0)
        {
            this.selected = 2;
            this.layers[selected].selected = 0;
        }
    }

    public void retract()
    {
        if (this.selected != 0)
        {
            this.layers[selected].selected = 0;
            this.selected = 0;
        }
    }

    public void moveUp()
    {
        if (this.layers[selected].selected != 0)
        {
            this.layers[selected].selected--;
        }
    }

    public void moveDown()
    {
        if (this.layers[selected].selected+1 != this.layers[selected].options.length)
        {
            this.layers[selected].selected++;
        }
    }

}

class Layer
{
    Action[] options;
    int selected;
    int depth;
    int size;
    boolean isShowing;
    Layer(int size, int depth)
    {
        this.options = new Action[size];
        this.size = size;
        this.depth = depth;
        this.isShowing = false;
        this.selected = 0;

    }
}
