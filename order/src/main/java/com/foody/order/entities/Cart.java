package com.foody.order.entities;

import com.foody.order.utils.ObjUtil;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Cart extends AbstractBaseEntity{

    private Set<String> foodIds;

    public void addFood(String foodId) {

        if(ObjUtil.isBlank(foodId))
            return;

        if(ObjUtil.isNullOrEmpty(this.foodIds))
            this.foodIds = new HashSet<>();

        this.foodIds.add(foodId);
    }
}
