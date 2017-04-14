package com.tech.harry.popularmovies.Data.Remote;

import com.tech.harry.popularmovies.Data.RestClient;

/**
 * Created by Gigi on 4/14/17.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static MovieRestService getMovieRestService() {
        return RestClient.getClient(BASE_URL).create(MovieRestService.class);
    }
}
