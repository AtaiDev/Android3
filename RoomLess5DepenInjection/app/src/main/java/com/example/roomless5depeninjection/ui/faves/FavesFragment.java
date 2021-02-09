package com.example.roomless5depeninjection.ui.faves;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import com.example.roomless5depeninjection.App;
import com.example.roomless5depeninjection.databinding.FragmentFavesBinding;
import com.example.roomless5depeninjection.ui.BaseFragment;
import com.example.roomless5depeninjection.ui.adapters.FavesAdapter;
import com.example.roomless5depeninjection.ui.dilogs.ShowDetailsFilm;


public class FavesFragment extends BaseFragment<FragmentFavesBinding> implements FavesAdapter.Listener {
    private static final String FAVES = "faves";


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();
        Log.e(FAVES, "onViewCreated: " + App.filmRepo.getFilms().toString());
    }

    private void initRecycler() {
        FavesAdapter favesAdapter = new FavesAdapter();
        binding.recyclerFaves.setAdapter(favesAdapter);
        favesAdapter.setList(App.filmRepo.getFilms());
        favesAdapter.setListener(this);

    }

    @Override
    public void onFavesClick(String favesId) {
        ShowDetailsFilm showDetailsFilm = ShowDetailsFilm.newInstance(favesId);
        showDetailsFilm.show(getParentFragmentManager(), ShowDetailsFilm.FRAG_TAG);
    }

    @Override
    protected FragmentFavesBinding fetchViewBinding() {
        return FragmentFavesBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}