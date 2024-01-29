package at.htl.timetableGenerator.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImportExceptionTest {
	@Test
	public void testConstructor(){
		String message = "Message";
		IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
		ImportException ex = new ImportException(message);

		assertEquals(message, ex.getMessage());

		ImportException exWithCause = new ImportException(message, illegalArgumentException);

		assertEquals(message, exWithCause.getMessage());
		assertEquals(illegalArgumentException, exWithCause.getCause());
	}
}