package domain;

import java.util.List;
import persistence.GameboardMapper;

/**
 *
 * @author Kevkof
 */
public class GameboardRepository {
    
    private final GameboardMapper gBMap;

    /**
     * Default constructor for the class GameBoardRepository
     * 
     * sets the field gBMap to be a new GameBoardMapper
     */
    public GameboardRepository() {
        gBMap = new GameboardMapper();
    }
    
    /**
     * Returns the String corresping to the board with the given boardNumber and gameNumber
     * 
     * @param boardNumber
     * @param gameNumber
     * @return String
     */
    public String readGameBoard(int boardNumber, int gameNumber){
        return gBMap.readGameBoard(boardNumber, gameNumber);
        
    }

    /**
     * Returns a list containing the ID's for all the boards that have the given gameNumber
     * 
     * @param gameNumber
     * @return List
     */
    public List<String> getGameBoardNumbers(int gameNumber) {
        return gBMap.getGameBoardNumbers(gameNumber);
    }
    
}
