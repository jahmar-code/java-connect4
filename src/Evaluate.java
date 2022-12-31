/**
 * this class implements all the auxiliary methods needed by the algorithm that plays the game
 * @author Jawaad Ahmar
 */

public class Evaluate {

    // instance variables for class Evaluate
    private char[][] gameBoard;
    private int tilesToWin;
    private int size;
    private char win_symbol;

    // constructor initializes instance variables, initializes all values of game board to empty
    public Evaluate (int size, int tilesToWin, int maxLevels) {

        this.size = size;
        this.tilesToWin = tilesToWin;
        gameBoard = new char[size][size];

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = 'e';
            }
        }
    }

    // method creates an object of Dictionary, initializing the hash table within
    public Dictionary createDictionary () {
        return new Dictionary(8999);
    }

    // method checks if the state of the game is already in the hash table
    public Record repeatedState (Dictionary dict) {

        String gameState = gameState();

        if (dict.get(gameState) instanceof Record) {
            return dict.get(gameState);
        } else {
            return null;
        }
    }

    // method inserts the current state of the game into the hash table
    public void insertState (Dictionary dict, int score, int level) {

        String gameState = gameState();

        Record recObj = new Record(gameState,score,level);
        if (dict.get(gameState) == null) {
            dict.put(recObj);
        }

    }

    // method stores the given symbol in the game board
    public void storePlay (int row, int col, char symbol) {
        gameBoard[row][col] = symbol;
    }

    // method checks if the given position is empty
    public boolean squareIsEmpty (int row, int col) {
        if (gameBoard[row][col] == 'e') {
            return true;
        } else {
            return false;
        }
    }

    // method checks if the given position is a computer tile
    public boolean tileOfComputer (int row, int col) {
        if (gameBoard[row][col] == 'c') {
            return true;
        } else {
            return false;
        }
    }

    // method checks if the given position is a human tile
    public boolean tileOfHuman (int row, int col) {
        if (gameBoard[row][col] == 'h') {
            return true;
        } else {
            return false;
        }
    }

    // method returns true if there are the required number of adjacent tiles of type symbol in the same row, column, or diagonal of gameBoard, otherwise it returns false
    public boolean wins (char symbol) {

        this.win_symbol = symbol;
        
        if (checkHorizontal() || checkVertical() || checkDiagonals()) {
            return true;
        }
        return false;
    }

    // method checks if the game is a draw
    public boolean isDraw () {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == 'e') {
                    return false;
                }
            }
        }
        return true;
    }

    // method checks what the condition of the game is
    public int evalBoard () {
        if (wins('c')) {
            return 3;
        } else if (wins('h')) {
            return 0;
        } else if (isDraw()) {
            return 2;
        } else {
            return 1;
        }
    }

    // helper method returns the current state of the game
    private String gameState () {

        String gameState = "";

        char[][] transpose = transpose_gameBoard();
        for (int i = 0; i < transpose.length; i++) {
            for (int j = 0; j < transpose[i].length; j++) {
                gameState += transpose[i][j];
            }
        }
        return gameState;
    }

    // helper method transposes the game board
    private char[][] transpose_gameBoard () {

        char[][] transpose = new char[size][size];
        
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                transpose[j][i] = gameBoard[i][j];
            }
        }
        return transpose;
    }

    // helper method checks the diagonals of the game board
    private boolean checkDiagonals () {

        int len = size - 1;
        char[][] transposedGameBoard = transpose_gameBoard();
        int j;
        int count;
        int k;

        for (int i = 0; i <= len; i++) {
            j = 0;
            count = 0;
            k = i;
            while (k <= len) {
                if (gameBoard[k][j] == win_symbol) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == tilesToWin) {
                    return true;
                }
                k++;
                j++;
            }
        }
        for (int i = 0; i <= len; i++) {
            j = 0;
            count = 0;
            k = i;
            while (k <= len) {
                if (transposedGameBoard[k][j] == win_symbol) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == tilesToWin) {
                    return true;
                }
                k++;
                j++;
            }
        }
        for (int i = len; i >= 0; i--) {
            j = 0;
            count = 0;
            k = i;
            while (k >= 0) {
                if (gameBoard[k][j] == win_symbol) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == tilesToWin) {
                    return true;
                }
                k--;
                j++;
            }
        }
        for (int i = 0; i <= len; i++) {
            j = len;
            count = 0;
            k = i;
            while (k <= len) {
                if (gameBoard[k][j] == win_symbol) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == tilesToWin) {
                    return true;
                }
                k++;
                j--;
            }
        }
        return false;
    }
    
    // helper method checks the verticals of the game board 
    private boolean checkVertical () {

        char[][] transGameBoard = transpose_gameBoard();

        for (int i = 0; i < transGameBoard.length; i++) {
            int count = 0;
            for (int j = 0; j < transGameBoard[i].length; j++) {
                if (transGameBoard[i][j] == win_symbol) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == tilesToWin) {
                    return true;
                }
            }
        }
        return false;
    }

    // helper method checks the horizontals of the game board
    private boolean checkHorizontal () {

        for (int i = 0; i < gameBoard.length; i++) {
            int count = 0;
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == win_symbol) {
                    count++;
                } else {
                    count = 0;
                } 
                if (count == tilesToWin) {
                    return true;
                }
            }
        }
        return false;
    }
}
