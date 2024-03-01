package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.Model.Subject;
import at.htl.timetableGenerator.exceptions.ImportException;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeeklySubjectsFactoryTest {

	@Test
	void testCreateFromFile() {
		WeeklySubjectsFactory weeklySubjectsFactory = new WeeklySubjectsFactory();

		Set<Subject> subjects = new HashSet<>();
		subjects.add(new Subject("Deutsch",
		                         "D")); //Needed, so jacoco recognizes the default constructor as tested

		assertThrows(ImportException.class,
		             () -> WeeklySubjectsFactory.createFromString("1;2;3;4;5;", subjects, ";"));

		assertDoesNotThrow(() -> {
			WeeklySubjectsFactory.createFromFile(
					"src/test/testFiles/correctWeeklySubjectsFactory.csv", subjects, ";");
		});

		assertThrows(ImportException.class, () -> WeeklySubjectsFactory.createFromFile(
				"src/test/testFiles/incorrectWeeklySubjectsFactory.csv", subjects, ";"));

		assertThrows(ImportException.class, () -> WeeklySubjectsFactory.createFromFile(
				"non/existing/path/but/pls/never_add/folders/called/like/this/or/everything" +
				"/will/break.csv",
				subjects, ";"));
	}
}