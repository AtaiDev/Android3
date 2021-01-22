package com.example.matchgame.data;


import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.matchgame.ui.EmojiAdapter;
import com.example.matchgame.ui.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;


public class Game<CardContent> {

    private static final String TAG = "gameClass";
    private final List<Card<CardContent>> cards = new ArrayList<>();

    public Game(List<CardContent> contents) {
        for (int i = 0; i < contents.size(); i++) {
            cards.add(new Card<>(false, false, contents.get(i), i * 2));
            cards.add(new Card<>(false, false, contents.get(i), i * 2 + 1));
        }
        Collections.shuffle(cards, new Random(0));
    }

    public void choose(Card<CardContent> card) {
        card.setFaceUp(!card.isFaceUp());
        if (card.isFaceUp()) findPairs(card);
    }

    public void findPairs(Card<CardContent> card) {
        for (Card<CardContent> searchCards : cards) {
            if (searchCards.getId() != card.getId()
                    && searchCards.equals(card)
                    && card.isFaceUp()) {
                Log.e(TAG, "findPairs:  Matched");
                /*removing the both cards if there's match*/
                new CountDownTimer(1000, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Log.e(TAG, "onTick: " + searchCards.isFaceUp());
                    }

                    @Override
                    public void onFinish() {
                        Log.e(TAG, "onFinish: " + searchCards.isFaceUp());
                        removeMatchCards(searchCards, card);
                        Log.e(TAG, "onFinish: " + cards.size());
                    }
                }.start();
            }

        }
    }

    public void removeMatchCards(Card<CardContent> searchCards, Card<CardContent> card) {
        if (card.isFaceUp()) {
            cards.remove(searchCards);
            cards.remove(card);
        }
    }


    public List<Card<CardContent>> getCards() {
        return cards;
    }
}
