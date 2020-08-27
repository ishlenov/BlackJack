package com.blackjack.game;

import java.util.ArrayList;

public class Deck {
   private static ArrayList<Card> cardsForGame ;

    protected static ArrayList<Card> getCardsForGame() {
        return cardsForGame;
    }



    public Deck() {
        cardsForGame = new ArrayList<>();
        for (int i = 6; i <11 ; i++) {
            cardsForGame.add(new Card(i,i+""));
        }
        cardsForGame.add(new Card(2,"J"));
        cardsForGame.add(new Card(3,"Q"));
        cardsForGame.add(new Card(4,"K"));
        cardsForGame.add(new Card(10,"A"));
    }
}
