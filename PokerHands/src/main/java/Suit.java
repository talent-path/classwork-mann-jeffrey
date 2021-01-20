// enum is short for "enumeration"
// describe a set of allowable values
// which are attached to the enum
// and then in code we can make variables of type Suit, which can have only these values
public enum Suit {
    SPADES(0),
    DIAMONDS(1),
    HEARTS(2),
    CLUBS(3);

    int value = -1;

    Suit(int value) {
        this.value = value;
    }
}
