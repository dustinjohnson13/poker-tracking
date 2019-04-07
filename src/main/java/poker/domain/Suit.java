package poker.domain;

public enum Suit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;

    public static Suit parse(char character) {
        switch (character) {
            case 'd':
                return DIAMONDS;
            case 'c':
                return CLUBS;
            case 'h':
                return HEARTS;
            case 's':
                return SPADES;
            default:
                throw new IllegalArgumentException("Unknown suit character: " + character);
        }
    }
}