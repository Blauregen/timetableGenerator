package at.htl.timetableGenerator;

import at.htl.timetableGenerator.exceptions.ExportException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExportExceptionTest {
	@Test
	void testConstructor() {
		assertThrows(ExportException.class, () -> {
			throw new ExportException("Error exporting");
		});

		assertThrows(ExportException.class, () -> {
			throw new ExportException("Error exporting", new IllegalArgumentException());
		});
	}
}