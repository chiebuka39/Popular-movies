package com.tech.harry.popularmovies.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tech.harry.popularmovies.R;
import com.tech.harry.popularmovies.Utils.Constant;

public class MovieDetailActivity extends AppCompatActivity {

    private String mTitle,mSynopsis,mRating,mReleasedDate,mPoster;

    private TextView movie_title,movie_synopsis, movie_rating, movie_date;
    private ImageView movie_poster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        movie_date = (TextView) findViewById(R.id.movie_release_date);
        movie_title = (TextView) findViewById(R.id.movie_title);
        movie_poster = (ImageView) findViewById(R.id.movie_poster);
        movie_synopsis = (TextView) findViewById(R.id.movie_plot_synopsis);
        movie_rating = (TextView) findViewById(R.id.movie_rating);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mTitle = extras.getString(Constant.title);
            mPoster = extras.getString(Constant.poster);
            mRating = extras.getString(Constant.rating);
            mReleasedDate = extras.getString(Constant.release_date);
            mSynopsis = extras.getString(Constant.synopsis);

            movie_date.setText("Released date :"+ mReleasedDate);
            movie_synopsis.setText(mSynopsis);
            movie_date.setText(mReleasedDate);
            movie_title.setText(mTitle);
            movie_rating.setText("Movie rating: "+ mRating);

            Picasso.with(this)
                    .load(mPoster)
                    .into(movie_poster);

        }


    }

}
