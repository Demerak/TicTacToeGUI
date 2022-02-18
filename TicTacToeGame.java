import java.util.Arrays;

public class TicTacToeGame {

    // FINISH THE VARIABLE DECLARATION
    /**
     * The board of the game, stored as a one dimension array.
     */
    CellValue[] board;

    /**
     * level records the number of rounds that have been
     * played so far.
     */
    int level;

    /**
     * gameState records the current state of the game.
     */
    GameState gameState;

    /**
     * lines is the number of lines in the grid
     */
    int lines;

    /**
     * columns is the number of columns in the grid
     */
    int columns;

    /**
     * sizeWin is the number of cell of the same type
     * that must be aligned to win the game
     */
    int sizeWin;

    /**
     * default constructor, for a game of 3x3, which must
     * align 3 cells
     */
    public TicTacToeGame() {

        // MY CODE HERE
        this(3, 3, 3);

    }

    /**
     * constructor allowing to specify the number of lines
     * and the number of columns for the game. 3 cells must
     * be aligned.
     * 
     * @param lines
     *                the number of lines in the game
     * @param columns
     *                the number of columns in the game
     */
    public TicTacToeGame(int lines, int columns) {

        // MY CODE HERE
        this(lines, columns, 3);

    }

    /**
     * constructor allowing to specify the number of lines
     * and the number of columns for the game, as well as
     * the number of cells that must be aligned to win.
     * 
     * @param lines
     *                the number of lines in the game
     * @param columns
     *                the number of columns in the game
     * @param sizeWin
     *                the number of cells that must be aligned to win.
     */
    public TicTacToeGame(int lines, int columns, int sizeWin) {

        // MY CODE HERE
        this.gameState = GameState.PLAYING;
        this.lines = lines;
        this.columns = columns;
        this.sizeWin = sizeWin;
        this.board = new CellValue[columns * lines];
        Arrays.fill(this.board, CellValue.EMPTY); // fill array with empty CellValue

    }

    /**
     * getter for the variable lines
     * 
     * @return
     *         the value of lines
     */
    public int getLines() {

        // MY CODE HERE
        return this.lines;

    }

    /**
     * getter for the variable columns
     * 
     * @return
     *         the value of columns
     */
    public int getColumns() {

        // MY CODE HERE
        return this.columns;

    }

    /**
     * getter for the variable level
     * 
     * @return
     *         the value of level
     */
    public int getLevel() {

        // MY CODE HERE
        return this.level;

    }

    /**
     * getter for the variable sizeWin
     * 
     * @return
     *         the value of sizeWin
     */
    public int getSizeWin() {

        // MY CODE HERE
        return this.sizeWin;

    }

    /**
     * getter for the variable gameState
     * 
     * @return
     *         the value of gameState
     */
    public GameState getGameState() {

        // MY CODE HERE
        return this.gameState;

    }

    /**
     * returns the cellValue that is expected next,
     * in other word, which played (X or O) should
     * play next.
     * This method does not modify the state of the
     * game.
     * 
     * @return
     *         the value of the enum CellValue corresponding
     *         to the next expected value.
     */
    public CellValue nextCellValue() {

        // even level # means X turn
        if (this.level % 2 == 0) {
            return CellValue.X;
        } else { // odd level # means O turn
            return CellValue.O;
        }
    }

    /**
     * returns the value of the cell at
     * index i.
     * If the index is invalid, an error message is
     * printed out. The behaviour is then unspecified
     * 
     * @param i
     *          the index of the cell in the array board
     * @return
     *         the value at index i in the variable board.
     */
    public CellValue valueAt(int i) {

        // MY CODE HERE
        return this.board[i];

    }

    /**
     * This method is called when the next move has been
     * decided by the next player. It receives the index
     * of the cell to play as parameter.
     * If the index is invalid, an error message is
     * printed out. The behaviour is then unspecified
     * If the chosen cell is not empty, an error message is
     * printed out. The behaviour is then unspecified
     * If the move is valide, the board is updated, as well
     * as the state of the game.
     * To faciliate testing, is is acceptable to keep playing
     * after a game is already won. If that is the case, the
     * a message should be printed out and the move recorded.
     * the winner of the game is the player who won first
     * 
     * @param i
     *          the index of the cell in the array board that has been
     *          selected by the next player
     */
    public void play(int i) {

        // MY CODE HERE
        if (i >= (this.lines * this.columns) || i < 0) {
            System.out.println("The value should be between 1 and 9");
        } else {
            switch (this.board[i]) {
                case EMPTY:
                    this.board[i] = nextCellValue();
                    this.level++;
                    this.setGameState(i);
                    break;
                default:
                    System.out.println("This cell has already been played");
                    break;
            }
        }
    }

    // what I would have done in the method play for more advance answer
    /*
     * case X:
     * if (nextCellValue() == CellValue.X) {
     * System.out.println("You already played there");
     * } else {
     * System.out.println("Your oponent already played there");
     * }
     * break;
     * case O:
     * if (nextCellValue() == CellValue.O) {
     * System.out.println("You already played there");
     * } else {
     * System.out.println("Your oponent already played there");
     * }
     * break;
     */

