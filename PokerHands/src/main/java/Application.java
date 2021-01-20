import java.util.Map;

public class Application {
    public static void main(String[] args) {

        //using "new" keyword to instantiate a PlayingCard object
        //when we use the "new" keyword, it calls the constructor method
        //we have not yet defined a constructor so it just calls the default that does nothing
        //we get back a reference to the object (which is on the heap)
        //that reference is what is stored inside of aceOfDiamonds
//        PlayingCard aceOfDiamonds = new PlayingCard();

        //the above won't work now because we have a non-default constructor
        //
//        PlayingCard aceOfDiamonds = new PlayingCard(FaceValue.ACE, Suit.DIAMONDS);
//        PlayingCard aceOfDiamonds = new PlayingCard(49);
//        System.out.println(aceOfDiamonds.cardSuit);
//        System.out.println(aceOfDiamonds.cardValue);

        // we now call non-static methods on our instance
        // NOT on PlayingCard but on aceOfDiamonds
//        aceOfDiamonds.setCardValue(FaceValue.ACE);
//        aceOfDiamonds.setCardSuit(Suit.DIAMONDS);

        // THIS DOES NOT WORK:
//        PlayingCard.setCardValue();
//        PlayingCard.setCardSuit();

        //                                                  straight flush vs 4 of a kind
        //                                            2D  3D 4D 5D  6D  7S  7D  7C  7H  8S
//        int[] bothHands = {1, 5, 9, 13, 17, 20, 21, 22, 23, 24};
//        PlayingCard[] hand1Cards = new PlayingCard[5];
//        PlayingCard[] hand2Cards = new PlayingCard[5];
//
//        for (int i = 0; i < bothHands.length; i++) {
//            if (i < 5) hand1Cards[i] = new PlayingCard(bothHands[i]);
//            else hand2Cards[i] = new PlayingCard(bothHands[i]);
//        }
//
//        PokerHand hand1 = new PokerHand(hand1Cards);
//        PokerHand hand2 = new PokerHand(hand2Cards);

//        HAND 1
        PokerHand hand1 = new PokerHand(new PlayingCard[]{
                new PlayingCard(0),
                new PlayingCard(1),
                new PlayingCard(18),
                new PlayingCard(19),
                new PlayingCard(36),
        });
        Map<FaceValue, Integer> h1Values = hand1.countFaceValues();
        Map<Suit, Integer> h1Suits = hand1.countSuits();

//        printing HAND 1
        System.out.println("HAND1 -----");
        for (PlayingCard c :
                hand1.cards) {
            System.out.print(c.cardValue);
            System.out.print(c.cardSuit);
            System.out.println();
        }
        for (FaceValue key :
                h1Values.keySet()) {
            System.out.println(key + ": " + h1Values.get(key));
        }
        for (Suit key :
                h1Suits.keySet()) {
            System.out.println(key + ": " + h1Suits.get(key));
        }
        System.out.println("Second Pair with value: " + hand1.lowerPairValue());
        System.out.println("Three of a kind of value: " + hand1.threeOfAKindValue());
        System.out.println("Four of a kind with value: " + hand1.fourOfAKindValue());
        System.out.println("Is this a full house: " + hand1.isFullHouse());
        System.out.println("Straight with high card: " + hand1.straightHighCardValue());
        System.out.println("Is this a flush? " + hand1.isFlush());
        System.out.println("Flush suit: " + hand1.flushSuit());
        System.out.println("Flush high card: " + hand1.flushHighestValue());
        System.out.println("Is this a straight flush? " + hand1.isStraightFlush());
        System.out.println("Is this a royal flush? " + hand1.isRoyalFlush());
        System.out.println("---------");

//        HAND 2
        PokerHand hand2 = new PokerHand(new PlayingCard[]{
                new PlayingCard(0),
                new PlayingCard(1),
                new PlayingCard(13),
                new PlayingCard(14),
                new PlayingCard(34)
        });
        Map<FaceValue, Integer> h2Values = hand2.countFaceValues();
        Map<Suit, Integer> h2Suits = hand2.countSuits();
//        printing HAND 2
        System.out.println("HAND2 -----");
        for (PlayingCard c:
             hand2.cards) {
            System.out.print(c.cardValue);
            System.out.print(c.cardSuit);
            System.out.println();
        }
        for (FaceValue key :
                h2Values.keySet()) {
            System.out.println(key + ": " + h2Values.get(key));
        }
        for (Suit key :
                h2Suits.keySet()) {
            System.out.println(key + ": " + h2Suits.get(key));
        }
        System.out.println("Pair of value: " + hand2.pairValue());
        System.out.println("Second Pair with value: " + hand2.lowerPairValue());
        System.out.println("Three of a kind of value: " + hand2.threeOfAKindValue());
        System.out.println("Four of a kind with value: " + hand2.fourOfAKindValue());
        System.out.println("Is this a full house: " + hand2.isFullHouse());
        System.out.println("Straight with high card: " + hand2.straightHighCardValue());
        System.out.println("Is this a flush? " + hand2.isFlush());
        System.out.println("Flush suit: " + hand2.flushSuit());
        System.out.println("Flush high card: " + hand2.flushHighestValue());
        System.out.println("Is this a straight flush? " + hand2.isStraightFlush());
        System.out.println("Is this a royal flush? " + hand2.isRoyalFlush());
        System.out.println("----------");

        int hand1WinsResult = hand1.compareTo(hand2);
        System.out.println(hand1WinsResult);
        if (hand1WinsResult > 0) {
            Console.print("Hand 1 Wins");
        } else if (hand1WinsResult < 0) {
            Console.print("Hand 2 Wins");
        } else {
            Console.print("Tie!");
        }


    }

    //player one's cards are 0-4
    //player two's cards are 5-9
    public static boolean playerOneWins(int[] bothHands) {
//        1. royal flush same suit (card%4 will be the same) and sequential from 10 to ace (32 on)

        int temp = bothHands[0];

        for (int i = 1; i < bothHands.length / 2; i++) {
            if (bothHands[i] < 32) return false;
        }
        throw new UnsupportedOperationException();
    }

    //generates an array of size 10 representing 2 hands of poker
    //the values are 0-51
    //the suit is value%4            (0-spades, 1-diamonds, 2-clubs, 3-hearts)
    //the card is value/4 + 2       J = 11, Q = 12, K = 13, A = 14
    public static int[] generatePokerHands() {
        int[] cards = new int[10];
        for (int i = 0; i < 10; i++) {
            int card = Integer.MIN_VALUE;

            while (card == Integer.MIN_VALUE) {
                card = Rng.nextInt(0, 51);
                for (int j = 0; j < i; j++) {
                    if (cards[j] == card) card = Integer.MIN_VALUE;
                }
            }
        }

        return cards;
    }
}
