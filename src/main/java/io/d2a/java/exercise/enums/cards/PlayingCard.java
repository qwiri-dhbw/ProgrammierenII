package io.d2a.java.exercise.enums.cards;

public record PlayingCard (Suit suit, CardValue value) implements Comparable<PlayingCard> {

    enum Suit {
        DIAMONDS,
        HEART,
        SPADE,
        CLUBS
    }

    @Override
    public int compareTo(final PlayingCard o) {
        final int suiteCompare = this.suit.compareTo(o.suit);
        if (suiteCompare != 0) {
            return suiteCompare;
        }
        return this.value.compareTo(o.value);
    }

    public enum CardValue {
        SEVEN,
        EIGHT,
        NINE,
        JACK,
        QUEEN,
        KING,
        TEN,
        ACE
    }

}
