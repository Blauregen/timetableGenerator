package at.htl.timetableGenerator;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherTest {
	@Test
	void testConstructor() {
		String name = "Walter von der Vogelweide";
		Set<Subject> subjects = new HashSet<>();
		subjects.add(new Subject("German", "GE"));
		subjects.add(new Subject("Music", "MU"));
		Teacher walter = new Teacher(name, subjects, 5, 10);

		assertEquals(name, walter.getName());
		assertEquals(subjects, walter.getSubjects());
	}

	@Test
	void testSetters() {
		Set<Subject> subjects = new HashSet<>();
		subjects.add(new Subject("German", "GE"));
		subjects.add(new Subject("Medicine", "ME"));
		Teacher hildegard = new Teacher("Hildegard von Bingen", subjects, 5, 10);

		Set<Subject> newSubjects = new HashSet<>();
		Timetable timetable = new Timetable(5, 8);

		hildegard.setSubjects(newSubjects);
		hildegard.setOccupiedLessons(timetable);

		assertEquals(newSubjects, hildegard.getSubjects());
		assertEquals(timetable, hildegard.getTimetable());
	}

	@Test
	void testSingleSetterAndGetters() {
		Set<Subject> subjects = new HashSet<>();
		Subject german = new Subject("German", "GE");
		TimeSlot monday0 = new TimeSlot(DayOfWeek.MONDAY, 0);
		subjects.add(german);
		subjects.add(new Subject("Lyrik", "LY"));
		Teacher ludwig = new Teacher("Carl Joachim Friedrich Ludwig „Achim“ von Arnim", subjects, 5, 10);

		ludwig.setLesson(new Lesson(german, monday0));

		assertEquals(german, ludwig.getLesson(monday0).getSubject());
		assertEquals(ludwig, ludwig.getLesson(monday0).getTeacher());
	}

	@Test
	void testToString() {
		Subject dichtung = new Subject("Dichtung", "Dt");
		Set<Subject> subjects = new HashSet<>();
		subjects.add(dichtung);
		Teacher heinrich = new Teacher("Heinrich von Veldeke", subjects, 5, 10);
		assertEquals("Heinrich von Veldeke", heinrich.toString());
	}
}
