package com.example.roomless5depeninjection.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.Toast;

import com.example.roomless5depeninjection.App;
import com.example.roomless5depeninjection.databinding.FragmentHomeBinding;
import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.ui.BaseFragment;
import com.example.roomless5depeninjection.ui.adapters.FilmAdapter;


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
        adapter.setList(App.filmRepo.getFilmsRemote());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }


    @Override
    public void onClickLike(Film film) {
        if (film.isSaved()) {
            Toast.makeText(requireContext(), film.getTitle() + " saved to your favorites ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), film.getTitle() + " removed from your favorites ", Toast.LENGTH_SHORT).show();
        }
        App.filmRepo.addFilm(film);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected FragmentHomeBinding fetchViewBinding() {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }
}