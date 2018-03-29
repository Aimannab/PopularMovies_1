package com.example.android.popularmovies;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MainActivity.MovieViewHolder>{

    //Interface implemented on MainActivity class
    public interface MovieAdapterOnClickHandler {
        void onClick(String movieDetail);
    }

    private Context mContext;
    private LayoutInflater mInflator;
    private List<Movie> mMovieList;

    public MoviesAdapter(Context context) {
        this.mContext = context;
        this.mInflator = LayoutInflater.from(context);
        this.mMovieList = new ArrayList<>();
    }

    @Override
    public MainActivity.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflator.inflate(R.layout.row_movie, parent, false);
        MainActivity.MovieViewHolder viewHolder = new MainActivity.MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainActivity.MovieViewHolder holder, int position) {

        Movie movie = mMovieList.get(position);

        //Using Picasso here to load images from the internet

        Picasso.with(mContext).load(movie.getPoster()).placeholder(R.color.colorAccent).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return (mMovieList == null) ? 0: mMovieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        this.mMovieList.clear();
        this.mMovieList.addAll(movieList);
        //This is done so we know that data has changed. To avoid crashing of app

        notifyDataSetChanged();
    }
}
