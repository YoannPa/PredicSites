/**
 * class BadCountException
 */
class BadCountException extends Exception {
    /**
     * Default constructor.
     */
    public BadCountException () {
		super ("BadCountException");
    }

    /**
     * Constructor.
     */
    public BadCountException (String message) {
    	super (message) ;
    }
}
