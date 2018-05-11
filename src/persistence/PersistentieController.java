package persistence;

import java.sql.Connection;

/**
 *
 * @author Kevkof
 */
public class PersistentieController {

    private static PersistentieController persistentieController;
    private final PlayerMapper pm;
    private final Connectie connectie;

    /**
     * Makes sure there is a PersistentieController
     *
     * @return PersistentieController
     */
    public static PersistentieController getInstance() {
        if (persistentieController == null) {
            persistentieController = new PersistentieController();
        }

        return persistentieController;
    }

    /**
     * Default constructor for PersistentieController
     */
    private PersistentieController() {
        connectie = new Connectie();
        pm = new PlayerMapper();
    }

    /**
     * Establishes the connection to the database
     *
     * @return Connection
     */
    public Connection getConnection() {
        return connectie.getConnection();
    }

    /**
     * Closes the connection to the database
     */
    public void closeConnection() {
        connectie.closeConnection();
    }
}
