package com.blackjack.game;

import java.util.ArrayList;

public class Player {
    private int score = 0 ;

    private String name ;

    private ArrayList<Card> cards = new ArrayList<>();

    public void setScore() {
        score = 0;
        for (Card card : cards) {
            score = card.getValue() + score;
        }

    }

    public Player(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void updateCards(){
        cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
