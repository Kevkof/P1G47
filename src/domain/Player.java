package domain;

/**
 *
 * @author Kevkof
 */
public class Player {

    private String userName;
    private String password;
    private String name;
    private String firstName;
    private boolean adminRights;

    /**
     * Constructor for Player with 5 arguments
     *
     * @param userName
     * @param password
     * @param name
     * @param firstName
     * @param adminRights
     */
    public Player(String userName, String password, String name, String firstName, boolean adminRights) {
        setUserName(userName);
        setPassword(password);
        setName(name);
        setFirstName(firstName);
        setAdminRights(adminRights);
    }

    /**
     * Constructor for Player with 4 arguments
     *
     * @param userName
     * @param password
     * @param name
     * @param firstName
     */
    public Player(String userName, String password, String name, String firstName) {
        this(userName, password, name, firstName, false);
    }

    /**
     * Returns the username
     *
     * @return String
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Sets the username
     *
     * @param userName
     */
    private void setUserName(String userName) {
        if (userName == null || userName.length() == 0) {
            throw new IllegalArgumentException("");
        }
        this.userName = userName;
    }

    /**
     * Returns the password
     *
     * @return String
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password
     *
     * @param password
     */
    private void setPassword(String password) {
        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("");
        }
        this.password = password;
    }

    /**
     * Returns the last name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the last name
     *
     * @param name
     */
    private void setName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("");
        }
        this.name = name;
    }

    /**
     * Returns the first name
     *
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the player's firstname
     *
     * @param firstName
     */
    private void setFirstName(String firstName) {
        if (firstName == null || firstName.length() == 0) {
            throw new IllegalArgumentException("");
        }
        this.firstName = firstName;
    }

    /**
     * Checks if the player has adminrights
     *
     * @return boolean
     */
    public boolean isAdmin() {
        return adminRights;
    }

    /**
     * Sets the adminrights
     *
     * @param adminRights
     */
    private void setAdminRights(boolean adminRights) {
        this.adminRights = adminRights;
    }

}
