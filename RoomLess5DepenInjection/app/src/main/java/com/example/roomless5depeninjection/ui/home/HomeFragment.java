package com.example.roomless5depeninjection.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.roomless5depeninjection.App;
import com.example.roomless5depeninjection.databinding.FragmentHomeBinding;
import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.network.RetrofitBuilder;
import com.example.roomless5depeninjection.ui.BaseFragment;
import com.example.roomless5depeninjection.ui.adapters.FilmAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements FilmAdapter.ListenerLike {

    private static final String HOME = "homeFrag";
    private FilmAdapter adapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecView();
        callFilms();

    }

    private void initRecView() {
        adapter = new FilmAdapter();
        adapter.setListener(this);
        binding.recyclerMain.setAdapter(adapter);
    }

    private void callFilms() {
        RetrofitBuilder
                .getInstance()
                .getFilms()
                .enqueue(new Callback<List<Film>>() {
                    @Override
                    public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Log.e(HOME, "onResponse: success");
                            adapter.setList(response.body());

                        } else {
                            Log.e(HOME, "onResponse: failure " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Film>> call, Throwable t) {
                        Log.e(HOME, "onFailure: failed to load");
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }


    @Override
    public void onLikeClick(Film film) {
        App.filmRepo.addFilm(film);
        Toast.makeText(requireContext(), film.getTitle() + " saved to your favorites ", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected FragmentHomeBinding fetchViewBinding() {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }
}