package com.movie.review.system.movie;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    private final ReviewRepository repository;
    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    //GET
    @GetMapping("/reviews")
    public List<Review> all() {
        return repository.findAll();
    }

    //GET
    @GetMapping("/reviews/{id}")
    public Review one(@PathVariable long id) {
        return repository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }

    //POST
    @PostMapping("/reviews")
    public Review addReview(@RequestBody Review newReview) {
        return repository.save(newReview);
    }

    //PUT
    @PutMapping("/reviews/{id}")
    public Review editReview(@PathVariable Long id, @RequestBody Review review) {
        return repository.findById(id).map(item -> {
            item.setUser(review.getUser());
            item.setRating(review.getRating());
            item.setComment(review.getComment());
            return repository.save(item);
        }).orElseGet(() -> {
            return repository.save(review);
        });
    }

    //DELETE
    @DeleteMapping("/reviews/{id}")
    public void deleteReview(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
