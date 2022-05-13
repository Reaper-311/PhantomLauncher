package com.example.finallauncherrefactored.Projects.VeryRealRPG;

import java.util.ArrayList;

class Tile
{
    int x, y, type, sprite;
    Interactable interact;
    /**
     * Type determines what a tile does, 3 or higher is passable, 1 is a screen transition, any other number is impassable.
     */
    Tile(int x, int y, int type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        this.sprite = type;
    }

    Tile(int x, int y, int type, Interactable interact)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        this.sprite = type;
        this.interact = interact;
    }

    /**
     * Type 2 is solid, Type 1 is a screen transition, all other types are passable.
     */
    Tile(int x, int y, char input)
    {
        this.x = x;
        this.y = y;
        if (input == 'w')
        {
            this.type = 2;
            this.sprite = 2;
        }
        else if (input == '4')
        {
            this.type = 2;
            this.sprite = 5;
        }
        else if (input == '.' || input == '1' || input == 'j')
        {
            this.type = 3;
            this.sprite = 3;
        }
        else if (input == 'r')
        {
            this.type = 3;
            this.sprite = 9;
        }
        else if (input == 'b')
        {
            this.type = 2;
            this.sprite = 6;
        }
        else if (input == 'd')
        {
            this.type = 3;
            this.sprite = 5;
        }
        else if (input == 'h' || input == '8')
        {
            this.type = 3;
            this.sprite = 3;
        }
        else if (input == 'f')
        {
            this.type = 3;
            this.sprite = 8;
        }
        else if (input == 'q')
        {
            this.type = 2;
            this.sprite = 1;
        }
        else if (input == 'l')
        {
            this.type = 2;
            this.sprite = 0;
        }
        else if (input == 'c')
        {
            this.type = 2;
            this.sprite = 7;
        }
        else if (input == 'i')
        {
            this.type = 3;
            this.sprite = 9;
        }
        else if (input == 'o')
        {
            this.type = 3;
            this.sprite = 3;
        }
        else
        {
            this.type = 3;
            this.sprite = 4;
        }

        if (input == 'm')
        {
            this.interact = new Interactable(2, 0, new Encounter());
        }
        else if (input == 'h')
        {
            this.interact = new Interactable(1, 2);
        }
        else if (input == 'r')
        {
            this.interact = new Interactable(3, 1);
        }
        else if (input == 'j')
        {
            ArrayList<Enemy> enemies = new ArrayList<Enemy>();
            enemies.add(new Enemy(Monster.GHOUL, 450, 200));
            for (int i = 0; i <= 2; i++)
            {
                enemies.add(new Enemy (Monster.SKELETON, i*250 + 200, 500));
            }
            this.interact = new Interactable(2, 0, new Encounter(enemies, 100, "You see a treasure chest, but there is a ghoul in the way!"));
        }
        else if (input == 'k')
        {
            ArrayList<Enemy> enemies = new ArrayList<Enemy>();
            for (int i = 0; i <= 2; i++)
            {
                enemies.add(new Enemy (Monster.BIRD, i*250 + 150, 200));
                if (i < 2)
                {
                    enemies.add(new Enemy (Monster.BIRD, i*250 + 275, 500));

                }

            }
            this.interact = new Interactable(2, 0, new Encounter(enemies, 25, "You come across a terrifying flock of birds!"));
        }
        else if (input == '5')
        {
            ArrayList<Enemy> enemies = new ArrayList<Enemy>();
            for (int i = 0; i <= 2; i++)
            {
                enemies.add(new Enemy (Monster.SPIDER, i*250 + 150, 200));
                if (i < 2)
                {
                    enemies.add(new Enemy (Monster.OWL, i*250 + 275, 500));

                }

            }
            this.interact = new Interactable(2, 0, new Encounter(enemies, 45, "You find some spiders blocking your path into the mansion, and some owls swoop upon you as well."));
        }
        else if (input == '6')
        {
            ArrayList<Enemy> enemies = new ArrayList<Enemy>();
            for (int i = 0; i <= 2; i++)
            {
                enemies.add(new Enemy (Monster.OWL, i*250 + 150, 300));


            }
            this.interact = new Interactable(2, 0, new Encounter(enemies, 35, "As you are about to enter some sort of garden, some owls block your path!"));
        }
        else if (input == '3')
        {
            ArrayList<Enemy> enemies = new ArrayList<Enemy>();
            for (int i = 0; i <= 2; i++)
            {
                enemies.add(new Enemy (Monster.SPIDER, i*250 + 150, 200));
            }
            this.interact = new Interactable(2, 0, new Encounter(enemies, 30, "You find some spiders in the forest! Kill them, quickly!"));
        }
        else if (input == '1')
        {
            ArrayList<Enemy> enemies = new ArrayList<Enemy>();
            for (int i = 0; i <= 2; i++)
            {
                enemies.add(new Enemy (Monster.SKELETON, i*250 + 150, 200));
            }
            this.interact = new Interactable(2, 0, new Encounter(enemies, 35, "You find some skeletons in a closet!"));
        }
        else if (input == '2')
        {
            ArrayList<Enemy> enemies = new ArrayList<Enemy>();

            enemies.add(new Enemy (Monster.BEAR, 450, 200));

            this.interact = new Interactable(2, 0, new Encounter(enemies, 45, "You find a bear in the distance, and decide to approach.\nWhat could go wrong?"));
        }
        else if (input == '7')
        {
            ArrayList<Enemy> enemies = new ArrayList<Enemy>();

            enemies.add(new Enemy (Monster.BEAR, 450, 200));
            for (int i = 0; i <= 2; i++)
            {
                enemies.add(new Enemy (Monster.BEE, i*250 + 200, 500));
            }

            this.interact = new Interactable(2, 0, new Encounter(enemies, 45, "You see a bear collecting honey from a beehive, and both the bear and bees attack!"));
        }
        else if (input == '8')
        {
            ArrayList<Enemy> enemies = new ArrayList<Enemy>();

            enemies.add(new Enemy (Monster.MANATEE, 450, 200));


            this.interact = new Interactable(2, 0, new Encounter(enemies, 1500, "You see! A manatee!\nIt is blocking the bridge!"));
        }
        else if (input == 'i')
        {
            ArrayList<Enemy> enemies = new ArrayList<Enemy>();
            enemies.add(new Enemy(Monster.BAT, 250, 175));
            enemies.add(new Enemy(Monster.RAT, 395, 425));
            enemies.add(new Enemy(Monster.CAT, 500, 175));


            this.interact = new Interactable(2, 0, new Encounter(enemies, 25, "You find a trio of animals, with rhyming names, intent on killing you!"));
        }
    }
}
