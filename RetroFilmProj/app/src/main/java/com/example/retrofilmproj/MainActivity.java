package com.example.retrofilmproj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofilmproj.data.model.Film;
import com.example.retrofilmproj.data.remote.RetrofitFactory;
import com.example.retrofilmproj.ui.DetailActivity;
import com.example.retrofilmproj.ui.FilmAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FilmAdapter.OnClickListener {

    private static final String LOGE = "retrofilmproj";
    public static final String KEY_MODEL = "modelKey";

    private RecyclerView mRecyclerView;
    private FilmAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        initView();
        loadFilm();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerMain);
        adapter = new FilmAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

    }

    private void loadFilm() {
        RetrofitFactory
                .getInstance()
                .getFilms()
                .enqueue(new Callback<List<Film>>() {
                    @Override
                    public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                        if (response.isSuccessful() && response != null)
                        Log.e(LOGE, "onResponse: "+ response.body().size()+ " this is the  size of list which we're getting");
                        adapter.setList(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<Film>> call, Throwable t) {
                        Log.e(LOGE, "onResponse: "+ t.getLocalizedMessage() + " one o one" );
                    }
                });
    }

    @Override
    public void onClick(Film film) {
        Toast.makeText(this, "film note "+ film.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(KEY_MODEL, film.getId());
        startActivity(intent);
    }
}