package domain;

/**
 *
 * @author Kevkof
 */
public class Game {

    private Board board;
    private final String name;
    private final int number;

    /**
     * In the default constructor for Game a new Board is created
     *
     * @param name
     * @param number
     */
    public Game(String name, int number) {
        this.board = new Board();
        this.name = name;
        this.number = number;
    }

    /**
     * Getter for the board attribute
     *
     * @return
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Setter for the board attribute
     *
     * @param board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Getter for the name attribute
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the number attribute
     *
     * @return int
     */
    public int getNumber() {
        return number;
    }

    /**
     * Prints the fields array in character form
     *
     * @return String
     */
    public String gameToString() {
        return this.board.arrayToString();
    }

    /**
     * Selecting the level and reading it into fields[][]
     *
     * @param boardString
     */
    public void readBoardToArray(String boardString) {
            board.readBoard(boardString);
    }

    /**
     * Moving the player/boxes
     *
     * @param direction (8,4,6,2,)
     */
    public void move(int direction) {
        board.move(direction);
    }

    /**
     * Checking if the board is completed
     *
     * @return boolean
     */
    public boolean checkCompletion() {
        return board.isCompleted();
    }

    /**
     * Returns the number of moves
     *
     * @return int
     */
    public int getNumberOfMoves() {
        return board.getNumberOfMoves();
    }
    
    public boolean emptyFieldsCheck(){
        return board.getFields() == new String[10][10];
    }

}
