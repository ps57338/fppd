package com.greenduck.vendingmachine.machine;

import com.greenduck.vendingmachine.foods.Food;
import com.greenduck.vendingmachine.money.Banknote;

public class GreenDuckFoodVendingMachine extends FoodVendingMachine {


    @Override
    public void addBalance(Banknote note) {
        this.balance += note.getAmount();
    }

    @Override
    public Food getFood(int selectedIndex) {
        verifyValidFoodSelection(selectedIndex);
        
        double currentBalance = getBalance();
        Food selectedFood = foods.get(selectedIndex);
        
        this.balance -= selectedFood.getPriceTag().getPrice();
        
        if (balance < 0) {
            System.out.println("In Your Dream");
            this.balance = currentBalance;
            selectedFood = null;
        } else {
            this.foods.remove(selectedIndex);
        }
        printBalance();
        return selectedFood;
    }

}
