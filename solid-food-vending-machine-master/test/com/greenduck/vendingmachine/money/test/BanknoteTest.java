package com.greenduck.vendingmachine.money.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.greenduck.vendingmachine.money.Banknote;

public class BanknoteTest {
    
    @Test
    public void testBanknoteCreation() {
        int value = 50000;
        Banknote vndNote1 = new Banknote( value);
        assertEquals(value, vndNote1.getAmount());
    }
}
