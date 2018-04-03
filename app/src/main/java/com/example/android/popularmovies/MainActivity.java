package com.example.android.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.android.popularmovies.Movie.MovieResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MovieAdapterOnClickHandler{

    private static final String DISCOVER_STATE_EXTRA = "state";
    private static final String DISCOVER_POPULAR_STATE = "popular";
    private static final String DISCOVER_TOP_RATED_STATE = "top_rated";
    private static final String DISCOVER_FAVORITE_STATE = "favorite";

    private String mDefaultState = DISCOVER_POPULAR_STATE;


    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Creating Grid Layout

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        //spanCount refers to number of columns in a grid
        mAdapter = new MoviesAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i<25; i++) {
            movies.add(new Movie());
        }

        mAdapter.setMovieList(movies);


        //Creating RestAdapter

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addEncodedQueryParam("api_key", "faaa06f746cc46c17d321731163eaae2");
            }
        })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        //Initializing MoviesApiService interface

        MoviesApiService service = restAdapter.create(MoviesApiService.class);

        service.getPopularMovies(new Callback<MovieResult>() {
            @Override
            public void success(MovieResult movieResult, Response response) {
                mAdapter.setMovieList(movieResult.getResults());

            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();

            }
        });

    }

    //Creating menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_most_popular) {
            mDefaultState = DISCOVER_POPULAR_STATE;
            mAdapter.setMovieList(null);
            //loadMovieData();
            return true;
        }

        if (id == R.id.action_top_rated) {
            mDefaultState = DISCOVER_TOP_RATED_STATE;
            mAdapter.setMovieList(null);
            //loadMoviesData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Creating intent - new activity - DetailActivity
    @Override
    public void onClick(String movieDetail) {
        Context context = this;

        String myMovieDetailKey = null;
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("myMovieDetailKey", movieDetail);
        startActivity(intent);
    }

}
