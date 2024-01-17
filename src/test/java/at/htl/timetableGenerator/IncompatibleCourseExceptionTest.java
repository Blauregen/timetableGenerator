package at.htl.timetableGenerator;

import at.htl.timetableGenerator.exceptions.IncompatibleCourseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class IncompatibleCourseExceptionTest {
	@Test
	void testConstructor() {
		assertThrows(IncompatibleCourseException.class, () -> {
			throw new IncompatibleCourseException("Teacher doesn't teach that course");
		});

		assertThrows(IncompatibleCourseException.class, () -> {
			throw new IncompatibleCourseException("Teacher doesn't teach that course", new IllegalArgumentException());
		});
	}
}