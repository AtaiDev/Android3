package com.example.matchgame.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.matchgame.R;
import com.example.matchgame.data.Card;

public class MainActivity extends AppCompatActivity implements EmojiAdapter.ClickListener {

    private static final String TAG = "mainActivity";
    EmojiAdapter adapter;
    EmojiGame game;
    RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void initViews() {
        recyclerView = findViewById(R.id.recyclerMain);
        game = new EmojiGame();
        adapter = new EmojiAdapter(this, game);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void listener(Card<Integer> card) {
        game.choose(card);
        adapter.notifyDataSetChanged();
        if (game.getCards().size() == 0) recreate();
    }

}