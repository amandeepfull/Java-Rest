package com.app.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.Data;

@Data
@Entity
public class Hotel {

    @Id
    private String id;

    @Index
    private String name;

    private String location;

    private float rating;

    private long ratedUsers;

    public void addRating(float rating) {
        long ratedUsers = this.getRatedUsers();
        ratedUsers += 1;
        System.out.println("rated user : "+ratedUsers);
        this.setRatedUsers(ratedUsers);
        System.out.println("old rating : "+this.getRating());
        float rate = (this.getRating() + rating) / ratedUsers;
        System.out.println("rating : "+rate);
        this.setRating(rate);
    }
}
