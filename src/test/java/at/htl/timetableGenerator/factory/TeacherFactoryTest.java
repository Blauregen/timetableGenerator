package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.Subject;
import at.htl.timetableGenerator.exceptions.ImportException;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TeacherFactoryTest {

	@Test
	void testCreateFromFile() {
		TeacherFactory teacherFactory =
				new TeacherFactory(); //Needed, so jacoco recognizes the default constructor as
		// tested

		Set<Subject> subjects = new HashSet<>();
		subjects.add(new Subject("Deutsch", "D"));

		assertDoesNotThrow(() -> {
			TeacherFactory.createFromFile("src/test/testFiles/correctTeacherFactory.csv", subjects,
					";");
		});

		assertThrows(ImportException.class, () -> TeacherFactory.createFromFile(
				"src/test/testFiles/incorrectTeacherFactory.csv",
				subjects, ";"));

		assertThrows(ImportException.class, () -> TeacherFactory.createFromFile(
				"non/existing/path/but/pls/never_add/folders/called/like/this/or/everything" +
				"/will/break.csv",
				subjects, ";"));
	}
}