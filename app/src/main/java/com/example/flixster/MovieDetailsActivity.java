package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixster.Models.Movie;
import com.example.flixster.adapter.MovieAdapter;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {
    Movie movie;


    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    ImageView ivPoster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview =  findViewById(R.id.tvOverview);
        rbVoteAverage =  findViewById(R.id.rbVoteAverage);
        ivPoster =  findViewById(R.id.ivPoster);

        movie = Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage / 2.0f);

        String imageUrl2;
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            imageUrl2 = movie.getBackdropPath();
        }
        else{
            imageUrl2 = movie.getPosterPath();
        }

        Glide.with(this).load(imageUrl2).placeholder(R.drawable.backdrop_placeholder).into(ivPoster);
    }
}