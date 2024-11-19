import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        clearBoard();
        display();

        Scanner scanner = new Scanner(System.in);
        boolean gameEnded = false;

        while (!gameEnded) {
            int[] move = getPlayerMove(scanner);
            int row = move[0];
            int col = move[1];

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                display();

                if (isWin(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameEnded = true;
                } else if (isTie()) {
                    System.out.println("It's a tie!");
                    gameEnded = true;
                } else {
                    currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                }
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
        scanner.close();
    }

    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("-------------");
        for (int i = 0; i < ROW; i++) {
            System.out.print("| ");
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROW && col >= 0 && col < COL && board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagnalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[] getPlayerMove(Scanner scanner) {
        int[] move = new int[2];
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Player " + currentPlayer + ", enter your move (row and column): ");
            int row = SafeInput.getRangedInt(scanner, "Enter row (1-3)", 1, 3) - 1;
            int col = SafeInput.getRangedInt(scanner, "Enter column (1-3)", 1, 3) - 1;
            move[0] = row;
            move[1] = col;
            if (isValidMove(row, col)) {
                validInput = true;
            } else {
                System.out.println("Invalid move. That cell is already taken.");
            }
        }

        return move;
    }
}