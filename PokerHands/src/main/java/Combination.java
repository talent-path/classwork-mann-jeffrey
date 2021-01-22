import java.util.ArrayList;
import java.util.List;

public class Combination {
    public static void main(String[] args) {
        PlayingCard[] sevenCards = new PlayingCard[]{
                new PlayingCard(0),
                new PlayingCard(4),
                new PlayingCard(8),
                new PlayingCard(12),
                new PlayingCard(16),
                new PlayingCard(20),
                new PlayingCard(24)
        };

        List<PlayingCard> selectedHand = new ArrayList<>();
        List<List<PlayingCard>> allCombinations = new ArrayList<>();

        sevenChooseFive(sevenCards, 0, selectedHand, allCombinations);

        List<PokerHand> allHands = new ArrayList<>();
        for (List<PlayingCard> hand : allCombinations) {
            allHands.add(new PokerHand(hand));
        }

        for (PokerHand hand : allHands) {
            for (PlayingCard card :
                    hand.cards) {
                System.out.print(card.cardValue);
                System.out.print(" of ");
                System.out.print(card.cardSuit);
                System.out.print("  ");
            }
            System.out.println();
            System.out.println("Score: " + hand.scoreHand());
        }

        System.out.println("----------------");
        for (PlayingCard card : bestCombination(allHands).cards) {
            System.out.print(card.cardValue);
            System.out.print(" of ");
            System.out.print(card.cardSuit);
            System.out.print("  ");
        }
        System.out.println();
        System.out.println("Score: " + bestCombination(allHands).scoreHand());
    }

    public static void sevenChooseFive(
            PlayingCard[] possible,
            int nextIndex,
            List<PlayingCard> currentlySelected,
            List<List<PlayingCard>> allCombinations) {

        int chosenNum = currentlySelected.size();
        int remainingNum = 5 - chosenNum;

        int availableCards = possible.length - nextIndex;


        //base cases
        if (currentlySelected.size() == 5) {
            List<PlayingCard> copy = new ArrayList<>();
            for (PlayingCard toCopy : currentlySelected) copy.add(toCopy);

            allCombinations.add(copy);
            return;
        }

        //if( nextIndex >= possible.length ) return;

        if (availableCards < remainingNum)
            return;

        //iterate through each card
        //for each card, we either include or don't
        //we'll try the recursion with the card included and with
        //the card excluded


        //try with the "card" first
        currentlySelected.add(possible[nextIndex]);
        sevenChooseFive(possible, nextIndex + 1, currentlySelected, allCombinations);

        currentlySelected.remove(currentlySelected.size() - 1);

        //try without choosing the card
        sevenChooseFive(possible, nextIndex + 1, currentlySelected, allCombinations);

    }

    public static PokerHand bestCombination(List<PokerHand> allHands) {
        PokerHand bestHand = allHands.get(0);
        for (PokerHand currentHand :
                allHands) {
            if (currentHand.scoreHand() > bestHand.scoreHand()) {
                bestHand = currentHand;
            }
        }
        return bestHand;
    }
}
