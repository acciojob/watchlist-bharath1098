package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String movies=movieService.addMovie(movie);
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String director1=movieService.addDirector(director);
        return new ResponseEntity<>(director1,HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName,@RequestParam("directorName") String directorName){
        String ans=movieService.addMovieDirectorPair(movieName,directorName);
        if(ans.equals("Invalid")){
            return new ResponseEntity<>(ans,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ans,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity  getMovieByName(@PathVariable("name") String movieName){
        Movie ans=movieService.getMovieByName(movieName);
        if(ans==null){
            return new ResponseEntity<>(ans,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ans,HttpStatus.FOUND);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity  getDirectorByName(@PathVariable("name") String directorName){
        Director ans=movieService.getDirectorByName(directorName);
        if(ans==null){
            return new ResponseEntity<>(ans,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ans,HttpStatus.FOUND);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName){
        List<String> ans=movieService.getMoviesByDirectorName(directorName);
        if(ans==null){
            return new ResponseEntity<>(ans,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ans,HttpStatus.FOUND);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String> ans=movieService.findAllMovies();
        if(ans==null){
            return new ResponseEntity<>(ans,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ans,HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("directorName") String directorName){
        String ans=movieService.deleteDirectorByName(directorName);
        return new ResponseEntity(ans, HttpStatus.GONE);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String ans=movieService.deleteAllDirectors();
        return new ResponseEntity(ans, HttpStatus.GONE);
    }

}
