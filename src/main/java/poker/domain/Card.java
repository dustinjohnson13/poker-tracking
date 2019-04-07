package poker.domain;

import static poker.domain.FaceValue.ACE;
import static poker.domain.FaceValue.EIGHT;
import static poker.domain.FaceValue.FIVE;
import static poker.domain.FaceValue.FOUR;
import static poker.domain.FaceValue.JACK;
import static poker.domain.FaceValue.KING;
import static poker.domain.FaceValue.NINE;
import static poker.domain.FaceValue.QUEEN;
import static poker.domain.FaceValue.SEVEN;
import static poker.domain.FaceValue.SIX;
import static poker.domain.FaceValue.TEN;
import static poker.domain.FaceValue.THREE;
import static poker.domain.FaceValue.TWO;
import static poker.domain.Suit.CLUBS;
import static poker.domain.Suit.DIAMONDS;
import static poker.domain.Suit.HEARTS;
import static poker.domain.Suit.SPADES;

public enum Card {

    ACE_OF_SPADES(ACE, SPADES),
    TWO_OF_SPADES(TWO, SPADES),
    THREE_OF_SPADES(THREE, SPADES),
    FOUR_OF_SPADES(FOUR, SPADES),
    FIVE_OF_SPADES(FIVE, SPADES),
    SIX_OF_SPADES(SIX, SPADES),
    SEVEN_OF_SPADES(SEVEN, SPADES),
    EIGHT_OF_SPADES(EIGHT, SPADES),
    NINE_OF_SPADES(NINE, SPADES),
    TEN_OF_SPADES(TEN, SPADES),
    JACK_OF_SPADES(JACK, SPADES),
    QUEEN_OF_SPADES(QUEEN, SPADES),
    KING_OF_SPADES(KING, SPADES),

    ACE_OF_CLUBS(ACE, CLUBS),
    TWO_OF_CLUBS(TWO, CLUBS),
    THREE_OF_CLUBS(THREE, CLUBS),
    FOUR_OF_CLUBS(FOUR, CLUBS),
    FIVE_OF_CLUBS(FIVE, CLUBS),
    SIX_OF_CLUBS(SIX, CLUBS),
    SEVEN_OF_CLUBS(SEVEN, CLUBS),
    EIGHT_OF_CLUBS(EIGHT, CLUBS),
    NINE_OF_CLUBS(NINE, CLUBS),
    TEN_OF_CLUBS(TEN, CLUBS),
    JACK_OF_CLUBS(JACK, CLUBS),
    QUEEN_OF_CLUBS(QUEEN, CLUBS),
    KING_OF_CLUBS(KING, CLUBS),

    ACE_OF_HEARTS(ACE, HEARTS),
    TWO_OF_HEARTS(TWO, HEARTS),
    THREE_OF_HEARTS(THREE, HEARTS),
    FOUR_OF_HEARTS(FOUR, HEARTS),
    FIVE_OF_HEARTS(FIVE, HEARTS),
    SIX_OF_HEARTS(SIX, HEARTS),
    SEVEN_OF_HEARTS(SEVEN, HEARTS),
    EIGHT_OF_HEARTS(EIGHT, HEARTS),
    NINE_OF_HEARTS(NINE, HEARTS),
    TEN_OF_HEARTS(TEN, HEARTS),
    JACK_OF_HEARTS(JACK, HEARTS),
    QUEEN_OF_HEARTS(QUEEN, HEARTS),
    KING_OF_HEARTS(KING, HEARTS),

    ACE_OF_DIAMONDS(ACE, DIAMONDS),
    TWO_OF_DIAMONDS(TWO, DIAMONDS),
    THREE_OF_DIAMONDS(THREE, DIAMONDS),
    FOUR_OF_DIAMONDS(FOUR, DIAMONDS),
    FIVE_OF_DIAMONDS(FIVE, DIAMONDS),
    SIX_OF_DIAMONDS(SIX, DIAMONDS),
    SEVEN_OF_DIAMONDS(SEVEN, DIAMONDS),
    EIGHT_OF_DIAMONDS(EIGHT, DIAMONDS),
    NINE_OF_DIAMONDS(NINE, DIAMONDS),
    TEN_OF_DIAMONDS(TEN, DIAMONDS),
    JACK_OF_DIAMONDS(JACK, DIAMONDS),
    QUEEN_OF_DIAMONDS(QUEEN, DIAMONDS),
    KING_OF_DIAMONDS(KING, DIAMONDS);

    private final FaceValue value;
    private final Suit suit;

    Card(FaceValue value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public static Card parse(String string) {
        String s = string.trim();
        Suit suit = Suit.parse(string.charAt(s.length() - 1));
        FaceValue value = FaceValue.parse(s.substring(0, s.length() - 1));

        for (Card card : Card.values()) {
            if (card.value == value && card.suit == suit) {
                return card;
            }
        }
        throw new IllegalArgumentException("Unknown card: " + string);
    }

    public FaceValue getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }
}
