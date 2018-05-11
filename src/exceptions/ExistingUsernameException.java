package exceptions;

/**
 *
 * @author Kevkof
 */
public class ExistingUsernameException extends Exception {

    /**
     * Default constructor for ExistingUsernameException
     */
    public ExistingUsernameException() {
    }

    /**
     * Constructor for ExistingUsernameException
     * 
     * @param message 
     */
    public ExistingUsernameException(String message) {
        super(message);
    }

    /**
     * Constructor for ExistingUsernameException
     * 
     * @param message
     * @param cause 
     */
    public ExistingUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for ExistingUsernameException
     * 
     * @param cause 
     */
    public ExistingUsernameException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for ExistingUsernameException
     * 
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace 
     */
    public ExistingUsernameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
