package com.example.roomless5depeninjection.ui.faves;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import com.example.roomless5depeninjection.App;
import com.example.roomless5depeninjection.databinding.FragmentFavesBinding;
import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.ui.BaseFragment;
import com.example.roomless5depeninjection.ui.adapters.FavesAdapter;
import com.example.roomless5depeninjection.ui.dilogs.DialogsDetailShow;
import java.util.ArrayList;
import java.util.List;


public class FavesFragment extends BaseFragment<FragmentFavesBinding> implements FavesAdapter.Listener {
    private static final String FAVES = "faves";
    private List<Film> filmList = new ArrayList<>();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();
        Log.e(FAVES, "onViewCreated: " + App.filmRepo.getFilms().toString());
    }

    private void initRecycler() {
        for (int i = 0; i < App.filmRepo.getFilms().size(); i++) {
            if (App.filmRepo.getFilms().get(i).isSaved()){
                filmList.add(App.filmRepo.getFilms().get(i));
            }
        }

        FavesAdapter favesAdapter = new FavesAdapter();
        binding.recyclerFaves.setAdapter(favesAdapter);
        favesAdapter.setList(filmList);
        favesAdapter.setListener(this);
    }

    @Override
    public void onFavesClick(String favesId) {
        DialogsDetailShow dialogsDetailShow = DialogsDetailShow.newInstance(favesId);
        dialogsDetailShow.show(getParentFragmentManager(), DialogsDetailShow.FRAG_TAG);
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