package com.example.android.popularmovies;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private String mMovieDetailString;
    private TextView mMovieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mMovieDetail = (TextView) findViewById(R.id.mMovieDetailTextView);

        Intent intent = getIntent();

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
            } else {
                newString = extras.getString("myMovieDetailKey");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("myMovieDetailKey");
        }

        /*if (intent != null) {
            if (intent.hasExtra()) {
                mMovieDetailString = intent.getStringExtra(Intent.EXTRA_TEXT);
                mMovieDetail.setText(mMovieDetailString);
            }
        }*/

    }
}
