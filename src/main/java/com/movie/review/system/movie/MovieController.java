package com.movie.review.system.movie;

import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.hateoas.EntityModel;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
// import java.util.Optional;
// import org.springframework.data.jpa.repository.JpaRepository;

@RestController
public class MovieController {
    private final MovieRepository repository;
    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    //GET
    //findall    
    @GetMapping("/movies")
    public List<Movie> all() {
        return repository.findAll();
    }

    //GET
    //findbyid
    @GetMapping("/movies/{id}")
    public Movie one(@PathVariable long id) {
        return repository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    //POST
    //addmovie

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie newMovie) {
        return repository.save(newMovie);
    }

    //PUT
    //modifyone
    @PutMapping("/movies/{id}")
    public Movie editMovie(@PathVariable Long id, @RequestBody Movie movie) {

        return repository.findById(id).map(item -> {
            item.setDirector(movie.getDirector());
            item.setGenre(movie.getGenre());
            item.setReleaseYear(movie.getReleaseYear());
            item.setTitle(movie.getTitle());
            return repository.save(item);
        }).orElseGet(() -> {
            return repository.save(movie);
        });
    }

    //DELETE
    //deleteone
    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
