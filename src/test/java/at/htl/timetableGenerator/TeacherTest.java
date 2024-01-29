package at.htl.timetableGenerator;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TeacherTest {
	@Test
	void testConstructor() {
		String name = "Walter von der Vogelweide";
		Set<Subject> subjects = new HashSet<>();
		subjects.add(new Subject("German", "GE"));
		subjects.add(new Subject("Music", "MU"));
		Teacher walter = new Teacher(name, subjects, 10, 5);

		assertEquals(name, walter.getName());
		assertEquals(subjects, walter.getSubjects());
	}

	@Test
	void testSetters() {
		Set<Subject> subjects = new HashSet<>();
		subjects.add(new Subject("German", "GE"));
		subjects.add(new Subject("Medicine", "ME"));
		Teacher hildegard = new Teacher("Hildegard von Bingen", subjects, 10, 5);

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
		Teacher ludwig =
				new Teacher("Carl Joachim Friedrich Ludwig „Achim“ von Arnim", subjects, 10, 5);

		ludwig.setLesson(new Lesson(german, monday0));

		assertEquals(german, ludwig.getLesson(monday0).getSubject());
		assertEquals(ludwig, ludwig.getLesson(monday0).getTeacher());
	}

	@Test
	void testToString() {
		Subject dichtung = new Subject("Dichtung", "Dt");
		Set<Subject> subjects = new HashSet<>();
		subjects.add(dichtung);
		Teacher heinrich = new Teacher("Heinrich von Veldeke", subjects, 10, 5);
		assertEquals("Heinrich von Veldeke", heinrich.toString());
	}

	@Test
	void testAddRemoveSubject() {
		Teacher hildegard = new Teacher("Hildegard von Bingen", new HashSet<>(), 11, 6);
		Subject medicine = new Subject("Medizin", "MED");

		assertEquals(0, hildegard.getSubjects().size());

		hildegard.addSubject(medicine);

		assertEquals(1, hildegard.getSubjects().size());

		hildegard.removeSubject(medicine);

		assertEquals(0, hildegard.getSubjects().size());
	}

	@Test
	void testEquality() {
		Teacher moench = new Teacher("Der Mönch von Salzburg", new HashSet<>(), 2, 2);
		Teacher alsoMoench = new Teacher("Der Mönch von Salzburg", new HashSet<>(), 2, 2);
		Teacher otto = new Teacher("Otto von Botenlauben", new HashSet<>(), 2, 2);
		assertEquals(moench, moench);
		assertEquals(moench, alsoMoench);
		assertNotEquals(moench, new Object());
		assertNotEquals(moench, null);
		assertNotEquals(moench, otto);
	}
}
