package com.movie.review.system.movie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String username;
    Rating rating;
    String comment;

    public Review() {}
    public Review(String username, Rating rating, String comment) {
        this.username = username;
        this.rating = rating;
        this.comment = comment;

    }
    public Long getId() {
        return this.id;
    }
    public String getUser() {
        return username;
    }
    public void setUser(String username) {
        this.username = username;
    }
    public Rating getRating() {
        return rating;
    }
    public void setRating(Rating rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return String.format("[Id %s, User %s, Rating %s, Comment %s]", this.id.toString(), this.username, this.rating.name(), this.comment);
    }
}
