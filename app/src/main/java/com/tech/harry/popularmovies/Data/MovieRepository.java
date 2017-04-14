package com.tech.harry.popularmovies.Data;

import com.tech.harry.popularmovies.Data.Remote.MovieRestService;
import com.tech.harry.popularmovies.Model.PopularMovies;

/**
 * Created by Gigi on 4/14/17.
 */

public class MovieRepository {

    private MovieRestService mMovieRestService;

    public MovieRepository(MovieRestService movieRestService){
        this.mMovieRestService = movieRestService;
    }

    public PopularMovies getPopuplar(String filter, String key){
        //mMovieRestService.getMovies(filter, key).
        return null;
    }
}
