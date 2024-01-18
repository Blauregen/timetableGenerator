package at.htl.timetableGenerator;

import at.htl.timetableGenerator.exceptions.IncompatibleSubjectException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class IncompatibleSubjectExceptionTest {
	@Test
	void testConstructor() {
		assertThrows(IncompatibleSubjectException.class, () -> {
			throw new IncompatibleSubjectException("Teacher doesn't teach that subject");
		});

		assertThrows(IncompatibleSubjectException.class, () -> {
			throw new IncompatibleSubjectException("Teacher doesn't teach that subject",
					new IllegalArgumentException());
		});
	}
}