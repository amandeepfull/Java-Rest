package com.app.daoImpl;

import com.app.dao.HotelDao;
import com.app.entity.Hotel;
import com.app.objectify.OfyService;
import com.app.utils.ObjUtil;
import com.app.utils.Preconditions;

import java.util.UUID;

public class HotelDaoImpl extends OfyService implements HotelDao{

    @Override
    public Hotel add(Hotel hotel) {
        Preconditions.checkArgument(hotel == null, "Invalid hotel body");
        Preconditions.checkArgument(ObjUtil.isBlank(hotel.getName()), "Invalid hotel name");
        Preconditions.checkArgument(ObjUtil.isBlank(hotel.getLocation()), "Invalid hotel location");
        hotel.setId(UUID.randomUUID().toString());
        return save(hotel) != null ? hotel : null;
    }

    @Override
    public Hotel rateHotel(String hotelId, float rating) {

        Hotel hotel = get(Hotel.class, hotelId);
        hotel.addRating(rating);

       return save(hotel) != null ? hotel : null;

    }
}
