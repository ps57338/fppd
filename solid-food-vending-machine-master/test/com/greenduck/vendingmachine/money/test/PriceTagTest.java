package com.greenduck.vendingmachine.money.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.greenduck.vendingmachine.common.test.FoodVendingTestConstant;
import com.greenduck.vendingmachine.money.PriceTag;

public class PriceTagTest {
    
    @Test
    public void testPriceTagCreation_ShouldCreateCorrectPriceTag() {
        
        double price = 5.60;
        PriceTag usdTag = new PriceTag(price);
        assertEquals(price, usdTag.getPrice(), FoodVendingTestConstant.EPSILON);
        
    }
}
