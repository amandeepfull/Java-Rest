package com.app.dao;

import com.app.entity.Food;

import java.util.List;
import java.util.Set;

public interface FoodDao {
    Food add(Food food);
    List<Food> getFoods(Set<String> foodIds);
}
