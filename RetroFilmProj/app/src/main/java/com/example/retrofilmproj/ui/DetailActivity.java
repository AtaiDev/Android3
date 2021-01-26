package com.example.retrofilmproj.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.retrofilmproj.MainActivity;
import com.example.retrofilmproj.R;
import com.example.retrofilmproj.data.model.Film;
import com.example.retrofilmproj.data.remote.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private String filmId;
    private View view;
    private TextView txtTitle, txtDescription, txtDirector, txtProducer, txtReleaseDate, txtRating, btnViewed;
    private RatingBar rtScore;
    private DialogFragment dialogFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        if (getIntent() != null) getData();
        callRetro();
        clickListeners();
    }



    private void initView() {
        view = findViewById(R.id.detail_holder);
        txtTitle = findViewById(R.id.title_detail);
        txtDescription = findViewById(R.id.desc_details);
        txtDirector = findViewById(R.id.director_detail);
        txtProducer = findViewById(R.id.producer_detail);
        txtReleaseDate = findViewById(R.id.release_detail);
        txtRating = findViewById(R.id.txt_rate_detail);
        rtScore = findViewById(R.id.rt_score_detail);
        btnViewed = findViewById(R.id.custom_viewed);


    }

    private void getData() {
        filmId = getIntent().getStringExtra(MainActivity.KEY_MODEL);
    }

    private void callRetro() {
        RetrofitFactory.getInstance()
                .getFilmById(filmId)
                .enqueue(new Callback<Film>() {
                    @Override
                    public void onResponse(Call<Film> call, Response<Film> response) {
                        view.setVisibility(View.VISIBLE);
                        setEachData(response.body());

                    }

                    @Override
                    public void onFailure(Call<Film> call, Throwable t) {
                        Log.e("ololo", "onFailure: " + t.getLocalizedMessage());
                    }
                });
    }

    private void setEachData(Film body) {
        txtTitle.setText(body.getTitle());
        txtDescription.setText(body.getDescription());
        txtDirector.setText("Director: " + body.getDirector());
        txtProducer.setText("Producer: " + body.getProducer());
        txtReleaseDate.setText("Release date: " + body.getReleaseDate());
        txtRating.setText(body.getRtScore());
        if (Integer.parseInt(body.getRtScore()) > 95) rtScore.setRating(5);
        else if (Integer.parseInt(body.getRtScore()) >= 85) rtScore.setRating(4);
        else if (Integer.parseInt(body.getRtScore()) >= 70) rtScore.setRating(3);
        else rtScore.setRating(2);
    }

    private void clickListeners() {

    }


}