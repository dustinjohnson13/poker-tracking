package poker.domain;

public enum FaceValue {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private final String shortName;

    FaceValue(String shortName) {
        this.shortName = shortName;
    }

    public static FaceValue parse(String string) {
        for (FaceValue value : FaceValue.values()) {
            if (value.shortName.equals(string)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown value for string: " + string);
    }
}