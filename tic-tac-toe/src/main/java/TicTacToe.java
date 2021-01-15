public class TicTacToe {
    public static void main(String[] args) {
        int numGames = getNumberOfGames();
        while (numGames > 0) {
            play();
            reset();
            numGames--;
        }
        Console.print(String.format("Wins: %d\nLosses: %d\nDraws: %d", wins, losses, draws));
    }

    public static int[][] board = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

    public static boolean isFinished = false;

    public static int wins = 0;
    public static int losses = 0;
    public static int draws = 0;

    public static void reset() {
        Console.print("New Game!");

        board = new int[][] {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        isFinished = false;
    }

    public static void play() {
        printBoard();
        while (!isFinished) {
            getUserChoice();
            if (isFinished) break;
            getComputerChoice();
            if (isFinished) break;
        }
    }

    public static void printBoard() {
        for (int i = 0; i < board.length * board.length; i++) {
            int row = i / board.length;
            int col = i % board.length;
            String boardToken = board[row][col] == 0 ? "-" : (board[row][col] == 1 ? "X" : "O");
            System.out.print(boardToken + " ");
            if (i % board.length == board.length - 1) System.out.println();
        }
    }

    public static void getUserChoice() {
        int userRow = Console.readInt("Choose the row: 1-3");
        int userCol = Console.readInt("Choose the column: 1-3");
        if (checkBoardPositionEmpty(userRow - 1, userCol - 1)) {
            board[userRow - 1][userCol - 1] = 1;
            printBoard();
            isFinished = gameOver(1, userRow - 1, userCol - 1);
            if (isFinished) {
                wins++;
                Console.print("You won!");
            }
        } else {
            isFinished = checkTie();
            if (!isFinished) getUserChoice();
        }
    }

    public static void getComputerChoice() {
        int compRow = RNG.randInt(0, 2);
        int compCol = RNG.randInt(0, 2);
        if (checkBoardPositionEmpty(compRow, compCol)) {
            Console.print(String.format("Computer chose row %d", compRow + 1));
            Console.print(String.format("Computer chose column %d", compCol + 1));
            board[compRow][compCol] = 2;
            printBoard();
            isFinished = gameOver(2, compRow, compCol);
            if (isFinished) {
                losses++;
                Console.print("Computer has won!");
            }
        } else {
            isFinished = checkTie();
            if (!isFinished) getComputerChoice();
        }
    }

    public static boolean checkBoardPositionEmpty(int row, int col) {
        if (row > board.length-1 || row < 0 || col > board.length-1 || col < 0) {
            Console.print("That position is out of bounds!");
            return false;
        }
        if (board[row][col] == 1) {
            Console.print("That location has already been picked!");
            return false;
        }
        else return board[row][col] != 2;
    }

    public static boolean gameOver(int player, int currentRow, int currentColumn) {
        return (board[currentRow][0] == player         // 3-in-the-row
                && board[currentRow][1] == player
                && board[currentRow][2] == player
                || board[0][currentColumn] == player      // 3-in-the-column
                && board[1][currentColumn] == player
                && board[2][currentColumn] == player
                || currentRow == currentColumn            // 3-in-the-diagonal
                && board[0][0] == player
                && board[1][1] == player
                && board[2][2] == player
                || currentRow + currentColumn == 2  // 3-in-the-opposite-diagonal
                && board[0][2] == player
                && board[1][1] == player
                && board[2][0] == player);
    }

    public static boolean checkTie() {
        for (int i = 0; i < board.length * board.length; i++) {
            int row = i / board.length;
            int col = i % board.length;
            if (board[row][col] == 0) return false;
        }
        draws++;
        return true;
    }

    public static int getNumberOfGames() {
        return Console.readInt("How many rounds would you like to play?");
    }
}
