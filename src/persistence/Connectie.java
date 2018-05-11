package persistence;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevkof
 */
public class Connectie {

    /**
     * database URL
     */
    private static final String dbURL = "jdbc:mysql://localhost:3306/sokoban";
    private static final String username = "root";
    private static final String password = "root";

    private Connection connection;

    /**
     * constructor connects to database
     */
    public Connectie() {
        /**
         * connect to database books
         */
        try {
            /**
             * establish connection to database
             */
            if (connection == null) {
                connection = (Connection) DriverManager.getConnection(dbURL, username, password);
            }

        } catch (SQLException sqlException) {
            System.out.println("can't connect to database, program will close now");
            System.exit(1);
        }

    }

    /**
     *
     */
    public void closeConnection() {
        try {
            connection.close();
        } /**
         * handle exceptions closing statement and connection
         */
        catch (SQLException sqlException) {
            System.out.println("Database Error: " + sqlException.getMessage());

            System.exit(1);
        }
    }

    public Connection getConnection() {
        try {
            if (connection.isClosed()) {
                return connection = (Connection) DriverManager.getConnection(dbURL, username, password);
            }
            return connection;
        } catch (SQLException ex) {
            Logger.getLogger(Connectie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
