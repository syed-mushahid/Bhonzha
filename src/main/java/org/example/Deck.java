package org.example;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;
    private int reshuffles;

    public Deck() {
        cards = new ArrayList<>();
        reshuffles = 0;
        initialize();
        shuffle();
    }

    public void initialize() {
        // Add cards to the deck with actual bean names
        for (int i = 0; i < 20; i++) {
            cards.add(new Card("Black Bean", 1));
            cards.add(new Card("Red Bean", 2));
            cards.add(new Card("Green Bean", 3));
            cards.add(new Card("Soybean", 4));
            cards.add(new Card("Coffee Bean", 5));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            reshuffle();
        }
        return cards.isEmpty() ? null : cards.remove(0);
    }

    public void reshuffle() {
        reshuffles++;
        // For simplicity, we'll just reinitialize and shuffle the deck again
        initialize();
        shuffle();
    }

    public int getReshuffles() {
        return reshuffles;
    }
}
