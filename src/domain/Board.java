package domain;

/**
 *
 * @author Kevkof
 */
public class Board {

    private String[][] fields = new String[10][10];
    private String[][] fieldsChecker = new String[10][10];
    private int curX;
    private int curY;
    private int moves = 0;

    private final int LEFT = 4;
    private final int RIGHT = 6;
    private final int TOP = 8;
    private final int BOTTOM = 2;

    private boolean completed;
    private int boardNumber;

    /**
     * Default constructor for the class Board, creates an example board
     */
    public Board() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                fields[i][j] = new String();
                fieldsChecker[i][j] = new String();
            }
        }
    }

    /**
     * Returns the String[][] double array
     *
     * @return String[][]
     */
    public String[][] getFields() {
        return this.fields;
    }

    /**
     * Returns the completion status
     *
     * @return boolean
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Returns the boardNumber
     *
     * @return int
     */
    public int getBoardNumber() {
        return boardNumber;
    }

    /**
     * Froms a level string to the double array
     *
     * @param boardString
     */
    public void readBoard(String boardString) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < boardString.length(); i++) {

            char item = boardString.charAt(i);

            if (x > 9) {
                y++;
                x = 0;
            }
            if ('W' == item) {
                fields[y][x] = "W";
                fieldsChecker[y][x] = "W";
                x++;
            } else if ('G' == item) {
                fields[y][x] = "G";
                fieldsChecker[y][x] = "G";
                x++;
            } else if ('B' == item) {
                fields[y][x] = "B";
                fieldsChecker[y][x] = "B";
                x++;
            } else if ('P' == item) {
                fields[y][x] = "P";
                fieldsChecker[y][x] = "P";
                curX = x;
                curY = y;
                x++;
            } else if ('E' == item) {
                fields[y][x] = "E";
                fieldsChecker[y][x] = "E";
                x++;
            }
        }
    }

    /**
     * Returns the double array as a single String
     *
     * @return String
     */
    public String arrayToString() {
        String output = "\n\n";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                output += fields[i][j];
                output += " ";
            }
            output += "\n";
        }
        return output;
    }

    /**
     * Moves the player/boxes if possible
     *
     * @param direction
     */
    public void move(int direction) {
        if (direction == LEFT) {
            switch (fields[curY][curX - 1]) {
                case "E":
                case "G":
                    if (wasThisAGoal()) {
                        fields[curY][curX] = "G";
                        fields[curY][curX - 1] = "P";
                        curX--;
                        moves++;
                    } else {
                        fields[curY][curX] = "E";
                        fields[curY][curX - 1] = "P";
                        curX--;
                        moves++;
                    }
                    break;
                case "B":
                    if (fields[curY][curX - 2].equals("E") || fields[curY][curX - 2].equals("G")) {
                        if (wasThisAGoal()) {
                            fields[curY][curX] = "G";
                            fields[curY][curX - 1] = "P";
                            fields[curY][curX - 2] = "B";
                            curX--;
                            moves++;
                        } else {
                            fields[curY][curX] = "E";
                            fields[curY][curX - 1] = "P";
                            fields[curY][curX - 2] = "B";
                            curX--;
                            moves++;
                        }
                    }
                    break;
            }
        } else if (direction == RIGHT) {
            switch (fields[curY][curX + 1]) {
                case "E":
                case "G":
                    if (wasThisAGoal()) {
                        fields[curY][curX] = "G";
                        fields[curY][curX + 1] = "P";
                        curX++;
                        moves++;
                    } else {
                        fields[curY][curX] = "E";
                        fields[curY][curX + 1] = "P";
                        curX++;
                        moves++;
                    }
                    break;
                case "B":
                    if (fields[curY][curX + 2].equals("E") || fields[curY][curX + 2].equals("G")) {
                        if (wasThisAGoal()) {
                            fields[curY][curX] = "G";
                            fields[curY][curX + 1] = "P";
                            fields[curY][curX + 2] = "B";
                            curX++;
                            moves++;
                        } else {
                            fields[curY][curX] = "E";
                            fields[curY][curX + 1] = "P";
                            fields[curY][curX + 2] = "B";
                            curX++;
                            moves++;
                        }
                    }
                    break;
            }

        } else if (direction == TOP) {
            switch (fields[curY - 1][curX]) {
                case "E":
                case "G":
                    if (wasThisAGoal()) {
                        fields[curY][curX] = "G";
                        fields[curY - 1][curX] = "P";
                        curY--;
                        moves++;
                    } else {
                        fields[curY][curX] = "E";
                        fields[curY - 1][curX] = "P";
                        curY--;
                        moves++;
                    }
                    break;
                case "B":
                    if (fields[curY - 2][curX].equals("E") || fields[curY - 2][curX].equals("G")) {
                        if (wasThisAGoal()) {
                            fields[curY][curX] = "G";
                            fields[curY - 1][curX] = "P";
                            fields[curY - 2][curX] = "B";
                            curY--;
                            moves++;
                        } else {
                            fields[curY][curX] = "E";
                            fields[curY - 1][curX] = "P";
                            fields[curY - 2][curX] = "B";
                            curY--;
                            moves++;
                        }
                    }
                    break;
            }

        } else if (direction == BOTTOM) {
            switch (fields[curY + 1][curX]) {
                case "E":
                case "G":
                    if (wasThisAGoal()) {
                        fields[curY][curX] = "G";
                        fields[curY + 1][curX] = "P";
                        curY++;
                        moves++;
                    } else {
                        fields[curY][curX] = "E";
                        fields[curY + 1][curX] = "P";
                        curY++;
                        moves++;
                    }
                    break;
                case "B":
                    if (fields[curY + 2][curX].equals("E") || fields[curY + 2][curX].equals("G")) {
                        if (wasThisAGoal()) {
                            fields[curY][curX] = "G";
                            fields[curY + 1][curX] = "P";
                            fields[curY + 2][curX] = "B";
                            curY++;
                            moves++;
                        } else {
                            fields[curY][curX] = "E";
                            fields[curY + 1][curX] = "P";
                            fields[curY + 2][curX] = "B";
                            curY++;
                            moves++;
                        }
                    }
                    break;
            }
        }
        checkCompletion();
    }

    /**
     * Checking method to know if the field you're leaving was a goal or not
     *
     * @return boolean
     */
    public boolean wasThisAGoal() {
        return "G".equals(fieldsChecker[curY][curX]);
    }

    /**
     * Checks if the board has been completed
     */
    public void checkCompletion() {
        completed = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (fieldsChecker[i][j].equals("G")) {
                    if (!fields[i][j].equals("B")) {
                        completed = false;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Returns the number of moves
     *
     * @return int
     */
    public int getNumberOfMoves() {
        return moves;
    }
}
