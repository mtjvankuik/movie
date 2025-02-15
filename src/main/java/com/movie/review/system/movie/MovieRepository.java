package com.movie.review.system.movie;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.reviews WHERE m.id = :id")
    Optional<Movie> findByIdWithReviews(@Param("id") Long id);
}
