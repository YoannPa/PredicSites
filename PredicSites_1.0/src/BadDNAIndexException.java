/** 
 * Class BadDNAIndexException
 * @author Mohamed Elati
 * @version 1.0
 * @date Mars 2009
 */

/**
 * class BadDNAIndexException
 */
class BadDNAIndexException extends Exception {
    /**
     * Default constructor.
     */
    public BadDNAIndexException () {
    	super ("BadDNAIndexException");
    }

    /**
     * Constructor
     */
    public BadDNAIndexException(String message) {
    	super (message) ;
    }	
}
