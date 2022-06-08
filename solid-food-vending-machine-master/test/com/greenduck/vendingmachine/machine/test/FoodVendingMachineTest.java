package com.greenduck.vendingmachine.machine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.greenduck.vendingmachine.common.test.FoodVendingTestConstant;
import com.greenduck.vendingmachine.foods.Biscuit;
import com.greenduck.vendingmachine.foods.ChocolateBar;
import com.greenduck.vendingmachine.foods.Food;
import com.greenduck.vendingmachine.foods.InstantNoodle;
import com.greenduck.vendingmachine.foods.Sausage;
import com.greenduck.vendingmachine.machine.FoodVendingMachine;
import com.greenduck.vendingmachine.machine.GreenDuckFoodVendingMachine;
import com.greenduck.vendingmachine.money.Banknote;
import com.greenduck.vendingmachine.money.PriceTag;

public class FoodVendingMachineTest {

    private FoodVendingMachine foodVendingMachine;

    @Before
    public void setUp() {
        //foodVendingMachine = new MonkeyFoodVendingMachine(Currency.VND);
        foodVendingMachine = new GreenDuckFoodVendingMachine();
    }

    @Test
    public void testFoodVendingMachineInitialization() {
        assertEquals(0, foodVendingMachine.getBalance(), FoodVendingTestConstant.EPSILON);
    }




    @Test
    public void testBuyFood_ShouldNotReturnFoodAndKeepCurrentBalance_WhenNotEnoughBalance() {
        // given
        Banknote vndNote = new Banknote(50);
        foodVendingMachine.addBalance(vndNote);

        Food dubaiSausage = new Sausage();
        PriceTag dubaiSausagePrice = new PriceTag(100);
        dubaiSausage.setPriceTag(dubaiSausagePrice);
        foodVendingMachine.addFood(dubaiSausage);

        // when
        Food food = foodVendingMachine.getFood(0);

        // then
        assertTrue(foodVendingMachine.getBalance() < dubaiSausage.getPriceTag().getPrice());
        assertNull(food);
        assertEquals(vndNote.getAmount(), foodVendingMachine.getBalance(), FoodVendingTestConstant.EPSILON);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuyFood_ShouldThrowException_WhenFoodSelectionIsOutOfRange() {
        // given
        Food omachiNoodle = new InstantNoodle();
        PriceTag dubaiSausagePrice = new PriceTag( 15000);
        omachiNoodle.setPriceTag(dubaiSausagePrice);
        foodVendingMachine.addFood(omachiNoodle);

        // when
        try {
            foodVendingMachine.getFood(4);
        } catch (IllegalArgumentException e) {
            assertEquals(FoodVendingMachine.ERR_MESSAGE_INVALID_FOOD_SELECTION, e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuyFood_ShouldRemoveFoodFromMachine_WhenPurchaseSuccessfully() {
        // given
        Food chocolateBar = new ChocolateBar();
        PriceTag chocolatePrice = new PriceTag(0.55);
        chocolateBar.setPriceTag(chocolatePrice);
        foodVendingMachine.addFood(chocolateBar);

        Banknote usdNote = new Banknote( 5);
        foodVendingMachine.addBalance(usdNote);

        // when
        try {
            Food expectedChocolateBar = foodVendingMachine.getFood(0);
            assertEquals(chocolateBar, expectedChocolateBar);

            foodVendingMachine.getFood(0);
        } catch (IllegalArgumentException e) {
            assertEquals(FoodVendingMachine.ERR_MESSAGE_INVALID_FOOD_SELECTION, e.getMessage());
            throw e;
        }
    }

    @Test
    public void testBuyFood_ShouldReturnFoodAndReduceBalance_WithMachineVNDCurrency() {
        // given

        Food biscuit = new Biscuit();
        PriceTag biscuitPrice = new PriceTag(1.43);
        biscuit.setPriceTag(biscuitPrice);
        foodVendingMachine.addFood(biscuit);

        // when
        Banknote vndNote = new Banknote( 50000);
        foodVendingMachine.addBalance(vndNote);
        double initialBalance = foodVendingMachine.getBalance();
        Food actualFood = foodVendingMachine.getFood(0);

        // then
        assertTrue(initialBalance > biscuit.getPriceTag().getPrice() );
        double expectedRemainingBalance = initialBalance
                - biscuitPrice.getPrice() ;
        assertEquals(expectedRemainingBalance, foodVendingMachine.getBalance(), FoodVendingTestConstant.EPSILON);
        assertEquals(biscuit, actualFood);

    }


}
