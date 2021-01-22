package com.example.matchgame.data;

import java.util.Objects;

public class Card<CardContent> {
    private boolean isFaceUp;
    private boolean isMatch;
    private CardContent content;
    private int id;

    public Card(boolean isFaceUp, boolean isMatch, CardContent content, int id) {
        this.isFaceUp = isFaceUp;
        this.isMatch = isMatch;
        this.content = content;
        this.id = id;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp(boolean faceUp) {
        isFaceUp = faceUp;
    }

    public boolean isMatch() {
        return isMatch;
    }

    public void setMatch(boolean match) {
        isMatch = match;
    }

    public CardContent getContent() {
        return content;
    }

    public void setContent(CardContent content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card<?> card = (Card<?>) o;
        return isFaceUp == card.isFaceUp &&
                isMatch == card.isMatch &&
                Objects.equals(content, card.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isFaceUp, isMatch, content, id);
    }

    @Override
    public String toString() {
        return "Card{" +
                "isFaceUp=" + isFaceUp +
                ", isMatch=" + isMatch +
                ", content=" + content +
                ", id=" + id +
                '}';
    }
}
