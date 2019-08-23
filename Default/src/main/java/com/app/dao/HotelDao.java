package com.app.dao;

import com.app.entity.Hotel;

public interface HotelDao {
    Hotel add(Hotel hotel);

    boolean rateHotel(String hotelId, float rating);
}
