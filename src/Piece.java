// Modified  by Neven Zuvich, zuvic003


import java.util.Scanner;
public class Piece {

    // Instance variables
    private char character;
    private int row;
    private int col;
    private boolean isBlack;

    /**
     * Constructor.
     * @param character     The character representing the piece.
     * @param row           The row on the board the piece occupies.
     * @param col           The column on the board the piece occupies.
     * @param isBlack       The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Determines if moving this piece is legal.
     * @param board     The current state of the board.
     * @param endRow    The destination row of the move.
     * @param endCol    The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            // Pawn chars
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            // Rook chars
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            // Knight chars
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            // Bishop chars
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            // Queen chars
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            // King chars
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }


    public void promote() {
        if ((character == '\u265f' || character == '\u2659') && (row == 0 || row == 7)) { // if this piece is a pawn at one of the end rows
            Scanner stdin = new Scanner(System.in);
            System.out.println("A pawn has reached the opposite row at " + row + ", " + col + "!");
            System.out.println("It must be promoted. Enter one of the following exactly to promote the pawn to a corresponding piece: ");
            System.out.println("Rook, Knight, Bishop, or Queen.");
            String input = stdin.nextLine();
            while (!input.equals("Rook") && !input.equals("Knight") && !input.equals("Bishop") && !input.equals("Queen")) { // makes sure input is correct
                System.out.println("You entered an invalid input. Remember your options are: Rook, Knight, Bishop, or Queen.");
                System.out.println("These must be typed exactly as shown, capitals included.");
                input = stdin.nextLine();
            }
            switch (input) {
                case "Knight":
                    character -= 1; // knight char of correct color is always one less than pawn
                    break;
                case "Bishop":
                    character -= 2; // bishop char of correct color is always two less than pawn
                    break;
                case "Rook":
                    character -= 3; // rook char of correct color is always three less than pawn
                    break;
                case "Queen":
                    character -= 4; // // queen char of correct color is always four less than pawn
                    break;
            }
        }
    }



    /**
     * Sets the position of the piece.
     * @param row   The row to move the piece to.
     * @param col   The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the current chess unicode character.
     * @return Unicode character.
     */
    public char getCharacter(){
        return this.character;
    }

    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack() {
        return this.isBlack;
    }

    /**
     * Tests the equality of two Piece objects based on
     * their character parameter.
     * @param other An instance of Piece to compare with this
     *              instance.
     * @return Boolean value representing equality result.
     */
    public boolean equals(Piece other){
        return this.character == other.character;
    }

    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        return "" + this.character;
    }


}
