package com.example.roomless5depeninjection.ui.viewholders;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomless5depeninjection.R;
import com.example.roomless5depeninjection.databinding.FilmListBinding;
import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.ui.adapters.FilmAdapter;

public class FilmViewHolder extends RecyclerView.ViewHolder {

    private final FilmListBinding fBinding;
    private final FilmAdapter adapter;
    private final FilmAdapter.ListenerLike listenerLike;


    public FilmViewHolder(@NonNull FilmListBinding itemView, FilmAdapter adapter, FilmAdapter.ListenerLike listenerLike) {
        super(itemView.getRoot());
        fBinding = itemView;
        this.adapter = adapter;
        this.listenerLike = listenerLike;
    }

    public void onBind(Film film) {
        fBinding.txtTitleFilm.setText(film.getTitle());
        fBinding.txtDescFilm.setText(film.getDescription());
        clickListeners(film);
    }

    private void clickListeners(Film film) {
        fBinding.btnMore.setOnClickListener(v -> {
            if (fBinding.btnMore.getText().toString().equalsIgnoreCase("Show more...")) {
                fBinding.txtDescFilm.setMaxLines(Integer.MAX_VALUE);
                fBinding.btnMore.setText(R.string.close_btn);
            } else {
                fBinding.txtDescFilm.setMaxLines(3);
                fBinding.btnMore.setText(R.string.show_btn);
                adapter.notifyDataSetChanged();

            }
        });

        fBinding
                .likeBtn
                .setOnClickListener(v -> {
                    if (itemView.getResources().getDrawable(R.drawable.heart).getConstantState()
                            == fBinding.likeBtn.getDrawable().getConstantState()) {
                        fBinding.likeBtn.setImageResource(R.drawable.saved_heart);
                    } else {
                        fBinding.likeBtn.setImageResource(R.drawable.heart);
                    }
                    listenerLike.onLikeClick(film);
                });

    }
}
