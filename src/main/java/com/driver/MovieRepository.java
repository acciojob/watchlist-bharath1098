package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
   HashMap<String,Movie> db1=new HashMap<>();
   HashMap<String,Director> db2=new HashMap<>();
   HashMap<String,List<String>> directormoviemap=new HashMap<>();

    public String addMovie(Movie movie){
       String name=movie.getName();
       db1.put(name,movie);
       return "movie added Successfully";
    }

    public String addDirector( Director director){
        String name=director.getName();
        db2.put(name,director);
        return "director added Successfully";
    }

    public String addMovieDirectorPair(String movieName,String directorName){
         if(db1.containsKey(movieName) && db2.containsKey(directorName)){
             db1.put(movieName,db1.get(movieName));
             db2.put(directorName,db2.get(directorName));
             List<String> movielist=new ArrayList<>();
             if(directormoviemap.containsKey(directorName)) movielist=directormoviemap.get(directorName);
             movielist.add(movieName);
             directormoviemap.put(directorName,movielist);
             return  "Movie pair added  Successfully";
         }
         return "Invalid";

    }

    public Movie  getMovieByName( String movieName){
        return db1.get(movieName);
    }

    public Director  getDirectorByName(String directorName){
        return db2.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        return directormoviemap.get(directorName);
    }

    public List<String> findAllMovies(){
       return new ArrayList<>(db1.keySet());
    }

    public String deleteDirectorByName( String directorName){
        if(directormoviemap.containsKey(directorName)){
            for(String movies:directormoviemap.get(directorName)){
                if(db1.containsKey(movies)){
                    db1.remove(movies);
                }
            }
            directormoviemap.remove(directorName);
        }

        if(db2.containsKey(directorName)){
            db2.remove(directorName);
            return "Director deleted Succesfully";
        }
        return "Director not found";
    }

    public String deleteAllDirectors(){
        List<String> movieset=new ArrayList<>();
        for(String directorName:directormoviemap.keySet()){
            for(String movies:directormoviemap.get(directorName)){
                if(db1.containsKey(movies)){
                    movieset.add(movies);
                }
            }
            directormoviemap.remove(directorName);
        }
        for(String movie:movieset){
            if(db1.containsKey(movie)){
                db1.remove(movie);
            }
        }
        db2.clear();
        return "Director removed Successfully";
    }

}
