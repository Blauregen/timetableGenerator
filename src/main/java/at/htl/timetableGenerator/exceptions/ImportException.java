package at.htl.timetableGenerator.exceptions;

/**
 * This class represents a custom exception that is thrown when an error occurs during the import
 * process.
 * It extends the RuntimeException class, meaning it is an unchecked exception.
 */
public class ImportException extends RuntimeException {
	/**
	 * Constructs a new ImportException with the specified detail message.
	 *
	 * @param message the detail message. The detail message is saved for later retrieval by the
	 *                Throwable.getMessage()
	 *                method.
	 */
	public ImportException(String message) {
		super(message);
	}

	/**
	 * Constructs a new ImportException with the specified detail message and cause.
	 *
	 * @param message the detail message (which is saved for later retrieval by the
	 *                Throwable.getMessage() method).
	 * @param cause   the cause (which is saved for later retrieval by the Throwable.getCause()
	 *                method). (A null value
	 *                is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public ImportException(String message, Throwable cause) {
		super(message, cause);
	}
}
