package com.app.dao;

import com.app.entity.Food;

import java.util.List;
import java.util.Set;

public interface FoodDao {
    Food add(Food food);
    double getTotalPrice(Set<String> foodIds);
}
