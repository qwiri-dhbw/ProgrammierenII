package io.d2a.java.exercise.enums.cards;

import io.d2a.java.exercise.enums.cards.PlayingCard.CardValue;
import io.d2a.java.exercise.enums.cards.PlayingCard.Suit;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardGame {

    private final Stack<PlayingCard> cards = new Stack<>();

    public CardGame() {
        // fill cards
        for (final Suit suit : Suit.values()) {
            for (final CardValue value : CardValue.values()) {
                cards.add(new PlayingCard(suit, value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public void sort() {
        this.cards.sort(PlayingCard::compareTo);
    }

    public PlayingCard get() {
        return this.cards.pop();
    }

    public List<PlayingCard> all() {
        return this.cards.subList(0, this.cards.size());
    }

    public static void main(String[] args) {
        final CardGame game = new CardGame();
        game.shuffle();

        final PlayingCard heartSeven = new PlayingCard(Suit.HEART, CardValue.SEVEN);
        for (int i = 0; i < 10; i++) {
            final PlayingCard card = game.get();
            System.out.println("pulled: " + card + ": " + card.compareTo(heartSeven));
        }
        game.sort();
        System.out.println(game.all());
    }

}
