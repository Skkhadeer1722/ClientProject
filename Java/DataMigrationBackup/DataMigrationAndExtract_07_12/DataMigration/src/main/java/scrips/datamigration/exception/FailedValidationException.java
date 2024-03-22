package scrips.datamigration.exception;

public class FailedValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FailedValidationException(String errorMessage, Throwable cause) {
		super(errorMessage,cause);
	}
	public FailedValidationException(String errorMessage) {
		super(errorMessage);
	}
}
