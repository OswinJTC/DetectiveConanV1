package com.tai.DetectiveConanV1.Movie;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://detective-conan-client.onrender.com")
@RequestMapping("/api/v1/movies")

public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/{MId}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String MId){

        return new ResponseEntity<Optional<Movie>>(movieService.singleMovie(MId), HttpStatus.OK);

    }
}
