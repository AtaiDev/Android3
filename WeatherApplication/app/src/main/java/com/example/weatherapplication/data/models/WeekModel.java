package com.example.weatherapplication.data.models;

public class WeekModel {
    private int iconCondition;
    private String timing;
    private int max_temp;
    private int min_temp;

    public WeekModel(int iconCondition, String timing, int max_temp, int min_temp) {
        this.iconCondition = iconCondition;
        this.timing = timing;
        this.max_temp = max_temp;
        this.min_temp = min_temp;
    }

    public int getIconCondition() {
        return iconCondition;
    }

    public void setIconCondition(int iconCondition) {
        this.iconCondition = iconCondition;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public int getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(int max_temp) {
        this.max_temp = max_temp;
    }

    public int getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(int min_temp) {
        this.min_temp = min_temp;
    }
}
