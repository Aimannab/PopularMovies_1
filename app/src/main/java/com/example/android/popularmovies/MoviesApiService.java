package com.example.android.popularmovies;


import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Aiman Nabeel on 12/03/18
 */

public interface MoviesApiService {
    @GET("/movie/popular")
    void getPopularMovies(Callback<Movie.MovieResult> cb);
}


