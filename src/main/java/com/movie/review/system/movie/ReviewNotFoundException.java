package com.movie.review.system.movie;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(Long id) {
        super("Could not find review " + id);
    }
}
