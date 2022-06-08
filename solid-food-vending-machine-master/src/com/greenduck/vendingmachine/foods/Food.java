package com.greenduck.vendingmachine.foods;

import com.greenduck.vendingmachine.money.PriceTag;

//TODO: Add javadoc, make it like a pro
public abstract class Food {
    
    private String name;
    private PriceTag priceTag;
    
    public String getName() {
        return name;
    }
        
    public PriceTag getPriceTag() {
        return priceTag;
    }
    
    protected void setName(String name) {
        this.name = name;
    }
    
    public void setPriceTag(PriceTag priceTag) {
        this.priceTag = priceTag;
    }
    
}
