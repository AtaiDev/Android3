package com.example.matchgame.ui;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.matchgame.R;
import com.example.matchgame.data.Card;
import com.example.matchgame.data.Game;

import java.util.List;

public class EmojiGame {

    private final Game<Integer> game;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public EmojiGame() {
        List<Integer> content = List.of(R.drawable.logimage,R.drawable.alien,R.drawable.twitter);
        game = new Game<>(content);
    }

    public void choose(Card<Integer> card){
        game.choose(card);

    }

    public List<Card<Integer>> getCards() {
        return game.getCards();
    }

}
