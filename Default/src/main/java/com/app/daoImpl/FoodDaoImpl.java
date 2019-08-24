package com.app.daoImpl;

import com.app.dao.FoodDao;
import com.app.entity.Food;
import com.app.objectify.OfyService;
import com.app.utils.ObjUtil;
import com.app.utils.Preconditions;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class FoodDaoImpl extends OfyService implements FoodDao {
    @Override
    public Food add(Food food) {
        Preconditions.checkArgument(food == null, "Invalid food");
        Preconditions.checkArgument(ObjUtil.isBlank(food.getHotelId()), "Invalid hotelId for food");
        Preconditions.checkArgument(ObjUtil.isBlank(food.getName()), "Food name is invalid");
        food.setId(UUID.randomUUID().toString());
        return save(food) != null ? food : null;
    }

    @Override
    public List<Food> getFoods(Set<String> foodIds) {
        Preconditions.checkArgument(ObjUtil.isNullOrEmpty(foodIds), "Invalid foodIds");
        Preconditions.checkArgument(foodIds.size() > 50, "foodIds should not be more than 30");
        return get(Food.class, foodIds);

    }
}
