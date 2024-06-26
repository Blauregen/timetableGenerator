package at.htl.timetableGenerator.model;

import at.htl.timetableGenerator.constraints.Constraint;
import at.htl.timetableGenerator.constraints.constraints.ContinuousHoursConstraint;
import at.htl.timetableGenerator.constraints.constraints.RoomConstraint;
import at.htl.timetableGenerator.constraints.constraints.TeacherConstraint;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class TimetableTest {
	Timetable timetable;

	@BeforeEach
	void setUp() {
		timetable = new Timetable(5, 10, 100);
	}

	@AfterEach
	void tearDown() {
		timetable = null;
	}

	@Test
	void testConstructor() {
		HashSet<Constraint> constraints = new HashSet<>();
		ContinuousHoursConstraint continuousHoursConstraint = new ContinuousHoursConstraint();
		TeacherConstraint teacherConstraint = new TeacherConstraint();
		RoomConstraint roomConstraint = new RoomConstraint();
		constraints.add(continuousHoursConstraint);
		constraints.add(teacherConstraint);
		constraints.add(roomConstraint);

		timetable = new Timetable(5, 10, constraints, 100);

		constraints.remove(continuousHoursConstraint);
		constraints.remove(teacherConstraint);
		constraints.remove(roomConstraint);
		assertEquals(constraints, timetable.getConstraints());
	}

	@Test
	void getTimetable() {
		HashMap<TimeSlot, Lesson> lessons = new HashMap<>();

		for (int i = 0; i < timetable.getNoOfDayPerWeek(); i++) {
			for (int j = 0; j < timetable.getMaxNoOfHoursPerDay(); j++) {
				TimeSlot slot = new TimeSlot(DayOfWeek.of(i + 1), j);
				lessons.put(slot, new Lesson(Timetable.FREISTUNDE, slot));
			}
		}

		timetable.setTimetable(lessons);

		assertEquals(lessons, timetable.getTimetable());
	}

	@Test
	void setTimetable() {
		Subject am = new Subject("Mathe", "AM", 3);
		HashMap<TimeSlot, Lesson> lessons = new HashMap<>();
		lessons.put(new TimeSlot(DayOfWeek.MONDAY, 0), new Lesson(am, new TimeSlot(DayOfWeek.MONDAY, 0)));
		timetable.setTimetable(lessons);
		assertEquals(lessons, timetable.getTimetable());

		for (int i = 0; i < timetable.getNoOfDayPerWeek(); i++) {
			for (int j = 0; j < timetable.getMaxNoOfHoursPerDay(); j++) {
				TimeSlot slot = new TimeSlot(DayOfWeek.of(i + 1), j);
				lessons.put(slot, new Lesson(am, slot));
			}
		}

		timetable.setTimetable(am);
		assertEquals(lessons, timetable.getTimetable());
	}

	@Test
	void setAndGetLesson() {
		Subject am = new Subject("Mathe", "AM", 3);
		TimeSlot slot = new TimeSlot(DayOfWeek.MONDAY, 0);
		timetable.setLesson(new Lesson(am, slot));
		assertEquals(timetable.getLesson(slot).getSubject(), am);
	}

	@Test
	void setAndGetLessonInvalidTime() {
		Subject am = new Subject("Mathe", "AM", 3);
		TimeSlot slot = new TimeSlot(DayOfWeek.SUNDAY, 0);
		TimeSlot secondSlot = new TimeSlot(DayOfWeek.MONDAY, 11);
		TimeSlot thirdSlot = new TimeSlot(DayOfWeek.MONDAY, -1);
		TimeSlot fourthSlot = new TimeSlot(DayOfWeek.MONDAY, 11);

		assertThrows(IllegalArgumentException.class, () -> timetable.setLesson(new Lesson(am, slot)),
		             "Day is " + "invalid");
		assertThrows(IllegalArgumentException.class, () -> timetable.setLesson(new Lesson(am, secondSlot)),
		             "Time is invalid");
		assertThrows(IllegalArgumentException.class, () -> timetable.setLesson(new Lesson(am, thirdSlot)),
		             "Time is invalid");
		assertEquals(new Lesson(Timetable.FREISTUNDE, fourthSlot), timetable.getLesson(fourthSlot));
	}

	@Test
	void getMaxNoOfHoursPerDay() {
		assertEquals(10, timetable.getMaxNoOfHoursPerDay());
	}

	@Test
	void setMaxNoOfHoursPerDay() {
		timetable.setMaxNoOfHoursPerDay(4);
		assertEquals(4, timetable.getMaxNoOfHoursPerDay());
	}

	@Test
	void setMaxNoOfHoursPerDayInvalid() {
		assertThrows(IllegalArgumentException.class, () -> timetable.setMaxNoOfHoursPerDay(0),
		             "Max number of hours per day can't be smaller than " +
		             Timetable.MIN_NUMBER_OF_HOURS_PER_DAY + "!");
	}

	@Test
	void getNoOfDayPerWeek() {
		assertEquals(5, timetable.getNoOfDayPerWeek());
	}

	@Test
	void setNoOfDayPerWeek() {
		timetable.setNoOfDayPerWeek(4);
		assertEquals(4, timetable.getNoOfDayPerWeek());
	}

	@Test
	void setNoOfDayPerWeekInvalid() {
		assertThrows(IllegalArgumentException.class, () -> timetable.setNoOfDayPerWeek(0),
		             "Number of days per week can't be smaller than " +
		             Timetable.MIN_NUMBER_OF_DAYS_PER_WEEK + " or bigger than 7!");
		assertThrows(IllegalArgumentException.class, () -> timetable.setNoOfDayPerWeek(8),
		             "Number of days per week can't be smaller than " +
		             Timetable.MIN_NUMBER_OF_DAYS_PER_WEEK + " or bigger than 7!");
	}

	@Test
	void contains() {
		Subject am = new Subject("Mathe", "AM", 3);
		Subject d = new Subject("german", "D", 3);
		TimeSlot slot = new TimeSlot(DayOfWeek.MONDAY, 0);
		timetable.setLesson(new Lesson(am, slot));
		assertTrue(timetable.contains(am));
		assertFalse(timetable.contains(d));
	}

	@Test
	void testAvailableDoubleHourSpot() {
		Subject math = new Subject("Math", "AM", 3);

		HashSet<Constraint> constraints = new HashSet<>();
		timetable = new Timetable(5, 10, constraints, 1000);

		assertFalse(timetable.hasAvailableDoubleHourSpot(math));
		timetable.setLesson(new Lesson(math, new TimeSlot(DayOfWeek.MONDAY, 0)));
		timetable.setLesson(new Lesson(math, new TimeSlot(DayOfWeek.MONDAY, 1)));
		timetable.setLesson(new Lesson(math, new TimeSlot(DayOfWeek.MONDAY, 2)));

		assertTrue(timetable.hasAvailableDoubleHourSpot(math));
	}

	@Test
	void hasAvailableDoubleHourSpotReturnsTrueWhenDoubleHourSpotIsAvailable() {
		Subject subject = new Subject("Math", "M", 3);
		timetable.setLesson(new Lesson(subject, new TimeSlot(DayOfWeek.MONDAY, 0)));
		assertTrue(timetable.hasAvailableDoubleHourSpot(subject));
	}

	@Test
	void hasAvailableDoubleHourSpotReturnsFalseWhenDoubleHourSpotIsNotAvailable() {
		Subject subject = new Subject("Math", "M", 3);
		assertFalse(timetable.hasAvailableDoubleHourSpot(subject));
	}

	@Test
	void hasAvailableDoubleHourSpotReturnsFalseWhenConstraintsAreNotMet() {
		Subject subject = new Subject("Math", "M", 3);
		timetable.setLesson(new Lesson(subject, new TimeSlot(DayOfWeek.MONDAY, 0)));
		timetable.getConstraints().add((timetable, lesson, lessons, rooms) -> false);
		assertFalse(timetable.hasAvailableDoubleHourSpot(subject));
	}

	@Test
	void testToString() {
		Subject am = new Subject("Mathe", "AM", 3);
		TimeSlot slot = new TimeSlot(DayOfWeek.MONDAY, 0);
		timetable.setLesson(new Lesson(am, slot));
		assertTrue(timetable.toString().contains("AM"));
	}

	@Test
	void testSetConstraints() {
		HashSet<Constraint> constraints = new HashSet<>();
		timetable.setConstraints(constraints);
		assertEquals(constraints, timetable.getConstraints());
	}

	@Test
	void testGetScoreForDay() {
		assertEquals(0, timetable.getScoreForDay(DayOfWeek.MONDAY));
	}
}