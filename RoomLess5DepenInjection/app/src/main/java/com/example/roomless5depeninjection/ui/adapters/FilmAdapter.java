package com.example.roomless5depeninjection.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomless5depeninjection.databinding.FilmListBinding;
import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.ui.viewholders.FilmViewHolder;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmViewHolder> {
    private final List<Film> filmList = new ArrayList<>();
    private ListenerLike listenerLike;

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FilmListBinding filmView = FilmListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FilmViewHolder(filmView, this, listenerLike);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        holder.onBind(filmList.get(position));

    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public void setList(List<Film> body) {
        filmList.addAll(body);
        notifyDataSetChanged();
    }

    public void setListener(ListenerLike listenerLike) {
        this.listenerLike = listenerLike;
    }


    public interface ListenerLike {
        void onLikeClick(Film film);
    }
}
