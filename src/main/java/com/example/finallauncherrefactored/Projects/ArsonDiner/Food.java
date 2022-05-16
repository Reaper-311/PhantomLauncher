package com.example.finallauncherrefactored.Projects.ArsonDiner;

public class Food extends Entity{
    
    boolean isCarried;
    boolean isDelivered;
    boolean isUsed;
    Order order;
    
    Food() {
        order = Order.BURGER;
        this.isCarried = false;
        this.isDelivered = false;
        this.isUsed = false;
        this.x = 50;
        this.y = 50;
        this.height = 48;
        this.width = 54;
    }
    
    Food(Order o, double xCoord, double yCoord) {
        order = o;
        this.isCarried = false;
        this.isDelivered = false;
        this.isUsed = false;
        this.x = xCoord;
        this.y = yCoord;
        if (o == Order.MILK) {
            this.width = 40;
            this.height = 68;
            this.x += 100;
        } else if (o == Order.MUFFIN) {
            this.width = 50;
            this.height = 56;
            this.x += 200;
        } else {
            this.width = 54;
            this.height = 48;
        }
    }
    
    Food(boolean isCarried, double xCoord, double yCoord) {
        order = Order.BURGER;
        this.isCarried = isCarried;
        this.isDelivered = false;
        this.isUsed = false;
        this.x = xCoord;
        this.y = yCoord;
        this.height = 48;
        this.width = 54;
    }
    
}