    /**
     * A helper method which updates the gameState variable
     * correctly after the cell at index i was just set in
     * the method play(int i)
     * The method assumes that prior to setting the cell
     * at index i, the gameState variable was correctly set.
     * it also assumes that it is only called if the game was
     * not already finished when the cell at index i was played
     * (i.e. the game was playing). Therefore, it only needs to
     * check if playing at index i has concluded the game, and if
     * set the oucome correctly
     * 
     * @param i
     *          the index of the cell in the array board that has just
     *          been set
     */

    private void setGameState(int i) {

        // MY CODE HERE
        if (this.level == this.lines * this.columns) {
            this.gameState = GameState.DRAW;
            System.out.println(toString());
            System.out.println("Result: " + this.gameState.name());
        }

        // find the row and column of i
        int row = 0;
        int column = 0;

        for (int rowIndex = 0; rowIndex < this.lines; rowIndex++) {
            for (int colIndex = 0; colIndex < this.columns; colIndex++) {
                if (i == (colIndex + rowIndex * this.columns)) {
                    row = rowIndex;
                    column = colIndex;
                    break;
                }
            }
        }

        // init two 1D array
        CellValue[] horizontal = new CellValue[this.columns];
        CellValue[] vertical = new CellValue[this.lines];

        // iterate over the board game and fill the
        // horizontal array with all the CellValue of
        // the current row
        int colCount = 0;
        while (colCount < this.columns) {
            horizontal[colCount] = this.board[this.columns * row + colCount];
            colCount++;
        }

        // iterate over the board game and fill the
        // vertical array with all the CellValue of
        // the current column
        int rowCount = 0;
        while (rowCount < this.lines) {
            vertical[rowCount] = this.board[this.columns * rowCount + column];
            rowCount++;
        }

        //
        // the algorithm for the diagonal array follows the same principle
        // as the two previous (horizontal and vertical) but it's more complicated
        //

        // init 2D array to represent the board game
        CellValue[][] boardGame = new CellValue[this.lines][this.columns];

        // add CellValue from 1D array, this.board, to the 2D array, boardgame
        // rightDiaIndex, and leftDiaIndex are the index used to find the
        // current right and left diagonal array (see later)
        row = 0;
        column = 0;
        int rightDiaIndex = 0;
        int leftDiaIndex = 0;
        for (int j = 0; j < (this.lines * this.columns); j++) {
            if (j == i) {
                rightDiaIndex = row + column;
                leftDiaIndex = column - row + this.lines - 1;
            }
            boardGame[row][column] = this.board[j];
            column++;
            if (column == this.columns) {
                row++;
                column = 0;
            }
        }

        // algorithm for right diagonal array (/)
        // store diagonal CellValue into 1D arrays
        int rowIndex = 0;
        int colIndex = 0;
        CellValue[] oneDRightDiagnoalArr = new CellValue[this.lines * this.columns];
        for (int j = 0; j < (this.lines * this.columns); j++) {
            oneDRightDiagnoalArr[j] = boardGame[rowIndex][colIndex];
            if ((rowIndex + colIndex) % 2 == 0) {
                if (colIndex == this.columns - 1) {
                    rowIndex++;
                } else if (rowIndex == 0) {
                    colIndex++;
                } else {
                    rowIndex--;
                    colIndex++;
                }
            } else {
                if (rowIndex == this.lines - 1) {
                    colIndex++;
                } else if (colIndex == 0) {
                    rowIndex++;
                } else {
                    rowIndex++;
                    colIndex--;
                }
            }
        }

        // algorithm for left diagnoal array (\)
        // store left diagonal CellValue into 1D arrays
        rowIndex = this.lines - 1;
        colIndex = 0;
        CellValue[] oneDLeftDiagnoalArr = new CellValue[this.lines * this.columns];
        for (int j = 0; j < (this.lines * this.columns); j++) {
            oneDLeftDiagnoalArr[j] = boardGame[rowIndex][colIndex];
            if ((rowIndex + colIndex) % 2 == 0) {
                if (rowIndex == 0) { // && colIndex == 0 || rowIndex == 0
                    colIndex++;
                } else if (colIndex == 0) {
                    rowIndex--;
                } else {
                    rowIndex--;
                    colIndex--;
                }
            } else {
                if (colIndex == this.columns - 1) {
                    rowIndex--;
                } else if (rowIndex == this.lines - 1) {
                    colIndex++;
                } else {
                    rowIndex++;
                    colIndex++;
                }
            }
        }

        // put right dia array in 2-D array
        CellValue[][] twoDRightDiagonalArr = new CellValue[this.lines + this.columns - 1][];
        twoDRightDiagonalArr = DiagonalArray(twoDRightDiagonalArr, oneDRightDiagnoalArr);

        // put left dia array in 2-D array
        CellValue[][] twoDLeftDiagonalArr = new CellValue[this.lines + this.columns - 1][];
        twoDLeftDiagonalArr = DiagonalArray(twoDLeftDiagonalArr, oneDLeftDiagnoalArr);

        checkGameState(horizontal);
        checkGameState(vertical);
        checkGameState(twoDRightDiagonalArr[rightDiaIndex]);
        checkGameState(twoDLeftDiagonalArr[leftDiaIndex]);

    }

