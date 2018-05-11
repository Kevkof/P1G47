package domain;

import exceptions.ExistingUsernameException;
import exceptions.PasswordException;
import java.util.List;

/**
 *
 * @author Kevkof
 */
public class DomainController {

    private final PlayerRepository pRep;
    private Player player;
    private String choice;
    private Game game;
    private final GameRepository gRep;
    private final GameboardRepository gBRep;

    /**
     * Default Constructor for the DomainController Creates a new PlayerRepository
     */
    public DomainController() {
        pRep = new PlayerRepository();
        gBRep = new GameboardRepository();
        gRep = new GameRepository();
    }

    /**
     * Signs the user into the application
     *
     * @param username
     * @param password
     */
    public void signIn(String username, String password) {
        Player user = pRep.verify(username, password);
        if (user != null) {
            setPlayer(user);
        }
    }

    /**
     * Creates a new Player Adds the player to the repository Sets the player as current player
     *
     * @param name
     * @param firstname
     * @param username
     * @param password
     * @throws ExistingUsernameException
     * @throws PasswordException
     */
    public void signUp(String name, String firstname, String username, String password) throws ExistingUsernameException, PasswordException {
        boolean adminRights = false;
        if (!pRep.existingAdmin()) {
            adminRights = true;
        }
        Player newPlayer = new Player(username, password, name, firstname, adminRights);
        pRep.add(newPlayer);
        setPlayer(newPlayer);
    }

    /**
     * Sets the player
     *
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Checks wether the user has adminRights
     *
     * @param username
     * @param password
     * @return boolean
     */
    public boolean isAdmin(String username, String password) {
        return pRep.isAdmin(username, password);
    }

    /**
     * Checks wether the username and password provided belong to the same player
     *
     * @param username
     * @param password
     * @return boolean (true: information is correct, false: information is incorrect)
     */
    public boolean checkUsernameAndPassword(String username, String password) {
        return pRep.checkUsernameAndPassword(username, password);
    }

    /**
     * Printing out the gameboard
     *
     * @return String
     */
    public String gameToString() {
        return game.gameToString();
    }

    /**
     * Selecting a level and reading it into fields[][]
     *
     * @param boardNumber
     * @param gameNumber
     */
    public void readGameBoard(int boardNumber, int gameNumber) {
        String level = gBRep.readGameBoard(boardNumber, game.getNumber());
        game.readBoardToArray(level);
    }

    /**
     * Moving the player/boxes
     *
     * @param direction (8,4,6,2)
     */
    public void move(int direction) {
        game.move(direction);
    }

    /**
     * Checking if the board is completed
     *
     * @return
     */
    public boolean checkCompletion() {
        return game.checkCompletion();
    }

    /**
     * Checks if the given password meets all the regulations provided in DC Nieuwe speler
     *
     * @param password
     * @return boolean
     * @throws PasswordException
     */
    public boolean checkPassword(String password) throws PasswordException {
        return pRep.checkPassword(password);
    }

    /**
     * Returns a list of the game names
     *
     * @return List
     */
    public List<String> getGameNames() {
        return gRep.getGameNames();
    }

    /**
     * Sets a specific game from the database
     *
     * @param gameName
     */
    public void setGameDB(String gameName) {
        game = new Game(gameName, gRep.getGameNumberFromDB(gameName));
    }

    /**
     * Sets an empty game
     */
    public void setGame() {
        game = new Game("Empty", 0);
    }

    /**
     * Returns the number corresponding with the selected game
     *
     * @return int
     */
    public int getGameNumber() {
        return game.getNumber();
    }

    /**
     * Returns a List that contains the ID's for the boards with the selected gamenumber
     *
     * @return List
     */
    public List<String> getGameBoardNumbers() {
        return gBRep.getGameBoardNumbers(getGameNumber());
    }

    /**
     * Checks if the username is already is use
     *
     * @param username
     * @return boolean
     */
    public boolean existingUsername(String username) {
        return pRep.existingUsername(username);
    }

    /**
     * Returns the number of moves
     *
     * @return int
     */
    public int getNumberOfMoves() {
        return game.getNumberOfMoves();
    }

    public void readGameBoardStop() {
        game = new Game("Empty", 0);
    }
    
    public boolean checkEmptyFields(){
        return game.emptyFieldsCheck();
    }
}
