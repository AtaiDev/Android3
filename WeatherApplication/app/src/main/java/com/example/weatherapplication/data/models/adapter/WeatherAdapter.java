package com.example.weatherapplication.data.models.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapplication.R;
import com.example.weatherapplication.data.models.WeekModel;
import com.example.weatherapplication.databinding.WeatherListBinding;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private final List<WeekModel> list;

    public WeatherAdapter(List<WeekModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeatherListBinding view = WeatherListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
            holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {

        private WeatherListBinding itemView;

        public WeatherViewHolder(@NonNull WeatherListBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

        public void onBind(WeekModel weekModel) {
            itemView.weekSunnyMonday.setImageResource(weekModel.getIconCondition());
            itemView.weekMonday.setText(weekModel.getTiming());
            itemView.weekSunset.setText(String.format("%s",weekModel.getMin_temp()+"\u00B0C"));
            itemView.weekSunrise.setText(String.format("%s",weekModel.getMax_temp()+"\u00B0C"));
        }
    }


}
