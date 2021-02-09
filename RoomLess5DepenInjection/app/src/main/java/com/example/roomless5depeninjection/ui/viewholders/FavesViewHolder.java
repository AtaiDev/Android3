package com.example.roomless5depeninjection.ui.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomless5depeninjection.databinding.ListFavesFilmBinding;
import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.ui.adapters.FavesAdapter;

public class FavesViewHolder extends RecyclerView.ViewHolder {
    private final ListFavesFilmBinding favesBinding;
    private final FavesAdapter.Listener listener;

    public FavesViewHolder(@NonNull ListFavesFilmBinding itemView, FavesAdapter.Listener listener) {
        super(itemView.getRoot());
        favesBinding = itemView;
        this.listener = listener;
    }

    public void onbind(Film film) {
        favesBinding.titleFavesFilm.setText(film.getTitle());
        favesBinding.descFavesFilm.setText(film.getDescription());
        listeners(film);
    }

    private void listeners(Film film) {
        itemView.setOnClickListener(v -> {
            listener.onFavesClick(film.getId());
        });
    }
}
