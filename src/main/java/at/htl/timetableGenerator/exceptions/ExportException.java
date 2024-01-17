package at.htl.timetableGenerator.exceptions;

/**
 * This class represents an exception that is thrown when an error occurs during the export process.
 * It extends the RuntimeException class and provides two constructors to create an exception with a message and an
 * optional cause.
 */
public class ExportException extends RuntimeException {

	/**
	 * Constructs a new ExportException with the specified detail message.
	 *
	 * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage()
	 *                method.
	 */
	public ExportException(String message) {
		super(message);
	}

	/**
	 * Constructs a new ExportException with the specified detail message and cause.
	 *
	 * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
	 * @param cause   the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value
	 *                is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public ExportException(String message, Throwable cause) {
		super(message, cause);
	}
}