package com.example.retrofilmproj.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofilmproj.R;
import com.example.retrofilmproj.data.model.Film;

import java.util.ArrayList;
import java.util.List;


public class FilmAdapter  extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder>{

    private static final String TAG = "adapter";
    private List<Film> listFilm = new ArrayList<>();
    private OnClickListener listener;



    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_list,parent,false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        holder.bind(listFilm.get(position));
    }

    @Override
    public int getItemCount() {
        return listFilm.size();
    }

    public void setClickListener(OnClickListener listener){
        this.listener = listener;
    }
    public void setList(List<Film> body) {
        listFilm.addAll(body);
        notifyDataSetChanged();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitleFilm,txtDescFilm,btnMore;


        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleFilm = itemView.findViewById(R.id.txtTitleFilm);
            txtDescFilm = itemView.findViewById(R.id.txt_desc_film);
            btnMore = itemView.findViewById(R.id.txt_more);

        }


        public void bind(Film film) {
            txtTitleFilm.setText(film.getTitle());
            txtDescFilm.setText(film.getDescription());
            clickListeners();
        }

        private void clickListeners() {
             btnMore.setOnClickListener(v -> {
                 if (btnMore.getText().toString().equalsIgnoreCase("Show more...")){
                     txtDescFilm.setMaxLines(Integer.MAX_VALUE);
                     btnMore.setText(R.string.Close);
                 }else {
                     txtDescFilm.setMaxLines(3);
                     btnMore.setText(R.string.showMore);
                     notifyDataSetChanged();
                 }
             });

             itemView.setOnClickListener(v -> listener.onClick(listFilm.get(getAdapterPosition())));

        }

    }

    public interface OnClickListener{
        void onClick(Film film);
    }
}
