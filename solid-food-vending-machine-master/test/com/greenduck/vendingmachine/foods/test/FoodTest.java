package com.greenduck.vendingmachine.foods.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.greenduck.vendingmachine.common.test.FoodVendingTestConstant;
import com.greenduck.vendingmachine.foods.Biscuit;
import com.greenduck.vendingmachine.foods.ChocolateBar;
import com.greenduck.vendingmachine.foods.Food;
import com.greenduck.vendingmachine.foods.InstantNoodle;
import com.greenduck.vendingmachine.foods.Sausage;
import com.greenduck.vendingmachine.money.PriceTag;

public class FoodTest {

    private Food food;

    @Test
    public void testBiscuitCreation_ShouldReturnCorrectProperties() {

        food = new Biscuit();
        PriceTag priceTag = new PriceTag(5.60);
        food.setPriceTag(priceTag);

        assertEquals(Biscuit.NAME, food.getName());
        assertEquals(5.60, food.getPriceTag().getPrice(), FoodVendingTestConstant.EPSILON);

    }

    @Test
    public void testChocolateBarCreation_ShouldReturnCorrectProperties() {

        food = new ChocolateBar();
        double price = 2.23;
        PriceTag priceTag = new PriceTag( price);
        food.setPriceTag(priceTag);
        assertEquals(ChocolateBar.NAME, food.getName());
        assertEquals(price, food.getPriceTag().getPrice(), FoodVendingTestConstant.EPSILON);
    }

    @Test
    public void testInstantNoodleCreation_ShouldReturnCorrectProperties() {
        food = new InstantNoodle();
        double price = 3.40;
        PriceTag priceTag = new PriceTag( price);
        food.setPriceTag(priceTag);

        assertEquals(InstantNoodle.NAME, food.getName());
        assertEquals(price, food.getPriceTag().getPrice(), FoodVendingTestConstant.EPSILON);
    }

    @Test
    public void testSausageCreation_ShouldReturnCorrectProperties() {
        food = new Sausage();
        double price = 15000;
        PriceTag priceTag = new PriceTag( price);
        food.setPriceTag(priceTag);

        assertEquals(Sausage.NAME, food.getName());
        assertEquals(price, food.getPriceTag().getPrice(), FoodVendingTestConstant.EPSILON);
    }

}
