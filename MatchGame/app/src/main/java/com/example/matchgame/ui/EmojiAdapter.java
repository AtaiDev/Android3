package com.example.matchgame.ui;

import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.matchgame.R;
import com.example.matchgame.data.Card;

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder> {
    public EmojiGame game;
    private final ClickListener listener;

    public EmojiAdapter(ClickListener listener, EmojiGame game) {
        this.listener = listener;
        this.game = game;
    }


    @NonNull
    @Override
    public EmojiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list, parent, false);
        return new EmojiViewHolder(view, listener);

    }

    @Override
    public void onBindViewHolder(@NonNull EmojiViewHolder holder, int position) {
        holder.bind(game.getCards().get(position));

    }

    @Override
    public int getItemCount() {
        return game.getCards().size();
    }

    class EmojiViewHolder extends RecyclerView.ViewHolder{

        private ClickListener listener;
        private ImageView imageList;

        public EmojiViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);
            this.listener = listener;
            imageList = itemView.findViewById(R.id.txtCardList);

        }

        public void bind(Card<Integer> card) {
            itemView.setOnClickListener(v -> listener.listener(card));
            if (card.isFaceUp()) {
                imageList.setBackgroundColor(Color.WHITE);
                imageList.setImageResource(card.getContent());
                YoYo.with(Techniques.FlipInY)
                        .duration(2000)
                        .playOn(imageList);
            } else {
                imageList.setImageResource(R.drawable.card_face_down);
            }
        }
    }
    interface ClickListener {
        void listener(Card<Integer> card);
    }
}
