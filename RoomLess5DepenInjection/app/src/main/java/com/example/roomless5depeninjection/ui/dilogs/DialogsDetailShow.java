package com.example.roomless5depeninjection.ui.dilogs;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.roomless5depeninjection.databinding.DiologDeteilBinding;
import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.network.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DialogsDetailShow extends DialogFragment {
    private static final String FILM_ID = "film_id";
    public static final String FRAG_TAG = "fragTag";
    private static final String TAG = "dialog";
    private DiologDeteilBinding diologDeteilBinding;


    private String filmId;

    public static DialogsDetailShow newInstance(String filmId) {
        DialogsDetailShow frag = new DialogsDetailShow();
        Bundle args = new Bundle();
        args.putString(FILM_ID, filmId);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filmId = getArguments().getString(FILM_ID);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        diologDeteilBinding = DiologDeteilBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(diologDeteilBinding.getRoot());
        actionInside();
        callFilmDetail();
        return builder.create();
    }

    private void callFilmDetail() {
        RetrofitBuilder
                .getInstance()
                .getFilm(filmId)
                .enqueue(new Callback<Film>() {
                    @Override
                    public void onResponse(Call<Film> call, Response<Film> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Log.e(TAG, "onResponse: " + response.body().getTitle());
                            settingData(response.body());
                        } else {
                            Log.e(TAG, "fail else: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Film> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                    }
                });
    }

    private void settingData(Film detail) {
        diologDeteilBinding.titleDetail.setText(String.format("%s", detail.getTitle()));
        diologDeteilBinding.descDetails.setText(String.format("%s", detail.getDescription()));
        diologDeteilBinding.releaseDetail.setText(String.format("Release date: %s", detail.getReleaseDate()));
        diologDeteilBinding.directorDetail.setText(String.format("Director: %s", detail.getDirector()));
        diologDeteilBinding.producerDetail.setText(String.format("Producer: %s", detail.getProducer()));
        if (Integer.parseInt(detail.getRtScore()) > 95)
            diologDeteilBinding.rtScoreDetail.setRating(5);
        else if (Integer.parseInt(detail.getRtScore()) >= 85)
            diologDeteilBinding.rtScoreDetail.setRating(4);
        else if (Integer.parseInt(detail.getRtScore()) >= 70)
            diologDeteilBinding.rtScoreDetail.setRating(3);
        else diologDeteilBinding.rtScoreDetail.setRating(2);
    }


    private void actionInside() {
        diologDeteilBinding.cancelBtn.setOnClickListener(v -> dismiss());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        diologDeteilBinding = null;
    }
}
