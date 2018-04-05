package com.example.android.popularmovies;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie implements Serializable {

    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    private String title;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("overview")
    private String description;

    @SerializedName("backdrop_path")
    private String backdrop;

    public Movie() {}


    //Methods for MainActivity
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPoster() { return TMDB_IMAGE_PATH + poster;}

    public void setPoster(String poster) {
        this.poster = poster;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getBackdrop() {
        return TMDB_IMAGE_PATH + backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    /*@SerializedName("results")
    private ArrayList<Movie> movieObjectsAl;

    @SerializedName("page")
    private int page;

    public ArrayList<Movie> getMovieObjectsAl() {
        return movieObjectsAl;
    }

    public int getPage() {
        return page;
    }

        @SerializedName("poster_path")
        private String poster;

        @SerializedName("adult")
        private boolean isAdult;

        @SerializedName("overview")
        private String overView;

        @SerializedName("release_date")
        private String releaseDate;

        @SerializedName("genre_ids")
        private int [] genre_ids;

        @SerializedName("id")
        private long id;

        @SerializedName("original_title")
        private String originalTitle;

        @SerializedName("original_language")
        private String originalLanguage;

        @SerializedName("title")
        private String title;

        @SerializedName("backdrop_path")
        private String backDropPath;

        @SerializedName("popularity")
        private float popularity;

        @SerializedName("vote_count")
        private long voteCount;

        @SerializedName("video")
        private boolean hasVideo;

        @SerializedName("vote_average")
        private float voteAverage;


        public String getPoster() {
            return poster;
        }

        public boolean isAdult() {
            return isAdult;
        }

        public String getOverView() {
            return overView;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public int[] getGenre_ids() {
            return genre_ids;
        }

        public long getId() {
            return id;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public String getTitle() {
            return title;
        }

        public String getBackDropPath() {
            return backDropPath;
        }

        public float getPopularity() {
            return popularity;
        }

        public long getVoteCount() {
            return voteCount;
        }

        public boolean isHasVideo() {
            return hasVideo;
        }

        public float getVoteAverage() {
            return voteAverage;
        }*/


    //Class for Movie List
    public static class MovieResult implements Serializable {

        @SerializedName("results")
        private ArrayList<Movie> results;

        public ArrayList<Movie> getResults() {
            return results;
        }
    }



}
