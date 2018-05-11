package exceptions;

/**
 *
 * @author Kevkof
 */
public class PasswordException extends Exception{
    /**
     * Default constructor for PasswordException
     */
    public PasswordException()
    {
    }
    
    /**
     * Constructor for PasswordException
     * @param message 
     */
    public PasswordException(String message)
    {
        super(message);
    }

    /**
     * Constructor for PasswordException
     * 
     * @param message
     * @param cause 
     */
    public PasswordException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Constructor for PasswordException
     * 
     * @param cause 
     */
    public PasswordException(Throwable cause)
    {
        super(cause);
    }

    /**
     * Constructor for PasswordException
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace 
     */
    public PasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
