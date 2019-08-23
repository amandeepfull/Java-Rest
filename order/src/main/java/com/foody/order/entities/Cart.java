package com.foody.order.entities;

import com.foody.order.utils.ObjUtil;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cart {

    @Id
    private String id;
    private List<String> foodIds;

    public void addFood(String foodId) {

        if(ObjUtil.isBlank(foodId))
            return;

        if(ObjUtil.isNullOrEmpty(this.foodIds))
            this.foodIds = new ArrayList<>();

        this.foodIds.add(foodId);
    }
}
