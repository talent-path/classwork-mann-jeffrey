import java.util.HashMap;
import java.util.Map;

public class PokerHand {
    //    field variables
    // compose a poker hand from multiple sub-objects that we pull into a collection
    PlayingCard[] cards;

    public PokerHand(PlayingCard[] cards) {
//        if (cards.length != 5) {
////            TODO: create a specific exception
//            throw new Exception("Invalid number of cards!");
//        }

        this.cards = cards;

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

    public FaceValue straightHighCardValue() {
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
        return (straightHighCardValue() != null) && isFlush();
    }

    public boolean isRoyalFlush() {
        if (this.isStraightFlush()) {
            return cards[cards.length - 1].cardValue.value > 7;
        }
        return false;
    }

    public boolean isFullHouse() {
        if (this.pairValue() != null && this.threeOfAKindValue() != null) return true;
        return false;
    }

    public FaceValue fourOfAKindValue() {
        FaceValue highestFour = null;
        for (FaceValue key : this.countFaceValues().keySet()) {
            if (this.countFaceValues().get(key) == 4) highestFour = key;
        }
        return highestFour;
    }

    public FaceValue threeOfAKindValue() {
        FaceValue highestTriple = null;
        for (FaceValue key : this.countFaceValues().keySet()) {
            if (this.countFaceValues().get(key) == 3) highestTriple = key;
        }
        return highestTriple;
    }

    public FaceValue pairValue() {
        FaceValue highestPair = null;
        for (FaceValue key : this.countFaceValues().keySet()) {
            if (this.countFaceValues().get(key) == 2) highestPair = key;
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
//        best possible game is two royal flushes which is a tie
//        if this or that has a royal flush they win
//        if this or that has a straight flush and the other has anything but a straight flush or higher they win
//

        if (hand1Score == hand2Score) {
            //TODO: This has several breaking null pointer exceptions when asking for value
//            high card straight
//            if (this.straightHighCardValue().value > that.straightHighCardValue().value) return 1;
//            else if (this.straightHighCardValue().value < that.straightHighCardValue().value) return -1;
//            high card four of a kind
//            if (this.fourOfAKindValue().value > that.fourOfAKindValue().value) return 1;
//            else if (this.fourOfAKindValue().value < that.fourOfAKindValue().value) return -1;
//            high card full house
//            if (this.threeOfAKindValue().value > that.threeOfAKindValue().value) return 1;
//            else if (this.threeOfAKindValue().value < that.threeOfAKindValue().value) return -1;
//             high card flush
//            if (this.flushHighestValue().value > that.flushHighestValue().value) return 1;
//            else if (this.flushHighestValue().value < that.flushHighestValue().value) return -1;
//            high card pair
//            if (this.pairValue().value > that.pairValue().value) return 1;
//            else if (this.pairValue().value < that.pairValue().value) return -1;
//            high card in hand
//            if (this.cards[0].cardValue.value > that.cards[0].cardValue.value) return 1;
//            else if (this.cards[0].cardValue.value < that.cards[0].cardValue.value) return -1;

            return 0;
        }
        return (hand1Score - hand2Score) / Math.abs(hand1Score - hand2Score);
    }

    public int scoreHand() {
        int score = 0;
        if (this.isRoyalFlush()) score += 100;
        else if (this.isStraightFlush()) score += 90;
        else if (this.fourOfAKindValue() != null) score += 80;
        else if (this.isFullHouse()) score += 70;
        else if (this.isFlush()) score += 60;
        else if (this.straightHighCardValue() != null) score += 50;
        else if (this.threeOfAKindValue() != null) score += 40;
        else if (this.pairValue() != null && this.lowerPairValue() != null) score += 30;
        else if (this.pairValue() != null) score += 20;

        System.out.println(score);

        return score;
    }

}
