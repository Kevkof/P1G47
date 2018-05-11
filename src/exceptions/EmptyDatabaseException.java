package exceptions;

/**
 *
 * @author Kevkof
 */
public class EmptyDatabaseException extends Exception{
    /**
     * Default constructor for EmptyDatabaseException
     */
    public EmptyDatabaseException()
    {
    }
    /**
     * Constructor for EmptyDatabaseException
     * 
     * @param message 
     */
    public EmptyDatabaseException(String message)
    {
        super(message);
    }

    /**
     * Constructor for EmptyDatabaseException
     * 
     * @param message
     * @param cause 
     */
    public EmptyDatabaseException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Constructor for EmptyDatabaseException
     * 
     * @param cause 
     */
    public EmptyDatabaseException(Throwable cause)
    {
        super(cause);
    }

    /**
     * Constructor for EmptyDatabaseException
     * 
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace 
     */
    public EmptyDatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
