package persistence;

import domain.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Kevkof
 */
public class PlayerMapper {

    /**
     * Checks if the information given is correct
     *
     * @param username
     * @return Player
     */
    public Player verify(String username) {
        Player speler = null;
        Connection connection = PersistentieController.getInstance().getConnection();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM sokoban.player WHERE username = ?");
            query.setString(1, username);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String password = rs.getString("password");
                    String name = rs.getString("name");
                    String firstName = rs.getString("firstname");
                    boolean adminRights = rs.getBoolean("adminRights");
                    speler = new Player(username, password, name, firstName, adminRights);
                }
            }
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return speler;
    }

    /**
     * Adds a player to the database
     *
     * @param player
     */
    public void add(Player player) {
        Connection connection = PersistentieController.getInstance().getConnection();
        try {
            PreparedStatement query = connection.prepareStatement("INSERT INTO sokoban.player (name, firstname, password, username, adminrights)"
                    + "VALUES (?, ?, ?, ?, ?)");
            query.setString(1, player.getName());
            query.setString(2, player.getFirstName());
            query.setString(3, player.getPassword());
            query.setString(4, player.getUserName());
            query.setBoolean(5, player.isAdmin());
            query.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Checks if the player has adminrights
     *
     * @param username
     * @param password
     * @return boolean
     */
    public boolean isAdmin(String username, String password) {
        boolean isAdmin = false;
        Connection connection = PersistentieController.getInstance().getConnection();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM sokoban.player WHERE username = ?");
            query.setString(1, username);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    boolean adminRights = rs.getBoolean("adminRights");
                    isAdmin = adminRights;
                }
            }
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return isAdmin;
    }

    /**
     * Checks if the password and username are from the same player
     *
     * @param username
     * @param password
     * @return boolean
     */
    public boolean checkUsernameAndPassword(String username, String password) {
        boolean correct = false;
        Connection connection = PersistentieController.getInstance().getConnection();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT username,password FROM sokoban.player");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String checkUsername = rs.getString("username");
                    String checkPassword = rs.getString("password");
                    if (checkUsername.equals(username) && checkPassword.equals(password)) {
                        correct = true;
                        break;
                    }
                }
            }
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return correct;
    }

    /**
     * Checks if there is an admin yet or not
     *
     * @return boolean
     */
    public boolean existingAdmin() {
        boolean existingAdmin = false;
        Connection connection = PersistentieController.getInstance().getConnection();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM sokoban.player WHERE playerID = 1");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    existingAdmin = rs.getBoolean("adminRights");
                }
            }
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return existingAdmin;
    }

    /**
     * Checks if there is already a user with that exact username
     * 
     * @param userName
     * @return boolean
     */
    public boolean existingUsername(String userName) {
        boolean existingUsername = false;
        Connection connection = PersistentieController.getInstance().getConnection();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM sokoban.player WHERE username = ?");
            query.setString(1, userName);
            try (ResultSet rs = query.executeQuery()) {
                if(rs.next()){
                    existingUsername = true;
                }
            }
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return existingUsername;
    }
}
