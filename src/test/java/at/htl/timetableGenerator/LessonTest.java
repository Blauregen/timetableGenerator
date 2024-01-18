package at.htl.timetableGenerator;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LessonTest {

	@Test
	void testConstructor() {
		Course course = new Course("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.FRIDAY, 8);
		Lesson lesson = new Lesson(course, timeslot);

		assertEquals(lesson.getCourse(), course);
		assertEquals(lesson.getTimeSlot(), timeslot);
	}

	@Test
	void testSetters() {
		Course course = new Course("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.SATURDAY, 9);
		Lesson lesson = new Lesson(course, timeslot);
		Course newCourse = new Course("English", "E");
		TimeSlot newTimeSlot = new TimeSlot(DayOfWeek.MONDAY, 7);

		lesson.setCourse(newCourse);
		lesson.setTimeSlot(newTimeSlot);

		assertEquals(newCourse, lesson.getCourse());
		assertEquals(newTimeSlot, lesson.getTimeSlot());
	}

	@Test
	void testToString() {
		Course course = new Course("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.SATURDAY, 9);
		Lesson lesson = new Lesson(course, timeslot);

		assertEquals("SA 9: M", lesson.toString());
	}

	@Test
	void testEquals() {
		Course course = new Course("Maths", "M");
		Course MathGerman = new Course("Maths", "D");
		Course itp = new Course("ITP", "D");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.SATURDAY, 9);
		Lesson lesson = new Lesson(course, timeslot);
		Lesson sameLesson = new Lesson(MathGerman, timeslot);
		Lesson differentLesson = new Lesson(itp, timeslot);
		Lesson alsoDifferentLesson = new Lesson(course, timeslot.prevHour());
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
		Course course = new Course("Maths", "M");
		TimeSlot timeslot = new TimeSlot(DayOfWeek.SATURDAY, 9);
		Lesson lesson = new Lesson(course, timeslot);

		assertEquals(Objects.hash(course, timeslot), lesson.hashCode());
	}

//	@Test
//	void testSetAndGetTeacher() {
//		Set<Course> subjects = new HashSet<>();
//		Course german = new Course("German", "GE");
//		TimeSlot monday0 = new TimeSlot(DayOfWeek.MONDAY, 0);
//		subjects.add(german);
//		subjects.add(new Course("Dichtung", "DT"));
//		Teacher ludwig = new Teacher("Carl Joachim Friedrich Ludwig „Achim“ von Arnim", subjects, new Timetable(5,
//				10));
//
//		ludwig.setLesson(new Lesson(german, monday0));
//
//		assertEquals(german, ludwig.getLesson(monday0).getCourse());
//	}
}