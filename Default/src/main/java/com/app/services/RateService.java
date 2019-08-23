package com.app.services;

import com.app.model.Hotel;
import com.app.objectify.OfyService;

public class RateService extends OfyService {
    public boolean rateHotel(String hotelId, float rating) {

        Hotel hotel = get(Hotel.class, hotelId);

        long ratedUsers = hotel.getRatedUsers();
        ratedUsers += 1;
        hotel.setRatedUsers(ratedUsers);
        float rate = (hotel.getRating() + rating) / ratedUsers;
        hotel.setRating((rate));

        save(hotel);
        return true;
    }
}