    // MY CODE HERE

    /**
     * A helper method that takes the board game 1D diagonal array and stores
     * its value inside a 2D array of all the diagonals of the board. Here's an
     * example for a 3x3 array. Here's the index of the board game.
     * 
     * 0 1 2 3
     * 4 5 6 7
     * 8 9 10 11
     * 12 13 14 15
     * 
     * Now here's the right and left diagonal 1D index array respectively
     * 
     * [0, 1, 4, 8, 5, 2, 3, 6, 9, 12, 13, 10, 7, 11, 14, 15]
     * [12, 13, 8, 4, 9, 14, 15, 10, 5, 0, 1, 6, 11, 7, 2, 3]
     * 
     * This method will take one of these 1D arrays and put them into a
     * 2D array like such. The first one is from the right 1D index array, and the
     * second one is from the left 1D index array.
     * 
     * [0], [1, 4], [8, 5, 2], [3, 6, 9 , 12], [13, 10, 7], [11, 14], [15]
     * [12], [13, 8], [4, 9, 14], [15, 10, 5, 0], [1, 6, 11], [7, 2], [3]
     * 
     * Index were used to understand the algorithm, the method used the CellValue of
     * these index.
     * 
     * @param arrDouble
     *                  the 2D array used to store the diagonal from the 1D array
     *                  arrSingle
     * @param arrSingle
     *                  the 1D array that contains all the CellValue of the game
     *                  board
     * @return
     *         arrDouble the 2D array that contains all the diagonal
     */
    private CellValue[][] DiagonalArray(CellValue[][] arrDouble, CellValue[] arrSingle) {
        int maxLenght = 1;
        int length = 1;
        int count = 0;
        for (int j = 0; j < arrDouble.length; j++) {
            if (length < Math.min(this.lines, this.columns)
                    && j + 1 < Math.min(this.lines, this.columns)) {
                arrDouble[j] = new CellValue[length];
                for (int index = 0; index < length; index++) {
                    arrDouble[j][index] = arrSingle[count];
                    count++;
                }
                length++;
                maxLenght++;
            } else if (j > arrDouble.length - maxLenght) {
                length--;
                arrDouble[j] = new CellValue[length];
                for (int index = 0; index < length; index++) {
                    arrDouble[j][index] = arrSingle[count];
                    count++;
                }
            } else {
                arrDouble[j] = new CellValue[maxLenght];
                for (int index = 0; index < maxLenght; index++) {
                    arrDouble[j][index] = arrSingle[count];
                    count++;
                }
            }
        }
        return arrDouble;
    }

    // MY CODE HERE

    /**
     * A helper method that check whether a player as
     * won not and changes the game state accordingly
     *
     * @param arr
     *            the 1D array that contains the current right diagonal
     *            or left diagonal or horizontal or vertical array
     */
    private void checkGameState(CellValue[] arr) {
        int count = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1] && arr[i] != CellValue.EMPTY) {
                count++;
            } else {
                count = 1;
            }
            if (count == this.sizeWin) {
                // we want the previous cellValue
                // not the next one that is suppose to play
                if (this.nextCellValue() == CellValue.O) {
                    this.gameState = GameState.XWIN;
                    System.out.println();
                } else if (this.nextCellValue() == CellValue.X) {
                    this.gameState = GameState.OWIN;
                }
                System.out.println(toString());
                System.out.println("Result: " + this.gameState.name());
            }
        }

    }

    /**
     * Returns a String representation of the game matching
     * the example provided in the assignment's description
     * 
     * @return
     *         String representation of the game
     */

    public String toString() {

        // MY CODE HERE

        /*
         * 
         * Example of the board
         * 
         * User Input:
         * 1 | 2 | 3
         * -----------
         * 4 | 5 | 6
         * -----------
         * 7 | 8 | 9
         * 
         * Index:
         * 0 | 1 | 2
         * -----------
         * 3 | 4 | 5
         * -----------
         * 6 | 7 | 8
         */

        String board = "";
        String linestr = "----";
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < this.columns; j++) {
                String value = this.board[j + i * this.columns].name();
                if (value.equals("EMPTY")) {
                    value = " ";
                }
                if (j == 0) {
                    board += " " + value + " | ";
                } else if (j + 1 == columns) {
                    board += value + " ";
                } else {
                    board += value + " | ";
                }

            }
            if (i + 1 != lines) {
                board += "\n" + linestr.repeat(columns - 1) + "---" + "\n";
            }
        }
        return board;
    }
}