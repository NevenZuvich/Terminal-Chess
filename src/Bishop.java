// Written and made by Neven Zuvich, zuvic003

public class Bishop {
    // member variables
    int row, col;
    boolean isBlack;

    public Bishop(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    } // constructor

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        return board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) && // returns if source and destination valid AND
                board.verifyDiagonal(row, col, endRow, endCol); // is diagonal with nothing in between
    }
}
