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
public class GameMapper {

     /**
     * Returns all game names currently in the database.
     *
     * @return
     */
    public List<String> getGameNames() {
        List<String> gameNames = new ArrayList<>();
        Connection connection = PersistentieController.getInstance().getConnection();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM sokoban.game ORDER BY idgame");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    gameNames.add(rs.getString("name"));
                }
            }connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return gameNames;
    }

    /**
     * Gets the gamenumber that goes with the name
     * 
     * @param gameName
     * @return int
     */
    public int getGameNumber(String gameName) {
        int gameNumber = 0;
        Connection connection = PersistentieController.getInstance().getConnection();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM sokoban.game WHERE name = ?");
            query.setString(1, gameName);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                     gameNumber = rs.getInt("idgame");
                    
                }
            }connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return gameNumber;
    }
}
