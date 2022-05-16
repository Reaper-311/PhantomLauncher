package com.example.finallauncherrefactored.Projects.ArsonDiner;

class World
{

    Player player;
    FoodSpawn foodSpawn;
    CustomerSpawn customerSpawn;
    Customer[] customers = new Customer[10]; //Increase Number for more customer variety
    Food[] foods = new Food[6];
    double width, height;

    World()
    {
        this.width = 800;
        this.height = 759;

        player = new Player(400, 400);
        foodSpawn = new FoodSpawn(375, 240);
        customerSpawn = new CustomerSpawn(800, height - player.height - 50);

        //Fill Food Array
        for (int i = 0; i < foods.length; i++) {
            foods[i] = new Food(decideOrder(i), foodSpawn.x, foodSpawn.y);
        }
        
        //Fill Customer Array
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(customerSpawn.x, customerSpawn.y);
        }
        
    }
    
        
        Order decideOrder(int value) {
        
        if (value == 0 || value == 1) {
            return Order.BURGER;
        } else if (value == 2 || value == 3) {
            return Order.MILK;
        } else {
            return Order.MUFFIN;
        }
    }
}