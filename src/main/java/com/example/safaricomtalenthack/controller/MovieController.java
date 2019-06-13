package com.example.safaricomtalenthack.controller;



import com.example.safaricomtalenthack.exception.MovieNotFoundException;
import com.example.safaricomtalenthack.model.MovieModel;
import com.example.safaricomtalenthack.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    //get all movies

    @GetMapping("/movies")
    public List<MovieModel>getAllMovies(){
        return movieRepository.findAll();
    }
//watched
    @GetMapping("/watched-movies")
    public List<MovieModel>getWatchedMovies(){
        return  movieRepository.getWatchedMovies(1);
    }
//Unwatched
    @GetMapping("/unwatched-movies")
    public List<MovieModel>getUnWatchedMovies(){
        return  movieRepository.getWatchedMovies(0);
    }


    //add movie
    @PostMapping("/add-movie")
    public MovieModel addMovie(@Valid @RequestBody MovieModel movieModel){
        return  movieRepository.save(movieModel);
    }

    //get a single movie
    @GetMapping("/movie/{id}")
    public MovieModel getMovieById(@PathVariable("id")long id){
        return movieRepository.findById(id).orElseThrow(()->new MovieNotFoundException("Movie","id",id));
    }
    //edit a movie
    @PutMapping("/edit-movie/{id}")
    public  MovieModel editMovie(@PathVariable("id")long id,@Valid @RequestBody MovieModel movieModel){
        MovieModel movie=movieRepository.findById(id).orElseThrow(()->new MovieNotFoundException("Movie","id",id));
        movie.setTitle(movieModel.getTitle());
        movie.setDescription(movieModel.getDescription());
        movie.setRecommandation(movieModel.getRecommandation());
        movie.setRating(movieModel.getRating());
        movie.setWatched_flag(movieModel.getWatched_flag());

        return movieRepository.save(movie);

    }


    //watched movies
//    @GetMapping("/watched-movies")
//    @Query(value = "select * from movies where watched_flag=1",nativeQuery = true)
//

    //delete movie

    @DeleteMapping("delete-movie/{id}")
    public ResponseEntity<?>deleteMovie(@PathVariable("id")long id){
        MovieModel movieModel=movieRepository.findById(id).orElseThrow(()->new MovieNotFoundException("Movie","id",id));
        movieRepository.delete(movieModel);
        return ResponseEntity.ok().build();

    }

}
