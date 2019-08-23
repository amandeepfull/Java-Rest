package com.app.services;

import com.app.model.Food;
import com.app.model.Hotel;
import com.app.objectify.OfyService;
import com.app.utils.ObjUtil;
import com.app.utils.Preconditions;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;

public class SearchService extends OfyService {

    public List<Hotel> searchHotelByFood(String foodName) {

        Preconditions.checkArgument(ObjUtil.isBlank(foodName), "invalid search");
        List<Food> foods = OfyService.ofy().load().type(Food.class).filter("name", foodName).list();

        List<String> hotelIds = new ArrayList<>();
        for (Food food : foods)
            hotelIds.add(food.getHotelId());

        return get(Hotel.class, hotelIds);
    }
}