package domain;

import java.util.List;
import persistence.GameMapper;

/**
 *
 * @author Kevkof
 */
public class GameRepository {

    private final GameMapper gMapper;

    /**
     * Default constructor for the class GameRepository
     * 
     * sets the field gMapper to be a new GameMapper
     */
    public GameRepository() {
        gMapper = new GameMapper();
    }

    /**
     * Returns a List containing the names of the games in the db
     * 
     * @return List
     */
    public List<String> getGameNames() {
        return gMapper.getGameNames();
    }

    /**
     * Returns the number corresponding to the given gameName
     * @param gameName
     * @return int
     */
    public int getGameNumberFromDB(String gameName) {
        return gMapper.getGameNumber(gameName);
    }
}
