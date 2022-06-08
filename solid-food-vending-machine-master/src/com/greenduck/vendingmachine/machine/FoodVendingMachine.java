package com.greenduck.vendingmachine.machine;

import java.util.ArrayList;
import java.util.List;

import com.greenduck.vendingmachine.foods.Food;
import com.greenduck.vendingmachine.money.Banknote;

public abstract class FoodVendingMachine {
    
    public static final String ERR_MESSAGE_INVALID_FOOD_SELECTION = "Invalid Food Selection";

    protected double balance;


    protected List<Food> foods;

    public FoodVendingMachine() {
        this.foods = new ArrayList<>();
    }



    public double getBalance() {
        return balance;
    }

    public void printBalance() {
        System.out.println("Current Balance: " + balance + " ");
    }

    public void addFood(Food food) {
        this.foods.add(food);
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
    
    protected void verifyValidFoodSelection(int selectedIndex) {
        if (selectedIndex >= this.foods.size()) {
            throw new IllegalArgumentException(ERR_MESSAGE_INVALID_FOOD_SELECTION);
        }
    } 
    

    /* Playground */
    public abstract void addBalance(Banknote note);

    public abstract Food getFood(int selectedIndex);

}
