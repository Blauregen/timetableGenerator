package at.htl.timetableGenerator.factory;

import at.htl.timetableGenerator.exceptions.ImportException;
import at.htl.timetableGenerator.model.Subject;
import at.htl.timetableGenerator.model.Teacher;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TeacherFactoryTest {

	@Test
	void testCreateFromFile() {
		TeacherFactory teacherFactory =
				new TeacherFactory(); //Needed, so jacoco recognizes the default constructor as
		// tested

		Set<Subject> subjects = new HashSet<>();
		subjects.add(new Subject("Deutsch", "D", 3));
		subjects.add(new Subject("Latin", "L", 3));
		subjects.add(new Subject("English", "E", 3));

		Set<Teacher> teachers = assertDoesNotThrow(
				() -> TeacherFactory.createFromFile("src/test/testFiles/correctTeacherFactory.csv", subjects,
				                                    ";"));

		Set<Subject> factorySubjects = new HashSet<>();
		teachers.forEach(teacher -> factorySubjects.addAll(teacher.getSubjects()));

		assertEquals(subjects, factorySubjects);

		assertThrows(ImportException.class,
		             () -> TeacherFactory.createFromFile("src/test/testFiles/incorrectTeacherFactory.csv",
		                                                 subjects, ";"));

		assertThrows(ImportException.class, () -> TeacherFactory.createFromFile(
				"non/existing/path/but/pls/never_add/folders/called/like/this/or/everything" +
				"/will/break.csv", subjects, ";"));
	}
}