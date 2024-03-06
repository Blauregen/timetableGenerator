package at.htl.timetableGenerator.exceptions;

public class NoSuchConstraintException extends RuntimeException {
	public NoSuchConstraintException() {
		super();
	}

	public NoSuchConstraintException(String message) {
		super(message);
	}

	public NoSuchConstraintException(String message, Throwable cause) {
		super(message, cause);
	}
}
