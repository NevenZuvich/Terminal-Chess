// Written and made by Neven Zuvich, zuvic003

public class Queen {

    // Member variables
    int row, col;
    boolean isBlack;

    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    } // constructor

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        return board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) && // returns if valid source and dest AND
                (board.verifyDiagonal(row, col, endRow, endCol) || // is diagonal with nothing in between OR
                        board.verifyVertical(row, col, endRow, endCol) || // is vertical with nothing in between OR
                        board.verifyHorizontal(row, col, endRow, endCol)); // is horizontal with nothing in between
    }
}