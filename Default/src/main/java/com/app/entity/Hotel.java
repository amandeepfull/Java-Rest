package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.Data;

@Data
@Entity
public class Hotel extends AbstractBaseEntity {

    @Index
    private String name;

    private String location;

    private double rating;

    private long ratedUsers;

    @JsonIgnore
    private double totalRating;

    public void addRating(float rating) {
        long ratedUsers = this.getRatedUsers();
        ratedUsers += 1;
        this.setRatedUsers(ratedUsers);
        this.totalRating = this.totalRating + rating;
        double rate = (this.totalRating) / ratedUsers;
        this.setRating(rate);
    }
}
