// Modified by Neven Zuvich, zuvic003

// I got the idea to use '\u2003' and '\u2004' characters instead of 2001 or 3000 from: https://jkorpela.fi/chars/spaces.html

import java.lang.Math;
public class Board {

    // Instance variables
    private Piece[][] board;
    public Board() {
        board = new Piece[8][8];
    }

    // Accessor Methods

    public Piece getPiece(int row, int col) {
        if (row < board.length && 0 <= row && col < board[1].length && 0 <= col)
            return board[row][col];
        else
            return null;
    }

    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods

    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (board[startRow][startCol].isMoveLegal(this, endRow, endCol)) { // if move is legal
            Piece piece = getPiece(startRow, startCol);
            setPiece(endRow, endCol, piece); // adds correct piece to new location
            setPiece(startRow, startCol, null); // removes old piece
            piece.setPosition(endRow, endCol); // sets new piece's position to new location
            return true;
        } else // if move is illegal
            return false;
    }

    public boolean isGameOver() {
        boolean hasWhiteKing = false, hasBlackKing = false;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] != null) {
                    if (board[row][col].getCharacter() == '\u2654') // if piece is white king
                        hasWhiteKing = true;
                    else if (board[row][col].getCharacter() == '\u265a') // if piece is black king
                        hasBlackKing = true;
                }
            }
        }
        return !(hasWhiteKing && hasBlackKing);
    }


    public String toString() {
        String endStr = "Board:\n " + '\u2004' + '\u3000';
        for (int i = 0; i < 8; i++) { // sets up numbers for columns at the top
            endStr += i + "" + '\u2003';
        }
        endStr += "\n";
        for (int row = 0; row < 8; row++) {
            endStr += row + " |"; // adds row numbers to side of rows
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == null)
                    endStr += '\u2003' + "|"; // if piece is null, just put a space between
                else
                    endStr += board[row][col] + "|"; // if piece isn't null, put its chatacter between
            }
            endStr += "\n";
        }
        return endStr;
    }

    public void clear() { // loops through the whole board with a nested loop, setting each space to null
         for (int row = 0; row < 8; row++) {
             for (int col = 0; col < 8; col++) {
                 board[row][col] = null;
             }
         }
    }


    // Movement helper functions


    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
       return inBounds(startRow, startCol, endRow, endCol) && // checks start and end cols and rows are within the array's bounds
               board[startRow][startCol] != null && // checks that the starting square contains a Piece object
               board[startRow][startCol].getIsBlack() == isBlack && // checks that start Piece and Player's color match
               (board[endRow][endCol] == null || board[endRow][endCol].getIsBlack() != isBlack); // checks end square is empty, or has opposite color Piece

    }

    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        return (Math.abs(endRow - startRow) <= 1) && // return: if start row is within one of end row AND
                (Math.abs(endCol - startCol) <= 1); // if start col is within one of end col
    }

    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow)  // if move is not horizontal (regardless of pieces in between)
            return false;
        else {
            int firstCol = Math.min(startCol, endCol) + 1;
            int lastCol = Math.max(startCol, endCol);

            for (int col = firstCol; col < lastCol; col++) {
                // loops over from smaller column to higher column, checks if pieces are between
                if (board[endRow][col] != null)
                    return false;
            }
        }
        return true; // returns true only if move is contained to one row (is horizontal) and no pieces are between
    }


    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol) // if move is not in the same column (not vertical)
            return false;
        else {
            int firstRow = Math.min(startRow, endRow) + 1;
            int lastRow = Math.max(startRow, endRow);

            for (int row = firstRow; row < lastRow; row++) {
                // loops from smaller row down to high row, checks if pieces are in between
                if (board[row][endCol] != null)
                    return false;
            }
        }
        return true; // only if end position is in the same column as start and no pieces between
    }

    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int sum = startRow + startCol;
        int diff = startRow - startCol;
        if (endRow - endCol != diff && endRow + endCol != sum)  // if not on either diagonal
            return false;
        else if (startRow == endRow && startCol == endCol) // if end is the same square as start
            return true;
        else
            return checkBetweenDiag(startRow,startCol, endRow, endCol);
    }

    public boolean checkBetweenDiag(int startRow, int startCol, int endRow, int endCol) {
        // returns if there are pieces between two diagonal positions on the board
        int rowIncSign = (endRow - startRow) / Math.abs(endRow - startRow); // -1 or 1, whether row should be decremented or incremented
        int colIncSign = (endCol - startCol) / Math.abs(endCol - startCol); // same as rowSign but for columns
        int curRow = startRow + rowIncSign, curCol = startCol + colIncSign; // initial values for curRow and curCol

        while (curRow != endRow && curCol != endCol) {
            if (board[curRow][curCol] != null)
                return false;
            curRow += rowIncSign; // decrements or increments variables
            curCol += colIncSign;
        }
        return true; // only returns true if no pieces are between
    }

    public static boolean inBounds(int... nums) { // returns if all the ints in nums (row or col values) are in bounds (0 <= n < 8)
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 || 7 < nums[i])
                return false;
        }
        return true;
    }

}
