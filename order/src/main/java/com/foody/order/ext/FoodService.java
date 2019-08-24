package com.foody.order.ext;

import com.foody.order.constants.AppConstants;
import com.foody.order.http.HttpMethod;
import com.foody.order.http.HttpRequest;
import com.foody.order.http.HttpResponse;
import com.foody.order.http.UrlFetcher;
import com.foody.order.model.Food;
import com.foody.order.utils.ObjUtil;

import java.util.List;
import java.util.Set;

public class FoodService {

    public List<Food> getFoods(Set<String> foodIds){


        HttpResponse response = null;
        try {
            HttpRequest request = new HttpRequest(AppConstants.DefaultModuleUrl + "/v1/food", HttpMethod.POST);
            request.addHeader("Content-Type","application/json");
            System.out.println("payload : "+ObjUtil.getJson(foodIds));
            request.setPayload(ObjUtil.getJson(foodIds));
            response = UrlFetcher.makeRequest(request);

            if(!response.wasSuccessful())
                return null;

        }catch (Exception e){
            return null;
        }
         return  (List<Food>) ObjUtil.convertToMap(ObjUtil.convertToMap(response.getResponseContent()).get("data")).get("foods");
    }}
