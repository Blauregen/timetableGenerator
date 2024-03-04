package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.exceptions.ImportException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubjectFactoryTest {

	@Test
	void testCreateFromFile() {
		SubjectFactory subjectFactory =
				new SubjectFactory(); //Needed, so jacoco recognizes the default constructor as
		// tested

		assertDoesNotThrow(() -> {
			SubjectFactory.createFromFile("src/test/resources/correctSubjectFactory.csv", ";");
		});

		assertThrows(ImportException.class, () -> SubjectFactory.createFromFile(
				"src/test/resources/incorrectSubjectFactory.csv", ";"));

		assertThrows(ImportException.class, () -> SubjectFactory.createFromFile(
				"non/existing/path/but/pls/never_add/folders/called/like/this/or/everything" +
				"/will/break.csv",
				";"));
	}
}