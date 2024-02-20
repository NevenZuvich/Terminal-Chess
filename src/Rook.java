// Written and made by Neven Zuvich, zuvic003

public class Rook {

    // Member variables
    private int row, col;
    private boolean isBlack;

    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    } // constructor

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        return board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) && // returns if valid source and destination AND
                (board.verifyVertical(row, col, endRow, endCol) || // vertical with nothing between OR
                board.verifyHorizontal(row, col, endRow, endCol)); // horizontal with nothing between
    }
}
