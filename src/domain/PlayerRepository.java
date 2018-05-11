package domain;

import exceptions.ExistingUsernameException;
import exceptions.PasswordException;
import persistence.PlayerMapper;

/**
 *
 * @author Kevkof
 */
public class PlayerRepository {

    private final PlayerMapper pMap;

    /**
     * Default constructor for PlayerRepository
     */
    public PlayerRepository() {
        pMap = new PlayerMapper();
    }

    /**
     * Check wether the correct combination of username and password was entered (used to sign in)
     *
     * @param userName
     * @param password
     * @return Player/null
     */
    public Player verify(String userName, String password) {
        Player s = pMap.verify(userName);
        if (s.getPassword().equals(password)) {
            return s;
        }
        return null;
    }

    /**
     * Adds a new player to the PlayerMapper
     *
     * @param player
     * @throws ExistingUsernameException
     * @throws PasswordException
     */
    public void add(Player player) throws ExistingUsernameException, PasswordException {
        if (existingUsername(player.getUserName()) && player.getUserName().length() < 8) {
            throw new ExistingUsernameException("");
        }
        if (checkPassword(player.getPassword()) == false) {
            throw new PasswordException("");
        }
        pMap.add(player);
    }

    /**
     * Checks wether the player has adminrights
     *
     * @param userName
     * @param password
     * @return boolean
     */
    public boolean isAdmin(String userName, String password) {
        return pMap.isAdmin(userName, password);
    }

    /**
     * Check wether the correct combination of username and password was entered
     *
     * @param userName
     * @param password
     * @return boolean
     */
    public boolean checkUsernameAndPassword(String userName, String password) {

        return pMap.checkUsernameAndPassword(userName, password);
    }

    /**
     * Checks if the username is in the database
     *
     * @param username
     * @return boolean
     */
    public boolean existingUsername(String username) {
        return pMap.existingUsername(username);
    }

    /**
     * Checks if the given password meets all the regulations provided in DC Nieuwe speler
     *
     * @param password
     * @return boolean
     * @throws PasswordException
     */
    public boolean checkPassword(String password) throws PasswordException {
        boolean lengthCheck = false;
        boolean upperCheck = false;
        boolean lowerCheck = false;
        boolean digitCheck = false;
        boolean check = false;

        for (int i = 0; i < password.length(); i++) {
            char s = password.charAt(i);
            if (Character.isUpperCase(s)) {
                upperCheck = true;
            }
            if (Character.isLowerCase(s)) {
                lowerCheck = true;
            }
            if (Character.isDigit(s)) {
                digitCheck = true;
            }
            if (password.length() >= 8) {
                lengthCheck = true;
            }
        }
        if (upperCheck == true && lowerCheck == true && digitCheck == true && lengthCheck == true) {
            check = true;
        }
        return check;
    }

    /**
     * Checks to see if there is an admin in the database yet
     *
     * @return boolean
     */
    public boolean existingAdmin() {
        return pMap.existingAdmin();
    }
}
