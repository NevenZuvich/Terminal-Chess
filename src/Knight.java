// Written and made by Neven Zuvich, zuvic003

public class Knight {
    // member variables
    int row, col;
    boolean isBlack;

    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    } // constructor

    public boolean verifyL(int endRow, int endCol) {
        // verifies that a move is shaped like an 'L', characteristic of the Knight's movement
        return ((endRow == row + 2 || endRow == row - 2) && (endCol == col + 1 || endCol == col - 1)) ||
                ((endCol == col + 2 || endCol == col - 2) && (endRow == row + 1 || endRow == row - 1));
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        return board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) && // returns if valid source and dest AND
                verifyL(endRow, endCol); // move is in the shape of an L
    }
}
