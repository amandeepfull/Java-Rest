package com.foody.order.model;

import lombok.Data;

import java.util.List;

@Data
public class Food {
    private String id;
    private String name;
    private double price;

    public static double getFoodsTotalPrice(List<Food> foods){
        double totalAmt = 0;
        for(Food food : foods)
            totalAmt = totalAmt + food.getPrice();
        return totalAmt;
    }
}
