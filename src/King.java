// Written and made by Neven Zuvich, zuvic003

public class King {
    // member variables
    int row, col;
    boolean isBlack;
    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    } // constructor

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        return board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) && // returns if is a valid source and dest AND
                board.verifyAdjacent(row, col, endRow, endCol); // move is adjacent
    }
}
