package com.tech.harry.popularmovies.Data.Remote;

import com.tech.harry.popularmovies.Model.PopularMovies;
import io.reactivex.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Gigi on 4/14/17.
 */

public interface MovieRestService {

    @GET("movie/{filter}?language=en-US&page=1")
    Call<PopularMovies> getMovies(@Path("filter") String filter , @Query("api_key") String api_key);
}
