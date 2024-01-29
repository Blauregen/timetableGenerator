package at.htl.timetableGenerator.exceptions;

/**
 * This class represents an exception that is thrown when a subject is incompatible.
 * It extends the RuntimeException class, meaning it is an unchecked exception.
 * It provides two constructors: one that takes a message, and one that takes a message and a cause.
 */
public class IncompatibleSubjectException extends RuntimeException {
	/**
	 * Constructs a new IncompatibleSubjectException with the specified detail message.
	 *
	 * @param message the detail message. The detail message is saved for later retrieval by the
	 *                Throwable.getMessage()
	 *                method.
	 */
	public IncompatibleSubjectException(String message) {
		super(message);
	}

	/**
	 * Constructs a new IncompatibleSubjectException with the specified detail message and cause.
	 *
	 * @param message the detail message (which is saved for later retrieval by the
	 *                Throwable.getMessage() method).
	 * @param cause   the cause (which is saved for later retrieval by the Throwable.getCause()
	 *                method). (A null value
	 *                is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public IncompatibleSubjectException(String message, Throwable cause) {
		super(message, cause);
	}
}