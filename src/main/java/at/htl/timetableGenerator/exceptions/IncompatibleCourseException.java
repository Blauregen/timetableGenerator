package at.htl.timetableGenerator.exceptions;

public class IncompatibleCourseException extends RuntimeException {
	public IncompatibleCourseException(String message) {
		super(message);
	}

	public IncompatibleCourseException(String message, Throwable cause) {
		super(message, cause);
	}
}
