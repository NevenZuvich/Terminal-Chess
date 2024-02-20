// Written and made by Neven Zuvich, zuvic003

import java.util.Scanner;
import java.lang.Math;

public class Game {

    private static final String[] turnStrArr = {"white", "black"}; // small auxiliary data structure used for formatting later

    public static boolean executeTurn(Board board, int startRow, int startCol, int endRow, int endCol, boolean isBlackTurn) {
        Piece piece = board.getPiece(startRow, startCol); // Piece to be moved
        if (piece == null) { // if no Piece object on (startRow, startCol) or (startRow, startCol) is out of bounds
            System.out.println("You entered an invalid space for your starting position. Please try again.");
            return false;
        } else if(piece.getIsBlack() != isBlackTurn) { // if user entered a space contain a wrongly-colored Piece
            System.out.println("You chose the wrong color, and thus that move is invalid. Please choose the right one.");
            return false;
        } else if (board.movePiece(startRow, startCol, endRow, endCol)) { // checks if move is legal, if it is, moves it
            char pieceChr = piece.getCharacter();
            if ((pieceChr == '\u265f' || pieceChr == '\u2659') && (endRow == 7 || endRow == 0))
                // if piece is a pawn at either top or bottom row
                piece.promote();
            return true;
        } else { // if move was otherwise illegal (wrong shape, out of bounds, etc.)
            System.out.println("The move you entered was illegal. Please enter a legal one.");
            return false;
        }
    }

    // format helper functions

    public static int[] processInput(String rawInput, Scanner stdin) {
        String[] inputStrArr = rawInput.trim().split(" ");
        while (inputStrArr.length != 4 || !verifyInts(inputStrArr)) { // to make sure user inputs in correct format
            System.out.println("You input something wrong. Please re-enter. Remember, the format is *exactly* [start row] [start col] [end row] [end col].");
            inputStrArr = stdin.nextLine().trim().split(" ");
        }
        return toIntArr(inputStrArr);
    }

    public static int[] toIntArr(String[] strArr) { // converts an array of strings of ints to an array of ints
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    public static boolean verifyInts(String[] strArr) {
        // ensures all members of an input String are string representations of ints ("6", "2", etc.)
        try {
            for (int i = 0; i < strArr.length; i++) {
                Integer.parseInt(strArr[i]);
            }
            return true; // if all were representations of ints, return true
        }
        catch (NumberFormatException e) { // if any were not ints (and thus Integer.parseInt errors) return false
            return false;
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        Fen.load( "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board); // initializes board to starting position
        Scanner stdin = new Scanner(System.in);
        int turn = 0;
        String turnStr; // a string, "black" if it is black's turn (turn % 2 == 1) "white" if white's (turn % 2 == 0)
        while (!board.isGameOver()) {
            turnStr = turnStrArr[turn % 2];
            System.out.println(board);
            System.out.println("It is currently " + turnStr + "'s turn to play.");
            System.out.println("What is your move? (format: [start row] [start col] [end row] [end col])");
            int[] input = processInput(stdin.nextLine(), stdin); // final input array for this turn, an array of 4 ints
            boolean turnIsLegal = executeTurn(board, input[0], input[1], input[2], input[3], turn % 2 == 1); // executes turn
            if (turnIsLegal)
                turn++; // increment turn only if the move was legal
        }
        System.out.println(turnStrArr[(turn + 1) % 2] + " has won the game.\n" + board);
        stdin.close();
    }
}

