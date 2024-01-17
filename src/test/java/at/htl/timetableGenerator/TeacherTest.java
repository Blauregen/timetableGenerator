package at.htl.timetableGenerator;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherTest {
	@Test
	void testConstructor() {
		String name = "Walter von der Vogelweide";
		Set<Course> subjects = new HashSet<>();
		subjects.add(new Course("German", "GE"));
		subjects.add(new Course("Music", "MU"));
		Teacher walter = new Teacher(name, subjects);

		assertEquals(name, walter.getName());
		assertEquals(subjects, walter.getSubjects());
	}

	@Test
	void testSetters() {
		Set<Course> subjects = new HashSet<>();
		subjects.add(new Course("German", "GE"));
		subjects.add(new Course("Medicine", "ME"));
		Teacher hildegard = new Teacher("Hildegard von Bingen", subjects);

		Set<Course> newSubjects = new HashSet<>();
		Timetable timetable = new Timetable(5, 8);

		hildegard.setSubjects(newSubjects);
		hildegard.setOccupiedLessons(timetable);

		assertEquals(newSubjects, hildegard.getSubjects());
		assertEquals(timetable, hildegard.getOccupiedLessons());
	}
}
