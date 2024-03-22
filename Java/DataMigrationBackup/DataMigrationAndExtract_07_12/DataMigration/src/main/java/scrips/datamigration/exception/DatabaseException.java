package scrips.datamigration.exception;

public class DatabaseException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DatabaseException(String errorMessage, Throwable cause) {
		super(errorMessage,cause);
	}
	public DatabaseException(String errorMessage) {
		super(errorMessage);
	}
	
	
}
