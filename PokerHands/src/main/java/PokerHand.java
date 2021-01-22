import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PokerHand {
    //    field variables
    // compose a poker hand from multiple sub-objects that we pull into a collection
    PlayingCard[] cards;

    public PokerHand(PlayingCard[] cards) {
        this.cards = cards;
        sortCards();
    }

    public PokerHand(List<PlayingCard> cards) {
        PlayingCard[] listToArray = new PlayingCard[5];

        for (int i = 0; i < cards.size(); i++) {
            listToArray[i] = cards.get(i);
        }

        this.cards = listToArray;
        sortCards();
    }

    private void sortCards() {
        //bubble sort
        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < cards.length - 1; i++) {
                //does the element at i need to be flipped with the element at i + 1?
                if ((cards[i].getCardValue().value < cards[i + 1].getCardValue().value) ||

                        (cards[i].getCardValue().value == cards[i + 1].getCardValue().value
                                && cards[i].getCardSuit().value < cards[i + 1].getCardSuit().value
                        )
                ) {
                    //we need to swap elements at i and i + 1
                    //it also means we found elements out of order
                    sorted = false;
                    PlayingCard temp = cards[i + 1];
                    cards[i + 1] = cards[i];
                    cards[i] = temp;
                }
            }

        }
    }

    public PlayingCard[] getCards() {
        return cards;
    }

    public Map<FaceValue, Integer> countFaceValues() {
        Map<FaceValue, Integer> count = new HashMap<>();

        for (PlayingCard toCount : cards) {
            if (!count.containsKey(toCount.getCardValue())) {
                count.put(toCount.getCardValue(), 0);
            }
            count.put(toCount.getCardValue(), 1 + count.get(toCount.getCardValue()));
        }
        return count;
    }

    public Map<Suit, Integer> countSuits() {
        Map<Suit, Integer> count = new HashMap<>();

        for (PlayingCard toCount : cards) {
            if (!count.containsKey(toCount.getCardSuit())) {
                count.put(toCount.getCardSuit(), 0);
            }
            count.put(toCount.getCardSuit(), 1 + count.get(toCount.getCardSuit()));
        }
        return count;
    }

    public FaceValue highestCardValue() {
        PlayingCard highestCard = cards[0];
        for (PlayingCard card : cards) {
            highestCard = card.cardValue.value > highestCard.cardValue.value ? card : highestCard;
        }
        return highestCard.cardValue;
    }

    public FaceValue straightHighestValue() {
        FaceValue highestValue = cards[cards.length - 1].cardValue;
        for (FaceValue key : this.countFaceValues().keySet()) {
            if (this.countFaceValues().get(key) != 1) return null;
            if (key.value > cards[cards.length - 1].cardValue.value + 4) return null;
            if (key.value > highestValue.value) highestValue = key;
        }
        return highestValue;
    }

    public boolean isFlush() {
        for (Suit key : this.countSuits().keySet()) {
            if (this.countSuits().get(key) == 5) return true;
        }
        return false;
    }

    public Suit flushSuit() {
        Suit flush = null;
        for (Suit key : this.countSuits().keySet()) {
            if (this.countSuits().get(key) == 5) flush = key;
        }
        return flush;
    }

    public FaceValue flushHighestValue() {
        if (this.isFlush()) return cards[0].cardValue;
        return null;
    }

    public boolean isStraightFlush() {
        return (straightHighestValue() != null) && isFlush();
    }

    public boolean isRoyalFlush() {
        if (this.isStraightFlush()) {
            return cards[cards.length - 1].cardValue.value > 7;
        }
        return false;
    }

    public boolean isFullHouse() {
        return this.pairValue() != null && this.threeOfAKindValue() != null;
    }

    public FaceValue fourOfAKindValue() {
        FaceValue highestFour = null;
        for (FaceValue key : this.countFaceValues().keySet()) {
            if (this.countFaceValues().get(key) == 4) {
                if (highestFour == null) {
                    highestFour = key;
                } else if (key.value > highestFour.value) {
                    highestFour = key;
                }
            }
        }
        return highestFour;
    }

    public FaceValue threeOfAKindValue() {
        FaceValue highestTriple = null;
        for (FaceValue key : this.countFaceValues().keySet()) {
            if (this.countFaceValues().get(key) == 3) {
                if (highestTriple == null) {
                    highestTriple = key;
                } else if (key.value > highestTriple.value) {
                    highestTriple = key;
                }
            }
        }
        return highestTriple;
    }

    public FaceValue pairValue() {
        FaceValue highestPair = null;
        for (FaceValue key : this.countFaceValues().keySet()) {
            if (this.countFaceValues().get(key) == 2) {
                if (highestPair == null) {
                    highestPair = key;
                } else if (key.value > highestPair.value) {
                    highestPair = key;
                }
            }
        }
        return highestPair;
    }

    public FaceValue lowerPairValue() {
        FaceValue lowerPair = null;
        for (FaceValue key : this.countFaceValues().keySet()) {
            if (key != this.pairValue()) {
                if (this.countFaceValues().get(key) == 2) lowerPair = key;
            }
        }
        return lowerPair;
    }

    public int compareTo(PokerHand that) {
        int hand1Score = this.scoreHand();
        int hand2Score = that.scoreHand();

        if (hand1Score == hand2Score) {
            //TODO: This has several breaking null pointer exceptions when asking for value

            //SWITCH
            switch (hand1Score) {
                case 90:
                    return 0;
                case 80:
                    return this.fourOfAKindValue().value > that.fourOfAKindValue().value ? 1 : -1;
                case 70:
                    if (this.threeOfAKindValue().value != that.threeOfAKindValue().value) {
                        return this.threeOfAKindValue().value > that.threeOfAKindValue().value ? 1 : -1;
                    } else {
                        return this.pairValue().value > that.pairValue().value ? 1 : -1;
                    }
                case 60:
                    return this.flushHighestValue().value > that.flushHighestValue().value ? 1 : -1;
                case 50:
                    return this.straightHighestValue().value > that.straightHighestValue().value ? 1 : -1;
                case 40:
                    return this.threeOfAKindValue().value > that.threeOfAKindValue().value ? 1 : -1;
                case 30:
                    if (this.pairValue().value != that.pairValue().value) {
                        return this.pairValue().value > that.pairValue().value ? 1 : -1;
                    } else if (this.lowerPairValue().value != that.lowerPairValue().value){
                        return this.lowerPairValue().value > that.lowerPairValue().value ? 1 : -1;
                    } else {
                        return this.highestCardValue().value > that.highestCardValue().value ? 1 : -1;
                    }
                case 20:
                    if (this.pairValue().value != that.pairValue().value) {
                        return this.pairValue().value > that.pairValue().value ? 1 : -1;
                    } else {
                        return this.highestCardValue().value > that.highestCardValue().value ? 1 : -1;
                    }
                case 0:
                    return this.highestCardValue().value > that.highestCardValue().value ? 1 : -1;
            }
            return 0;
        }
        return (hand1Score - hand2Score) / Math.abs(hand1Score - hand2Score);
    }

    public int scoreHand() {
        int score = 0;
        //royal flush
        if (this.isRoyalFlush()) score += 100;
        //straight flush
        else if (this.isStraightFlush()) score += 90;
        //four of a kind
        else if (this.fourOfAKindValue() != null) score += 80;
        //full house
        else if (this.isFullHouse()) score += 70;
        //flush
        else if (this.isFlush()) score += 60;
        //straight
        else if (this.straightHighestValue() != null) score += 50;
        //three of a kind
        else if (this.threeOfAKindValue() != null) score += 40;
        //two pair
        else if (this.pairValue() != null && this.lowerPairValue() != null) score += 30;
        //pair
        else if (this.pairValue() != null) score += 20;

        return score;
    }

}
