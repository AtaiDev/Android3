package com.example.roomless5depeninjection.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomless5depeninjection.databinding.ListFavesFilmBinding;
import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.ui.viewholders.FavesViewHolder;

import java.util.ArrayList;
import java.util.List;

public class FavesAdapter extends RecyclerView.Adapter<FavesViewHolder> {
    private final List<Film> favesList = new ArrayList<>();
    private Listener listener;

    @NonNull
    @Override
    public FavesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListFavesFilmBinding favesList = ListFavesFilmBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavesViewHolder(favesList, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FavesViewHolder holder, int position) {
        holder.onbind(favesList.get(position));


    }


    @Override
    public int getItemCount() {
        return favesList.size();
    }

    public void setList(List<Film> savedMovieList) {
        favesList.addAll(savedMovieList);
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onFavesClick(String favesId);
    }
}
