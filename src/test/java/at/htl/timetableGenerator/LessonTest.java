package at.htl.timetableGenerator;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LessonTest {

	@Test
	void testConstructor() {
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.FRIDAY, 8);
		Lesson lesson = new Lesson(subject, timeslot);

		assertEquals(lesson.getSubject(), subject);
		assertEquals(lesson.getTimeSlot(), timeslot);
	}

	@Test
	void testSetters() {
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.SATURDAY, 9);
		Lesson lesson = new Lesson(subject, timeslot);
		Subject newSubject = new Subject("English", "E");
		TimeSlot newTimeSlot = new TimeSlot(DayOfWeek.MONDAY, 7);

		lesson.setSubject(newSubject);
		lesson.setTimeSlot(newTimeSlot);

		assertEquals(newSubject, lesson.getSubject());
		assertEquals(newTimeSlot, lesson.getTimeSlot());
	}

	@Test
	void testToString() {
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.SATURDAY, 9);
		Lesson lesson = new Lesson(subject, timeslot);

		assertEquals("SA 9: M", lesson.toString());
	}

	@Test
	void testEquals() {
		Subject subject = new Subject("Maths", "M");
		Subject MathGerman = new Subject("Maths", "D");
		Subject itp = new Subject("ITP", "D");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.SATURDAY, 9);
		Lesson lesson = new Lesson(subject, timeslot);
		Lesson sameLesson = new Lesson(MathGerman, timeslot);
		Lesson differentLesson = new Lesson(itp, timeslot);
		Lesson alsoDifferentLesson = new Lesson(subject, timeslot.prevHour());
		Lesson stillDifferentLesson = new Lesson(itp, timeslot.prevHour());

		assertEquals(lesson, lesson);
		assertEquals(lesson, sameLesson);
		assertNotEquals(lesson, differentLesson);
		assertNotEquals(lesson, alsoDifferentLesson);
		assertNotEquals(lesson, stillDifferentLesson);
		assertNotEquals(lesson, null);
		assertNotEquals(lesson, new Object());
	}

	@Test
	void testHashCode() {
		Subject subject = new Subject("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.SATURDAY, 9);
		Lesson lesson = new Lesson(subject, timeslot);

		assertEquals(Objects.hash(subject, timeslot, null, null, null), lesson.hashCode());
	}

	@Test
	void testSetAndGetTeacher() {
		Set<Subject> subjects = new HashSet<>();
		Subject german = new Subject("German", "GE");
		Subject minnegesang = new Subject("Minnegesang", "MG");
		subjects.add(german);
		subjects.add(minnegesang);
		TimeSlot timeSlot = new TimeSlot(DayOfWeek.THURSDAY, 7);

		Teacher derKuerenberger = new Teacher("Der Kürenberger", subjects, 10, 5);

		Lesson lesson = new Lesson(minnegesang, timeSlot);
		lesson.setTeacher(derKuerenberger);

		assertEquals(derKuerenberger, lesson.getTeacher());
		assertEquals(lesson, derKuerenberger.getLesson(timeSlot));
	}
}