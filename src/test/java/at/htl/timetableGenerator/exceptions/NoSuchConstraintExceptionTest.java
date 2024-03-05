package at.htl.timetableGenerator.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NoSuchConstraintExceptionTest {
	@Test
	void testConstructor() {
		assertThrows(NoSuchConstraintException.class, () -> {
			throw new NoSuchConstraintException("Error exporting");
		});

		assertThrows(NoSuchConstraintException.class, () -> {
			throw new NoSuchConstraintException("Error exporting", new IllegalArgumentException());
		});

		assertThrows(NoSuchConstraintException.class, () -> {
			throw new NoSuchConstraintException();
		});
	}
}