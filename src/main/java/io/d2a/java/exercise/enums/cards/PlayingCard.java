package io.d2a.java.exercise.enums.cards;

public record PlayingCard (Suit suit, CardValue value) implements Comparable<PlayingCard> {

    enum Suit {
        DIAMONDS,
        HEART,
        SPADE,
        CLUBS
    }

    enum CardValue {
        SEVEN,
        EIGHT,
        NINE,
        JACK,
        QUEEN,
        KING,
        TEN,
        ACE
    }

    private <T> int index(final T[] arr, final T val) {
        for (int i = 0; i < arr.length; i++) {
            if (val.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int compareTo(final PlayingCard o) {
        // check if other card's suit is "better"
        final int currentSuitIndex = this.index(Suit.values(), this.suit);
        final int targetSuitIndex = o.index(Suit.values(), o.suit);
        if (currentSuitIndex > targetSuitIndex) {
            return 1;
        } else if (currentSuitIndex < targetSuitIndex) {
            return -1;
        }
        // check value
        final int currentValueIndex = this.index(CardValue.values(), this.value);
        final int targetValueIndex = this.index(CardValue.values(), o.value);
        if (currentValueIndex > targetValueIndex) {
            return 1;
        } else if (currentValueIndex < targetValueIndex) {
            return -1;
        }
        return 0;
    }

}
