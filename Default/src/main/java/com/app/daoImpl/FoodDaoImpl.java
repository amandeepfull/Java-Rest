package com.app.daoImpl;

import com.app.dao.FoodDao;
import com.app.model.Food;
import com.app.objectify.OfyService;
import com.app.utils.ObjUtil;
import com.app.utils.Preconditions;
import com.app.utils.RandomUtil;

import java.util.UUID;

public class FoodDaoImpl extends OfyService implements FoodDao {
    @Override
    public Food add(Food food) {
        Preconditions.checkArgument(food == null,"Invalid food");
        Preconditions.checkArgument(ObjUtil.isBlank(food.getHotelId()), "Invalid hotelId for food");
        Preconditions.checkArgument(ObjUtil.isBlank(food.getName()), "Food name is invalid");
        food.setId(UUID.randomUUID().toString());
        return save(food) != null ? food : null;
    }
}
