/** 
 * Class BadDNACharacterException
 * @author Mohamed Elati
 * @version 1.0
 * @date Mars 2009
 */

/**
 * class BadDNACharacterException
 */
class BadDNACharacterException extends Exception {
    /**
     * Default constructor.
     */
    public BadDNACharacterException () {
    	super ("BadDNACharacterException");
    }

    /**
     * Constructor
     */
    public BadDNACharacterException(String message) {
    	super (message) ;
    }	
}
