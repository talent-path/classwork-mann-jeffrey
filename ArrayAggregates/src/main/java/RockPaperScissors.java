
public class RockPaperScissors {
    public static void play() {
        Console.print("Choose (1) for Rock");
        Console.print("Choose (2) for Paper");
        Console.print("Choose (3) for Scissors");
        int numGames = getNumberOfGames();

        int wins = 0;
        int losses = 0;
        int draws = 0;

        while (numGames > 0) {
            int userChoice = getUserChoice();
            int compChoice = getComputerChoice();

            if ((userChoice == 1 && compChoice == 2)
                    || (userChoice == 2 && compChoice == 3)
                    || (userChoice == 3 && compChoice == 1)
            ) {
                Console.print(String.format("Computer chose %d", compChoice));
                Console.print("Computer Wins!");
                losses++;
            } else if ((userChoice == 2 && compChoice == 1)
                    || (userChoice == 3 && compChoice == 2)
                    || (userChoice == 1 && compChoice == 3)
            ) {
                Console.print(String.format("Computer chose %d", compChoice));
                Console.print("You Win!");
                wins++;
            } else if (userChoice == compChoice) {
                Console.print(String.format("Computer chose %d", compChoice));
                Console.print("Draw!");
                draws++;
            }

            numGames--;
        }

        Console.print(String.format("Wins: %d \n Losses: %d \n Draws: %d", wins, losses, draws));
    }

    public static int getNumberOfGames() {
        return Console.readInt("How many rounds would you like to play?");
    }

    public static int getUserChoice() {
        return Console.readInt("Let's play Rock, Paper, Scissors!");
    }

    public static int getComputerChoice() {
        return RNG.randInt(1, 3);
    }
}
