package com.movie.review.system.movie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class MovieApplication {
	private static final Logger logger = LoggerFactory.getLogger(MovieApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(MovieRepository movieRepository, ReviewRepository reviewRepository) {
		return (args) -> {
			movieRepository.save(new Movie("Inception", "Sci-Fi", 2010));
			movieRepository.save(new Movie("The Matrix", "Sci-Fi", 1999));
			movieRepository.save(new Movie("The Godfather", "Crime", 1972));
			movieRepository.save(new Movie("Interstellar", "Sci-Fi", 1972, "Christopher Nolan"));
			movieRepository.save(new Movie("The Dark Knight", "Action", 2008, "Christopher Nolan"));

			logger.info("---------------------------------");
			logger.info(movieRepository.findById(1L).toString());
			logger.info("---------------------------------");

			List<Movie> movies = movieRepository.findAll();
			for (Movie movie : movies) {
				logger.info("[Title = " + movie.getTitle() + " , Genre = " + movie.getGenre() + " , ReleaseYear = " + String.valueOf(movie.getReleaseYear()) + "]");;
			}

			reviewRepository.save(new Review("Martin van Kuik", Rating.GOOD, "Perfect movie!"));
			reviewRepository.save(new Review("Theodore Roosevelt", Rating.BAD, "Distasteful."));

			logger.info("---------------------------------");
			logger.info(reviewRepository.findById(1L).toString());
			logger.info("---------------------------------");

			List<Review> reviews = reviewRepository.findAll();
			for (Review review : reviews) {
				logger.info("[Name = " + review.getUser() + " , Rating = " + review.getRating().name() + " , Comment = " + review.getComment() + "]");;
			}
		};
	}

	
}
