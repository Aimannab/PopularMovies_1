package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.android.popularmovies.Movie.MovieResult;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Aiman Nabeel on 28/02/18
 */

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MovieAdapterOnClickHandler {

    //private static final String DISCOVER_STATE_EXTRA = "state";
    private static final String DISCOVER_POPULAR_STATE = "popular";
    private static final String DISCOVER_TOP_RATED_STATE = "top_rated";
    //private static final String DISCOVER_FAVORITE_STATE = "favorite";

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
        //TODO Remove this key before uploading the project
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addEncodedQueryParam("api_key", "11111");
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
    /*@Override
    public void onClick(String movieDetail) {
        Context context = this;

        String myMovieDetailKey = null;
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("myMovieDetailKey", movieDetail);
        startActivity(intent);
    }*/

    boolean mTwoPane;
    private static final String DETAILFRAGMENT_TAG = "DFTAG";


    @Override
    public void onMovieItemClicked(Movie movieObject) {

        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            View view = findViewById(R.id.mMovieDetailTextView);
            if(view.getVisibility()==View.INVISIBLE) {
                findViewById(R.id.mMovieDetailTextView).setVisibility(View.VISIBLE);
            }
            /*Bundle args = new Bundle();

            DetailsActivityFragment fragment = new DetailsActivityFragment();
            args.putSerializable("movieObject", movieObject);
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mMovieDetailTextView, fragment, DETAILFRAGMENT_TAG)
                    .commit();

        }*/ } else {
            Intent detailsIntent = new Intent(this, DetailActivity.class);
            detailsIntent.putExtra("movieObject", movieObject);
            startActivity(detailsIntent);
        }
    }

}
