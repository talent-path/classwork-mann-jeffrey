// creating a new class to represent different playing cards
// each "instance" of this class will be an object of PlayingCard type

public class PlayingCard {
//    field variables
//    variables which exist "per-instance"
//    (so one PlayingCard may have different values for these variables from the next)

    Suit cardSuit;
    FaceValue cardValue;

    static Suit[] suits = {Suit.SPADES, Suit.DIAMONDS, Suit.CLUBS, Suit.HEARTS};
    static FaceValue[] values = {
            FaceValue.TWO, FaceValue.THREE, FaceValue.FOUR, FaceValue.FIVE,
            FaceValue.SIX, FaceValue.SEVEN, FaceValue.EIGHT, FaceValue.NINE, FaceValue.TEN,
            FaceValue.JACK, FaceValue.QUEEN, FaceValue.KING, FaceValue.ACE
    };

    //    constructors are special methods which run as soon as the object is instantiated
//    (when we use the "new" keyword)
//    constructors don't have a return type and are named the same as the class name
    public PlayingCard(FaceValue cardValue, Suit cardSuit) {
        this.cardValue = cardValue;
        this.cardSuit = cardSuit;
    }

    public PlayingCard(int cardNum) {
//        values 0-51
//        card suit is cardNum % 4      (0 = spades, 1 = diamonds, 2 = clubs, 3 = hearts)
//        card value is cardNum / 4 + 2
        this( values[cardNum / 4], suits[cardNum % 4] );
    }

    public Suit getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(Suit cardSuit) {
//        "this" refers to the current instance
//        normally the keyword is not needed (as in the "getter"^^)
//        but if we have another variable with this name
//        its the only way to access the field variable
        this.cardSuit = cardSuit;
    }

    public FaceValue getCardValue() {
        return cardValue;
    }

    public void setCardValue(FaceValue cardValue) {
        this.cardValue = cardValue;
    }

}
