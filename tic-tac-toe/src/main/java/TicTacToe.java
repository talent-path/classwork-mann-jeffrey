public class TicTacToe {
    public static void main(String[] args) {
        play();
    }

    public static int[][] board = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

    private static boolean isFinished = false;

    public static void play() {
        printBoard();
        while (!isFinished) {
            getUserChoice();
            getComputerChoice();
            isFinished = checkTie();
        }
    }

    public static void printBoard() {
        for (int i = 0; i < board.length * board.length; i++) {
            int row = i / board.length;
            int col = i % board.length;
            System.out.print(board[row][col] + " ");
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
            if (isFinished) Console.print("You won!");
        } else {
            Console.print("You have already picked that location!");
            getUserChoice();
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
            if (isFinished) Console.print("Computer has won!");
        } else {
            Console.print("The computer has already picked that location!");
            getComputerChoice();
        }
    }

    public static boolean checkBoardPositionEmpty(int row, int col) {
        if (board[row][col] == 1) {
            return false;
        } else {
            return board[row][col] != 2;
        }
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
        return true;
    }
}
