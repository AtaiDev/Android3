package com.example.unittesting.ui;

import android.content.Context;

import com.example.unittesting.R;

import java.net.URI;
import java.net.URL;


public class UriHelper {

    private final Context context;

    public UriHelper(Context context) {
        this.context = context;
    }


    public String validate(String uri){
        if (isUrl(uri)){
            return context.getString(R.string.url);
        }else if (isPath(uri)){
            return context.getString(R.string.path);
        }else {
            return context.getString(R.string.unknown);
        }
    }

    public boolean isUrl(String uri){
        return "https".equals(URI.create(uri).getScheme());
    }

    public boolean isPath(String uri){
        return "file".equals(URI.create(uri).getScheme());
    }


}
