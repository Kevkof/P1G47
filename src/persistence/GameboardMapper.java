package persistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevkof
 */
public class GameboardMapper {

    /**
     * Returns a list of all gameBoardNumbers in the db with the given gameNumber
     * 
     * @param gameNumber
     * @return List
     */
    public List<String> getGameBoardNumbers(int gameNumber) {
     List<String> gameBoardNumbers = new ArrayList<>();
        Connection connection = PersistentieController.getInstance().getConnection();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM sokoban.board WHERE idgame = ?");
            query.setInt(1, gameNumber);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    gameBoardNumbers.add(rs.getString("idboard"));
                }
            }connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return gameBoardNumbers;
    }
    
    /**
     * 
     * @param boardNumber
     * @param gameNumber
     * @return 
     */
    public String readGameBoard(int boardNumber, int gameNumber) {
        String board = "";
        Connection connection = PersistentieController.getInstance().getConnection();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM sokoban.board WHERE idboard = ? AND idgame = ?");
            query.setInt(1, boardNumber);
            query.setInt(2, gameNumber);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                     board = rs.getString("boardString");    
                }
            }connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return board;
    }

    
}
