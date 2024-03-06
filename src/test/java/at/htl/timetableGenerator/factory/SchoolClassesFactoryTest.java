package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.exceptions.ImportException;
import at.htl.timetableGenerator.model.Subject;
import at.htl.timetableGenerator.model.Teacher;
import at.htl.timetableGenerator.model.WeeklySubject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SchoolClassesFactoryTest {

	@Test
	void testCreateFromFileAndString() {
		SchoolClassesFactory schoolClassesFactory =
				new SchoolClassesFactory(); //Needed, so jacoco recognizes the default constructor
		// as tested

		HashSet<Subject> subjects = new HashSet<>();
		subjects.add(new Subject("Deutsch", "D", 3));

		Teacher hildegard = new Teacher("Hildegard von Bingen", subjects, 5, 5);
		HashSet<Teacher> teachers = new HashSet<>();
		teachers.add(hildegard);

		HashMap<String, HashSet<WeeklySubject>> possibleWeeklySubjects =
				WeeklySubjectsFactory.createFromFile(
						"src/test/resources/correctWeeklySubjectsFactory.csv", subjects, ";");

		assertThrows(ImportException.class,
		             () -> SchoolClassesFactory.createFromString("arg; arg; arg", teachers,
		                                                         possibleWeeklySubjects, ";"));

		assertDoesNotThrow(() -> {
			SchoolClassesFactory.createFromFile(
					"src/test/resources/correctSchoolClassesFactory.csv", teachers,
					possibleWeeklySubjects, ";");
		});

		assertThrows(ImportException.class, () -> SchoolClassesFactory.createFromFile(
				"src/test/resources/incorrectSchoolClassesFactory.csv", teachers,
				possibleWeeklySubjects, ";"));

		assertThrows(ImportException.class, () -> SchoolClassesFactory.createFromFile(
				"non/existing/path/but/pls/never_add/folders/called/like/this/or/everything" +
				"/will/break.csv", teachers, possibleWeeklySubjects, ";"));
	}
}