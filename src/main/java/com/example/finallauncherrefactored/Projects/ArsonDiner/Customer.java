package com.example.finallauncherrefactored.Projects.ArsonDiner;

import java.util.Random;

public class Customer extends Entity {
    
    Random random = new Random();
    
    Order order;
    int satisfaction;
    int seat;
    boolean seated;
    
    Customer() {
        order = decideOrder();
        satisfaction = 10;
        x = 400;
        y = 400;
        height = 180;
        width = 150;
        seat = 0;
        seated = false;
    }
    
    Customer(double xCoord, double yCoord) {
        order = decideOrder();;
        this.satisfaction = 10;
        this.x = xCoord;
        this.y = yCoord;
        this.height = 180;
        this.width = 150;
        seat = 0;
        seated = false;
    }
    
    Order decideOrder() {
        int value = random.nextInt(3);
        
        if (value == 0) {
            return Order.BURGER;
        } else if (value == 1) {
            return Order.MILK;
        } else {
            return Order.MUFFIN;
        }
    }
    
}